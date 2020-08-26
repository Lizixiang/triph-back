package tripleh.happyhappyhappy.com.tripleh.happy.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.PathVariable;
import tripleh.happyhappyhappy.com.api.reuqest.ReptileQueryParams;
import tripleh.happyhappyhappy.com.tripleh.happy.entity.mongodto.FilterSite;

/**
 * Author: zixli
 * Date: 2020/8/21 15:31
 * FileName: ReptileService
 * Description: 爬虫接口
 */
public interface ReptileService {

    /**
     * 根据条件查询爬虫数据
     *
     * @param reptileQueryParams
     * @return
     */
    Page<FilterSite> findListByParams(ReptileQueryParams reptileQueryParams);

    /**
     * 捞取
     *
     * @param ids mongodb id集合
     * @return
     */
    int gain(@PathVariable String ids);

    /**
     * 删除
     * @param ids mongodb id集合
     * @return
     */
    int del(@PathVariable String ids);
}
