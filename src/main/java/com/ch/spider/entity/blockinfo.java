package com.ch.spider.entity;

public class blockinfo {
    public String articleSourceUrl="";//文章链接
    public String coverPicUrl="";//文章列表上的图片链接
    public String abstractInfo="";//文章简介

    public String getArticleSourceUrl() {
        return articleSourceUrl;
    }

    public void setArticleSourceUrl(String articleSourceUrl) {
        this.articleSourceUrl = articleSourceUrl;
    }

    public String getCoverPicUrl() {
        return coverPicUrl;
    }

    public void setCoverPicUrl(String coverPicUrl) {
        this.coverPicUrl = coverPicUrl;
    }

    public String getAbstractInfo() {
        return abstractInfo;
    }

    public void setAbstractInfo(String abstractInfo) {
        this.abstractInfo = abstractInfo;
    }
}
