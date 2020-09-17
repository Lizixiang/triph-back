package tripleh.happyhappyhappy.com.interceptor;

import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import tripleh.happyhappyhappy.com.api.response.CommonResultCode;
import tripleh.happyhappyhappy.com.api.response.ResponseResult;
import tripleh.happyhappyhappy.com.constant.RedisKeyConstant;
import tripleh.happyhappyhappy.com.utils.RedisUUID;
import tripleh.happyhappyhappy.com.utils.SpringContextUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * Author: zixli
 * Date: 2020/9/16 14:26
 * FileName: SecurityGlobalInterceptor
 * Description: 全局安全拦截器
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SecurityGlobalInterceptor implements HandlerInterceptor {

    private RedisUUID redisUUID;

    private SpringContextUtil springContextUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 开发环境无需安全拦截，方便调试
        if ("dev".equals(springContextUtil.getActiveProfile())) return true;
        String secretKey = request.getHeader(RedisKeyConstant.SECURITY_KEY);
        if (StringUtils.isNotBlank(secretKey)) {
            String secret = (String) redisUUID.get(RedisKeyConstant.SECURITY_KEY);
            if (StringUtils.isNotBlank(secret) && secret.equals(secretKey)) {
                return true;
            }
        }
        response.setContentType("application/json; charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.write(JSON.toJSONString(ResponseResult.FAIL(CommonResultCode.FORBID_DIRECT_ACCESS_SERVICE)));
        return false;
    }
}
