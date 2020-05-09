package com.tao.lumiadmin.dao;

import com.tao.lumiadmin.pojo.AdminUserRole;
// import com.tao.lumiadmin.entity.AdminUserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdminUserRoleDAO extends JpaRepository<AdminUserRole, Integer> {
    List<AdminUserRole> findAllByUid(int uid);
    void deleteAllByUid(int uid);
}