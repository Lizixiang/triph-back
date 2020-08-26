package tripleh.happyhappyhappy.com.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import tripleh.happyhappyhappy.com.api.response.CommonResultCode;
import tripleh.happyhappyhappy.com.api.response.ResponseResult;

import javax.servlet.http.HttpServletRequest;

/**
 * Author: zixli
 * Date: 2020/8/20 15:39
 * FileName: CommonExceptionHandler
 * Description: 增强控制器-全局异常
 */
@RestControllerAdvice
@Slf4j
public class CommonExceptionHandler {

    /**
     * 系统异常
     *
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseResult exceptionHandler(HttpServletRequest request, Exception e) {
        log.error("do [{}] on [{}] failed. exMsg:{}", request.getMethod(), request.getRequestURL(), e.getLocalizedMessage());
        if (log.isDebugEnabled()) {
            log.error("queryString:{}, parameterMap:{}", request.getQueryString(), request.getParameterMap());
        }
        return ResponseResult.FAIL(CommonResultCode.SERVER_ERROR.getMessage());
    }


    /**
     * 业务异常
     *
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler(ServiceException.class)
    @ResponseBody
    public ResponseResult exceptionHandler(HttpServletRequest request, ServiceException e) {
        if (log.isDebugEnabled()) {
            log.error("queryString:{}, parameterMap:{}", request.getQueryString(), request.getParameterMap());
        }
        log.error("do [{}] on [{}] failed. exMsg:{}", request.getMethod(), request.getRequestURL(), e.getLocalizedMessage());
        return ResponseResult.FAIL(CommonResultCode.BUSINESS_ABNORMAL.getCode(), e.getLocalizedMessage());
    }

}
