package com.java.web.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Author: you are really cool!!
 * @Date: 2019/2/6 13:37
 * @Version 1.0
 */
@Controller
public class IndexController {

    @RequestMapping(value="index",method= RequestMethod.GET)
    public String index(){
        return "index";
    }
}
