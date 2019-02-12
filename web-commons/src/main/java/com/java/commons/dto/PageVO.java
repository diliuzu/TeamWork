package com.java.commons.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: you are really cool!!
 * @Date: 2019/1/31 15:32
 * @Version 1.0
 */
@Data
public class PageVO<T> implements Serializable {
    private int draw;
    private int recordsTotal ;
    private int recordsFiltered;
    private List<T> data;
    private String error;
}
