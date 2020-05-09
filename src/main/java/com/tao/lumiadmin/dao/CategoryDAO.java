package com.tao.lumiadmin.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tao.lumiadmin.pojo.Category;
// import com.tao.lumiadmin.entity.Category;

public interface CategoryDAO extends JpaRepository<Category, Integer> {

}
