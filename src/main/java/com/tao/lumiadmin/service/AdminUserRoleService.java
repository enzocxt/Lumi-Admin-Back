package com.tao.lumiadmin.service;

import com.tao.lumiadmin.dao.AdminUserRoleDAO;
import com.tao.lumiadmin.pojo.AdminRole;
import com.tao.lumiadmin.pojo.AdminUserRole;
// import com.tao.lumiadmin.entity.AdminRole;
// import com.tao.lumiadmin.entity.AdminUserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdminUserRoleService {
    @Autowired
    AdminUserRoleDAO adminUserRoleDAO;

    public List<AdminUserRole> listAllByUid(int uid) {
        return adminUserRoleDAO.findAllByUid(uid);
    }

    @Modifying
    @Transactional  // 因为执行了删除操作，要加上该注释开启事务，以保证数据一致性
    public void saveRoleChanges(int uid, List<AdminRole> roles) {
        adminUserRoleDAO.deleteAllByUid(uid);
        for (AdminRole role : roles) {
            AdminUserRole ur = new AdminUserRole();
            ur.setUid(uid);
            ur.setRid(role.getId());
            adminUserRoleDAO.save(ur);
        }
    }
}