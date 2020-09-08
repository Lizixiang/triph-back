package tripleh.happyhappyhappy.com.api.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tripleh.happyhappyhappy.com.api.enums.SourceEnum;
import tripleh.happyhappyhappy.com.api.enums.StatusEnum;
import tripleh.happyhappyhappy.com.api.response.ResponseResult;
import tripleh.happyhappyhappy.com.api.reuqest.HTecEditParams;
import tripleh.happyhappyhappy.com.api.reuqest.HTecParams;
import tripleh.happyhappyhappy.com.handler.ServiceException;
import tripleh.happyhappyhappy.com.tripleh.happy.entity.HTechnique;
import tripleh.happyhappyhappy.com.tripleh.happy.service.IHPersonalSummaryTechniqueService;
import tripleh.happyhappyhappy.com.tripleh.happy.service.IHTechniqueService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
        List<HTechnique> hTechniques = ihTechniqueService.queryAllTech();
        ArrayList<Map<String, String>> list = new ArrayList<>();
        for (HTechnique hTechnique : hTechniques) {
            HashMap<String, String> map1 = new HashMap<>();
            map1.put("text", hTechnique.getTechniqueName());
            map1.put("value", hTechnique.getId().toString());
            list.add(map1);
        }
        map.put("cateOption", list); //分类
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
        log.info("getData and hTecParams:{}", hTecParams);
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

    /**
     * 技术分类列表
     * @return
     */
    @PostMapping("/cate/get")
    @ApiOperation(value = "技术分类列表", httpMethod = "POST")
    public ResponseResult getCate(HttpServletRequest request, Principal principal) {
        return ResponseResult.SUCCESS(ihTechniqueService.queryAllTech());
    }

    /**
     * 删除技术分类
     * @return
     */
    @GetMapping("/cate/del/{id}")
    @ApiOperation(value = "删除技术分类", httpMethod = "GET")
    public ResponseResult delCate(@PathVariable String id) {
        log.info("delCate and id:{}", id);
        if (ObjectUtils.isEmpty(id)) {
            log.error("id is empty...");
            throw new ServiceException("id is empty...");
        }
        ihTechniqueService.delCate(id);
        return ResponseResult.SUCCESS();
    }

    /**
     * 添加技术分类
     * @return
     */
    @PostMapping("/cate/add")
    @ApiOperation(value = "添加技术分类", httpMethod = "POST")
    public ResponseResult addCate(String techniqueName, Principal principal) {
        log.info("addCate and techniqueName:{}", techniqueName);
        if (ObjectUtils.isEmpty(techniqueName)) {
            log.error("techniqueName is empty...");
            throw new ServiceException("参数为空");
        }
        ihTechniqueService.addCate(techniqueName.trim(), principal);
        return ResponseResult.SUCCESS();
    }

}
