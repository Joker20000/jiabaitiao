package com.bootdo.Check.domain;

public class Checker {
    //信审编号
    private String chackno;
    //企业ID
    private int eid;
    //申请时间
    private String applytime;
    //终审时间
    private String lastinstancetime;
    //信审状态
    private String chackstate;
    //是否有效
    private String valid;

    public String getValid() {
        return valid;
    }

    public void setValid(String valid) {
        this.valid = valid;
    }

    public String getChackno() {
        return chackno;
    }

    public void setChackno(String chackno) {
        this.chackno = chackno;
    }

    public int getEid() {
        return eid;
    }

    public void setEid(int eid) {
        this.eid = eid;
    }

    public String getApplytime() {
        return applytime;
    }

    public void setApplytime(String applytime) {
        this.applytime = applytime;
    }

    public String getLastinstancetime() {
        return lastinstancetime;
    }

    public void setLastinstancetime(String lastinstancetime) {
        this.lastinstancetime = lastinstancetime;
    }

    public String getChackstate() {
        return chackstate;
    }

    public void setChackstate(String chackstate) {
        this.chackstate = chackstate;
    }
}
