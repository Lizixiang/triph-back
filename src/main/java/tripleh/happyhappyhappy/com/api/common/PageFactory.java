package tripleh.happyhappyhappy.com.api.common;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Author: zixli
 * Date: 2020/8/19 13:25
 * FileName: PageFactory
 * Description: 分页参数基类
 */
@Data
public class PageFactory<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 当前页
     */
    @TableField(exist = false)
    private int current;

    /**
     * 每页数据量
     */
    @TableField(exist = false)
    private int size;

    /**
     * 排序字段
     */
    @TableField(exist = false)
    private String order;

    /**
     * 排序类型
     */
    @TableField(exist = false)
    private String orderType;

    public void setOrder(String order) {
        this.order = camel2Underline(order);
    }

    @JsonIgnore
    public PageFactory() {
        this.current = 1;
        this.size = 100;
    }

    @JsonIgnore
    public Page<T> getIPage() {
        if (StringUtils.isBlank(order)) {
            Page<T> page = new Page<>(getCurrent(), getSize());
            return page;
        } else {
            Page<T> page = new Page<T>(current, size);
            if ("asc".equals(orderType)) {
                page.setAsc(getOrder());
            } else {
                page.setDesc(getOrder());
            }
            return page;
        }
    }

    @JsonIgnore
    public Page<T> getData(long total, List<T> data) {
        if (StringUtils.isBlank(order)) {
            Page<T> page = new Page<>(getCurrent(), getSize());
            return page.setRecords(data).setTotal(total);
        } else {
            Page<T> page = new Page<T>(current, size);
            if ("asc".equals(orderType)) {
                page.setAsc(getOrder());
            } else {
                page.setDesc(getOrder());
            }
            return page.setRecords(data).setTotal(total);
        }
    }


    /**
     * @Description: 驼峰转下划线
     * @Param: [line]
     * @return: java.lang.String
     * @Author: wwr
     * @Date: 2018-12-25
     */
    @JsonIgnore
    public static String camel2Underline(String line) {
        if (line == null || "".equals(line)) {
            return "";
        }
        line = String.valueOf(line.charAt(0)).toUpperCase().concat(line.substring(1));
        StringBuffer sb = new StringBuffer();
        Pattern pattern = Pattern.compile("[A-Z]([a-z\\d]+)?");
        Matcher matcher = pattern.matcher(line);
        while (matcher.find()) {
            String word = matcher.group();
            sb.append(word.toUpperCase());
            sb.append(matcher.end() == line.length() ? "" : "_");
        }
        return sb.toString();
    }

    /**
     * 下划线转驼峰法(默认小驼峰)
     *
     * @param line       源字符串
     * @param smallCamel 大小驼峰,是否为小驼峰(驼峰，第一个字符是大写还是小写)
     * @return 转换后的字符串
     */
    @JsonIgnore
    public static String underline2Camel(String line, boolean... smallCamel) {
        if (line == null || "".equals(line)) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        Pattern pattern = Pattern.compile("([A-Za-z\\d]+)(_)?");
        Matcher matcher = pattern.matcher(line);
        //匹配正则表达式
        while (matcher.find()) {
            String word = matcher.group();
            //当是true 或则是空的情矿
            if ((smallCamel.length == 0 || smallCamel[0]) && matcher.start() == 0) {
                sb.append(Character.toLowerCase(word.charAt(0)));
            } else {
                sb.append(Character.toUpperCase(word.charAt(0)));
            }
            int index = word.lastIndexOf('_');
            if (index > 0) {
                sb.append(word.substring(1, index).toLowerCase());
            } else {
                sb.append(word.substring(1).toLowerCase());
            }
        }
        return sb.toString();
    }

}
