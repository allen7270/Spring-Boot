package com.springboot.project.role.model.dao;

import com.springboot.project.role.model.bo.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDao extends JpaRepository<Role, String> {
    Role findByRoleName(String roleName);
}
