package tripleh.happyhappyhappy.com.api.reuqest;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tripleh.happyhappyhappy.com.api.common.PageFactory;
import tripleh.happyhappyhappy.com.tripleh.happy.entity.HPersonalSummaryTechnique;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Author: zixli
 * Date: 2020/8/19 13:33
 * FileName: HTecParams
 * Description: 个人技术列表请求参数
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HTecParams extends PageFactory {

    @ApiModelProperty("id")
    private long id;

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("分类")
    private long cate;

    @ApiModelProperty("来源")
    private String source;

    @ApiModelProperty("状态")
    private String status;

    @ApiModelProperty("开始时间")
    @JsonFormat(shape= JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;

    @ApiModelProperty("结束时间")
    @JsonFormat(shape= JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;

    public QueryWrapper getWrapper() {
        QueryWrapper<HPersonalSummaryTechnique> query = Wrappers.query();
        if (!Objects.isNull(id) && id != 0) {
            query.eq("id", id);
        }
        if (StringUtils.isNotBlank(title)) {
            query.like("question_description", title);
        }
        if (!Objects.isNull(cate) && cate != 0) {
            query.eq("technique_id", cate);
        }
        if (StringUtils.isNotBlank(source)) {
            query.like("source", source);
        }
        if (StringUtils.isNotBlank(source)) {
            query.like("source", source);
        }
        if (StringUtils.isNotBlank(status)) {
            query.like("status", status);
        }
        if (!Objects.isNull(startTime)) {
            query.apply("date_format (create_time,'%Y-%m-%d %H:%i:%s') >= date_format('" + startTime + "','%Y-%m-%d %H:%i:%s')");
        }
        if (!Objects.isNull(startTime)) {
            query.apply("date_format (create_time,'%Y-%m-%d %H:%i:%s') <= date_format('" + endTime + "','%Y-%m-%d %H:%i:%s')");
        }
        if ("asc".equalsIgnoreCase(getOrderType())) {
            query.orderByAsc("create_time");
        } else {
            query.orderByDesc("create_time");
        }
        query.eq("del_flag", "0");
        return query;
    }

}
