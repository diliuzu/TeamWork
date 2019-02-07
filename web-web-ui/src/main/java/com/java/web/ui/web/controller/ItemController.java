package com.java.web.ui.web.controller;

import com.java.commons.util.BaseResult;
import com.java.commons.util.HttpClientUtils;
import com.java.commons.util.MapperUtils;
import com.java.domain.Item;
import com.java.web.ui.web.controller.vo.ItemInfoVO;
import com.java.web.ui.web.controller.vo.PageVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("item")
public class ItemController {

    @GetMapping("getitembycid")
    public String getItemByCid(PageVO pageVO, Model model) throws Exception {
        String result = HttpClientUtils.doGet(HttpClientUtils.URL + "/item/getitembycid?" + pageVO.myToString());
        pageVO=MapperUtils.json2pojoByTree(result,"data",PageVO.class);
        model.addAttribute("pageVO",pageVO);
        return "item";
    }


    @GetMapping("info")
    public String itemInfo(Item item,Model model){
        String result = HttpClientUtils.doGet(HttpClientUtils.URL + "/item/info?id=" + item.getId() + "&parent.id=" + item.getParent().getId());
        try {
            ItemInfoVO itemInfoVO = MapperUtils.json2pojoByTree(result, "data", ItemInfoVO.class);
            model.addAttribute("ItemVO",itemInfoVO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "iteminfo";
    }
}
