package com.java.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: you are really cool!!
 * @Date: 2019/1/25 14:49
 * @Version 1.0
 */
@Data
public class TbUserCar implements Serializable {
    private int id;
    private String itemTitle;
    private Double itemPrice;
    private int count;
    private String itemImage;
    private Long itemId;
    private Long userId;
}
