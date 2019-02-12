package com.java.web.admin.dao;

import com.java.domain.TbItem;
import com.java.domain.TbOrder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Author: you are really cool!!
 * @Date: 2019/2/11 19:06
 * @Version 1.0
 */
@Repository
public interface TbOrderDao {
    List<TbOrder> selectAll(Map map);

    int getCount();

    int update(TbOrder tbOrder);

    TbOrder selectById(String id);

}
