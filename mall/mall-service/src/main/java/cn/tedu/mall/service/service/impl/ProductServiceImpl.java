package cn.tedu.mall.service.service.impl;

import cn.tedu.mall.common.constant.ServiceCode;
import cn.tedu.mall.common.constant.ServiceConstant;
import cn.tedu.mall.common.ex.ServiceException;
import cn.tedu.mall.common.util.PojoConvert;
import cn.tedu.mall.service.dao.repository.IBrandRepository;
import cn.tedu.mall.service.dao.repository.ICategoryRepository;
import cn.tedu.mall.service.dao.repository.IProductRepository;
import cn.tedu.mall.service.pojo.dto.ProductAddDTO;
import cn.tedu.mall.service.pojo.po.BrandPO;
import cn.tedu.mall.service.pojo.po.CategoryPO;
import cn.tedu.mall.service.pojo.po.ProductPO;
import cn.tedu.mall.service.pojo.vo.ProductVO;
import cn.tedu.mall.service.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Primary
@Service
public class ProductServiceImpl implements IProductService {
    @Autowired
    private IProductRepository productRepository;

    @Autowired
    private ICategoryRepository categoryRepository;

    @Autowired
    private IBrandRepository brandRepository;

    @Override
    public void addProduct(ProductAddDTO productAddDTO) {
        ProductPO productOrigPO = productRepository.selectProductByProductName(productAddDTO.getProductName());

        //查看商品SPU是否已存在
        if(productOrigPO!=null){
            throw new ServiceException(ServiceCode.ERROR_BAD_REQUEST, ServiceConstant.PRODUCT_ALREADY_EXISTED);
        }

        //查看商品分类id是否存在
        CategoryPO categoryById = categoryRepository.getCategoryById(productAddDTO.getTbCategoryId());
        if(categoryById==null){
            throw new ServiceException(ServiceCode.ERROR_BAD_REQUEST, ServiceConstant.CATEGORY_NOT_EXIST);
        }

        //查看品牌id是否存在
        BrandPO brandById = brandRepository.selectBrandById(productAddDTO.getTbBrandId());
        if(brandById==null){
            throw new ServiceException(ServiceCode.ERROR_BAD_REQUEST, ServiceConstant.BRAND_NOT_EXIST);
        }
        ProductPO productPO = PojoConvert.convert(productAddDTO, ProductPO.class);
        productRepository.saveProduct(productPO);
    }

    @Override
    public void deleteProductById(Long id) {
        productRepository.deleteProductById(id);
    }

    @Override
    public ProductVO getProductById(Long id) {
        return productRepository.getProductById(id);
    }
}
