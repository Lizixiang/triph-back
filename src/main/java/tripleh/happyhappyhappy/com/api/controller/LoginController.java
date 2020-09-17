//package tripleh.happyhappyhappy.com.api.controller;
//
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import io.swagger.annotations.ApiParam;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RestController;
//import tripleh.happyhappyhappy.com.api.response.ResponseResult;
//import tripleh.happyhappyhappy.com.tripleh.happy.service.LoginService;
//
///**
// * Author: zixli
// * Date: 2020/8/26 20:25
// * FileName: LogonController
// * Description: 登录控制层
// */
//@RestController
//@Api(tags = "登录接口")
//@Slf4j
////@RequestMapping("/login")
//public class LoginController {
//
//    @Autowired
//    private LoginService loginService;
//
//    @PostMapping("/login")
//    @ResponseBody
//    @ApiOperation(value = "用户密码登录", httpMethod = "POST")
//    public ResponseResult login(@ApiParam("用户名") String username, @ApiParam("密码") String password) {
//        return ResponseResult.SUCCESS(loginService.login(username, password));
//    }
//
//}
