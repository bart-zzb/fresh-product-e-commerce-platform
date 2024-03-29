package cn.tedu.mall.common.util;

import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * pojo转换工具, 通过泛型的应用把传进来的对象属性复制给目标类型对象
 */
public class PojoConvert {


    public static <T>T convert(Object o, Class<T> cls){
        T t = null;
        try {
            t = cls.newInstance();
            BeanUtils.copyProperties(o,t);
            return t;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static <T,E>List<T> convertList(List<E> list, Class<T> cls){
        return list.stream().map(workOrderVo -> {
            T exportVo = null;
            try{
                exportVo = (T) cls.newInstance();
            }catch (Exception e){
                e.printStackTrace();
            }
            assert exportVo != null;
            BeanUtils.copyProperties(workOrderVo, exportVo);
            return exportVo;
        }).collect(Collectors.toList());
    }


}
