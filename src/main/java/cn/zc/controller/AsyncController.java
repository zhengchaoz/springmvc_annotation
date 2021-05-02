package cn.zc.controller;

import cn.zc.service.DeferredResultQueue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.UUID;
import java.util.concurrent.Callable;

/**
 * 异步拦截器：
 * 1）原生API的AsyncListener
 * 2）springmvc的实现AsyncHandlerInterceptor
 *
 * @author 郑超
 * @date 2021-05-02 19:39
 */
@Controller
public class AsyncController {

    @ResponseBody
    @RequestMapping("/async01")
    public Callable<String> async01() {
        System.out.println("main start..." + Thread.currentThread() + "==>" + System.currentTimeMillis());
        Callable<String> stringCallable = () -> {
            System.out.println("start..." + Thread.currentThread() + "==>" + System.currentTimeMillis());
            Thread.sleep(2000);// 模拟业务执行
            System.out.println("end..." + Thread.currentThread() + "==>" + System.currentTimeMillis());
            return "Callable<String> async01()";
        };
        System.out.println("main end..." + Thread.currentThread() + "==>" + System.currentTimeMillis());
        return stringCallable;
    }

    /**
     * 模拟消息中间件，想第三方应用发起请求
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/order")
    public DeferredResult<Object> createOrder() {
        DeferredResult<Object> result = new DeferredResult<>(3000L, "time out");
        DeferredResultQueue.save(result);

        return result;
    }

    @ResponseBody
    @RequestMapping("/create")
    public String create() {
        // 模拟创建订单
        String orderId = UUID.randomUUID().toString();
        DeferredResult<Object> result = DeferredResultQueue.get();
        result.setResult(orderId);
        return "success " + orderId;
    }
}
