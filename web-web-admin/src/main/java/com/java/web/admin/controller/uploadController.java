package com.java.web.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: you are really cool!!
 * @Date: 2019/1/8 19:33
 * @Version 1.0
 */
@Controller
public class uploadController {

    public String upload(MultipartFile MultipartFile, HttpServletRequest request){
        File filePath = new File(request.getServletContext().getRealPath("/static/upload"));
        if (!filePath.exists()){
            filePath.mkdirs();
        }

        String filename = MultipartFile.getOriginalFilename();
        File file = new File(filePath,filename);
        if (file.exists()){
            StringBuffer f = new StringBuffer(filename);
            int num = f.indexOf(".");
            f.insert(num,"!");
            filename = f.toString();
            file = new File(filePath,filename);
        }

        try {
            MultipartFile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        filename = "/static/upload/"+filename;
        return filename;
    }

    @ResponseBody
    @RequestMapping(value = "uploadPic",method = RequestMethod.POST)
    public Map<String,Object> uploadPic(MultipartFile dropFile, HttpServletRequest request){
        String filename = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+upload(dropFile,request);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("filename",filename);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "uploadContent",method = RequestMethod.POST)
    public Map<String,Object> uploadContent(MultipartFile editorFile, HttpServletRequest request){
        String filename = upload(editorFile,request);
        Map<String,Object> map = new HashMap<String,Object>();
        String editorFilePath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+filename;
        map.put("errno",0);
        map.put("data",new String[]{editorFilePath});
        return map;
    }

}
