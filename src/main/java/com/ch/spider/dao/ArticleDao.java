package com.ch.spider.dao;

import com.ch.spider.entity.ArticleEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface ArticleDao<T> {
    List<ArticleEntity> resultqueryObject() ;
    void addResultEntity(T t);
}
