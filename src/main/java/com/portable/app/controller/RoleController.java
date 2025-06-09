package com.portable.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.portable.app.entity.Role;
import com.portable.app.service.RoleServiceImpl;

@RestController
@RequestMapping("/v1/api")
public class RoleController {

    @Autowired
    private RoleServiceImpl roleServiceImpl;

    @GetMapping("/roles")
    public List<Role> getRoles() {
        return roleServiceImpl.listRoles();
    }
}
