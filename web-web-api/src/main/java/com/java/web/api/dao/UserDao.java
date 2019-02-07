package com.java.web.api.dao;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface UserDao<T> {

    T selectPasswordByUsername(String username);

    List<T> selectByPage(Map<String, Object> map);

    T selectByName(String username);

    void insert(T user);

    T selectById(Long id);

    int count(T user);

    void update(T user);

    void deleteById(String[] ids);


}
