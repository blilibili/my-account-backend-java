package com.account.account.model;

public class Pay {
    private int id;
    private String incoming;
    private String staging;
    private String debt;
    private String output;
    private String canuse;
    private String openid;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIncoming() {
        return incoming;
    }

    public void setIncoming(String incoming) {
        this.incoming = incoming;
    }

    public String getStaging() {
        return staging;
    }

    public void setStaging(String staging) {
        this.staging = staging;
    }

    public String getDebt() {
        return debt;
    }

    public void setDebt(String debt) {
        this.debt = debt;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public String getCanuse() {
        return canuse;
    }

    public void setCanuse(String canuse) {
        this.canuse = canuse;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }
}
