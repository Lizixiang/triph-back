package tripleh.happyhappyhappy.com.tripleh.happy.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import tripleh.happyhappyhappy.com.tripleh.happy.entity.HTechnique;
import tripleh.happyhappyhappy.com.tripleh.happy.mapper.HTechniqueDao;
import tripleh.happyhappyhappy.com.tripleh.happy.service.IHTechniqueService;

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
public class HTechniqueServiceImpl extends ServiceImpl<HTechniqueDao, HTechnique> implements IHTechniqueService {
    @Override
    public List<HTechnique> queryAllTech() {
        QueryWrapper<HTechnique> wrapper = Wrappers.query();
        wrapper.eq("del_flag", "0");
        return baseMapper.selectList(wrapper);
    }
}
