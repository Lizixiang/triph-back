package tripleh.happyhappyhappy.com.api.response;

import lombok.AllArgsConstructor;
import lombok.ToString;

/**
 * Author: zixli
 * Date: 2020/8/20 13:53
 * FileName: CommonResultCode
 * Description: 状态码类
 */
@ToString
@AllArgsConstructor
public enum CommonResultCode implements ResultCode{

    SUCCESS(true, 100000, "操作成功"),
    UNAUTHENTICATED(false, 100001, "此操作需要"),
    UNAUTHORISE(false, 100002, "权限不足，无法操作"),
    INVALID_PARAM(false, 100003, "非法参数"),
    BUSINESS_ABNORMAL(false, 100004, "业务异常"),
    SERVICE_UNAVAILABLE(false, 100005, "服务暂时不可用"),
    FORBID_DIRECT_ACCESS_SERVICE(false, 100006, "禁止直接访问该服务"),
    FAIL(false, 111111, "操作失败"),
    SERVER_ERROR(false, 99999, "抱歉，系统繁忙，请稍后重试！");

    private boolean success;
    private int code;
    private String message;

    @Override
    public boolean isSuccess() {
        return success;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
