package com.java.web.admin.service.serviceImpl;


import com.java.domain.TbAdmin;
import com.java.web.admin.dao.TbAdminDao;
import com.java.web.admin.service.TbAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

/**
 * @Author: you are really cool!!
 * @Date: 2019/1/22 16:26
 * @Version 1.0
 */
@Service
public class TbAdminServiceImpl implements TbAdminService {
    @Autowired
    private TbAdminDao dao;

    @Override
    public TbAdmin login(String username, String password) {
        TbAdmin admin =null;
        admin = dao.selectByUsername(username);
        if(admin!=null){
            if(admin.getPassword().equals(DigestUtils.md5DigestAsHex(password.getBytes()))){
                return admin;
            }
        }
        return null;
    }
}
