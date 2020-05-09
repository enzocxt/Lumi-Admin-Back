package com.tao.lumiadmin.dao;

import com.tao.lumiadmin.pojo.AdminRole;
// import com.tao.lumiadmin.entity.AdminRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRoleDAO extends JpaRepository<AdminRole, Integer> {
    AdminRole findById(int id);
}