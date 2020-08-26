package tripleh.happyhappyhappy.com.tripleh.happy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tripleh.happyhappyhappy.com.handler.ServiceException;
import tripleh.happyhappyhappy.com.tripleh.happy.entity.HPersonalSummaryTechnique;
import tripleh.happyhappyhappy.com.tripleh.happy.mapper.HPersonalSummaryTechniqueDao;
import tripleh.happyhappyhappy.com.tripleh.happy.service.IHPersonalSummaryTechniqueService;

import java.util.Arrays;
import java.util.Objects;

/**
 * <p>
 * 个人总结技术表 服务实现类
 * </p>
 *
 * @author zixli
 * @since 2020-07-23
 */
@Service
@Slf4j
public class HPersonalSummaryTechniqueServiceImpl extends ServiceImpl<HPersonalSummaryTechniqueDao, HPersonalSummaryTechnique> implements IHPersonalSummaryTechniqueService {

    @Autowired
    private HPersonalSummaryTechniqueDao hPersonalSummaryTechniqueDao;

    @Override
    public int addOrUpdate(HPersonalSummaryTechnique hPersonalSummaryTechnique) {
      log.info("addOrUpdate and hPersonalSummaryTechnique:{}", hPersonalSummaryTechnique);
      if (hPersonalSummaryTechnique.getId() == 0) { //新增
          return baseMapper.insert(hPersonalSummaryTechnique);
      } else { // 编辑
          return hPersonalSummaryTechniqueDao.updateById(hPersonalSummaryTechnique);
      }
    }

    @Override
    public int delBatch(String ids) {
        if (ObjectUtils.isEmpty(ids)) {
            log.error("ids is empty");
            throw new ServiceException("请至少选择一条记录");
        }
        return hPersonalSummaryTechniqueDao.delBatch(ids.split(","));
    }
}
