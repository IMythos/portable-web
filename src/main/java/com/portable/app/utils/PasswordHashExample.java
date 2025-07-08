package com.portable.app.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordHashExample {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String password = encoder.encode("jose123");
        String password2 = encoder.encode("carlos123");
        String password3 = encoder.encode("marco123");
        System.out.println(password);  
        System.out.println(password2); 
        System.out.println(password3); 
    } 
}
