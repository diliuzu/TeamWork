package com.java.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: you are really cool!!
 * @Date: 2019/1/25 15:56
 * @Version 1.0
 */
@Data
public class TbItemDesc implements Serializable {
    private Long itemId;
    private String itemDesc;
    private Date created;
    private Date updated;
}
