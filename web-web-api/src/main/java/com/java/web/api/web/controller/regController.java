package com.java.web.api.web.controller;

import com.java.commons.util.BaseResult;
import com.java.commons.util.CookieUtils;
import com.java.domain.User;
import com.java.web.api.service.UserService;
import com.java.web.api.web.controller.vo.LoginVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
public class regController {

    @Autowired
    UserService userService;

    @GetMapping("exit")
    public String exit(HttpServletRequest request, HttpServletResponse response){
        CookieUtils.deleteCookie(request,response,"username");
        CookieUtils.deleteCookie(request,response,"password");
        request.getSession().invalidate();
        return "redirect:/index";
    }



    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public BaseResult login(LoginVO loginVO, HttpServletResponse response, HttpServletRequest request){
        BaseResult baseResult=userService.userLogin(loginVO);

        if(baseResult.getStatus()==200){
            request.getSession().setAttribute("user",(User)baseResult.getData());
            if("true".equals(loginVO.getIsRemember())) {
                CookieUtils.setCookie(request, response, "username", loginVO.getUsername(), 60 * 60 * 24 * 7);
                CookieUtils.setCookie(request, response, "password", loginVO.getPassword(), 60 * 60 * 24 * 7);
            }
        }
        return baseResult;

    }


    @GetMapping("rename")
    @ResponseBody
    public BaseResult isRename(String username){

        return userService.selectByName(username);

    }

    @PostMapping("reg")
    @ResponseBody
    public BaseResult reg(HttpServletRequest request, HttpServletResponse response, User user){
        BaseResult baseResult=userService.insert(user);
        return baseResult;
    }

    @ResponseBody
    @RequestMapping(value="upload",method = RequestMethod.POST)
    public Map<String,Object> upload(MultipartFile dropFile, MultipartFile editorFile, HttpServletRequest request){

        MultipartFile multiFile = dropFile == null ? editorFile:dropFile;

        Map<String,Object> result = new HashMap<>();
        //在static内下创建一个upload文件夹
        //1.获得该文件夹的路径的file对象
        File filePath = new File(request.getServletContext().getRealPath("/static/users/1"));
        //2.如果该文件夹不存在则创建
        if(!filePath.exists()){
            filePath.mkdirs();
        }

        //创建一个File来描述文件
        String filename = multiFile.getOriginalFilename();
        //获得扩展名

        filename = filename.substring(filename.lastIndexOf('.'));
        filename = UUID.randomUUID()+filename;
        File file = new File(filePath,filename);
        //把dropFile对象转换成file
        try {
            multiFile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        filename = "/static/users/"+1+"/"+filename;
        if(dropFile!=null){
            //通过dropZone上传的图片
            //把文件名存到map里，map被转换成json，json被传给前端
            result.put("filename",filename);
            return result;
        }else{
            //通过wangEidtor上传的图片
            result.put("errno",0);
            String editorFilePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+filename;
            result.put("data",new String[]{editorFilePath});//线上图片的地址
            return result;
        }

    }
}
