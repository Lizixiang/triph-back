package tripleh.happyhappyhappy.com.tripleh.happy.service;

/**
 * Author: zixli
 * Date: 2020/8/26 20:33
 * FileName: LoginService
 * Description: 登录服务
 */
public interface LoginService {

    /**
     * 用户名密码登录
     *
     * @param username
     * @param password
     * @return 返回accessToken
     */
    String login(String username, String password);

}
