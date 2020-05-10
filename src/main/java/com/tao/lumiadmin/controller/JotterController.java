package com.tao.lumiadmin.controller;

import com.tao.lumiadmin.pojo.JotterArticle;
// import com.tao.lumiadmin.entity.JotterArticle;
import com.tao.lumiadmin.result.Result;
import com.tao.lumiadmin.result.ResultFactory;
import com.tao.lumiadmin.service.JotterArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class JotterController {
    @Autowired
    JotterArticleService jotterArticleService;

    @CrossOrigin
    @PostMapping("api/admin/content/article")
    public Result saveArticle(@RequestBody @Valid JotterArticle article) {
        jotterArticleService.addOrUpdate(article);
        return ResultFactory.buildSuccessResult("保存成功");
    }

    @GetMapping("/api/article/{size}/{page}")
    public Result listArticles(@PathVariable("size") int size, @PathVariable("page") int page) {
        return ResultFactory.buildSuccessResult(jotterArticleService.list(page - 1, size));
    }

    @GetMapping("/api/article/{id}")
    public Result getOneArticle(@PathVariable("id") int id) {
        return ResultFactory.buildSuccessResult(jotterArticleService.findById(id));
    }

    @DeleteMapping("/api/admin/content/article/{id}")
    public Result deleteArticle(@PathVariable("id") int id) {
        jotterArticleService.delete(id);
        return ResultFactory.buildSuccessResult("删除成功");
    }
}