//package tripleh.happyhappyhappy.com.tripleh.happy.service.impl;
//
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang3.ObjectUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.oauth2.common.OAuth2AccessToken;
//import org.springframework.stereotype.Service;
//import tripleh.happyhappyhappy.com.api.feign.FeignApi;
//import tripleh.happyhappyhappy.com.handler.ServiceException;
//import tripleh.happyhappyhappy.com.tripleh.happy.service.LoginService;
//
//import java.util.HashMap;
//
///**
// * Author: zixli
// * Date: 2020/8/26 20:36
// * FileName: LoginServiceImpl
// * Description: 登录实现类
// */
//@Service
//@Slf4j
//public class LoginServiceImpl implements LoginService {
//
//    @Value("${oauth2.password.client}")
//    private String PASSWORD_CLIENT_ID;
//
//    @Value("${oauth2.password.client.secret}")
//    private String PASSWORD_CLIENT_SECRET;
//
//    @Autowired
//    private FeignApi feignApi;
//
//    @Override
//    public String login(String username, String password) {
//        log.info("login and username:{}, password:{}", username, password);
//        if (ObjectUtils.isEmpty(username) || ObjectUtils.isEmpty(password)) {
//            log.error("username or password is emppty...");
//            throw new ServiceException("用户名密码不能为空");
//        }
//        HashMap<String, String> map = new HashMap<>();
//        map.put("username", username);
//        map.put("password", password);
//        map.put("grant_type", "password");
//        map.put("scope", "select");
//        map.put("client_id", PASSWORD_CLIENT_ID);
//        map.put("client_secret", PASSWORD_CLIENT_SECRET);
//        log.info("map:{}", map);
//        ResponseEntity<OAuth2AccessToken> accessToken = feignApi.postAccessToken(map);
//        log.info("postAccessToken and accessToken:{}", accessToken);
//        if (accessToken.getStatusCodeValue() != 200) {
//            throw new ServiceException(accessToken.getStatusCode().getReasonPhrase());
//        }
//        return accessToken.getBody().getValue();
//    }
//}
