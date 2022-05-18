package com.atdecap.springbootbackstage.excepction;


import lombok.Getter;

/**
 * @author
 * @BelongsProject: IntelliJ IDEA
 * @BelongsPackage: com.atdecap.springbootbackstage.excepction
 * @create 2022-05-2022/5/1215:05
 * @desc 自定义异常
 **/
@Getter
public class ServiceException extends RuntimeException {
    private String code;

    public ServiceException(String code, String msg) {
        super(msg);
        this.code = code;
    }

}
