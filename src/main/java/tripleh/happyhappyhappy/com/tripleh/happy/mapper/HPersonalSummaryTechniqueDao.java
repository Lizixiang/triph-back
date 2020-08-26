package tripleh.happyhappyhappy.com.tripleh.happy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import tripleh.happyhappyhappy.com.tripleh.happy.entity.HPersonalSummaryTechnique;

import java.util.List;

/**
 * <p>
 * 个人总结技术表 Mapper 接口
 * </p>
 *
 * @author zixli
 * @since 2020-07-23
 */
@Repository
public interface HPersonalSummaryTechniqueDao extends BaseMapper<HPersonalSummaryTechnique> {

    /**
     * 根据id修改个人技术
     * @param hPersonalSummaryTechnique
     * @return
     */
    int updateById(HPersonalSummaryTechnique hPersonalSummaryTechnique);

    /**
     * 删除个人技术
     * @param ids id集合
     * @return
     */
    int delBatch(String[] ids);

}
