package com.elyr1c.el1batis.mapper;

import com.elyr1c.el1batis.session.SqlSession;

import java.lang.reflect.Proxy;
import java.util.Map;

/**
 * @ClassName MapperProxyFactory
 * @Description MapperProxy factory class
 * @Author Elyr1c
 * @Date 2025/6/24 17:20
 */
public class MapperProxyFactory<T> {
    private final Class<T> mapperInterface;
    public MapperProxyFactory(Class<T> mapperInterface) {
        this.mapperInterface = mapperInterface;
    }
    @SuppressWarnings("unchecked")
    public T getMapperInstance(SqlSession sqlSession) {
        MapperProxy<T> mapperProxy = new MapperProxy<>(sqlSession, mapperInterface);
        return (T) Proxy.newProxyInstance(
                mapperInterface.getClassLoader(),
                new Class[]{mapperInterface},
                mapperProxy);
    }
}
