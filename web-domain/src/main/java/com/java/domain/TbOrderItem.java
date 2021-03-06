package com.java.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: you are really cool!!
 * @Date: 2019/1/25 14:33
 * @Version 1.0
 */
@Data
public class TbOrderItem implements Serializable {
    private String id;
    private String itemId;
    private String orderId;
    private int num;
    private String title;
    private double price;
    private Long totalFee;
    private String picPath;
}
