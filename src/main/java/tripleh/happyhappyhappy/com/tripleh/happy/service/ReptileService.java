package tripleh.happyhappyhappy.com.tripleh.happy.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.PathVariable;
import tripleh.happyhappyhappy.com.api.reuqest.ReptileQueryParams;
import tripleh.happyhappyhappy.com.tripleh.happy.entity.mongodto.FilterSite;
import tripleh.happyhappyhappy.com.tripleh.happy.entity.mongodto.HotWord;

import java.util.List;

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
     * @param techId    技术分类id
     * @return
     */
    int gain(String ids, String techId);

    /**
     * 删除
     * @param ids mongodb id集合
     * @return
     */
    int del(String ids);

    /**
     * 获取热词
     * @return
     */
     List<HotWord> queryHotWord();
}
