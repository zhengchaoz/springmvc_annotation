package cn.zc.service;

import org.springframework.stereotype.Service;

/**
 * @author 郑超
 * @date 2021-05-02 17:31
 */
@Service
public class HelloService {

    public String sayHello(String name) {
        return "Hello "+name + "!";
    }

}
