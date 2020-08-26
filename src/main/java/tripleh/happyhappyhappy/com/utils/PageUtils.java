package tripleh.happyhappyhappy.com.utils;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.poi.ss.formula.functions.T;

import java.util.List;

/**
 * Author: zixli
 * Date: 2020/8/21 16:13
 * FileName: PageUtils
 * Description: 分页工具类
 */
public class PageUtils {

    public static Page<T> pageData(long total, List<T> data) {
        return new Page<T>().setRecords(data).setTotal(total);
    }

}
