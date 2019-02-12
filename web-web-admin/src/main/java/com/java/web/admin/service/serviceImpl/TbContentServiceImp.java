package com.java.web.admin.service.serviceImpl;

import com.java.commons.dto.BaseResult;
import com.java.commons.dto.PageVO;
import com.java.commons.util.validator.BeanValidator;
import com.java.domain.TbContent;
import com.java.web.admin.dao.TbContentDao;
import com.java.web.admin.service.TbContentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: you are really cool!!
 * @Date: 2019/1/7 20:34
 * @Version 1.0
 */
@Service
@Transactional(readOnly = true)
public class TbContentServiceImp implements TbContentService<TbContent> {
    @Autowired
    private TbContentDao<TbContent> dao;

    @Override
    public List<TbContent> selectAll() {
        List<TbContent> tbContent = dao.selectAll();
        return tbContent;
    }



    @Override
    @Transactional(readOnly = false)
    public BaseResult save(TbContent tbContent) {
        String validator = BeanValidator.validator(tbContent);
        BaseResult baseResult = null;
        if (validator==null){
            if(tbContent.getId()==null){
                //新增用户
                tbContent.setCreated(new Date());
                tbContent.setUpdated(new Date());
                dao.Insert(tbContent);
            }
            else{
                //编辑用户
                tbContent.setUpdated(new Date());
                dao.Update(tbContent);
            }
            baseResult = BaseResult.success("保存内容成功！");

        }else {
            baseResult = BaseResult.fail(validator);
        }
        return baseResult;
    }

    @Override
    public List<TbContent> searchTbContents(TbContent tbContent) {

        List<TbContent> tbContents = dao.selectBySearch(tbContent);
        return tbContents;
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
    public PageVO<TbContent> selectPageContent(int draw, int start, int length, TbContent tbContent) {
        PageVO<TbContent> pageVO = new PageVO<TbContent>();
        System.out.println(tbContent.toString());
        int count = dao.getCount(tbContent);
        pageVO.setRecordsFiltered(count);
        pageVO.setRecordsFiltered(count);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("start",start);
        map.put("length",length);
        map.put("tbContent",tbContent);
        pageVO.setData(dao.pageListContents(map));

        //pageVO.setError("服务器错误");
        return pageVO;
    }

    @Override
    public TbContent selectById(Long id) {
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
