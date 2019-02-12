package com.java.web.admin.controller;

import com.java.commons.dto.BaseResult;
import com.java.commons.dto.PageVO;
import com.java.domain.TbItem;
import com.java.web.admin.service.TbItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: you are really cool!!
 * @Date: 2019/1/25 15:46
 * @Version 1.0
 */
@Controller
@RequestMapping("item")
public class ItemController {
    @Autowired
    private TbItemService<TbItem> service;

    @ModelAttribute
    public TbItem selectById(Long id){
        TbItem tbItem = new TbItem();
        if (id != null) {
            tbItem = service.selectById(id);
        }
        return tbItem;
    }

    @RequestMapping(value="list",method = RequestMethod.GET)
    public String list(){
        return "item_list";
    }

    @ResponseBody
    @RequestMapping(value = "selectPageItem",method = RequestMethod.POST)
    public PageVO<TbItem> selectAll(HttpServletRequest request, TbItem tbItem){
        String strStart = request.getParameter("start");
        String strDraw = request.getParameter("draw");
        String strLength=request.getParameter("length");
        int draw = strDraw==null?1:Integer.parseInt(strDraw);
        int start = strStart==null?0:Integer.parseInt(strStart);
        int length = strLength==null?10:Integer.parseInt(strLength);
        PageVO<TbItem> pageVO = service.selectAll(start,draw,length, tbItem);
        return pageVO;
    }

    @RequestMapping(value = "deleteById",method = RequestMethod.GET)
    public String deleteById(Long id,RedirectAttributes redirectAttributes){
        BaseResult baseResult =null;
        baseResult = service.deleteById(id);
        redirectAttributes.addFlashAttribute("baseResult",baseResult);
        return "redirect:/item/list";
    }

    @RequestMapping(value = "form",method = RequestMethod.GET)
    public String form(){

        return "item_form";
    }

    @RequestMapping(value = "details",method = RequestMethod.GET)
    public String details(){
        return "item_details";
    }



    @RequestMapping(value="save",method = RequestMethod.POST)
    public String save(TbItem tbItem, RedirectAttributes redirectAttributes, Model model){
        BaseResult baseResult = service.save(tbItem);
        if(baseResult.getStatus()==BaseResult.STATUS_SUCCESS){
            //如果表单验证通过了,把封装了具体状态码和信息的baseResult对象转递到目标页面
            redirectAttributes.addFlashAttribute("baseResult",baseResult);
            return "redirect:/item/list";
        }
        else{
            //如果表单验证没有通过
            //返回错误信息，并且回到新增页面
            model.addAttribute("baseResult",baseResult);
            return "item_form";
        }
    }
}
