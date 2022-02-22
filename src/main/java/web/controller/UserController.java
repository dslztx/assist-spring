package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import me.dslztx.assist.util.ObjectAssist;
import web.domain.User;

@Controller
public class UserController {

    @RequestMapping("/hello")
    @ResponseBody
    public String hello() {
        return "Hello World! Welcome to visit dslztx.com";
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    @ResponseBody
    public String addUser(@RequestBody User user) {
        if (ObjectAssist.isNotNull(user))
            return user.toString();
        return "NULL";
    }

    @RequestMapping("/hello/way")
    @ResponseBody
    public User helloWay() {
        return new User("dslztx", 33);
    }
}
