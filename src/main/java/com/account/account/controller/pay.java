package com.account.account.controller;

import com.account.account.model.Pay;
import com.account.account.service.pay.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Converter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;

@RestController
@RequestMapping("/pay")
public class pay {
    @Autowired
    PayService payService;

    @RequestMapping(value = "/queryAll" , method = RequestMethod.GET)
    public HashMap<String , String> getAllPayCalculate(
            @RequestParam("openId")String openId
    ){
        HashMap<String , String> result = new HashMap<>();
        Pay pays = payService.queryAccountObj(openId);
        if (pays == null) {
            result.put("status" , "200");
            result.put("msg" , "没有数据");
            result.put("incoming" , "");
            result.put("staging" , "");
            result.put("debt" , "");
            result.put("output" ,"");
            result.put("canuse" , "");
            return result;
        }

        result.put("status" , "200");
        result.put("msg" , "数据分析成功");
        result.put("incoming" , pays.getIncoming());
        result.put("staging" , pays.getStaging());
        result.put("debt" , pays.getDebt());
        result.put("output" , pays.getOutput());
        result.put("canuse" , pays.getCanuse());
        return result;
    }

    @RequestMapping(value = "/info" , method = RequestMethod.POST)
    public HashMap<String, String> getCanUsedIncome(@RequestParam("incoming") String incoming ,
                                                    @RequestParam("staging")String staging ,
                                                    @RequestParam("debt")String debt ,
                                                    @RequestParam("output")String output ,
                                                    @RequestParam("openId")String openId
                                   ){
        int changeIncoming = Integer.parseInt(incoming);
        int changeStaging = Integer.parseInt(staging);
        int changeDebt = Integer.parseInt(debt);
        int changeOutput = Integer.parseInt(output);
        int calCanuse = changeIncoming - (changeStaging + changeDebt + changeOutput);
        String canuse = String.valueOf(calCanuse);
        int payId = payService.queryAccountId(openId);

        if(payId == 0){
            int status = payService.saveAccount(incoming , staging , debt , output , canuse , openId);
            HashMap<String , String> result = new HashMap<>();
            if(status == 0){
                result.put("status" , "201");
                result.put("msg" , "数据插入错误");
            }else{
                result.put("status" , "200");
                result.put("msg" , "数据分析成功");
                result.put("canuse" , canuse);
            }
            return result;
        }else{
            HashMap<String , String> result = new HashMap<>();
            payService.updateAccount(incoming , staging , debt , output , canuse , openId);
            Pay pays = payService.queryAccountObj(openId);
            result.put("status" , "200");
            result.put("msg" , "数据分析成功");
            result.put("incoming" , pays.getIncoming());
            result.put("staging" , pays.getStaging());
            result.put("debt" , pays.getDebt());
            result.put("output" , pays.getOutput());
            result.put("canuse" , pays.getCanuse());
            return result;
        }
    }
}
