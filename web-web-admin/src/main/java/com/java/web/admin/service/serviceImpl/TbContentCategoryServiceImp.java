package com.java.web.admin.service.serviceImpl;

import com.java.commons.dto.BaseResult;
import com.java.commons.util.validator.BeanValidator;
import com.java.domain.TbContentCategory;
import com.java.web.admin.dao.TbContentCategoryDao;
import com.java.web.admin.service.TbContentCategoryService;
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
public class TbContentCategoryServiceImp implements TbContentCategoryService<TbContentCategory> {
    @Autowired
    private TbContentCategoryDao<TbContentCategory> dao;


    @Override
    public List<TbContentCategory> selectAll() {
            List<TbContentCategory> allCategoties = dao.selectAll();
        return allCategoties;
    }

    @Override
    public List<TbContentCategory> selectByParentId(Long id) {
        List<TbContentCategory> categoryList = dao.getCategoryByParentId(id);

        return categoryList;
    }

    @Override
    public TbContentCategory selectById(Long id) {
        TbContentCategory tbTbContentCategory = dao.selectById(id);
        return tbTbContentCategory;
    }

    @Override
    @Transactional(readOnly = false)
    public BaseResult save(TbContentCategory tbTbContentCategory) {
        String validator = BeanValidator.validator(tbTbContentCategory);
        BaseResult baseResult = null;
        if (validator==null){
            //验证通过
            //判断是编辑还是新增
            if (tbTbContentCategory.getId()==null){
                tbTbContentCategory.setStatus(1);
                tbTbContentCategory.setCreated(new Date());
                tbTbContentCategory.setUpdated(new Date());
                //判断新增目录 是否在根目录
                if (tbTbContentCategory.getParent().getId()==null|| tbTbContentCategory.getParent().getId()==null){
                    tbTbContentCategory.setIsParent(true);
                    tbTbContentCategory.getParent().setId(0L);
                    tbTbContentCategory.getParent().setName("/");
                }
                //判断父节点是否为目录
                else{
                    TbContentCategory contentCategory = dao.selectById(tbTbContentCategory.getParent().getId());
                    contentCategory.setIsParent(true);
                    dao.Update(contentCategory);
                    tbTbContentCategory.setIsParent(false);
                }
                dao.Insert(tbTbContentCategory);
                baseResult=BaseResult.success("成功添加分类");
            }else{
                //判断编辑目录是否在根目录
                if (tbTbContentCategory.getParent().getId()==0|| tbTbContentCategory.getParent().getId()==null){
                    tbTbContentCategory.setIsParent(true);
                    tbTbContentCategory.getParent().setName("/");
                }
                if (tbTbContentCategory.getIsParent()==false){
                    tbTbContentCategory.setIsParent(false);
                }
                tbTbContentCategory.setUpdated(new Date());
                dao.Update(tbTbContentCategory);
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
