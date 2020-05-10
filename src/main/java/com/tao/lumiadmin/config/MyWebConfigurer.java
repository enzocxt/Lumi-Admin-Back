package com.tao.lumiadmin.config;

// import com.tao.lumiadmin.interceptor.LoginInterceptor;
import org.springframework.boot.SpringBootConfiguration;
// import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.*;

@SpringBootConfiguration
public class MyWebConfigurer implements WebMvcConfigurer {

    // @Bean
    // public LoginInterceptor getLoginIntercepter() {
    //     return new LoginInterceptor();
    // }

    // @Override
    // public void addInterceptors(InterceptorRegistry registry) {
    //     // 添加已经写好的拦截器
    //     registry.addInterceptor(getLoginIntercepter())
    //             .addPathPatterns("/**")
    //             .excludePathPatterns("/index.html")
    //             .excludePathPatterns("/api/login")
    //             .excludePathPatterns("/api/logout");
    // }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/api/file/**")
                .addResourceLocations("file:" + "d:/Workspace/luminocity/img/");
    }

    // 为了允许跨域的 cookie
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 允许跨域使用 cookie 的情况下，allowedOrigins() 不能使用通配符 * （出于安全上的考虑）
        registry.addMapping("/**")
                .allowCredentials(true)
                .allowedOrigins("http://localhost:8080")
                .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE")
                .allowedHeaders("*")
                .maxAge(3600);
    }
}

