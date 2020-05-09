package com.tao.lumiadmin.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity // javax.persistence.xx
@Table(name = "user") // javax.persistence.xx
@ToString
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
// 因为是做前后端分离，而前后端数据交互用的是 json 格式。 
// 那么 User 对象就会被转换为 json 数据。 
// 而本项目使用 jpa 来做实体类的持久化，jpa 默认会使用 hibernate, 
// 在 jpa 工作过程中，就会创造代理类来继承 User ，
// 并添加 handler 和 hibernateLazyInitializer 这两个无须 json 化的属性，
// 所以这里需要用 JsonIgnoreProperties 把这两个属性忽略掉。
public class User {

    @Id // javax.persistence.xx
    @GeneratedValue(strategy = GenerationType.IDENTITY) // javax.persistence.xx
    @Column(name = "id")
    private int id;
    
    @NotEmpty(message = "用户名不能为空")
    private String username;
    private String password;
    private String salt;
    private String name;
    private String phone;

    @Email(message = "情输入正确的邮箱")
    private String email;
    private boolean enabled;
    
    // 为了前后端传递参数更方便，直接在查询用户信息时就把角色信息查询出来
    @Transient  // 由于数据库中并没有相应定义，所以要加上 @Transient 注释
    private List<AdminRole> roles;

}

