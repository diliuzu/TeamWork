package com.java.web.admin.service;

import com.java.commons.dto.BaseResult;
import com.java.domain.TbContentCategory;

import java.util.List;

/**
 * @Author: you are really cool!!
 * @Date: 2019/1/7 11:26
 * @Version 1.0
 */

public interface TbItemCatService<T>  {

    List<T> selectAll();

    T selectById(Long id);

    BaseResult save(T entity);

    BaseResult deleteById(Long id);

    List<T> selectByParentId(Long id);

}
