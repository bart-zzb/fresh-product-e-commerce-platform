package cn.tedu.mall.common.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TimeConstant {
    TWO(2),

    //1s 5s 10s 30s 1m 2m 3m 4m 5m 6m 7m 8m 9m 10m 20m 30m 1h 2h
    //10s
    DELAY_TIME_LEVEL_THREE(3),

    DELAY_TIME_LEVEL_SIX(6);

    private final int value;
}
