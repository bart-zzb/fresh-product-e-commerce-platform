package cn.tedu.mall.common.util;

import org.springframework.beans.BeanUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * pojo转换工具, 通过泛型的应用把传进来的对象属性复制给目标类型对象
 */
public class PojoConvert {

    public static <T> T convert(Object o, Class<T> cls) {
        T t = null;
        try {
            t = cls.newInstance();
            BeanUtils.copyProperties(o, t);
            return t;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> T convert(Object o, Class<T> cls, Map<String, String> fieldMap) {
        T t = null;
        try {
            t = cls.newInstance();
            BeanUtils.copyProperties(o, t);
            for (Map.Entry<String, String> entry : fieldMap.entrySet()) {
                try {
                    Field sourceField = o.getClass().getDeclaredField(entry.getKey());
                    sourceField.setAccessible(true);
                    Object object = sourceField.get(o);
                    Field targetField = t.getClass().getDeclaredField(entry.getValue());
                    targetField.setAccessible(true);
                    targetField.set(t, object);
                    sourceField.setAccessible(false);
                    targetField.setAccessible(false);
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            return t;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T, E> List<T> convertList(List<E> list, Class<T> cls) {
        return list.stream().map(workOrderVo -> {
            T exportVo = null;
            try {
                exportVo = (T) cls.newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
            assert exportVo == null : "class T is not found";
            BeanUtils.copyProperties(workOrderVo, exportVo);
            return exportVo;
        }).collect(Collectors.toList());
    }

    public static <T, E> List<T> convertList(List<E> list, Class<T> cls, Map<String, String> fieldMap) {
        return list.stream().map(workOrderVo -> {
            T exportVo = null;
            try {
                exportVo = (T) cls.newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
            assert exportVo == null : "class " + cls + "is not found";
            BeanUtils.copyProperties(workOrderVo, exportVo);
            for (Map.Entry<String, String> entry : fieldMap.entrySet()) {
                try {
                    Field sourceField = workOrderVo.getClass().getDeclaredField(entry.getKey());
                    sourceField.setAccessible(true);
                    Object o = sourceField.get(workOrderVo);
                    Field targetField = exportVo.getClass().getDeclaredField(entry.getValue());
                    targetField.setAccessible(true);
                    targetField.set(exportVo, o);
                    sourceField.setAccessible(false);
                    targetField.setAccessible(false);
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            return exportVo;
        }).collect(Collectors.toList());
    }
}
