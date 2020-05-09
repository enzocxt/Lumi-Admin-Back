package com.tao.lumiadmin.dao;

import com.tao.lumiadmin.pojo.Book;
import com.tao.lumiadmin.pojo.Category;
// import com.tao.lumiadmin.entity.Book;
// import com.tao.lumiadmin.entity.Category;
// import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookDAO extends JpaRepository<Book,Integer> {
    List<Book> findAllByCategory(Category category);
    // 两个参数分别对应标题或作者（必须两个参数都写？）
    List<Book> findAllByTitleLikeOrAuthorLike(String keyword1, String keyword2);
}
