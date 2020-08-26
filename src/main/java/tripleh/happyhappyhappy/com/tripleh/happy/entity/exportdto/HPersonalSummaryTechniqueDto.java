package tripleh.happyhappyhappy.com.tripleh.happy.entity.exportdto;

import lombok.Data;
import tripleh.happyhappyhappy.com.tripleh.happy.util.Excel;

/**
 * Author: zixli
 * Date: 2020/7/23 13:26
 * FileName: HPersonalSummaryTechniqueDto
 * Description: 个人总结技术导出类
 */
@Data
public class HPersonalSummaryTechniqueDto {

    @Excel(importName = "开发技术", exportName = "问题描述", columnIndex = 1, exportSecondName = "question_description")
    private String questionDescription;

    @Excel(importName="结果", exportName = "状态", columnIndex = 2, exportSecondName = "status")
    private String status;

    @Excel(importName = "备注", exportName = "解决方案", columnIndex = 3, exportSecondName = "answer")
    private String answer;

    @Excel(importName = "参考文档1", exportName = "参考链接", columnIndex = 4, exportSecondName = "refer_url")
    private String referUrl;

    @Excel(importName = "参考文档2", exportName = "参考链接", columnIndex = 4, exportSecondName = "refer_url")
    private String referUrl1;

    /**
     * 状态转换
     * @return 0:未完成 1:进行中 2:已完成
     */
    public String getStatus() {
        String status = "";
        switch (this.status) {
            case "未完成": status = "0"; break;
            case "进行中": status = "1"; break;
            case "已完成": status = "2"; break;
            default: status = "0";
        }
        return status;
    }

//    @Excel(exportName = "问题描述", exportSecondName = "question_description")
//    private String questionDescription;
//
//    @Excel(exportName = "状态", exportSecondName = "status")
//    private String status;
//
//    @Excel(exportName = "解决方案", exportSecondName = "answer")
//    private String answer;
//
//    @Excel(exportName = "参考链接", exportSecondName = "refer_url")
//    private String referUrl;



}
