package tripleh.happyhappyhappy.com.tripleh.happy.util;

import java.lang.annotation.*;

/**
 * Author: zixli
 * Date: 2020/7/23 15:01
 * FileName: Excel
 * Description: excel注解
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface Excel {

    /**
     * 导出时的列名,也是excel的表头
     *
     * @return 导出列名
     */
    String exportName() default "";

    /**
     * 导入时，对应数据库的字段 主要是用户区分每个字段，不能有重名的
     * 用来和excel的表头一一对应,这样才好取值
     * @return 导入列名
     */
    String importName() default "";

    /**
     * 导出时在excel中每个列的宽 单位为字符，一个汉字=2个字符
     * 如以列名列内容中较合适的长度 例如姓名列6 【姓名一般三个字】 性别列4【男女占1，但是列标题两个汉字】
     * 限制1-255
     *
     * @return 列宽
     */
    int exportFieldWidth() default 20;

    /**
     * 导出数据转换格式固定采用类似("A-男,B-女")才能解析，否则报错
     * 比如在数据库里sex的值为 A 象征着男性,输出到excel里就要把A转成男显示,
     * 这个字段就是这样用的，用来转换你要展示的数据,当然有维护字典就不用这操作了.
     * @return
     */
    String exportConvertSign() default "";

    /**
     * 列索引(通过这个字段,自定义该字段在excel表头的第几列)
     *
     * @return
     */
    int columnIndex() default 0;

    /**
     * 第二列名**(如果还有第二个列名就用这个字段维护,有时列名分中英文两列)**
     *
     * @return
     */
    String exportSecondName() default "";

    /**
     * 日期转字符串(顾名思义就是excel导出有日期类型的要转换)
     *
     * @return
     */
    String dateToString() default "";

}