package tripleh.happyhappyhappy.com.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import tripleh.happyhappyhappy.com.constant.NacosConstant;

/**
 * @author lizixiang
 * @since 2021/5/10
 */
@RestController
public class TestController {

    @Autowired
    private NacosConstant nacosConstant;

    @GetMapping("/test1")
    public String test1() {
        return nacosConstant.getMessage();
    }

}
