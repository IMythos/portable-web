package com.portable.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Integer userId;
    private Integer roleId;
    private Integer employeeId;
    private String username;
    private String password;
    private boolean status;
}
