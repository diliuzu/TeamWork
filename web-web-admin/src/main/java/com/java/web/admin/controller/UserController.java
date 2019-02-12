package com.java.web.admin.controller;

import com.java.commons.dto.BaseResult;
import com.java.commons.dto.PageVO;
import com.java.domain.TbUser;
import com.java.web.admin.service.TbUserService;
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

@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    private TbUserService service;


    @RequestMapping(value="list",method = RequestMethod.GET)
    public String list(){
        return "user_list";
    }



    @ModelAttribute
    public TbUser selectById(Long id){
        TbUser tbUser = new TbUser();
        if (id != null) {
            tbUser = service.selectById(id);

        }
        return tbUser;
    }



    @RequestMapping(value="form",method = RequestMethod.GET)
    public String form(){
        return "user_form";
    }



    @RequestMapping(value="save",method = RequestMethod.POST)
    public String save(TbUser tbUser, RedirectAttributes redirectAttributes, Model model){
        //System.out.println(tbUser);
        BaseResult baseResult = service.save(tbUser);
        if(baseResult.getStatus()==BaseResult.STATUS_SUCCESS){
            //如果表单验证通过了,把封装了具体状态码和信息的baseResult对象转递到目标页面
            redirectAttributes.addFlashAttribute("baseResult",baseResult);
            return "redirect:/user/list";
        }
        else{
            //如果表单验证没有通过
            //返回错误信息，并且回到新增页面
            model.addAttribute("baseResult",baseResult);
            return "user_form";
        }
    }

    @RequestMapping(value="search",method = RequestMethod.POST)
    public String search(TbUser tbUser,Model model){
        List<TbUser> tbUsers = service.searchTbUsers(tbUser);
        model.addAttribute("tbUsers",tbUsers);
        return "user_list";
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
    @RequestMapping(value = "selectPageUser",method = RequestMethod.POST)
    public PageVO<TbUser> selectPageUser(HttpServletRequest request, TbUser tbUser){
        String strdraw = request.getParameter("draw");
        String strstart = request.getParameter("start");
        String strlength = request.getParameter("length");
        int draw = strdraw==null?1:Integer.parseInt(strdraw);
        int start = strstart==null?0:Integer.parseInt(strstart);
        int length = strlength==null?10:Integer.parseInt(strlength);
        PageVO<TbUser> pageVO =service.selectPageUser(draw,start,length,tbUser);
        return pageVO;
    }


    @RequestMapping(value = "details",method = RequestMethod.GET)
    public String details(){
        return "user_details";
    }

    @RequestMapping(value = "deleteById")
    public String deleteById(Long id,RedirectAttributes redirectAttributes){
        BaseResult baseResult = service.deleteById(id);
        redirectAttributes.addFlashAttribute("baseResult",baseResult);
        return "redirect:/user/list";
    }
}
