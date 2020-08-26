package tripleh.happyhappyhappy.com.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * Author: zixli
 * Date: 2020/7/21 14:24
 * FileName: CorsConfig
 * Description: 跨域配置类:用于解决前后端分离项目
 */
//@Configuration
public class CorsConfig extends WebMvcConfigurationSupport {

    @Override
    protected void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/*")
                .allowCredentials(true)
                .allowedOrigins("*")
                .allowedMethods("*")// 允许的请求方式
                .maxAge(3600);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("index.html")
//                .addResourceLocations("classpath:/META-INF/resources/webjars.springfox-swagger-ui/");
        registry.addResourceHandler("/springfox-swagger-ui/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/springfox-swagger-ui/");

    }


}
