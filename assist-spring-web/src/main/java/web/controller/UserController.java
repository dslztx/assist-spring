package web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import me.dslztx.assist.util.ObjectAssist;
import web.domain.User;

@Controller
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

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

    @RequestMapping("/upload/datastream")
    @ResponseBody
    public String dataStream(@RequestBody String body) {

        logger.info("transfered data stream: {}", body);

        return "transfer data stream successfully";
    }

    @RequestMapping("/timeout")
    @ResponseBody
    public String timeout(@RequestBody String body) throws InterruptedException {

        logger.info("timeout: {}", body);

        Thread.sleep(10000L);

        return "timeout";
    }
}
