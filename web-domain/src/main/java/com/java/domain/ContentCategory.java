package com.java.domain;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * @Author: you are really cool!!
 * @Date: 2019/1/25 15:27
 * @Version 1.0
 */
@Data
public class ContentCategory {
    private Long id;
    private Long parentId;
    @Length(min = 1,max = 10,message = "分类名称长度为1-10")
    private String name;
    private int status;
    @Length(min=1,message="排序必须介于1-20之间")
    private String sortOrder;
    private Boolean isParent;
    private String created;
    private String updated;

}
