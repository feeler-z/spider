package com.ch.spider.dao;

import com.ch.spider.entity.ArticleEntity;
import com.ch.spider.entity.MemberEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberDao<T> {
    MemberEntity allqueryObject(String salt) ;
    List<MemberEntity>  resultqueryObject() ;
    void addResultEntity(T t);
    void update(T t);

}
