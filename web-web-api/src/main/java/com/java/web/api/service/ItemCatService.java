package com.java.web.api.service;

import com.java.commons.util.BaseResult;
import com.java.domain.ItemCat;

import java.util.List;
import java.util.Map;

public interface ItemCatService {
    List<ItemCat> selectAllParent();

    List<ItemCat> selectByParent(Long id);

    Map<String, Map<String, List<ItemCat>>> selectThree();

    ItemCat selectById(Long id);


    List<ItemCat> selectAllCategory();

    BaseResult save(ItemCat itemCat);
}
