package com.portable.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.portable.app.dto.UserDto;
import com.portable.app.entity.User;
import com.portable.app.service.UserServiceImpl;

@RestController
@RequestMapping("/v1/api/users")
public class UserController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @GetMapping("/list")
    @PreAuthorize("hasAnyRole('ADMINISTRADOR')")
    public List<User> getUsersList() {
        return userServiceImpl.listUsers();
    }

    @PostMapping("/create")
    @PreAuthorize("hasAnyRole('ADMINISTRADOR')")
    private ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        try {
            UserDto createdUser = userServiceImpl.createUser(userDto);
            return ResponseEntity.ok(createdUser);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAnyRole('ADMINISTRADOR')")
    public ResponseEntity<Void> updateUser(@PathVariable Integer id, @RequestBody UserDto userDto) {
        try {
            userDto.setUserId(id);
            userServiceImpl.updateUser(userDto);
            return ResponseEntity.ok().build();
        } catch (Exception error) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyRole('ADMINISTRADOR')")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        try {
            userServiceImpl.deleteUser(id);
            return ResponseEntity.ok().build();
        } catch (Exception error) {
            return ResponseEntity.badRequest().build();
        }
    }
}
