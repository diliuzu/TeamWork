package com.java.web.admin.controller;

import com.java.commons.dto.BaseResult;
import com.java.domain.TbContentCategory;
import com.java.domain.TbItemCat;
import com.java.web.admin.service.TbContentCategoryService;
import com.java.web.admin.service.TbItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: you are really cool!!
 * @Date: 2019/1/7 11:27
 * @Version 1.0
 */
@Controller
@RequestMapping("itemCat")
public class ItemCatController {
    @Autowired
    private TbItemCatService<TbItemCat> service;

    @RequestMapping(value = "list",method = RequestMethod.GET)
    public String list(Model model){
        List<TbItemCat> itemCats = service.selectAll();

        List<TbItemCat> result = new ArrayList<TbItemCat>();
        sortContentCategory(itemCats,result,0L);

        model.addAttribute("ListItemCats",result);
        return "item_cat_list";
    }

    @ResponseBody
    @RequestMapping(value = "getItemCats",method = RequestMethod.POST)
    public List<TbItemCat> getItemCats(Long id){
        if (id==null){
            id=0L;
        }
        List<TbItemCat> itemCatList = service.selectByParentId(id);
        return itemCatList;
    }

    @ModelAttribute
    public TbItemCat selectById(Long id){
        TbItemCat tbItemCat;
        if (id == null) {
            tbItemCat = new TbItemCat();

        }else {
            tbItemCat = service.selectById(id);
        }
        return tbItemCat;
    }

    @RequestMapping(value = "form",method = RequestMethod.GET)
    public String form(TbItemCat tbItemCat){
        return "item_cat_form";
    }


    @RequestMapping(value="save",method = RequestMethod.POST)
    public String save(TbItemCat tbContentCategory, RedirectAttributes redirectAttributes, Model model){
        System.out.println(tbContentCategory.toString());
        BaseResult baseResult = service.save(tbContentCategory);
        if(baseResult.getStatus()==BaseResult.STATUS_SUCCESS){
            //如果表单验证通过了,把封装了具体状态码和信息的baseResult对象转递到目标页面
            redirectAttributes.addFlashAttribute("baseResult",baseResult);
            return "redirect:/itemCat/list";
        }
        else{
            //如果表单验证没有通过
            //返回错误信息，并且回到新增页面
            model.addAttribute("baseResult",baseResult);
            return "item_cat_form";
        }
    }

    @RequestMapping(value = "deleteById",method = RequestMethod.GET)
    public String deleteById(Long id,RedirectAttributes redirectAttributes){
        BaseResult baseResult = service.deleteById(id);
        redirectAttributes.addFlashAttribute("baseResult",baseResult);
        return "redirect:/itemCat/list";
    }

    public void sortContentCategory(List<TbItemCat> oldList,List<TbItemCat> newList ,Long parentId){
        for (TbItemCat tbItemCat : oldList) {
            if (tbItemCat.getParent().getId().equals(parentId)){
                newList.add(tbItemCat);
                if (tbItemCat.getIsParent()){
                    for (TbItemCat itemCat : oldList) {
                        if (itemCat.getParent().getId().equals(tbItemCat.getId())){
                            sortContentCategory(oldList,newList,tbItemCat.getId());
                            break;
                        }
                    }
                }
            }
        }
    }
}
