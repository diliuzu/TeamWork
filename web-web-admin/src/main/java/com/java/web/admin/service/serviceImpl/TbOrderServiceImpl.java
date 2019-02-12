package com.java.web.admin.service.serviceImpl;

import com.java.commons.dto.BaseResult;
import com.java.commons.dto.PageVO;
import com.java.commons.util.validator.BeanValidator;
import com.java.domain.TbItem;
import com.java.domain.TbOrder;
import com.java.web.admin.dao.TbItemDao;
import com.java.web.admin.dao.TbOrderDao;
import com.java.web.admin.service.TbOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: you are really cool!!
 * @Date: 2019/2/11 19:10
 * @Version 1.0
 */
@Service
public class TbOrderServiceImpl implements TbOrderService<TbOrder> {
    @Autowired
    private TbOrderDao dao;


    @Override
    public PageVO<TbOrder> selectAll(int start, int draw, int length, TbOrder tbOrder) {
        PageVO<TbOrder> pageVO= new PageVO<TbOrder>();
        int count = dao.getCount();
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("start",start);
        map.put("length",length);
        List<TbOrder> tbOrders = dao.selectAll(map);
        pageVO.setData(tbOrders);
        pageVO.setDraw(draw);
        pageVO.setRecordsTotal(count);
        pageVO.setRecordsFiltered(count);
        return pageVO;
    }

    @Override
    @Transactional(readOnly = false)
    public BaseResult save(TbOrder tbOrder) {
        String validator = BeanValidator.validator(tbOrder);
        BaseResult baseResult = null;
        if (validator==null){

            //编辑用户
            tbOrder.setUpdateTime(new Date());
            dao.update(tbOrder);

            baseResult = BaseResult.success("保存内容成功！");

        }else {
            baseResult = BaseResult.fail(validator);
        }
        return baseResult;
    }


    @Override
    public TbOrder selectById(String id) {
        TbOrder tbOrder =null;
        tbOrder = dao.selectById(id);
        return tbOrder;
    }


}
