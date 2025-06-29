package com.elyr1c.el1batis;

import com.elyr1c.el1batis.common.lang.ClassScanner;
import com.elyr1c.el1batis.mapper.MapperRegister;
import com.elyr1c.el1batis.mapping.MappedStatement;
import com.elyr1c.el1batis.session.SqlSession;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName Configuration
 * @Description TODO
 * @Author Elyr1c
 * @Date 2025/6/25 22:37
 */
public class Configuration {
    protected MapperRegister mapperRegister = new MapperRegister(this);
    // sql cache
    protected final Map<String, MappedStatement> mappedStatements = new HashMap<>();

    public void addMappers(String packageName){
        mapperRegister.addMappers(packageName);
    }
    public <T> void addMapper(Class<T> type) {
        mapperRegister.addMapper(type);
    }

    public <T> T getMapper(Class<T> type, SqlSession sqlSession) {
        return mapperRegister.getMapper(type, sqlSession);
    }
    public boolean hasMapper(Class<?> type) {
        return mapperRegister.hasMapper(type);
    }

    public void addMappedStatement(MappedStatement ms) {
        mappedStatements.put(ms.getId(), ms);
    }

    public MappedStatement getMappedStatement(String id) {
        return mappedStatements.get(id);
    }

//    private static class Holder{
//        private static final Configuration configuration = new Configuration();
//    }
//    public static Configuration getConfigurationInfo(){
//        return Holder.configuration;
//    }

}
