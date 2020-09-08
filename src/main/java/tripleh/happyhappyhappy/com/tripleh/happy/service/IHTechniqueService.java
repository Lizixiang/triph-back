package tripleh.happyhappyhappy.com.tripleh.happy.service;


import com.baomidou.mybatisplus.extension.service.IService;
import tripleh.happyhappyhappy.com.tripleh.happy.entity.HTechnique;

import java.security.Principal;
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
     *
     * @return
     */
    List<HTechnique> queryAllTech();

    /**
     * 删除技术分类
     *
     * @return
     */
    int delCate(String id);

    /**
     * 添加技术分类
     *
     * @param techniqueName 技术名称
     * @return
     */
    int addCate(String techniqueName, Principal principal);

}
