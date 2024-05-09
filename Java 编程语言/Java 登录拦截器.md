```java
...
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录拦截器
 *
 * @author liuzonglin
 * @date 2022/09/08
 */
public class LoginInterceptor implements HandlerInterceptor {

    /**
     * 检测去全局session 对象是否有 uuid, 如果有则方行，如果没有重定向到登录页
     *
     * @param request  当前的 HTTP 请求
     * @param response 当前的 HTTP 响应
     * @param handler  选择要执行的处理程序，用于类型和或实例评估
     * @return true : 放行当前请求，false : 拦截当前请求
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // HttpServletRequest 对象获取全局 session 对象
        Object uid = request.getSession().getAttribute("uid");

        if (uid == null) {
            // 用户没有登录，重定向login.html
            response.sendRedirect("/web/login.html");
            // 结束后续的调用
            return false;
        }

        // 请求方行
        return true;
    }


}

```

```java
...
import com.cy.store.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * 登录拦截器配置
 * 处理器拦截器的注册
 *
 * @author liuzonglin
 * {@code @date} 2022/09/08
 * {@code @Configuration}  加载当前的拦截器，进行加载
 */
@Configuration
public class LoginInterceptorConfigurer implements WebMvcConfigurer {


    /**
     * 添加拦截器
     *
     * @param registry 注册表
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 创建拦截器对象
        HandlerInterceptor interceptor = new LoginInterceptor();
        // 配置白名单：存放在list集合当中;未登录状态，让用户访问
        List<String> patterns = new ArrayList<>();
        // 目录下所有方行
        patterns.add("/bootstrap3/**");
        patterns.add("/css/**");
        patterns.add("/images");
        patterns.add("/js/**");
        patterns.add("/web/register.html");
        patterns.add("/web/login.html");
        patterns.add("/web/index.html");
        patterns.add("/web/product.html");
        patterns.add("/users/reg");
        patterns.add("/users/login");
        // 完成了连接器的注册
        registry.addInterceptor(interceptor)
                // 表示拦截的 url
                .addPathPatterns("/**")
                .excludePathPatterns(patterns);
    }

}
```