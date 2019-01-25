package com.java.domain;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * @Author: you are really cool!!
 * @Date: 2019/1/25 15:27
 * @Version 1.0
 */
@Data
public class Content {
    private Long id;
    private Long categoryId;
    @Length(min = 1,max = 20,message = "标题的长度必须介于1个和20个之间")
    private String title;
    @Length(min = 1,max = 20,message = "子标题的长度必须介于1个和20个之间")
    private String subTitle;
    @Length(min = 1,max = 20,message = "标题描述的长度必须介于1个和20个之间")
    private String titleDesc;
    private String url;
    private String pic;
    private String pic2;
    private String content;
    private String created;
    private String updated;
}
