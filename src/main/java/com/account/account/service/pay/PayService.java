package com.account.account.service.pay;

import com.account.account.model.Pay;
import org.apache.ibatis.annotations.*;

@Mapper
public interface PayService {

    @Insert("INSERT INTO account(incoming,staging,debt , output , canuse , openid) VALUES(#{incoming}, #{staging}, #{debt}, #{output}, #{canuse} , #{openId})")
    int saveAccount(@Param("incoming")String incoming,
                     @Param("staging")String staging ,
                     @Param("debt")String debt ,
                     @Param("output")String output ,
                     @Param("canuse")String canuse ,
                     @Param("openId")String openId
    );

    @Update("UPDATE account SET incoming = #{incoming} , staging = #{staging} , debt = #{debt} , output = #{output} , canuse = #{canuse}")
    int updateAccount(@Param("incoming")String incoming,
                      @Param("staging")String staging ,
                      @Param("debt")String debt ,
                      @Param("output")String output ,
                      @Param("canuse")String canuse ,
                      @Param("openId")String openId
    );

    @Select("SELECT COUNT(*) FROM account where openid = #{openId}")
    int queryAccountId(@Param("openId")String openId);

    @Select("SELECT * FROM account where openid = #{openId}")
    Pay queryAccountObj(@Param("openId")String openId);
}
