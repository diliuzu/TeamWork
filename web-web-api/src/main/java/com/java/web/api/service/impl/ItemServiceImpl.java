package com.java.web.api.service.impl;


import com.java.domain.Item;
import com.java.web.api.dao.ItemDao;
import com.java.web.api.service.ItemService;
import com.java.web.api.web.controller.vo.PageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    ItemDao<Item> itemDao;

    @Override
    public List<Item> selectItemByCid(PageVO<Item> pageVO) {
        return itemDao.selectByPage(pageVO);
    }

    @Override
    public int count(PageVO<Item> pageVO) {
        return itemDao.count(pageVO);
    }

    @Override
    public Item selectById(Long id) {
        return itemDao.selectById(id);
    }
}
