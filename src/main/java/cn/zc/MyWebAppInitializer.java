package cn.zc;

import cn.zc.config.AppConfig;
import cn.zc.config.RootConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * 在web容器启动的时候创建对象,调用方法来初始化容器以及前端控制器
 *
 * @author 郑超
 * @date 2021-05-02 17:19
 */
public class MyWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    /**
     * 获得根容器的配置类（spring的配置信息）：父容器
     *
     * @return
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{RootConfig.class};
    }

    /**
     * 获取web容器的配置类（springmvc的配置信息）：子容器
     *
     * @return
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{AppConfig.class};
    }

    /**
     * 获取DispatcherServlet的映射信息
     * / 拦截所有请求（包括静态资源 .js .png），但不包括*.jsp
     * 不能写/* 会连*.jsp都拦截
     *
     * @return
     */
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
