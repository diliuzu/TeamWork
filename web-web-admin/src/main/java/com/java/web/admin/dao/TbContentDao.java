package com.java.web.admin.dao;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Author: you are really cool!!
 * @Date: 2019/1/7 20:37
 * @Version 1.0
 */
@Repository
public interface TbContentDao<T> {

    //获取所有对象
    List<T> selectAll();


    //插入新数据
    void Insert(T entity);
    //更新新数据
    void Update(T entity);
    //删除某一条数据
    void deleteById(Long id);

    //根据实体的id获取实体
    T selectById(Long id);

    List<T> selectBySearch(T entity);

    void delSelect(String[] arr);

    int getCount(T entity);

    List<T> pageListContents(Map<String, Object> map);


}
