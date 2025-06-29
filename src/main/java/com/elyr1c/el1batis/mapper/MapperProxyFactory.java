package com.elyr1c.el1batis.mapper;

import com.elyr1c.el1batis.session.SqlSession;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName MapperProxyFactory
 * @Description MapperProxy factory class
 * @Author Elyr1c
 * @Date 2025/6/24 17:20
 */
public class MapperProxyFactory<T> {
    private final Class<T> mapperInterface;
    private Map<Method, MapperMethod> methodCache = new ConcurrentHashMap<Method, MapperMethod>();
    public MapperProxyFactory(Class<T> mapperInterface) {
        this.mapperInterface = mapperInterface;
    }
    public Map<Method, MapperMethod> getMethodCache() {
        return methodCache;
    }
    @SuppressWarnings("unchecked")
    public T getMapperInstance(SqlSession sqlSession) {
        MapperProxy<T> mapperProxy = new MapperProxy<>(sqlSession, mapperInterface,methodCache);
        return (T) Proxy.newProxyInstance(
                mapperInterface.getClassLoader(),
                new Class[]{mapperInterface},
                mapperProxy);
    }
}
