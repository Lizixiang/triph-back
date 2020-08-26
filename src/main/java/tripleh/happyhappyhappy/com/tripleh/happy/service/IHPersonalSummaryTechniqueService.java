package tripleh.happyhappyhappy.com.tripleh.happy.service;


import com.baomidou.mybatisplus.extension.service.IService;
import tripleh.happyhappyhappy.com.tripleh.happy.entity.HPersonalSummaryTechnique;

/**
 * <p>
 * 个人总结技术表 服务类
 * </p>
 *
 * @author zixli
 * @since 2020-07-23
 */
public interface IHPersonalSummaryTechniqueService extends IService<HPersonalSummaryTechnique> {

    /**
     * 新增/编辑个人技术
     * @param hPersonalSummaryTechnique
     * @return
     */
    int addOrUpdate(HPersonalSummaryTechnique hPersonalSummaryTechnique);

    /**
     * 删除个人技术
     * @param ids id集合
     * @return
     */
    int delBatch(String ids);

}
