package com.java.web.api.service;



import com.java.domain.Item;
import com.java.web.api.web.controller.vo.PageVO;

import java.util.List;

public interface ItemService {
    List<Item> selectItemByCid(PageVO<Item> pageVO);

    int count(PageVO<Item> pageVO);

    Item selectById(Long id);
}
