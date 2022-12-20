package com.cwuom.botchat;

public class ChatList {

    // 封裝模板的数据
    private String title;
    private String sub;
    private Integer imgId;
    private Boolean iTB;

    // setter、getter方法集
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }

    public Integer getImgId() {
        return imgId;
    }

    public void setImgId(Integer imgId) {
        this.imgId = imgId;
    }

    public Boolean getiTB() {
        return iTB;
    }

    public void setITB(Boolean iTB) {
        this.iTB = iTB;
    }
}