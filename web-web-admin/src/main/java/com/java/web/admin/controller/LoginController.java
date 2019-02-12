package com.java.web.admin.controller;

import com.java.domain.TbAdmin;
import com.java.web.admin.service.TbAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

    //自动装配
    @Autowired
    private TbAdminService service;
    /**
     * http://localhost:8080/login
     * 返回登录页面
     * @return
     */
    @RequestMapping(value={"","login"},method= RequestMethod.GET)
    public String login(){
        return "login";//   /WEB-INF/views/login.jsp
    }

    /**
     * 执行登录业务
     * @param username
     * @param password
     * @return
     */
    @RequestMapping(value="login",method=RequestMethod.POST)
    public String login(String username, String password, Model model, HttpServletRequest request){
        //实现登录
        TbAdmin admin = service.login(username,password);
        if(admin==null){
            //登录失败
            model.addAttribute("msg","用户名或密码错误");
            return login();
        }else{
            //登录成功
            request.getSession().setAttribute("admin",admin);
            request.getSession().setMaxInactiveInterval(60*60*24*7);
//            redirectAttributes.addFlashAttribute(ConstantUitls.SESSION_USER,user);
            return "redirect:/index";
        }
//        return "login";
    }

    /**
     * 注销
     * @param request
     * @return
     */
    @RequestMapping(value="logout",method = RequestMethod.GET)
    public String logout(HttpServletRequest request){
        request.getSession().invalidate();
        return "login";
    }
}