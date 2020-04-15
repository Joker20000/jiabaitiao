package com.bootdo.Utils;

public enum UserChannelType {


    JF("嘉福平台","01"),
    JX("嘉薪平台","02"),
    JF_BT_NAME("福利预支","嘉福平台"),
    JX_BT_NAME("工资预支","嘉薪平台"),
    NONE("","");

    private String type;
    private String name;
    UserChannelType(String name, String type){
        this.name = name;
        this.type = type;
    }
    public String getType(){
        return this.type;
    }
    public String getName(){
        return this.name;
    }

    public static String getName(String type){
        for (UserChannelType t: UserChannelType.values()) {
            if (t.getType().equals(type)){
                return t.getName();
            }
        }
        return UserChannelType.JF.getType();
    }
}
