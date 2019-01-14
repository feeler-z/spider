package com.ch.spider.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DataQuickDao<T> {
    void addResultEntity(T t);
}
