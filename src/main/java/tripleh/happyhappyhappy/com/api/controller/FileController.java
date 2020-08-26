package tripleh.happyhappyhappy.com.api.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import tripleh.happyhappyhappy.com.tripleh.happy.entity.HPersonalSummaryTechnique;
import tripleh.happyhappyhappy.com.tripleh.happy.entity.exportdto.HPersonalSummaryTechniqueDto;
import tripleh.happyhappyhappy.com.tripleh.happy.mapper.HPersonalSummaryTechniqueDao;
import tripleh.happyhappyhappy.com.tripleh.happy.util.ExcelUtils;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/file")
@Slf4j
@Api(tags = "文件API")
public class FileController {

    @Autowired
    private HPersonalSummaryTechniqueDao hPersonalSummaryTechniqueDao;

    @PostMapping(value = "/importTechnique")
    @ApiOperation(value = "导入个人总结技术文档", httpMethod = "POST")
    public String importTechnique(@RequestParam("上传文件") MultipartFile file) {
        try {
            List<HPersonalSummaryTechniqueDto> list = Arrays.asList(ExcelUtils.importExel(file, HPersonalSummaryTechniqueDto.class).toArray(new HPersonalSummaryTechniqueDto[0]));
            list.parallelStream().forEach(item -> hPersonalSummaryTechniqueDao.insert(HPersonalSummaryTechnique.buildTechnique(item)));
        } catch (Exception e) {
            e.printStackTrace();
            return "操作失败";
        }
        return "操作成功";
    }

}
