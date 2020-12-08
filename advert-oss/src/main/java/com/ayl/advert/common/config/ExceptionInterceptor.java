package com.ayl.advert.common.config;


import com.ayl.advert.common.response.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ayl.advert.common.response.BusinessException;
/**
 * 全局异常捕捉处理
 *
 * @author wfd
 */
@ControllerAdvice
public class ExceptionInterceptor {

    private Logger logger= LoggerFactory.getLogger(this.getClass());

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Result handler(Exception ex) {
        logger.error(ex.getMessage(),ex);
        if (ex instanceof BusinessException) {
            BusinessException e = (BusinessException) ex;
            return new Result(e.getCode(), e.getMsg());
        } else if (ex instanceof IllegalStateException || ex instanceof IllegalArgumentException){
            return new Result(Result.Code.SYSTEM_ERROR, ex.getMessage());
        }
        return new Result(Result.Code.SYSTEM_ERROR);
    }
}
