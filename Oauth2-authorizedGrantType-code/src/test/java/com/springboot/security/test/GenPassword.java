package com.springboot.security.test;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class GenPassword {

    public static void main(String[] args){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String password = bCryptPasswordEncoder.encode("123456");
        System.out.println("{bcrypt}"+password);
    }
}
