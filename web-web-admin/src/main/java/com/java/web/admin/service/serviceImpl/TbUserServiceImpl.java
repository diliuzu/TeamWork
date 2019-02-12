package com.java.web.admin.service.serviceImpl;


import com.java.commons.dto.BaseResult;
import com.java.commons.dto.PageVO;
import com.java.commons.util.validator.BeanValidator;
import com.java.domain.TbUser;
import com.java.web.admin.dao.TbUserDao;
import com.java.web.admin.service.TbUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(readOnly = true)
public class TbUserServiceImpl  implements TbUserService {
    @Autowired
    private TbUserDao<TbUser> dao;

    @Override
    public List<TbUser> selectAll() {
        List<TbUser> tbUsers = dao.selectAll();
        return tbUsers;
    }

    public TbUser login(String email, String password) {
        TbUser user = null;
        user = dao.getUserByEmail(email);
        if(user!=null){
            if(user.getPassword().equals(DigestUtils.md5DigestAsHex(password.getBytes()))){
                return user;
            }
        }
        return null;
    }

    @Override
    @Transactional(readOnly = false)
    public BaseResult save(TbUser tbUser) {
        String validator = BeanValidator.validator(tbUser);
        BaseResult baseResult = null;
        if(validator==null){
            if(tbUser.getId()==null){
                //新增用户
                tbUser.setPassword(DigestUtils.md5DigestAsHex(tbUser.getPassword().getBytes()));
                tbUser.setCreated(new Date());
                tbUser.setUpdated(new Date());
                dao.Insert(tbUser);

            }
            else{
                //编辑用户
                tbUser.setUpdated(new Date());
                dao.Update(tbUser);

            }
            baseResult=BaseResult.success("保存用户成功！");
        }else{
            baseResult=BaseResult.fail(validator);
        }
        return baseResult;
    }

    @Override
    public List<TbUser> searchTbUsers(TbUser tbUser) {

        List<TbUser> tbUsers = dao.selectBySearch(tbUser);
        return tbUsers;
    }
    @Override
    @Transactional(readOnly = false)
    public BaseResult delSelect(String ids) {
        BaseResult baseResult = null;
        if (StringUtils.isNotBlank(ids)){
            String[] arr = ids.split(",");
            dao.delSelect(arr);
            baseResult = BaseResult.success("删除成功");
        }else {
            baseResult = BaseResult.fail("删除失败");
        }
        return baseResult;
    }



    @Override
    public PageVO<TbUser> selectPageUser(int draw, int start, int length, TbUser tbUser) {
        PageVO<TbUser> pageVO = new PageVO<TbUser>();
        int count = dao.getCount(tbUser);
        pageVO.setRecordsTotal(count);
        pageVO.setRecordsFiltered(length);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("start",start);
        map.put("length",length);
        map.put("tbUser",tbUser);
        pageVO.setData(dao.pageListUsers(map));
        //pageVO.setError("服务器错误");
        return pageVO;
    }

    @Override
    public TbUser selectById(Long id) {
        return dao.selectById(id);
    }

    @Override
    @Transactional(readOnly = false)
    public BaseResult deleteById(Long id) {
        BaseResult baseResult = BaseResult.success();

        dao.deleteById(id);
        baseResult.setMessage("删除用户成功！");

        return baseResult;
    }

}
