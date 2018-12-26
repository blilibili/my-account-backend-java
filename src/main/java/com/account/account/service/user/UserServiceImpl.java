package com.account.account.service.user;

import com.account.account.Dao.RedisDao;
import com.account.account.model.WxLogin;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private RedisDao redis;



    @Override
    public HashMap Login(String code) throws IOException {
        RestTemplate restTemplate = new RestTemplate();

        String appid = "wx4c0383dda14ae1a4";
        String appSecret = "6a6afc93eca7f4e851919d42e42193fa";

        String url="https://api.weixin.qq.com/sns/jscode2session?appid="+appid+"&secret="+appSecret+"&js_code="+code+"&grant_type=authorization_code";
        String response = "";

        LinkedMultiValueMap body=new LinkedMultiValueMap();

        HttpHeaders headers = new HttpHeaders();
        HttpEntity entity = new HttpEntity(body, headers);

        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);


        ObjectMapper objectMapper = new ObjectMapper();
        //微信返回对象json 解析
        Map wxRes = objectMapper.readValue(responseEntity.getBody(), Map.class);

        //登陆数据模型
        WxLogin wxLogin = new WxLogin();
        wxLogin.setOpenId((String)wxRes.get("openid"));
        wxLogin.setSessionKey((String)wxRes.get("session_key"));

        //判断下是否有token
        String token;

        if(!redis.existValue("token")){
            //生成唯一标识符
            token = UUID.randomUUID().toString();
            redis.setKey("token", token , 1000 * 60 * 30);
        }else{
            token = redis.getValue("token");
        }

        //session 存入redis
        redis.setKey("openId",
                wxLogin.getOpenId(),
                1000 * 60 * 30);

        HashMap userInfo = new HashMap<>();

        userInfo.put("token" , token);
        userInfo.put("openid" , wxLogin.getOpenId());
        return userInfo;
    }
}
