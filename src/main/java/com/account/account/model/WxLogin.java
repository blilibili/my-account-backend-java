package com.account.account.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 *
 * login 登陆的模型类
 */

@JsonIgnoreProperties({ "openId", "sessionKey" })
public class WxLogin {
    private String openId;
    private String sessionKey;

    public WxLogin() {
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }
}
