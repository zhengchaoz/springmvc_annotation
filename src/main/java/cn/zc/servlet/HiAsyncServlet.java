package cn.zc.servlet;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * servlet3.0异步请求
 *
 * @author 郑超
 * @date 2021-05-02 19:24
 */
@WebServlet(value = "/async", asyncSupported = true)
public class HiAsyncServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 支持异步处理：asyncSupported = true
        AsyncContext asyncContext = req.startAsync();
        System.out.println(Thread.currentThread() + "main start...");
        asyncContext.start(() -> {
            try {
                System.out.println(Thread.currentThread() + "start...");
                sayHi();
                asyncContext.complete();// 处理完成，返回响应
                ServletResponse response = asyncContext.getResponse();
                response.getWriter().write("hi async...");
                System.out.println(Thread.currentThread() + "start...");
            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            }
        });
        System.out.println(Thread.currentThread() + "main start...");
    }

    public void sayHi() throws InterruptedException {
        System.out.println(Thread.currentThread() + "processing...");
        Thread.sleep(3000);
    }
}
