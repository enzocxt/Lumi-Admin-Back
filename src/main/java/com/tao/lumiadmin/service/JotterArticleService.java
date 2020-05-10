package com.tao.lumiadmin.service;

import com.tao.lumiadmin.dao.JotterArticleDAO;
import com.tao.lumiadmin.pojo.JotterArticle;
// import com.tao.lumiadmin.entity.JotterArticle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class JotterArticleService {
    @Autowired
    JotterArticleDAO jotterArticleDAO;

    public Page list(int page, int size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        System.out.println("\n------- JotterArticleService.list()");
        // PageRequest: 
        Pageable pageable = PageRequest.of(page, size, sort);
        System.out.println(pageable);
        return jotterArticleDAO.findAll(pageable);
    }

    public JotterArticle findById(int id) {
        return jotterArticleDAO.findById(id);
    }

    public void addOrUpdate(JotterArticle article) {
        jotterArticleDAO.save(article);
    }

    public void delete(int id) {
        jotterArticleDAO.deleteById(id);
    }

}