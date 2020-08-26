package tripleh.happyhappyhappy.com.api.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tripleh.happyhappyhappy.com.api.enums.SourceEnum;
import tripleh.happyhappyhappy.com.api.enums.StatusEnum;
import tripleh.happyhappyhappy.com.api.response.ResponseResult;
import tripleh.happyhappyhappy.com.api.reuqest.HTecEditParams;
import tripleh.happyhappyhappy.com.api.reuqest.HTecParams;
import tripleh.happyhappyhappy.com.tripleh.happy.service.IHPersonalSummaryTechniqueService;
import tripleh.happyhappyhappy.com.tripleh.happy.service.IHTechniqueService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * Author: zixli
 * Date: 2020/8/17 15:08
 * FileName: TechController
 * Description: 个人技术控制器
 */

@RestController
@RequestMapping("/tech")
@Slf4j
@Api(tags = "个人技术API")
public class TechController {

    @Autowired
    private IHTechniqueService ihTechniqueService;

    @Autowired
    private IHPersonalSummaryTechniqueService ihPersonalSummaryTechniqueService;

    /**
     * 个人技术列表下拉框数据
     * @return
     */
    @GetMapping("/option/init")
    @ApiOperation(value = "个人技术列表下拉框数据", httpMethod = "GET")
    public Map initOption() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("cateOption", ihTechniqueService.queryAllTech()); //分类
        map.put("sourceOption", SourceEnum.getAll()); //来源
        map.put("statusOption", StatusEnum.getAll()); //状态
        return map;
    }

    /**
     * 个人技术列表数据
     * @return
     */
    @PostMapping("/data/get")
    @ApiOperation(value = "个人技术列表数据", httpMethod = "POST")
    public Object getData(@RequestBody HTecParams hTecParams) {
        Page page = ihPersonalSummaryTechniqueService.page(hTecParams.getIPage(), hTecParams.getWrapper());
        return page;
    }

    /**
     * 新增/编辑个人技术
     * @return
     */
    @PostMapping("/data/addOrUpdate")
    @ApiOperation(value = "新增个人技术", httpMethod = "POST")
    public ResponseResult addOrUpdate(@Valid @RequestBody HTecEditParams hTecEditParams) {
        ihPersonalSummaryTechniqueService.addOrUpdate(hTecEditParams.transfer());
        return ResponseResult.SUCCESS();
    }

    /**
     * 删除个人技术
     * @return
     */
    @GetMapping("/data/del/{ids}")
    @ApiOperation(value = "删除个人技术", httpMethod = "GET")
    public ResponseResult delete(@PathVariable String ids) {
        ihPersonalSummaryTechniqueService.delBatch(ids);
        return ResponseResult.SUCCESS();
    }

}
