package com.portable.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.portable.app.entity.Role;
import com.portable.app.interfaces.IRoleService;
import com.portable.app.repository.RoleRepository;

@Service
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Role> listRoles() {
        return roleRepository.listRolesSP();
    }

    @Override
    @Transactional
    public Role createRole(Role role) {
        Integer newId = roleRepository.insertRole(
            role.getRoleName()
        );
        role.setIdRole(newId);
        return role;
    }

    @Override
    @Transactional
    public void updateRole(Role role) {
        roleRepository.updateRole(
            role.getIdRole(),
            role.getRoleName()
        );
    }

    @Override
    @Transactional
    public void deleteRole(Integer roleId) {
        roleRepository.deleteRole(roleId);
    }
}
