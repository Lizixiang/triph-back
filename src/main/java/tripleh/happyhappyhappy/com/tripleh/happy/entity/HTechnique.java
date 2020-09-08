package tripleh.happyhappyhappy.com.tripleh.happy.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 技术表
 * </p>
 *
 * @author zixli
 * @since 2020-07-23
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class HTechnique extends Model<HTechnique> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 技术名称
     */
    private String techniqueName;
    /**
     * 简称
     */
    private String nickName;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 创建人
     */
    private String createName;
    /**
     * 修改时间
     */
    private Date updateTime;
    /**
     * 修改人
     */
    private String updateName;
    /**
     * 删除标记 0:未删除 1:删除 默认0 
     */
    private String delFlag;


    public static final String ID = "id";

    public static final String TECHNIQUE_NAME = "technique_name";

    public static final String NICK_NAME = "nick_name";

    public static final String CREATE_TIME = "create_time";

    public static final String CREATE_NAME = "create_name";

    public static final String UPDATE_TIME = "update_time";

    public static final String UPDATE_NAME = "update_name";

    public static final String DEL_FLAG = "del_flag";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
