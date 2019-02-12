package com.java.web.admin.dao;

import com.java.domain.TbAdmin;
import org.springframework.stereotype.Repository;

/**
 * @Author: you are really cool!!
 * @Date: 2019/1/22 16:21
 * @Version 1.0
 */
@Repository
public interface TbAdminDao {
    TbAdmin selectByUsername(String username);
}
