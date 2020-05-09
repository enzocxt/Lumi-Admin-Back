package com.tao.lumiadmin.service;

import com.tao.lumiadmin.dao.AdminRolePermissionDAO;
import com.tao.lumiadmin.pojo.AdminPermission;
import com.tao.lumiadmin.pojo.AdminRolePermission;
// import com.tao.lumiadmin.entity.AdminPermission;
// import com.tao.lumiadmin.entity.AdminRolePermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdminRolePermissionService {
      @Autowired
      AdminRolePermissionDAO adminRolePermissionDAO;

      List<AdminRolePermission> findAllByRid(int rid) {
          return adminRolePermissionDAO.findAllByRid(rid);
      }

      @Modifying
      @Transactional
      public void savePermChanges(int rid, List<AdminPermission> perms) {
          adminRolePermissionDAO.deleteAllByRid(rid);
          for (AdminPermission perm : perms) {
              AdminRolePermission rp = new AdminRolePermission();
              rp.setRid(rid);
              rp.setPid(perm.getId());
              adminRolePermissionDAO.save(rp);
          }
      }
}