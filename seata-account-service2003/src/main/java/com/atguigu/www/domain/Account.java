package com.atguigu.www.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * BigDecimal叫高精度类型，专门在金融行业表示钱
     * 总额度
     */
    private BigDecimal total;

    /**
     * BigDecimal叫高精度类型，专门在金融行业表示钱
     * 已用额度
     */
    private BigDecimal used;

    /**
     * BigDecimal叫高精度类型，专门在金融行业表示钱
     * 剩余额度
     */
    private BigDecimal residue;
}