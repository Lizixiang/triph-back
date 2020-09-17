package tripleh.happyhappyhappy.com.api.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Author: zixli
 * Date: 2020/8/20 13:59
 * FileName: ResponseResult
 * Description: 响应实体基类
 */
@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseResult {

    @ApiModelProperty("成功标志")
    private boolean success;

    @ApiModelProperty("标志码")
    private int code;

    @ApiModelProperty("标志码")
    private String message;

    @ApiModelProperty("成功数据")
    private Object data;

    public ResponseResult(ResultCode resultCode) {
        this.success = resultCode.isSuccess();
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
    }

    public ResponseResult(ResultCode resultCode, String msg) {
        this.success = resultCode.isSuccess();
        this.code = resultCode.getCode();
        this.message = msg;
    }

    public ResponseResult(ResultCode resultCode, Object data) {
        this.success = resultCode.isSuccess();
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
        this.data = data;
    }

    /**
     * 操作成功（不带返回值）
     *
     * @return
     */
    public static ResponseResult SUCCESS() {
        return new ResponseResult(CommonResultCode.SUCCESS);
    }

    /**
     * 操作成功（带返回值）
     *
     * @return
     */
    public static ResponseResult SUCCESS(Object data) {
        return new ResponseResult(CommonResultCode.SUCCESS, data);
    }

    /**
     * 操作失败
     *
     * @return
     */
    public static ResponseResult FAIL() {
        return new ResponseResult(CommonResultCode.FAIL);
    }

    /**
     * 操作失败
     *
     * @return
     */
    public static ResponseResult FAIL(String msg) {
        return new ResponseResult(CommonResultCode.FAIL, msg);
    }

    /**
     * 操作失败
     * @param commonResultCode
     * @return
     */
    public static ResponseResult FAIL(CommonResultCode commonResultCode) {
        return new ResponseResult(commonResultCode.isSuccess(), commonResultCode.getCode(), commonResultCode.getMessage(), null);
    }

    /**
     * 操作失败
     * @param resultCode
     * @param msg
     * @return
     */
    public static ResponseResult FAIL(int resultCode, String msg) {
        return new ResponseResult(false, resultCode, msg, null);
    }

}
