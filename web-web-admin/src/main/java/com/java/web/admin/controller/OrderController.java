package com.java.web.admin.controller;

import com.java.commons.dto.BaseResult;
import com.java.commons.dto.PageVO;
import com.java.domain.TbItem;
import com.java.domain.TbOrder;
import com.java.web.admin.service.TbItemService;
import com.java.web.admin.service.TbOrderService;
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
 * @Date: 2019/2/11 19:06
 * @Version 1.0
 */
@Controller
@RequestMapping("order")
public class OrderController {
    @Autowired
    private TbOrderService<TbOrder> service;


    @ModelAttribute
    public TbOrder selectById(String id){
        TbOrder tbOrder = new TbOrder();
        if (id != null) {
            tbOrder = service.selectById(id);
        }
        return tbOrder;
    }

    @RequestMapping(value="list",method = RequestMethod.GET)
    public String list(){
        return "order_list";
    }

    @ResponseBody
    @RequestMapping(value = "selectPageOrder",method = RequestMethod.POST)
    public PageVO<TbOrder> selectAll(HttpServletRequest request, TbOrder tbOrder){
        String strStart = request.getParameter("start");
        String strDraw = request.getParameter("draw");
        String strLength=request.getParameter("length");
        int draw = strDraw==null?1:Integer.parseInt(strDraw);
        int start = strStart==null?0:Integer.parseInt(strStart);
        int length = strLength==null?10:Integer.parseInt(strLength);
        PageVO<TbOrder> pageVO = service.selectAll(start,draw,length, tbOrder);
        return pageVO;
    }

    @RequestMapping(value = "form",method = RequestMethod.GET)
    public String form(){

        return "item_form";
    }

    @RequestMapping(value = "details",method = RequestMethod.GET)
    public String details(){
        return "order_details";
    }



    @RequestMapping(value="save",method = RequestMethod.GET)
    public String save(TbOrder tbOrder,String strstatus, RedirectAttributes redirectAttributes, Model model){
        int status = 0;
        if (strstatus!=null){
            status = Integer.parseInt(strstatus);
        }
        tbOrder.setStatus(status);
        BaseResult baseResult = service.save(tbOrder);
        if(baseResult.getStatus()==BaseResult.STATUS_SUCCESS){
            //如果表单验证通过了,把封装了具体状态码和信息的baseResult对象转递到目标页面
            redirectAttributes.addFlashAttribute("baseResult",baseResult);
            return "redirect:/order/list";
        }
        else{
            //如果表单验证没有通过
            //返回错误信息，并且回到新增页面
            model.addAttribute("baseResult",baseResult);
            return "order_form";
        }
    }
}
