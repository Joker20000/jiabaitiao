package com.bootdo.Check.domain.dto;

import com.bootdo.Check.domain.TbChack;

import java.util.List;

public class TbChackDto {
    private String opinion;
    private String result;
    private String userId;
    private String userName;
    private TbChack tbChack;
    private List<TbChack> tbChacks;

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public TbChack getTbChack() {
        return tbChack;
    }

    public void setTbChack(TbChack tbChack) {
        this.tbChack = tbChack;
    }

    public List<TbChack> getTbChacks() {
        return tbChacks;
    }

    public void setTbChacks(List<TbChack> tbChacks) {
        this.tbChacks = tbChacks;
    }
}
