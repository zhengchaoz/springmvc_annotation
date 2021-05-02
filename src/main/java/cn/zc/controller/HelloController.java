package cn.zc.controller;

import cn.zc.service.HelloService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 郑超
 * @date 2021-05-02 17:30
 */
@Controller
public class HelloController {

    private final HelloService helloService;

    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }

    @ResponseBody
    @RequestMapping("/sayHello")
    public String sayHello() {
        return helloService.sayHello("郑超");
    }

    @RequestMapping("/helloJsp")
    public String helloJsp() {
        return "hello";
    }

    @RequestMapping("/helloHtml")
    public String helloHtml() {
        return "hello";
    }

}
