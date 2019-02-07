package com.java.web.ui.web.interceptor;

import com.java.commons.util.CookieUtils;
import com.java.commons.util.HttpClientUtils;
import com.java.commons.util.MapperUtils;
import com.java.domain.User;
import com.java.web.ui.web.controller.vo.LoginVO;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class AutoLogin implements HandlerInterceptor {
    private Logger logger= LoggerFactory.getLogger(Autowired.class);

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        Object autoLogin = httpServletRequest.getSession().getAttribute("AutoLogin");

        if(autoLogin==null||autoLogin.equals(true)){
            String username = CookieUtils.getCookieValue(httpServletRequest, "username");
            String password = CookieUtils.getCookieValue(httpServletRequest, "password");
            if (username != null && password != null) {
                LoginVO loginVO = new LoginVO();
                loginVO.setPassword(password);
                loginVO.setUsername(username);
                List<BasicNameValuePair> list=new ArrayList<>();
                list.add(new BasicNameValuePair("username", loginVO.getUsername()));
                list.add(new BasicNameValuePair("password",loginVO.getPassword()));
                BasicNameValuePair[] params= (BasicNameValuePair[]) list.toArray(new BasicNameValuePair[0]);
                String s1 = HttpClientUtils.doPost(HttpClientUtils.URL + "/login", params);
                User user= MapperUtils.json2pojoByTree(s1,"data",User.class);
                if (user!=null) {
                    httpServletRequest.getSession().setAttribute("user", user);
                    logger.info("自动登录成功！");
                }else
                    logger.info("自动登录失败");
            }
        }
        httpServletRequest.getSession().setAttribute("AutoLogin",false);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
