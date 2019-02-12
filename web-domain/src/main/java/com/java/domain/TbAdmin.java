package com.java.domain;

import lombok.Data;

import java.util.Date;

/**
 * @Author: you are really cool!!
 * @Date: 2019/1/22 16:22
 * @Version 1.0
 */
@Data
public class TbAdmin {
    private Long id;
    private String username;
    private String password;
    private String name;
    private Date created;
    private Date updated;
}
