package com.java.web.api.dao;

import com.java.web.api.web.controller.vo.PageVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemDao<T> {
    List<T> selectByPage(PageVO<T> pageVO);
    Integer count(PageVO<T> pageVO);
    void update(T item);
    T selectById(Long id);
}
