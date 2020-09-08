package tripleh.happyhappyhappy.com.api.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tripleh.happyhappyhappy.com.api.response.ResponseResult;
import tripleh.happyhappyhappy.com.api.reuqest.ReptileQueryParams;
import tripleh.happyhappyhappy.com.tripleh.happy.entity.mongodto.FilterSite;
import tripleh.happyhappyhappy.com.tripleh.happy.service.ReptileService;

/**
 * Author: zixli
 * Date: 2020/8/21 15:08
 * FileName: ReptileController
 * Description: 爬虫控制器
 */
@RestController
@RequestMapping("/reptile")
@Slf4j
@Api(tags = "爬虫API")
public class ReptileController {

    @Autowired
    private ReptileService reptileService;

    /**
     * 爬虫列表
     * @param reptileQueryParams
     * @return
     */
    @PostMapping("/data/get")
    @ApiOperation(value = "爬虫列表", httpMethod = "POST")
    public Object getData(@RequestBody ReptileQueryParams reptileQueryParams) {
        Page<FilterSite> listByParams = reptileService.findListByParams(reptileQueryParams);
        return ResponseResult.SUCCESS(listByParams);
    }

    /**
     * 捞取
     * @param ids mongodb id集合
     * @return
     */
    @GetMapping("/data/gain/{ids}")
    @ApiOperation(value = "捞取", httpMethod = "POST")
    public ResponseResult gain(@PathVariable String ids, String techId) {
        int gain = reptileService.gain(ids, techId);
        return ResponseResult.SUCCESS();
    }

    /**
     * 删除
     * @param ids mongodb id集合
     * @return
     */
    @GetMapping("/data/del/{ids}")
    @ApiOperation(value = "删除", httpMethod = "POST")
    public ResponseResult del(@PathVariable String ids) {
        reptileService.del(ids);
        return ResponseResult.SUCCESS();
    }
}
