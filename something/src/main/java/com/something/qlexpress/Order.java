package com.something.qlexpress;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Description
 *
 * @author wei.cai@hand-china.com 2020/4/10
 */
@Data
@Builder
public class Order {
    private String code;
    private Double amount;
    private String type;
    private Date creationTime;
    private List<OrderEntry> entries;

    @Data
    @Builder
    public static class OrderEntry {
        private Integer number;
        private Integer qty;
        private String sku;
        private String spu;
//        private Product product;
        private BigDecimal price;
    }

    @Data
    @Builder
    public static class Product {
        private String code;
        private String name;
        private String category;
        private BigDecimal price;
    }

}
