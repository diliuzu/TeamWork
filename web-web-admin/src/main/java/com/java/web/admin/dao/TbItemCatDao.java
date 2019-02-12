package com.java.web.admin.dao;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: you are really cool!!
 * @Date: 2019/1/7 11:26
 * @Version 1.0
 */
@Repository
public interface TbItemCatDao<T>{

    //获取所有对象
    List<T> selectAll();


    //插入新数据
    int Insert(T entity);
    //更新新数据
    int Update(T entity);
    //删除某一条数据
    int deleteById(Long id);

    //根据实体的id获取实体
    T selectById(Long id);

    List<T> getItemCatByParentId(Long id);


}
