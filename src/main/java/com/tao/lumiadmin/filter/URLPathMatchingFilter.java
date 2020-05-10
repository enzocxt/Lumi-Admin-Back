package com.tao.lumiadmin.filter;

import com.tao.lumiadmin.service.AdminPermissionService;
import com.tao.lumiadmin.utils.SpringContextUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;

public class URLPathMatchingFilter extends PathMatchingFilter {
    @Autowired
    AdminPermissionService adminPermissionService;

    @Override
    protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        // 放行 options 请求
        if (HttpMethod.OPTIONS.toString().equals((httpServletRequest).getMethod())) {
            httpServletResponse.setStatus(HttpStatus.NO_CONTENT.value());
            return true;
        }

        if (null == adminPermissionService) {
            adminPermissionService = SpringContextUtils.getContext().getBean(AdminPermissionService.class);
        }

        String requestAPI = getPathWithinApplication(request);
        System.out.println("ACCESS API：" + requestAPI);

        // Get the currently executing user
        Subject subject = SecurityUtils.getSubject();
        // System.out.println("|------- Subject: " + subject);
        // System.out.println(subject.getPrincipal().toString());

        if (!subject.isAuthenticated()) {
            System.out.println("REQUIRE LOGIN");
            return false;
        }

        // Check whether the api needs filter (if there is corresponding info in Database)
        boolean needFilter = adminPermissionService.needFilter(requestAPI);
        if (!needFilter) {
            System.out.println("API：" + requestAPI + "NEEDS NO PERMISSION");
            return true;
        } else {
            System.out.println("CHECK ACCESS PERMISSION：" + requestAPI);
            // Check whether the current user has the permission
            boolean hasPermission = false;
            String username = subject.getPrincipal().toString();
            Set<String> permissionAPIs = adminPermissionService.listPermissionURLsByUser(username);
            for (String api : permissionAPIs) {
                if (requestAPI.startsWith(api)) {
                    hasPermission = true;
                    break;
                }
            }

            if (hasPermission) {
                System.out.println("ACCESS PERMISSION：" + requestAPI + " CONFIRMED");
                return true;
            } else {
                System.out.println("CURRENT USER HAS NO PERMISSION FOR API " + requestAPI);
                return false;
            }
        }
    }
}
