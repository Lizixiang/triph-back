package tripleh.happyhappyhappy.com.tripleh.happy.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import tripleh.happyhappyhappy.com.tripleh.happy.entity.exportdto.HPersonalSummaryTechniqueDto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * <p>
 * 个人总结技术表
 * </p>
 *
 * @author zixli
 * @since 2020-07-23
 */
@Data
@Accessors(chain = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HPersonalSummaryTechnique extends Model<HPersonalSummaryTechnique> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 问题描述
     */
    private String questionDescription;
    /**
     * 解决方案
     */
    private String answer;
    /**
     * 来源 0:个人 1:csdn（爬虫） 默认是0
     */
    private String source;
    /**
     * 状态 0:未完成 1:进行中 2:已完成 默认是0
     */
    private String status;
    /**
     * 参考链接
     */
    private String referUrl;
    /**
     * 技术分类id
     */
    private Long techniqueId;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    /**
     * 创建人
     */
    private String createName;
    /**
     * 修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
    /**
     * 修改人
     */
    private String updateName;
    /**
     * 删除标记 0:未删除 1:删除 默认0 
     */
    private String delFlag;


    public static final String ID = "id";

    public static final String QUESTION_DESCRIPTION = "question_description";

    public static final String ANSWER = "answer";

    public static final String SOURCE = "source";

    public static final String STATUS = "status";

    public static final String REFER_URL = "refer_url";

    public static final String TECHNIQUE_ID = "technique_id";

    public static final String CREATE_TIME = "create_time";

    public static final String CREATE_NAME = "create_name";

    public static final String UPDATE_TIME = "update_time";

    public static final String UPDATE_NAME = "update_name";

    public static final String DEL_FLAG = "del_flag";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    public static final HPersonalSummaryTechnique buildTechnique (HPersonalSummaryTechniqueDto dto) {
        return HPersonalSummaryTechnique.builder().questionDescription(dto.getQuestionDescription())
                .answer(dto.getAnswer())
                .source("0")
                .status(dto.getStatus())
                .referUrl(dto.getReferUrl())
                .createName("admin")
                .createTime(LocalDateTime.now()).build();
    }

}
