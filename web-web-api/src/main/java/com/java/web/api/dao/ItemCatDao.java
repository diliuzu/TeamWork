package com.java.web.api.dao;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemCatDao<T>{


    List<T> selectAllParent();
    List<T> selectByParent(Long parentId);
    List<T> selectAllCategory();

    T selectById(Long id);

    void insert(T itemCat);

    void update(T itemCat);
}
