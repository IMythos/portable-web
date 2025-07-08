package com.portable.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.portable.app.dto.UserDto;
import com.portable.app.entity.User;
import com.portable.app.interfaces.IUserService;
import com.portable.app.repository.UserRepository;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public List<User> listUsers() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public UserDto createUser(UserDto userDto) {
        Integer newId = userRepository.insertUser(
            userDto.getUsername(),
            userDto.getPassword(),
            userDto.isStatus(),
            userDto.getRoleId(),
            userDto.getEmployeeId()
        );

        userDto.setUserId(newId);
        return userDto;
    }

    @Override
    @Transactional
    public void updateUser(UserDto userDto) {
        userRepository.updateUser(
            userDto.getUserId(),
            userDto.getUsername(),
            userDto.getPassword(),
            userDto.isStatus(),
            userDto.getRoleId(),
            userDto.getEmployeeId()
        );
    }

    @Override
    @Transactional
    public void deleteUser(Integer userId) {
        userRepository.deleteUser(userId);
    }
}
