package com.tao.lumiadmin.service;

import com.tao.lumiadmin.dao.UserDAO;
import com.tao.lumiadmin.pojo.AdminRole;
import com.tao.lumiadmin.pojo.User;
// import com.tao.lumiadmin.entity.AdminRole;
// import com.tao.lumiadmin.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// 实际上是对 UserDAO 进行了二次封装
@Service
public class UserService {
    @Autowired
    UserDAO userDAO;
    @Autowired
    AdminRoleService adminRoleService;

    public List<User> list() {
        List<User> users =  userDAO.list();
        List<AdminRole> roles;
        for (User user : users) {
            roles = adminRoleService.listRolesByUser(user.getUsername());
            user.setRoles(roles);
        }
        return users;
    }

    public boolean isExist(String username) {
        User user = findByUsername(username);
        return null != user;
    }

    public User findByUsername(String username) {
        return userDAO.findByUsername(username);
    }

    public User get(String username, String password){
        // 通过用户名和密码查询数据库
        return userDAO.getByUsernameAndPassword(username, password);
    }

    public void addOrUpdate(User user) {
        userDAO.save(user);
    }
}
