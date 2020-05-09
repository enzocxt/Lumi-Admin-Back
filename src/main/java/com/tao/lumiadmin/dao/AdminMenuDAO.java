package com.tao.lumiadmin.dao;

import com.tao.lumiadmin.pojo.AdminMenu;
// import com.tao.lumiadmin.entity.AdminMenu;
// import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AdminMenuDAO extends JpaRepository<AdminMenu, Integer> {
    // 通过 username 字段查询到对应的行，并返回给 User 类
    AdminMenu findById(int id);
    List<AdminMenu> findAllByParentId(int parentId);
}