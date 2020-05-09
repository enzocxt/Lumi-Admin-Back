// package com.tao.lumiadmin.interceptor;

// import com.tao.lumiadmin.pojo.User;

// import org.apache.catalina.security.SecurityUtil;
// import org.apache.commons.lang.StringUtils;
// import org.apache.shiro.SecurityUtils;
// import org.apache.shiro.subject.Subject;
// import org.springframework.http.HttpMethod;
// import org.springframework.http.HttpStatus;
// import org.springframework.web.servlet.HandlerInterceptor;

// import javax.servlet.http.HttpServletRequest;
// import javax.servlet.http.HttpServletResponse;
// import javax.servlet.http.HttpSession;

// // 继承 SpringBoot 拦截器接口，然后实现 preHandle 方法
// // preHandle 方法里的代码会在访问需要拦截的页面时执行
// // （需要把拦截器配置到项目中：config/MyWebConfigurer ）
// public class LoginInterceptor  implements HandlerInterceptor{

//     @Override
//     public boolean preHandle (HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
//         // 由于跨域情况下会先发出一个 options 请求试探，这个请求是不带 cookie 信息的
//         // 所以 Shiro 无法获取 sessionID，将导致认证失败。

//         // 放行 options 请求，否则无法让前端带上自定义的 header 信息，导致 sessionID 改变，Shiro 验证失败
//         if (HttpMethod.OPTIONS.toString().equals(httpServletRequest.getMethod())) {
//             httpServletResponse.setStatus(HttpStatus.NO_CONTENT.value());
//             return true;
//         }

//         Subject subject = SecurityUtils.getSubject();
//         // 使用 Shiro 验证
//         if (!subject.isAuthenticated() && !subject.isRemembered()) {
//             return false;
//         }
//         System.out.println(subject.isRemembered());
//         System.out.println(subject.isAuthenticated());
        
//         /*
//         // 判断 session 中是否存在 user 属性
//         // 如果存在就放行，否则跳转到 login 页面
//         HttpSession session = httpServletRequest.getSession();
//         String contextPath = session.getServletContext().getContextPath();
//         // 路径列表里面包含了需要拦截的路径（当然，我们可以拦截所有路径）
//         String[] requireAuthPages = new String[]{
//                 "index",
//         };

//         String uri = httpServletRequest.getRequestURI();
//         uri = StringUtils.remove(uri, contextPath+"/");
//         String page = uri;

//         if (begingWith(page, requireAuthPages)) {
//             User user = (User) session.getAttribute("user");
//             if(user == null) {
//                 httpServletResponse.sendRedirect("login");
//                 return false;
//             }
//         }
//         */
//         return true;
//     }

//     private boolean begingWith(String page, String[] requiredAuthPages) {
//         boolean result = false;
//         for (String requiredAuthPage : requiredAuthPages) {
//             if (StringUtils.startsWith(page, requiredAuthPage)) {
//                 result = true;
//                 break;
//             }
//         }
//         return result;
//     }
// }

