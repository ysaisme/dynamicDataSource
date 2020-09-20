package com.dynamicDataSource.dynamicDataSource.enums;

import lombok.Getter;

/**
 * @Author: ysd
 * @Description:
 * @Date: Created in 2020/9/20 11:12
 * Modified By:
 */
@Getter
public enum ResultCodeEnum {

    SUCCEED(0,"OK",true),
    CODE_1(1,"你没有访问权限！",false),
    CODE_2(2,"{0}",false),
    CODE_4(4,"会话超时，请重新登录！",false),
    CODE_3(3,"系统异常",false),
    CODE_40(40,"无token",false);


    private int code;
    private String message;
    private Boolean success;

    ResultCodeEnum(int code,String message,Boolean success){
        this.code = code;
        this.message = message;
        this.success = success;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Boolean getSuccess() {
        return success;
    }


    public static ResultCodeEnum getByCode(int code){
        ResultCodeEnum[] enums = ResultCodeEnum.values();
        for(ResultCodeEnum item:enums){
            if(item.getCode()==code){
                return item;
            }
        }
        return  ResultCodeEnum.CODE_3;
    }
}
