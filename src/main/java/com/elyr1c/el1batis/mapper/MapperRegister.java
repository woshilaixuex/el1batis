package com.elyr1c.el1batis.mapper;


import com.elyr1c.el1batis.common.lang.ClassScanner;
import com.elyr1c.el1batis.session.SqlSession;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @ClassName MapperRegister
 * @Description TODO
 * @Author Elyr1c
 * @Date 2025/6/25 14:27
 */
public class MapperRegister {
    private final Map<Class<?>, MapperProxyFactory<?>> knownMappers = new HashMap<>();
    public<T> T getMapper(Class<T> type, SqlSession sqlSession) {
        final MapperProxyFactory<T> mapperProxyFactory = (MapperProxyFactory<T>) knownMappers.get(type);
        if (mapperProxyFactory == null) {
            throw new RuntimeException("Error getting mapperProxyFactory. Causer: type " + type.getName() + " is not registered");
        }
        try {
            return mapperProxyFactory.getMapperInstance(sqlSession);
        }catch (Exception e) {
            throw new RuntimeException("Error getting mapper instance. Cause: " + e, e);
        }
    }
    public  <T> void addMapper(Class<T> type) {
        if (type.isInterface()) {
            if (hasMapper(type)){
                throw new RuntimeException("Error add mapper type. Cause: type "+ type.getName() + "is already known to the MapperRegistry." );
            }
            knownMappers.put(type, new MapperProxyFactory<>(type));
        }
    }
    public void addMappers(String packageName) throws IOException {
        Set<Class<?>> mapperSet = ClassScanner.scanPackage(packageName);
        for (Class<?> mapperClass : mapperSet) {
            addMapper(mapperClass);
        }
    }
    public <T> boolean hasMapper(Class<T> type) {
        return knownMappers.containsKey(type);
    }
}
