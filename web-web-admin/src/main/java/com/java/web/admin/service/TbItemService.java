package com.java.web.admin.service;

import com.java.commons.dto.BaseResult;
import com.java.commons.dto.PageVO;
import com.java.domain.TbItem;

/**
 * @Author: you are really cool!!
 * @Date: 2019/1/25 15:47
 * @Version 1.0
 */
public interface TbItemService<T> {
    PageVO<T> selectAll(int start, int draw, int length, T tbItem);

    BaseResult save(T tbItem);

    BaseResult deleteById(Long id);

    T selectById(Long id);
}
