package com.java.web.admin.service;

import com.java.commons.dto.BaseResult;
import com.java.commons.dto.PageVO;
import com.java.domain.TbContent;

import java.util.List;

/**
 * @Author: you are really cool!!
 * @Date: 2019/1/7 20:34
 * @Version 1.0
 */

public interface TbContentService<T>{
    List<T> selectAll();

    T selectById(Long id);

    BaseResult save(T entity);

    BaseResult deleteById(Long id);

    /**
     * 根据关键字搜索
     * @param tbContent
     * @return
     */
    List<T> searchTbContents(T tbContent);

    /**
     * 删除选中id用户
     * @param ids
     * @return
     */
    BaseResult delSelect(String ids);

    /**
     * 分页查找符合条件用户
     * @param draw
     * @param start
     * @param length
     * @param tbContent
     * @return
     */
    PageVO<TbContent> selectPageContent(int draw, int start, int length, T tbContent);


}
