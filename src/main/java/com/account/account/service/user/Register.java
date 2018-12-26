package com.account.account.service.user;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface Register {

    @Insert("INSERT INTO userinfo(nickname,avater,phone , email , openid) VALUES(#{nickname}, #{avater}, #{phone}, #{email} , #{openid})")
    int registerUser(@Param("nickname")String nickname ,
                     @Param("avater")String avater ,
                     @Param("phone")String phone ,
                     @Param("email")String email ,
                     @Param("openid")String openid
    );

}
