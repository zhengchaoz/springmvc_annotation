package cn.zc.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebListener;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 郑超
 * @date 2021-05-02 19:06
 */
@WebServlet("/hi")// 利用注解代替web.xml中的<servlet>和<servlet-Mapper>配置，类似的还有@WebListener监听器和@WebFilter拦截器
public class MyServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(Thread.currentThread() + "start...");
        try {
            sayHi();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        resp.getWriter().write("hi...");
        System.out.println(Thread.currentThread() + "end...");
    }

    public void sayHi() throws InterruptedException {
        System.out.println(Thread.currentThread() + "processing...");
        Thread.sleep(3000);
    }
}
