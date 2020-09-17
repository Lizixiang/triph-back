package tripleh.happyhappyhappy.com.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import tripleh.happyhappyhappy.com.interceptor.SecurityGlobalInterceptor;
import tripleh.happyhappyhappy.com.utils.RedisUUID;
import tripleh.happyhappyhappy.com.utils.SpringContextUtil;

/**
 * Author: zixli
 * Date: 2020/9/16 14:38
 * FileName: WebMvcConfig
 * Description: web配置
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private RedisUUID redisUUID;

    @Autowired
    private SpringContextUtil springContextUtil;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        SecurityGlobalInterceptor securityGlobalInterceptor = new SecurityGlobalInterceptor();
        securityGlobalInterceptor.setRedisUUID(redisUUID);
        securityGlobalInterceptor.setSpringContextUtil(springContextUtil);
        registry.addInterceptor(securityGlobalInterceptor).addPathPatterns("/**");
    }
}
