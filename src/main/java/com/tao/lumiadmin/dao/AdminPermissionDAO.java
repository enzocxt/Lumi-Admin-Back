package com.tao.lumiadmin.dao;

import com.tao.lumiadmin.pojo.AdminPermission;
// import com.tao.lumiadmin.entity.AdminPermission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminPermissionDAO extends JpaRepository<AdminPermission, Integer> {
    AdminPermission findById(int id);
}