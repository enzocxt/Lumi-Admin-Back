package com.tao.lumiadmin.controller;

import com.tao.lumiadmin.pojo.AdminMenu;
// import com.tao.lumiadmin.entity.AdminMenu;
import com.tao.lumiadmin.service.AdminMenuService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.List;

@RestController
public class MenuController {
    @Autowired
    AdminMenuService adminMenuService;

    @CrossOrigin
    @GetMapping("/api/menu")
    public List<AdminMenu> menu() {
        System.out.println("------- in api/menu -------");
        List<AdminMenu> menus = adminMenuService.getMenusByCurrentUser();
        
        System.out.println("------- menu length:");
        System.out.println(menus.size());
        for (AdminMenu menu : menus) {
            menu.setChildren(adminMenuService.getAllByParentId(menu.getId()));
        }

        System.out.println("------- menu length:");
        System.out.println(menus.size());

        Iterator<AdminMenu> iterator = menus.iterator();
        while (iterator.hasNext()) {
            AdminMenu menu = iterator.next();
            if (menu.getParentId() != 0) {
                iterator.remove();
            }
        }
        return menus;
    }

}