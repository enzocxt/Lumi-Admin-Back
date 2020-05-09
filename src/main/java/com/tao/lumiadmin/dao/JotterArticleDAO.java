package com.tao.lumiadmin.dao;

import com.tao.lumiadmin.pojo.JotterArticle;
// import com.tao.lumiadmin.entity.JotterArticle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JotterArticleDAO  extends JpaRepository<JotterArticle,Integer> {
    JotterArticle findById(int id);
}