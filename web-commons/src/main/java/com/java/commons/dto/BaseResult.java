package com.java.commons.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class BaseResult implements Serializable {
    public static final int STATUS_SUCCESS = 200;
    public static final int STATUS_FAIL = 500;

    private int status;
    //错误信息或者正确信息
    private String message;
    private Object data;


    public  static BaseResult success(){
        BaseResult baseResult = BaseResult.createBaseResult(STATUS_SUCCESS, "验证成功",null);
        return baseResult;
    }

    public  static BaseResult success(String message){
        BaseResult baseResult = BaseResult.createBaseResult(STATUS_SUCCESS, message,null);
        return baseResult;
    }

    public  static BaseResult success(String message,Object data){
        BaseResult baseResult = BaseResult.createBaseResult(STATUS_SUCCESS, message,data);
        return baseResult;
    }

    public  static BaseResult success(int status,String message,Object data){
        BaseResult baseResult = BaseResult.createBaseResult(status, message,data);
        return baseResult;
    }

    public static BaseResult fail(){
        BaseResult baseResult = BaseResult.createBaseResult(STATUS_FAIL, "验证失败",null);
        return baseResult;
    }
    public static BaseResult fail(String message){
        BaseResult baseResult = BaseResult.createBaseResult(STATUS_FAIL,message ,null);
        return baseResult;
    }

    public static BaseResult fail(int status,String message){
        BaseResult baseResult = BaseResult.createBaseResult(status, message,null);
        return baseResult;
    }

    public static BaseResult fail(int status,String message,Object data){
        BaseResult baseResult = BaseResult.createBaseResult(status, message,data);
        return baseResult;
    }

    public static BaseResult createBaseResult(int status , String message,Object data){
        BaseResult baseResult = new BaseResult();
        baseResult.setStatus(status);
        baseResult.setMessage(message);
        baseResult.setData(data);
        return baseResult;
    }

}
