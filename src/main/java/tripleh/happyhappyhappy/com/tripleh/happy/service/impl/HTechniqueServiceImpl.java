package tripleh.happyhappyhappy.com.tripleh.happy.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tripleh.happyhappyhappy.com.handler.ServiceException;
import tripleh.happyhappyhappy.com.tripleh.happy.entity.HTechnique;
import tripleh.happyhappyhappy.com.tripleh.happy.mapper.HTechniqueDao;
import tripleh.happyhappyhappy.com.tripleh.happy.service.IHTechniqueService;

import java.security.Principal;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 技术表 服务实现类
 * </p>
 *
 * @author zixli
 * @since 2020-07-23
 */
@Service
@Slf4j
public class HTechniqueServiceImpl extends ServiceImpl<HTechniqueDao, HTechnique> implements IHTechniqueService {
    @Override
    public List<HTechnique> queryAllTech() {
        QueryWrapper<HTechnique> wrapper = Wrappers.query();
        wrapper.eq("del_flag", "0");
        List<HTechnique> hTechniques = baseMapper.selectList(wrapper);
        log.info("queryAllTech and hTechniques:{}", hTechniques);
        return hTechniques;
    }

    @Override
    public int delCate(String id) {
        int i = baseMapper.delById(Long.parseLong(id));
        log.info("delCate and i:{}", i);
        if (i <= 0) {
            throw new ServiceException("操作失败");
        }
        return i;
    }

    @Override
    public int addCate(String techniqueName, Principal principal) {
        log.info("addCate and techniqueName:{}, principal:{}", techniqueName, principal);
        QueryWrapper<HTechnique> wrapper = Wrappers.query();
        wrapper.eq("technique_name", techniqueName);
        wrapper.eq("del_flag", "0");
        List<HTechnique> hTechniques = baseMapper.selectList(wrapper);
        if (ObjectUtils.isNotEmpty(hTechniques)) {
            log.error("techniqueName:{} already exists...", techniqueName);
            throw new ServiceException("【" + techniqueName + "】已经存在");
        }
        HTechnique hTechnique = HTechnique.builder()
                .techniqueName(techniqueName)
                .nickName(techniqueName)
                .createTime(new Date())
                .createName(principal.getName())
                .delFlag("0")
                .build();
        log.info("addCate and hTechnique:{}", hTechnique);
        int i = baseMapper.insert(hTechnique);
        if (i <= 0) {
            log.error("addCate error...");
            throw new ServiceException("插入失败");
        }
        return i;
    }
}
