package tripleh.happyhappyhappy.com.api.reuqest;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.format.annotation.DateTimeFormat;
import tripleh.happyhappyhappy.com.api.common.PageFactory;

import java.util.Date;

/**
 * Author: zixli
 * Date: 2020/8/21 15:17
 * FileName: ReptileQueryParams
 * Description: 爬虫查询参数
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReptileQueryParams extends PageFactory {

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("状态")
    private int status;

    @ApiModelProperty("开始时间")
//    @JsonFormat(shape= JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    @ApiModelProperty("结束时间")
//    @JsonFormat(shape= JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    /**
     * 构造分页查询条件
     * @return
     */
    public Query getPageQuery() {
        Query query = new Query();
        if (ObjectUtils.isNotEmpty(title)) {
            query.addCriteria(Criteria.where("title").regex(".*?" + title + ".*?"));
        }
        if (ObjectUtils.isNotEmpty(status) && status != 0) {
            query.addCriteria(Criteria.where("status").is(status));
        }
        if (ObjectUtils.isNotEmpty(startTime) && ObjectUtils.isNotEmpty(endTime)) {
            query.addCriteria(Criteria.where("createTime").gte(startTime).lte(endTime));
        }
        if (ObjectUtils.isNotEmpty(getCurrent()) && ObjectUtils.isNotEmpty(getSize())) {
            query.with(PageRequest.of(getCurrent(), getSize()));
        }
        query.with(Sort.by(Sort.Order.desc("createTime")));
        return query;
    }

    /**
     * 构造查询条件
     * @return
     */
    public Query getQuery() {
        Query query = new Query();
        if (ObjectUtils.isNotEmpty(title)) {
            query.addCriteria(Criteria.where("title").regex(".*?" + title + ".*?"));
        }
        if (ObjectUtils.isNotEmpty(status) && status != 0) {
            query.addCriteria(Criteria.where("status").is(status));
        }
        if (ObjectUtils.isNotEmpty(startTime) && ObjectUtils.isNotEmpty(endTime)) {
            query.addCriteria(Criteria.where("createTime").gte(startTime).lte(endTime));
        }
        return query;
    }

}
