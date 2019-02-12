package com.java.web.admin.controller;

import com.java.commons.dto.BaseResult;
import com.java.domain.TbContentCategory;
import com.java.web.admin.service.TbContentCategoryService;
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
@RequestMapping("category")
public class ContentCategoryController {
    @Autowired
    private TbContentCategoryService<TbContentCategory> tbContentCategoryService;

    @RequestMapping(value = "list",method = RequestMethod.GET)
    public String list(Model model){
        List<TbContentCategory> allCategory = tbContentCategoryService.selectAll();

        List<TbContentCategory> result = new ArrayList<TbContentCategory>();
        sortContentCategory(allCategory,result,0L);

        model.addAttribute("ListCategories",result);
        return "content_category_list";
    }

    @ResponseBody
    @RequestMapping(value = "getCategories",method = RequestMethod.POST)
    public List<TbContentCategory> getCategoryList(Long id){
        if (id==null){
            id=0L;
        }
        List<TbContentCategory> categoryList = tbContentCategoryService.selectByParentId(id);
        return categoryList;
    }

    @ModelAttribute
    public TbContentCategory selectById(Long id){
        TbContentCategory tbContentCategory;
        if (id == null) {
            tbContentCategory = new TbContentCategory();

        }else {
            tbContentCategory = tbContentCategoryService.selectById(id);
        }
        return tbContentCategory;
    }

    @RequestMapping(value = "form",method = RequestMethod.GET)
    public String form(TbContentCategory tbContentCategory){
        return "content_category_form";
    }


    @RequestMapping(value="save",method = RequestMethod.POST)
    public String save(TbContentCategory tbContentCategory, RedirectAttributes redirectAttributes, Model model){
        System.out.println(tbContentCategory.toString());
        BaseResult baseResult = tbContentCategoryService.save(tbContentCategory);
        if(baseResult.getStatus()==BaseResult.STATUS_SUCCESS){
            //如果表单验证通过了,把封装了具体状态码和信息的baseResult对象转递到目标页面
            redirectAttributes.addFlashAttribute("baseResult",baseResult);
            return "redirect:/category/list";
        }
        else{
            //如果表单验证没有通过
            //返回错误信息，并且回到新增页面
            model.addAttribute("baseResult",baseResult);
            return "content_category_form";
        }
    }

    @RequestMapping(value = "deleteById",method = RequestMethod.GET)
    public String deleteById(Long id,RedirectAttributes redirectAttributes){
        BaseResult baseResult = tbContentCategoryService.deleteById(id);
        redirectAttributes.addFlashAttribute("baseResult",baseResult);
        return "redirect:/category/list";
    }

    public void sortContentCategory(List<TbContentCategory> oldList,List<TbContentCategory> newList ,Long parentId){
        for (TbContentCategory tbContentCategory : oldList) {
            if (tbContentCategory.getParent().getId().equals(parentId)){
                newList.add(tbContentCategory);
                if (tbContentCategory.getIsParent()){
                    for (TbContentCategory contentCategory : oldList) {
                        if (contentCategory.getParent().getId().equals(tbContentCategory.getId())){
                            sortContentCategory(oldList,newList,tbContentCategory.getId());
                            break;
                        }
                    }
                }
            }
        }
    }
}
