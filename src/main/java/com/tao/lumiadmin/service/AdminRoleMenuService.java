package com.tao.lumiadmin.service;

import com.tao.lumiadmin.dao.AdminRoleMenuDAO;
import com.tao.lumiadmin.pojo.AdminRoleMenu;
// import com.tao.lumiadmin.entity.AdminRoleMenu;
import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
// import org.springframework.transaction.annotation.Transactional;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class AdminRoleMenuService {
    @Autowired
    AdminRoleMenuDAO adminRoleMenuDAO;

    public List<AdminRoleMenu> findAllByRid(int rid) {
        return adminRoleMenuDAO.findAllByRid(rid);
    }

}