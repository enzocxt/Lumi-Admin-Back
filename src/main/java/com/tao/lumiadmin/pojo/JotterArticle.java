package com.tao.lumiadmin.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@Entity
@Table(name = "jotter_article")
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })
public class JotterArticle {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @NotNull(message = "id 不能为 null")
    int id;
    
    @NotEmpty(message = "文章标题不能为空")
    String articleTitle;
    String articleContentHtml;
    String articleContentMd;
    String articleAbstract;
    String articleCover;
    Date articleDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public String getArticleContentHtml() {
      return articleContentHtml;
  }

  public void setArticleContentHtml(String articleContentHtml) {
      this.articleContentHtml = articleContentHtml;
  }

  public String getArticleContentMd() {
    return articleContentMd;
}

public void setArticleContentMd(String articleContentMd) {
    this.articleContentMd = articleContentMd;
}

public String getArticleAbstract() {
  return articleAbstract;
}

public void setArticleAbstract(String articleAbstract) {
  this.articleAbstract = articleAbstract;
}

public String getArticleCover() {
  return articleCover;
}

public void setArticleCover(String articleCover) {
  this.articleCover = articleCover;
}

public Date getArticleDate() {
  return articleDate;
}

public void setArticleDate(Date articleDate) {
  this.articleDate = articleDate;
}

}