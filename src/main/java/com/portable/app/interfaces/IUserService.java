package com.portable.app.interfaces;

import java.util.List;

import com.portable.app.dto.UserDto;
import com.portable.app.entity.User;

public interface IUserService {
    List<User> listUsers();
    UserDto createUser(UserDto userDto);
    void updateUser(UserDto userDto);
    void deleteUser(Integer userId);
}
