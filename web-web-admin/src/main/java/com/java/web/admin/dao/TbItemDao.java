package com.java.web.admin.dao;

import com.java.domain.TbItem;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Author: you are really cool!!
 * @Date: 2019/1/25 15:50
 * @Version 1.0
 */
@Repository
public interface TbItemDao {
    List<TbItem> selectAll(Map map);

    int getCount();

    int insert(TbItem tbItem);

    int update(TbItem tbItem);

    int deleteById(Long id);

    TbItem selectById(Long id);
}
