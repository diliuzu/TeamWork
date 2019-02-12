package com.java.web.admin.service.serviceImpl;

import com.java.commons.dto.BaseResult;
import com.java.commons.dto.PageVO;
import com.java.commons.util.validator.BeanValidator;
import com.java.domain.TbItem;
import com.java.web.admin.dao.TbItemDao;
import com.java.web.admin.service.TbItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: you are really cool!!
 * @Date: 2019/1/25 15:47
 * @Version 1.0
 */
@Service
public class TbItemServiceImpl implements TbItemService<TbItem> {
    @Autowired
    private TbItemDao dao;


    @Override
    public PageVO<TbItem> selectAll(int start, int draw, int length, TbItem tbItem) {
        PageVO<TbItem> pageVO= new PageVO<TbItem>();
        int count = dao.getCount();
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("start",start);
        map.put("length",length);
        List<TbItem> tbItems = dao.selectAll(map);
        pageVO.setData(tbItems);
        pageVO.setDraw(draw);
        pageVO.setRecordsTotal(count);
        pageVO.setRecordsFiltered(count);
        return pageVO;
    }

    @Override
    @Transactional(readOnly = false)
    public BaseResult save(TbItem tbItem) {
        String validator = BeanValidator.validator(tbItem);
        BaseResult baseResult = null;
        if (validator==null){
            if(tbItem.getId()==null){
                //新增用户
                tbItem.setCreated(new Date());
                tbItem.setUpdated(new Date());
                dao.insert(tbItem);
            }
            else{
                //编辑用户
                tbItem.setUpdated(new Date());
                dao.update(tbItem);
            }
            baseResult = BaseResult.success("保存内容成功！");

        }else {
            baseResult = BaseResult.fail(validator);
        }
        return baseResult;
    }


    @Override
    public BaseResult deleteById(Long id) {
        BaseResult baseResult =null;
        int result = 0;
        result = dao.deleteById(id);
        if (result!=0){
            baseResult = BaseResult.success("删除成功");
        }else{
            baseResult = BaseResult.fail("删除失败");
        }
        return baseResult;
    }


    @Override
    public TbItem selectById(Long id) {
        TbItem tbItem =null;
        tbItem = dao.selectById(id);
        return tbItem;
    }


}
