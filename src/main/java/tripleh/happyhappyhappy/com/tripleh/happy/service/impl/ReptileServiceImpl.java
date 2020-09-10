package tripleh.happyhappyhappy.com.tripleh.happy.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import tripleh.happyhappyhappy.com.api.reuqest.ReptileQueryParams;
import tripleh.happyhappyhappy.com.handler.ServiceException;
import tripleh.happyhappyhappy.com.tripleh.happy.entity.HPersonalSummaryTechnique;
import tripleh.happyhappyhappy.com.tripleh.happy.entity.HTechnique;
import tripleh.happyhappyhappy.com.tripleh.happy.entity.mongodto.FilterSite;
import tripleh.happyhappyhappy.com.tripleh.happy.entity.mongodto.HotWord;
import tripleh.happyhappyhappy.com.tripleh.happy.service.IHPersonalSummaryTechniqueService;
import tripleh.happyhappyhappy.com.tripleh.happy.service.IHTechniqueService;
import tripleh.happyhappyhappy.com.tripleh.happy.service.ReptileService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: zixli
 * Date: 2020/8/21 15:39
 * FileName: ReptileServiceImpl
 * Description: 爬虫服务实现类
 */
@Service
@Slf4j
public class ReptileServiceImpl implements ReptileService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private IHPersonalSummaryTechniqueService ihPersonalSummaryTechniqueService;

    @Autowired
    private IHTechniqueService ihTechniqueService;

    @Override
    public Page<FilterSite> findListByParams(ReptileQueryParams reptileQueryParams) {
        log.info("findListByParams and reptileQueryParams:{}", reptileQueryParams);
        List<FilterSite> filterSites = mongoTemplate.find(reptileQueryParams.getPageQuery(), FilterSite.class);
        long count = mongoTemplate.count(reptileQueryParams.getQuery(), FilterSite.class);
        log.info("count:{}", count);
        return reptileQueryParams.getData(count, filterSites);
    }

    @Override
    public int gain(String ids, String techId) {
        log.info("gain and ids:{}, techId:{}", ids, techId);
        if (ObjectUtils.isEmpty(ids)) {
            log.error("ids is empty..");
            throw new ServiceException("参数不能为空");
        }
        HTechnique hTechnique = ihTechniqueService.getById(techId);
        Query query = new Query();
        query.addCriteria(Criteria.where("id").in(ids.split(",")));
        query.addCriteria(Criteria.where("status").is(0));
        List<FilterSite> filterSites = mongoTemplate.find(query, FilterSite.class);
//        log.info("filterSites:{}", filterSites);
        if (ObjectUtils.isNotEmpty(filterSites)) {
            List<HPersonalSummaryTechnique> list = new ArrayList<>();
            for (FilterSite filterSite : filterSites) {
                list.add(HPersonalSummaryTechnique.builder()
                        .questionDescription(filterSite.getTitle())
                        .answer(filterSite.getContent())
                        .source("1")
                        .status("0")
                        .referUrl(filterSite.getLink())
                        .createTime(LocalDateTime.now())
                        .createName("admin")
                        .delFlag("0")
                        .techniqueId(ObjectUtils.isNotEmpty(hTechnique) ? hTechnique.getId() : 0)
                        .build());
            }
            boolean b = ihPersonalSummaryTechniqueService.saveBatch(list);
            if (b) {
                Update status = Update.update("status", 1);
                mongoTemplate.updateMulti(query, status, FilterSite.class);
                return filterSites.size();
            }
        }
        return 0;
    }

    @Override
    public int del(String ids) {
        log.info("del and ids:{}", ids);
        if (ObjectUtils.isEmpty(ids)) {
            log.error("ids is empty..");
            throw new ServiceException("参数不能为空");
        }
        Query query = new Query();
        query.addCriteria(Criteria.where("id").in(ids.split(",")));
        query.addCriteria(Criteria.where("status").is(0));
        List<FilterSite> allAndRemove = mongoTemplate.findAllAndRemove(query, FilterSite.class);
//        log.info("allAndRemove:{}", allAndRemove);
        return allAndRemove.size();
    }

    @Override
    public List<HotWord> queryHotWord() {
        Query query = new Query();
        query.addCriteria(Criteria.where("year").is(LocalDateTime.now().getYear()));
        query.addCriteria(Criteria.where("status").is(0));
        query.with(Sort.by(Sort.Order.desc("count"))).limit(10);
        List<HotWord> hotWords = mongoTemplate.find(query, HotWord.class);
        return hotWords;
    }
}
