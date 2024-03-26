package cn.tedu.mall.common.web;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class PageInfo<T> {
    /**
     * 当前页码
     */
    private String pageCurrent;
    /**
     * 总页数
     */
    private String pageCount;
    /**
     * 总条数
     */
    private String rowCount;

    /**
     * 数据
     */
    private List<T> records;
}
