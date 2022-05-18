package com.atdecap.springbootbackstage.excepction;

import com.atdecap.springbootbackstage.common.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Decap
 * @BelongsProject: IntelliJ IDEA
 * @BelongsPackage: com.atdecap.springbootbackstage.excepction
 * @create 2022-05-2022/5/1215:03
 * @desc
 **/
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 如果抛出的的是ServiceException，则调用该方法
     * @param se 业务异常
     * @return Result
     */
    @ExceptionHandler(ServiceException.class)
    @ResponseBody
    public Result handle(ServiceException se){
        return Result.error(se.getCode(), se.getMessage());
    }

}
