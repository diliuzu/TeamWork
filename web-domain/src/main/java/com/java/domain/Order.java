package com.java.domain;

import lombok.Data;

/**
 * @Author: you are really cool!!
 * @Date: 2019/1/25 14:22
 * @Version 1.0
 */
@Data
public class Order {
    private String orderId;
    private String payment;
    private int paymentType;
    private String postFree;
    private int status;
    private String createTime;
    private String updateTime;
    private String paymentTime;
    private String consignTime;
    private String endTime;
    private String closeTime;
    private String shoppingName;
    private String shoppingCode;
    private Long userId;
    private String buyerMessage;
    private String buyerNick;
    private int buyerRate;
}
