package com.portable.app.interfaces;

import java.util.List;

import com.portable.app.entity.Role;

public interface IRoleService {
    List<Role> listRoles();
    Role createRole(Role role);
    void updateRole(Role role);
    void deleteRole(Integer id);
}
