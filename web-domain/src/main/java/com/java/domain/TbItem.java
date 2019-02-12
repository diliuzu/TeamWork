package com.java.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class TbItem implements Serializable {

    private Long id;
    private String title;
    private String sellPoint;
    private Long price;
    private int num;
    private String barcode;
    private String image;
    private int status;
    private Date created;
    private Date updated;
    private TbItemCat parent;

}
