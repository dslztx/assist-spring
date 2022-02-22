package web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import web.domain.User;

@RestController
public class UserController {

    @RequestMapping("/hello")
    public String hello() {
        return "Hello World! Welcome to visit dslztx.com";
    }

    @RequestMapping("/hello/way")
    public User helloWay() {
        return new User("dslztx", 33);
    }
}
