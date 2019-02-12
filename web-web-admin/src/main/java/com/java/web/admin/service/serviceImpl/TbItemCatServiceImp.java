package com.java.web.admin.service.serviceImpl;

import com.java.commons.dto.BaseResult;
import com.java.commons.util.validator.BeanValidator;
import com.java.domain.TbContentCategory;
import com.java.domain.TbItemCat;
import com.java.web.admin.dao.TbContentCategoryDao;
import com.java.web.admin.dao.TbItemCatDao;
import com.java.web.admin.service.TbContentCategoryService;
import com.java.web.admin.service.TbItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @Author: you are really cool!!
 * @Date: 2019/1/7 11:26
 * @Version 1.0
 */
@Service
@Transactional(readOnly = true)
public class TbItemCatServiceImp implements TbItemCatService<TbItemCat> {
    @Autowired
    private TbItemCatDao<TbItemCat> dao;


    @Override
    public List<TbItemCat> selectAll() {
            List<TbItemCat> tbItemCats = dao.selectAll();
        return tbItemCats;
    }

    @Override
    public List<TbItemCat> selectByParentId(Long id) {
        List<TbItemCat> categoryList = dao.getItemCatByParentId(id);

        return categoryList;
    }

    @Override
    public TbItemCat selectById(Long id) {
        TbItemCat tbItemCat = dao.selectById(id);
        return tbItemCat;
    }

    @Override
    @Transactional(readOnly = false)
    public BaseResult save(TbItemCat tbItemCat) {
        String validator = BeanValidator.validator(tbItemCat);
        BaseResult baseResult = null;
        if (validator==null){
            //验证通过
            //判断是编辑还是新增
            if (tbItemCat.getId()==null){
                tbItemCat.setStatus(1);
                tbItemCat.setCreated(new Date());
                tbItemCat.setUpdated(new Date());
                //判断新增目录 是否在根目录
                if (tbItemCat.getParent()==null|| tbItemCat.getParent().getId()==null){
                    tbItemCat.setIsParent(true);
                    tbItemCat.getParent().setId(0L);
                    tbItemCat.getParent().setName("/");
                }
                //判断父节点是否为目录
                else{
                    TbItemCat itemCat = dao.selectById(tbItemCat.getParent().getId());
                    itemCat.setIsParent(true);
                    dao.Update(itemCat);
                    tbItemCat.setIsParent(false);
                }
                dao.Insert(tbItemCat);
                baseResult=BaseResult.success("成功添加分类");
            }else{
                //判断编辑目录是否在根目录
                if (tbItemCat.getParent().getId()==0|| tbItemCat.getParent().getId()==null){
                    tbItemCat.setIsParent(true);
                    tbItemCat.getParent().setName("/");
                }
                if (tbItemCat.getIsParent()==false){
                    tbItemCat.setIsParent(false);
                }
                tbItemCat.setUpdated(new Date());
                dao.Update(tbItemCat);
                baseResult=BaseResult.success("成功编辑分类");
            }
        }else{
            //验证不通过
            baseResult=BaseResult.fail(validator);
        }
        return baseResult;
    }

    @Override
    @Transactional(readOnly = false)
    public BaseResult deleteById(Long id) {
        BaseResult baseResult;
        if (id!=null){
            dao.deleteById(id);
            baseResult = BaseResult.success("删除分类成功！");
            return baseResult;
        }
        baseResult=BaseResult.fail("删除分类失败！");
        return baseResult;
    }
}
