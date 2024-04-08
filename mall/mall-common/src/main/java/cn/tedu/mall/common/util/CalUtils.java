package cn.tedu.mall.common.util;

import java.math.BigDecimal;

public class CalUtils {
    public static BigDecimal calTotal(BigDecimal price, Integer num){
        return price.multiply(BigDecimal.valueOf(num));
    }
}
