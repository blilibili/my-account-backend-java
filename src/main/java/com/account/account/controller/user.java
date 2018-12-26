package com.account.account.controller;

//小程序用户模块

import com.account.account.service.user.Register;
import com.account.account.service.user.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;

@RestController
@RequestMapping("/account/user")
public class user {

    @Autowired
    private UserService userService;

    @Autowired
    private Register register;

    @GetMapping("/test")
    public String test(){
//        System.out.println("userRedis = " + valueOperations.get("hello"));
        return "hello world";
    }

    @PostMapping("/login")
    public HashMap Login(@RequestParam("code") String code) throws IOException {
        return userService.Login(code);
    }

    @PostMapping("/register")
    public HashMap Register(
            @RequestParam("nickname")String nickname ,
            @RequestParam("avater")String avater ,
            @RequestParam("openid")String openid ,
            @RequestParam(value = "phone" , required = false , defaultValue = "")String phone ,
            @RequestParam(value = "email" , required = false , defaultValue = "")String email
    ){
        int status = register.registerUser(nickname, avater, phone, email , openid);
        HashMap<String , String> result = new HashMap<>();
        if(status == 0){
            result.put("status" , "201");
            result.put("msg" , "数据插入错误");
        }else{
            result.put("status" , "200");
            result.put("msg" , "用户注册成功");
        }

        return result;
    }
}
