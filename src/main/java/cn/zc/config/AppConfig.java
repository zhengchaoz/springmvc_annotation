package cn.zc.config;

import cn.zc.controller.MyInterceptors;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.validation.MessageCodesResolver;
import org.springframework.validation.Validator;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.*;

import java.util.List;

/**
 * @author 郑超
 * @date 2021-05-02 17:26
 */
// 只扫描@Controller
@ComponentScan(value = "cn.zc", includeFilters = {
        @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Controller.class)
}, useDefaultFilters = false)
@EnableWebMvc//开启springmvc的定制配置功能
public class AppConfig extends WebMvcConfigurerAdapter /*WebMvcConfigurer*/ {

    /**
     * 定制视图解析器
     *
     * @param registry
     */
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
//        registry.jsp();
        registry.jsp("/WEB-INF/view/", ".jsp");
        // 配置了静态资源访问configureDefaultServletHandling()后才能跳转html
//        registry.jsp("/WEB-INF/view/", ".html");
    }

    /**
     * 访问静态资源，将springmvc处理不了的请求交给tomcat：访问静态资源
     * 相当于<mvc:default-servlet-handler/>
     *
     * @param configurer
     */
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    /**
     * 拦截器
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 自定义拦截器，拦截任意多层路径的请求
        registry.addInterceptor(new MyInterceptors()).addPathPatterns("/**");
    }
}
