package com.java.web.admin.service;

import com.java.commons.dto.BaseResult;
import com.java.commons.dto.PageVO;

/**
 * @Author: you are really cool!!
 * @Date: 2019/2/11 19:07
 * @Version 1.0
 */
public interface TbOrderService<T> {
    PageVO<T> selectAll(int start, int draw, int length, T tbItem);

    BaseResult save(T tbOrder);

    T selectById(String id);
}
