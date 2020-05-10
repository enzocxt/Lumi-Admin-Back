package com.tao.lumiadmin.service;

import com.tao.lumiadmin.pojo.*;
// import com.tao.lumiadmin.entity.*;
import com.tao.lumiadmin.dao.AdminPermissionDAO;
import com.tao.lumiadmin.dao.AdminRolePermissionDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashSet;

@Service
public class AdminPermissionService {
    @Autowired
    AdminPermissionDAO adminPermissionDAO;
    @Autowired
    AdminRolePermissionDAO adminRolePermissionDAO;
    @Autowired
    AdminUserRoleService adminUserRoleService;
    @Autowired
    AdminRoleService adminRoleService;
    @Autowired
    AdminRolePermissionService adminRolePermissionService;
    @Autowired
    UserService userService;

    public AdminPermission findById(int id) {
        return adminPermissionDAO.findById(id);
    }

    public List<AdminPermission> list() {return adminPermissionDAO.findAll();}

    /**
     * Determine whether client requires permission when requests
     * a certain API.
     * @param requestAPI API requested by client
     * @return true when requestAPI is found in the DB
     */
    public boolean needFilter(String requestAPI) {
        List<AdminPermission> ps = adminPermissionDAO.findAll();
        System.out.println("|------- request api: " + requestAPI);
        for (AdminPermission p : ps) {
            // 这里我们进行前缀匹配，拥有父权限就拥有所有子权限
            if (requestAPI.startsWith(p.getUrl())) {
                System.out.println(p.getUrl());
                System.out.println("|------- prefix matched");
                return true;
            }
        }
        return false;
    }
    
    public List<AdminPermission> listPermsByRole(int rid) {
        List<AdminRolePermission> rps = adminRolePermissionService.findAllByRid(rid);
        List<AdminPermission> perms = new ArrayList<>();
        for (AdminRolePermission rp : rps) {
            perms.add(adminPermissionDAO.findById(rp.getPid()));
        }
        return perms;
    }

    public Set<String> listPermissionURLsByUser(String username) {
        List<AdminRole> roles = adminRoleService.listRolesByUser(username);
        Set<String> URLs = new HashSet<>();

        for (AdminRole role : roles) {
            List<AdminRolePermission> rps = adminRolePermissionService.findAllByRid(role.getId());
            for (AdminRolePermission rp : rps) {
                URLs.add(adminPermissionDAO.findById(rp.getPid()).getUrl());
            }
        }    
        return URLs;
    }
  
}