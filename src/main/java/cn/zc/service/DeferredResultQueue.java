package cn.zc.service;

import org.springframework.web.context.request.async.DeferredResult;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * @author 郑超
 * @date 2021-05-02 19:59
 */
public class DeferredResultQueue {

    private static Queue<DeferredResult<Object>> queue = new ConcurrentLinkedDeque<>();

    public static void save(DeferredResult<Object> result) {
        queue.add(result);
    }

    public static DeferredResult<Object> get() {
        return queue.poll();
    }

}
