package cn.tedu.mall.common.util;

import java.math.BigDecimal;

/**
 * 计算BigDecimal的常用工具类
 */
public class CalUtils {
    public static BigDecimal calTotal(BigDecimal price, Integer num){
        return price.multiply(BigDecimal.valueOf(num));
    }
}
