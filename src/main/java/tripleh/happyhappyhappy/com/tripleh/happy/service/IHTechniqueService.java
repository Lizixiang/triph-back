package tripleh.happyhappyhappy.com.tripleh.happy.service;


import com.baomidou.mybatisplus.extension.service.IService;
import tripleh.happyhappyhappy.com.tripleh.happy.entity.HTechnique;

import java.util.List;

/**
 * <p>
 * 技术表 服务类
 * </p>
 *
 * @author zixli
 * @since 2020-07-23
 */
public interface IHTechniqueService extends IService<HTechnique> {

    /**
     * 查询所有技术分类
     * @return
     */
    List<HTechnique> queryAllTech();

}
