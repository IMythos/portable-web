package com.portable.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portable.app.entity.Role;
import com.portable.app.interfaces.IRoleService;
import com.portable.app.repository.RoleRepository;

@Service
public class RoleServiceImpl implements IRoleService{

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<Role> listRoles() {
        return roleRepository.findAll();
    }
    
}
