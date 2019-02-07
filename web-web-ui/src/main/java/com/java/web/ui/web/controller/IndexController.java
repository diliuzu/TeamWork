package com.java.web.ui.web.controller;

import com.java.commons.util.BaseResult;
import com.java.commons.util.CookieUtils;
import com.java.commons.util.HttpClientUtils;
import com.java.commons.util.MapperUtils;
import com.java.domain.User;
import com.java.web.ui.service.ItemCatService;


import com.java.web.ui.web.controller.vo.LoginVO;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    ItemCatService itemCatService;

    @GetMapping("index")
    public String get(){
        return "test";
    }

    @GetMapping("regjsp")
    public String reg(HttpServletRequest request){
        User user= (User) request.getSession().getAttribute("user");
        if (user==null)
            return "reg";
        else
            return "redirect:/index";
    }

    @PostMapping("reg")
    @ResponseBody
    public BaseResult userReg(User user,HttpServletRequest request) throws Exception {
        String validator = null;
         //       BeanValidator.validator(user);
        if (validator!=null)
            return BaseResult.fail("fail");

        else{
            List<BasicNameValuePair> list=new ArrayList<>();
            list.add(new BasicNameValuePair("username", user.getUsername()));
            list.add(new BasicNameValuePair("password",user.getPassword()));
            list.add(new BasicNameValuePair("email",user.getEmail()));
            BasicNameValuePair[] params= (BasicNameValuePair[]) list.toArray(new BasicNameValuePair[0]);
            String s = HttpClientUtils.doPost(HttpClientUtils.URL + "/reg", params);
            String s1 = HttpClientUtils.doPost(HttpClientUtils.URL + "/login", params);
            user=MapperUtils.json2pojoByTree(s1,"data",User.class);
            request.getSession().setAttribute("user",user);
            return BaseResult.success("success");
        }

    }


    @GetMapping("rename")
    @ResponseBody
    public BaseResult isRename(String username) throws Exception {
        String s = HttpClientUtils.doGet(HttpClientUtils.URL + "/rename?username=" + username);
        BaseResult baseResult = MapperUtils.json2pojo(s, BaseResult.class);
        return baseResult;
    }
    @PostMapping("login")
    @ResponseBody
    public BaseResult login(LoginVO loginVO, HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<BasicNameValuePair> list=new ArrayList<>();
        list.add(new BasicNameValuePair("username", loginVO.getUsername()));
        list.add(new BasicNameValuePair("password",loginVO.getPassword()));
        BasicNameValuePair[] params= (BasicNameValuePair[]) list.toArray(new BasicNameValuePair[0]);
        String s1 = HttpClientUtils.doPost(HttpClientUtils.URL + "/login", params);
        User user=MapperUtils.json2pojoByTree(s1,"data",User.class);
        if (user!=null){
            request.getSession().setAttribute("user",user);
            if("true".equals(loginVO.getIsRemember())) {
                CookieUtils.setCookie(request, response, "username", loginVO.getUsername(), 60 * 60 * 24 * 7);
                CookieUtils.setCookie(request, response, "password", loginVO.getPassword(), 60 * 60 * 24 * 7);
            }
            return BaseResult.success("success");
        }else
            return BaseResult.fail("fail");
    }
}
