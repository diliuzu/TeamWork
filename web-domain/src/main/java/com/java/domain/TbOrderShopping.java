package com.java.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: you are really cool!!
 * @Date: 2019/1/25 14:44
 * @Version 1.0
 */
@Data
public class TbOrderShopping implements Serializable {
    private String orderId;
    private String receiverName;
    private String receiverPhone;
    private String receiverMobile;
    private String receiverState;
    private String receiverCity;
    private String receiverDistrict;
    private String receiverAddress;
    private String receiverZip;
    private Date created;
    private Date updated;
}
