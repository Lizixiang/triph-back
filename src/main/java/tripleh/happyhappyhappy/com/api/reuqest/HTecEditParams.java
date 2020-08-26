package tripleh.happyhappyhappy.com.api.reuqest;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tripleh.happyhappyhappy.com.tripleh.happy.entity.HPersonalSummaryTechnique;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * Author: zixli
 * Date: 2020/8/20 12:29
 * FileName: HTecEditParams
 * Description: 编辑个人技术入参
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HTecEditParams {

    @ApiModelProperty("id")
    private long id;

    @ApiModelProperty("标题")
    @NotBlank(message = "标题不能为空")
    private String title;

    @ApiModelProperty("分类")
    private long cate;

    @ApiModelProperty("状态")
    @NotBlank(message = "状态不能为空")
    private String status;

    @ApiModelProperty("内容")
    @NotBlank(message = "内容不能为空")
    private String content;

    public HPersonalSummaryTechnique transfer() {
        return HPersonalSummaryTechnique.builder()
                .id(id)
                .questionDescription(title)
                .answer(content)
                .source("0")
                .status(status)
                .techniqueId(cate)
                .createTime(LocalDateTime.now())
                .createName("admin")
                .delFlag("0").build();
    }

}
