package tripleh.happyhappyhappy.com.tripleh.happy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tripleh.happyhappyhappy.com.config.CustomerUserDetails;
import tripleh.happyhappyhappy.com.handler.ServiceException;
import tripleh.happyhappyhappy.com.tripleh.happy.entity.HUser;
import tripleh.happyhappyhappy.com.tripleh.happy.mapper.HUserDao;
import tripleh.happyhappyhappy.com.tripleh.happy.service.IHUserService;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author zixli
 * @since 2020-08-26
 */
@Service("userService")
@Slf4j
public class HUserServiceImpl extends ServiceImpl<HUserDao, HUser> implements IHUserService {

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        log.info("loadUserByUsername and username:{}", s);
        QueryWrapper<HUser> wrapper = new QueryWrapper<>();
        wrapper.eq("username", s);
        HUser hUser = baseMapper.selectOne(wrapper);
        log.info("user info:{}", hUser);
        if (ObjectUtils.isEmpty(hUser)) {
            log.error("user not exists...");
            throw new ServiceException("该用户不存在！");
        }
        if ("1".equals(hUser.getStatus())) {
            log.error("user is disabled...");
            throw new ServiceException("该用户已停用！");
        }
        return new CustomerUserDetails(hUser);
    }

}
