package com.java.web.api.service;


import com.java.commons.util.BaseResult;
import com.java.domain.User;
import com.java.web.api.web.controller.dto.PageInfo;
import com.java.web.api.web.controller.vo.LoginVO;

public interface UserService {
    BaseResult userLogin(LoginVO loginVO);

    BaseResult selectByName(String username);

    BaseResult insert(User user);

    User userLogin(User user);

    User selectById(Long id);

    PageInfo<User> page(int draw, int start, int length, User user);

    BaseResult save(User user);

    BaseResult deleteByIds(String[] split);
}
