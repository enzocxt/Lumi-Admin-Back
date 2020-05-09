package com.tao.lumiadmin.dao;

import com.tao.lumiadmin.pojo.User;
// import com.tao.lumiadmin.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserDAO extends JpaRepository<User,Integer> {
    // 通过 username 字段查询到对应的行，并返回给 User 类
    User findByUsername(String username);
    User getByUsernameAndPassword(String username,String password);
    // 自定义查询语句，选择指定字段
    // 这是 JPQL 的写法，也可以写原生的 SQL 语句，指定 nativeQuery=True 即可
    @Query(value = "select new User(u.id,u.username,u.name,u.phone,u.email,u.enabled) from User u")
    List<User> list();
}
