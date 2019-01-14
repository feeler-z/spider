package com.ch.spider.entity;

public class resultinfo {
    public String articleSourceUrl="";//文章链接
    public int typeID=0;//栏目的编号
    public String title="";//文章标题
    public String time="";//创建时间
    public String content="";//文章内容
    public String coverPicName="";//列表上的图片
    public String abstractInfo="";//文章简介

    public String getArticleSourceUrl() {
        return articleSourceUrl;
    }

    public void setArticleSourceUrl(String articleSourceUrl) {
        this.articleSourceUrl = articleSourceUrl;
    }

    public int getTypeID() {
        return typeID;
    }

    public void setTypeID(int typeID) {
        this.typeID = typeID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCoverPicName() {
        return coverPicName;
    }

    public void setCoverPicName(String coverPicName) {
        this.coverPicName = coverPicName;
    }

    public String getAbstractInfo() {
        return abstractInfo;
    }

    public void setAbstractInfo(String abstractInfo) {
        this.abstractInfo = abstractInfo;
    }
}
