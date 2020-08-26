package tripleh.happyhappyhappy.com.config;

import java.lang.annotation.*;

/**
 * Author: zixli
 * Date: 2020/7/23 13:26
 * FileName: ExportEntityMap
 * Description: POI自定义注解类
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ExportEntityMap {

    String EnName() default "数据库列名";
    String CnName() default "实体映射名";

}
