package tripleh.happyhappyhappy.com.api.response;

import lombok.AllArgsConstructor;
import lombok.ToString;

/**
 * Author: zixli
 * Date: 2020/8/20 13:47
 * FileName: ResultCode
 * Description: 状态码基类
 */
public interface ResultCode {

    /**
     * 操作是否成功， true:成功 false:失败
     * @return
     */
    boolean isSuccess();

    /**
     * 状态码
     * @return
     */
    int getCode();

    /**
     * 提示信息
     */
    String getMessage();


}
