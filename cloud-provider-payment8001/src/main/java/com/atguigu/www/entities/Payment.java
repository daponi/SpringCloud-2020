package com.atguigu.www.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 实体类
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Payment {
    private long id;
    private String serial;
}
