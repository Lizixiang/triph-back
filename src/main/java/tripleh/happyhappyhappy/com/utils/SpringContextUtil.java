package tripleh.happyhappyhappy.com.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Author: zixli
 * Date: 2020/9/16 15:38
 * FileName: SpringContextUtil
 * Description: 上下文工具类
 */
@Component
public class SpringContextUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextUtil.applicationContext = applicationContext;
    }

    /**
     * 获取上下文对象
     * @return
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static String getActiveProfile() {
        return getApplicationContext().getEnvironment().getActiveProfiles()[0];
    }

}
