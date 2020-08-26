package tripleh.happyhappyhappy.com.api.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/index")
@Slf4j
public class IndexController {

    @GetMapping("/helloworld")
    public String getHello() {
        return "hello world";
    }

    @PostMapping("/sayHello")
    public String sayHello(@RequestParam String username) {
        return "hello " + username;
    }

}
