package com.dynamicDataSource.dynamicDataSource.untils;

import com.dynamicDataSource.dynamicDataSource.enums.ResultCodeEnum;

/**
 * @Author: ysd
 * @Description:
 * @Date: Created in 2020/9/20 11:11
 * Modified By:
 */
public class BaseResult {

    private Integer code;
    private String message;
    private Object data;


    public BaseResult(){

    }

    public BaseResult(Integer code, String message){
        this.code  = code;
        this.message = message;
        this.data = "";
    }

    public BaseResult(ResultCodeEnum codeEnum){
        this.code  = codeEnum.getCode();
        this.message = codeEnum.getMessage();
        this.data = "";
    }

    public  static BaseResult success(){
        return  new BaseResult(ResultCodeEnum.SUCCEED);
    }

    public  static BaseResult success(Object o){
        return  new BaseResult(ResultCodeEnum.SUCCEED).setData(o);
    }

    public  static BaseResult fail(String message){
        return  new BaseResult(ResultCodeEnum.CODE_2).setMessage(message);
    }

    public  static BaseResult sessionOut(){
        return  new BaseResult(ResultCodeEnum.CODE_4);
    }

    public  static BaseResult notToken(){
        return  new BaseResult(ResultCodeEnum.CODE_40);
    }

    public  static BaseResult systemError(){
        return  new BaseResult(ResultCodeEnum.CODE_3);
    }

    public  static BaseResult notAuthorize(){
        return  new BaseResult(ResultCodeEnum.CODE_1);
    }

    public  static BaseResult customizeError(Integer code, String message){
        return  new BaseResult(ResultCodeEnum.CODE_1);
    }


    public Integer getCode() {
        return code;
    }

    public BaseResult setCode(Integer code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public BaseResult setMessage(String message) {
        this.message = message;
        return this;
    }

    public Object getData() {
        return data;
    }

    public BaseResult setData(Object data) {
        this.data = data;
        return this;
    }
}
