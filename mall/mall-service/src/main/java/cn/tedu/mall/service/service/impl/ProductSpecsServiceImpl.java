package cn.tedu.mall.service.service.impl;

import cn.hutool.core.util.RandomUtil;
import cn.tedu.mall.common.constant.RedisConstants;
import cn.tedu.mall.common.constant.ServiceCode;
import cn.tedu.mall.common.constant.ServiceConstant;
import cn.tedu.mall.common.ex.ServiceException;
import cn.tedu.mall.common.util.PojoConvert;
import cn.tedu.mall.common.web.PageData;
import cn.tedu.mall.service.dao.repository.ICategoryRepository;
import cn.tedu.mall.service.dao.repository.IProductRepository;
import cn.tedu.mall.service.dao.repository.IProductSpecsRepository;
import cn.tedu.mall.service.pojo.bo.ProductSpecsBO;
import cn.tedu.mall.service.pojo.dto.ProductSpecDeleteDTO;
import cn.tedu.mall.service.pojo.po.CategoryPO;
import cn.tedu.mall.service.pojo.vo.ProductSpecsTreeVO;
import cn.tedu.mall.service.pojo.vo.ProductSpecsVO;
import cn.tedu.mall.service.service.IProductSpecsService;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;

@Slf4j
@Primary
@Service
public class ProductSpecsServiceImpl implements IProductSpecsService {
    @Autowired
    private IProductSpecsRepository productSpecsRepository;

    @Autowired
    private IProductRepository productRepository;

    @Autowired
    private ICategoryRepository categoryRepository;

    @Autowired
    private RedissonClient redissonClient;

    @Override
    public ProductSpecsVO getProductSpecsById(Long id) {
        return productSpecsRepository.getProductSpecsById(id);
    }

    @Override
    public PageData<ProductSpecsVO> getProductSpecsByCategoryId(Long id, Integer pageNum, Integer pageSize) {
        return productSpecsRepository.getProductSpecsByCategoryId(id, pageNum, pageSize);
    }

    @Override
    public List<ProductSpecsTreeVO> getProductSpecsTree() throws InterruptedException {
        //设置大key
        String key = RedisConstants.KEY_PREFIX_PRODUCT_SPECS;
        //从缓存中获取数据
        List<ProductSpecsTreeVO> productSpecsTreeVOSFromRedis = getProductSpecsTreeVOSFromRedis(key);
        //判断是否获取了数据,为null说明不存在key值,有则直接返回数据
        if(productSpecsTreeVOSFromRedis!=null){
            return productSpecsTreeVOSFromRedis;
        }

        //抢锁,高并发下只能一个线程获取锁, 使用Redission框架实现
        RLock lock = redissonClient.getLock("lockProductSpecs");
        //让锁重试, 实现锁的重试, 默认第二个参数是-1,开启缓存续命,watchdog
        boolean tryLock = lock.tryLock(20, TimeUnit.MILLISECONDS);
        if (tryLock) {
            try {
                //双重检查锁,再次检查Redis
                //判断是否获取了数据,为null说明不存在key值,有则直接返回数据
                List<ProductSpecsTreeVO> doubleProductSpecsTreeVOSFromRedis = getProductSpecsTreeVOSFromRedis(key);
                if(doubleProductSpecsTreeVOSFromRedis!=null){
                    return doubleProductSpecsTreeVOSFromRedis;
                }

                return getProductSpecsTreeVOSFromMysql(key);
            } finally {
                lock.unlock();
            }
        }

        int retryTimes = 3;
        // 当缓存中取不到值时sleep20毫秒，最多循环3次
        while (retryTimes > 0) {
            // 休眠20ms后递归
            TimeUnit.MILLISECONDS.sleep(20L);
            List<ProductSpecsTreeVO> doubleProductSpecsTreeVOSFromRedis = getProductSpecsTreeVOSFromRedis(key);
            if(doubleProductSpecsTreeVOSFromRedis!=null){
                return doubleProductSpecsTreeVOSFromRedis;
            }
            retryTimes--;
        }

        throw new ServiceException(ServiceCode.ERROR_LOAD_DATA_FAILED, ServiceConstant.LOAD_DATA_FAILED);
    }

    private List<ProductSpecsTreeVO> getProductSpecsTreeVOSFromMysql(String key) {
        //如果Redis不存在,去数据库查
        List<CategoryPO> topCategoryPO = categoryRepository.getCategoryListByParentId(0L);
        Map<String, String> map = new HashMap<>();
        //封装一个text属性,前端使用到
        map.put("categoryName", "text");
        List<ProductSpecsTreeVO> topCategoryTreeVOS = PojoConvert.convertList(topCategoryPO, ProductSpecsTreeVO.class, map);
        List<CategoryPO> all = categoryRepository.getAll();
        //使用递归方法
        for (ProductSpecsTreeVO topVo : topCategoryTreeVOS) {
            appendChild(topVo, all);
        }

        RList<ProductSpecsTreeVO> redissonClientList = redissonClient.getList(key);
        //直接通过redisson存入list数据
        redissonClientList.addAll(topCategoryTreeVOS);
        //设置随机过期时间
        redissonClientList.expire(10 + RandomUtil.randomInt(0, 10), TimeUnit.HOURS);
        log.debug("从数据库中获取topCategoryTreeVOS数据,并加载到Redis{}", topCategoryTreeVOS);
        return topCategoryTreeVOS;
    }

    @Override
    public void deleteProductSpecsAmount(Long id, List<ProductSpecDeleteDTO> productSpecDeleteDTOS) {
        log.debug("当前用户{}",id);
        if (!productSpecDeleteDTOS.isEmpty()) {
            for (ProductSpecDeleteDTO productSpecDeleteDTO : productSpecDeleteDTOS) {
                log.debug("入参{}", productSpecDeleteDTOS);
                productSpecsRepository.deleteProductSpecsAmountByIdAndAmount(productSpecDeleteDTO.getTbProductSpecId(), productSpecDeleteDTO.getAmount());
                ProductSpecsBO productSpecsBO = productSpecsRepository.getProductIdByProductSpecsId(productSpecDeleteDTO.getTbProductSpecId());
                productRepository.modifyProductSales(productSpecsBO.getTbProductId(), productSpecDeleteDTO.getAmount());
            }
        }
    }

    @Override
    public void initProductSpecsTree(){
        //先删除key
        String key = RedisConstants.KEY_PREFIX_PRODUCT_SPECS;
        long delete = redissonClient.getKeys().delete(key);
        getProductSpecsTreeVOSFromMysql(key);
    }

    //递归方法
    private ProductSpecsTreeVO appendChild(ProductSpecsTreeVO vo, List<CategoryPO> all) {
        List<ProductSpecsTreeVO> children = new ArrayList<>();
        for (CategoryPO categoryPO : all) {
            if (vo.getId().equals(categoryPO.getParentId())) {
                ProductSpecsTreeVO childVO = PojoConvert.convert(categoryPO, ProductSpecsTreeVO.class);
                assert childVO != null;
                childVO.setText(categoryPO.getCategoryName());
                children.add(appendChild(childVO, all)); //去找childVo的子级
            }
                //没有子级了,终止条件 不调用appendChild,退出了

        }
        vo.setChildren(children);
        vo.setProductSpecsList(getProductSpecsByCategoryId(vo.getId(), 1, Integer.MAX_VALUE).getList());
        return vo;
    }

    private List<ProductSpecsTreeVO> getProductSpecsTreeVOSFromRedis(String targetKey) {
        //查看大key是否存在
        RKeys rkeys = redissonClient.getKeys();
        Iterable<String> keys = rkeys.getKeys();
        boolean flag = false;
        for (String key : keys) {
            if (targetKey.equals(key)) {
                flag = true;
                break;
            }
        }
        //如果存在直接在缓存中获取
        if (flag) {
            //获取RList<ProductSpecsTreeVO>
            RList<ProductSpecsTreeVO> productSpecsTreeVOS = redissonClient.getList(targetKey);
            log.debug("从缓存中获取productSpecsTreeVOS数据{}", productSpecsTreeVOS);
            //返回结果
            return productSpecsTreeVOS;
        }
        return null;
    }
}
