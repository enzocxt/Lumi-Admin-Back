package com.tao.lumiadmin.service;

import com.tao.lumiadmin.dao.AdminMenuDAO;
import com.tao.lumiadmin.pojo.*;
// import com.tao.lumiadmin.entity.*;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
// import java.util.Iterator;
import java.util.List;
// import java.util.stream.Collectors;

@Service
public class AdminMenuService {
    @Autowired
    AdminMenuDAO adminMenuDAO;
    @Autowired
    UserService userService;
    @Autowired
    AdminUserRoleService adminUserRoleService;
    @Autowired
    AdminRoleMenuService adminRoleMenuService;

    public List<AdminMenu> getAllByParentId(int parentId) {
        return adminMenuDAO.findAllByParentId(parentId);
    }

    public List<AdminMenu> getMenusByCurrentUser() {
        System.out.println("------- getMenusByCurretnUser()");
        String username = SecurityUtils.getSubject().getPrincipal().toString();
        System.out.println("\n------- username:");
        System.out.println(username);
        User user = userService.findByUsername(username);
        System.out.println("\n------- user:");
        System.out.println(user);
        List<AdminUserRole> userRoleList = adminUserRoleService.listAllByUid(user.getId());
        System.out.println("\n------- userRoleList length:");
        System.out.println(userRoleList.size());
        List<AdminMenu> menus = new ArrayList<>();
        for (AdminUserRole userRole : userRoleList) {
            List<AdminRoleMenu> roleMenuList = adminRoleMenuService.findAllByRid(userRole.getRid());
            System.out.println("\n------- roleMenuList length:");
            System.out.println(roleMenuList.size());
            for (AdminRoleMenu roleMenu : roleMenuList) {
                // 增加防止多角色状态下菜单重复的逻辑
                AdminMenu menu = adminMenuDAO.findById(roleMenu.getMid());
                boolean isExist = false;
                for (AdminMenu m : menus) {
                    if (m.getId() == menu.getId()) {
                        isExist = true;
                    }
                }
                if (!isExist) {
                    menus.add(menu);
                }
            }
        }
        // 处理菜单项的结构
        // handleMenus(menus);
        return menus;
    }

    // public List<AdminMenu> getMenusByRoleId(int rid) {
    //     List<AdminMenu> menus = new ArrayList<>();
    //     List<AdminRoleMenu> rms = adminRoleMenuService.findAllByRid(rid);

    //     rms.forEach(rm -> menus.add(adminMenuDAO.findById(rm.getMid())));

    //     handleMenus(menus);
    //     return menus;
    // }

    // public void handleMenus(List<AdminMenu> menus) {
    //     menus.forEach(m -> {
    //         List<AdminMenu> children = getAllByParentId(m.getId());
    //         m.setChildren(children);
    //     });

    //     menus.removeIf(m -> m.getParentId() != 0);
    // }

}