package com.java.web.admin.service;

import com.java.commons.dto.BaseResult;
import com.java.commons.dto.PageVO;
import com.java.domain.TbUser;

import java.util.List;


public interface TbUserService{
    List<TbUser> selectAll();

    TbUser selectById(Long id);

    BaseResult save(TbUser tbUser);

    BaseResult deleteById(Long id);

    /**
     * 登录验证
     * @param email
     * @param password
     * @return
     */
    TbUser login(String email, String password);


    /**
     * 根据关键字搜索
     * @param tbUser
     * @return
     */
    List<TbUser> searchTbUsers(TbUser tbUser);

    /**
     * 删除选中id用户
     * @param ids
     * @return
     */
    BaseResult delSelect(String ids);

    /**
     * 分页查找符合条件用户
     * @param draw
     * @param start
     * @param length
     * @param tbUser
     * @return
     */
    PageVO<TbUser> selectPageUser(int draw, int start, int length, TbUser tbUser);

}
