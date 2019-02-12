package com.java.web.admin.controller;

import com.java.commons.dto.BaseResult;
import com.java.commons.dto.PageVO;
import com.java.domain.TbContent;
import com.java.web.admin.service.TbContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author: you are really cool!!
 * @Date: 2019/1/7 20:33
 * @Version 1.0
 */
@Controller
@RequestMapping("content")
public class ContentController{

    @Autowired
    private TbContentService<TbContent> service;

    @RequestMapping(value="list",method = RequestMethod.GET)
    public String list(){
        return "content_list";
    }


    @ModelAttribute
    public TbContent selectById(Long id){
        TbContent tbContent = new TbContent();
        if (id != null) {
            tbContent = service.selectById(id);

        }
        return tbContent;
    }


    @RequestMapping(value="form",method = RequestMethod.GET)
    public String form(){
        return "content_form";
    }


    @RequestMapping(value="save",method = RequestMethod.POST)
    public String save(TbContent tbContent, RedirectAttributes redirectAttributes, Model model){
        BaseResult baseResult = service.save(tbContent);
        if(baseResult.getStatus()==BaseResult.STATUS_SUCCESS){
            //如果表单验证通过了,把封装了具体状态码和信息的baseResult对象转递到目标页面
            redirectAttributes.addFlashAttribute("baseResult",baseResult);
            return "redirect:/content/list";
        }
        else{
            //如果表单验证没有通过
            //返回错误信息，并且回到新增页面
            model.addAttribute("baseResult",baseResult);
            return "content_form";
        }
    }

    @RequestMapping(value="search",method = RequestMethod.POST)
    public String search(TbContent tbContent,Model model){
        List<TbContent> tbContents = service.searchTbContents(tbContent);
        model.addAttribute("tbContents",tbContents);
        return "content_list";
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "delSelect",method = RequestMethod.POST)
    public BaseResult delSelect(String ids){
        BaseResult baseResult =service.delSelect(ids);
        return baseResult;
    }

    @ResponseBody
    @RequestMapping(value = "selectPageContent",method = RequestMethod.POST)
    public PageVO<TbContent> selectPageContent(HttpServletRequest request, TbContent tbContent){
        String strdraw = request.getParameter("draw");
        String strstart = request.getParameter("start");
        String strlength = request.getParameter("length");
        int draw = strdraw==null?1:Integer.parseInt(strdraw);
        int start = strstart==null?0:Integer.parseInt(strstart);
        int length = strlength==null?10:Integer.parseInt(strlength);
        PageVO<TbContent> pageVO =service.selectPageContent(draw,start,length,tbContent);

        return pageVO;
    }


    @RequestMapping(value = "details",method = RequestMethod.GET)
    public String details(){
        return "content_details";
    }

    @RequestMapping(value = "deleteById")
    public String deleteById(Long id ,RedirectAttributes redirectAttributes){
        BaseResult baseResult = service.deleteById(id);
        redirectAttributes.addFlashAttribute("baseResult",baseResult);
        return "redirect:/content/list";
    }
}
