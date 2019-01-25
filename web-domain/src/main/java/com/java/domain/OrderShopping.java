package com.java.domain;

import lombok.Data;

/**
 * @Author: you are really cool!!
 * @Date: 2019/1/25 14:44
 * @Version 1.0
 */
@Data
public class OrderShopping {
    private String orderId;
    private String receiverName;
    private String receiverPhone;
    private String receiverMobile;
    private String receiverState;
    private String receiverCity;
    private String receiverDistrict;
    private String receiverAddress;
    private String receiverZip;
    private String created;
    private String updated;
}
