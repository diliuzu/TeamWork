package com.java.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: you are really cool!!
 * @Date: 2019/1/25 14:22
 * @Version 1.0
 */
@Data
public class TbOrder implements Serializable {
    private String orderId;
    private String payment;
    private int paymentType;
    private String postFee;
    private int status;
    private Date createTime;
    private Date updateTime;
    private Date paymentTime;
    private Date consignTime;
    private Date endTime;
    private Date closeTime;
    private String shippingName;
    private String shippingCode;
    private Long userId;
    private String buyerMessage;
    private String buyerNick;
    private int buyerRate;
}
