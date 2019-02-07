package com.java.web.api.web.controller;

import com.java.commons.util.BaseResult;
import com.java.commons.util.MyTree;
import com.java.commons.util.MyTreeUtils;
import com.java.domain.Item;
import com.java.domain.ItemCat;
import com.java.web.api.service.ItemCatService;
import com.java.web.api.service.ItemService;
import com.java.web.api.web.controller.vo.ItemInfoVO;
import com.java.web.api.web.controller.vo.PageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("item")
public class ItemController {

    private MyTreeUtils myTreeUtils=new MyTreeUtils();

    @Autowired
    ItemService itemService;

    @Autowired
    ItemCatService itemCatService;

    //通过各种条件获取满足条件的所有商品的信息
    @GetMapping("getitembycid")
    public BaseResult getItembyCid(HttpServletRequest request, PageVO<Item> pageVO){

        if(pageVO.getPage()==0)
            pageVO.setPage(1);
        ItemCat itemCat = itemCatService.selectById(pageVO.getCid());
        List<Long> ids=new ArrayList<Long>();
        Map<String,MyTree> treeMap = (Map<String, MyTree>) request.getServletContext().getAttribute("treeMap");
        myTreeUtils.getAllChildId(treeMap.get(pageVO.getCid()),ids);
        pageVO.setIds(ids);

        pageVO.setBegin((pageVO.getPage()-1)*20);
        List<Item> list=itemService.selectItemByCid(pageVO);
        pageVO.setData(list);

        pageVO.setRecordsTotal(itemService.count(pageVO));
        pageVO.setMaxPage((int) Math.ceil(pageVO.getRecordsTotal()*1.0/20));
        return BaseResult.success("成功",pageVO);
    }

    //获取商品信息和推荐信息
    @GetMapping("info")
    public Object info(Item item,HttpServletRequest request,Model model){
        PageVO pageVO=new PageVO();
        List<Long> ids=new ArrayList<Long>();
        Map<Long,MyTree> myTreeMap=(Map<Long,MyTree>)request.getServletContext().getAttribute("treeMap");
        myTreeUtils.getAllChildId((myTreeMap).get(item.getParent().getId()),ids);
        pageVO.setIds(ids);
        pageVO.setDraw(1);
        pageVO.setPage(1);
        ItemInfoVO itemInfoVO=new ItemInfoVO();
        itemInfoVO.setRecommend(itemService.selectItemByCid(pageVO));
        itemInfoVO.setItem(itemService.selectById(item.getId()));
//        model.addAttribute("ItemVO",itemInfoVO);
        return BaseResult.success("成功",itemInfoVO);
    }



}
