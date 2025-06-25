package com.elyr1c.el1batis.session.defaults;

import com.elyr1c.el1batis.mapper.MapperRegister;
import com.elyr1c.el1batis.session.SqlSession;

/**
 * @ClassName DefultSqlSession
 * @Description TODO
 * @Author Elyr1c
 * @Date 2025/6/25 21:40
 */
public class DefaultSqlSession implements SqlSession {
    /**
     * 映射器注册机
     */
    private MapperRegister mapperRegister;
    public DefaultSqlSession(MapperRegister mapperRegistry) {
        this.mapperRegister = mapperRegistry;
    }

    @Override
    public <T> T selectOne(String statement) {
        return (T) ("你被代理了！" + statement);
    }

    @Override
    public <T> T selectOne(String statement, Object parameter) {
        return (T) ("你被代理了！" + "方法：" + statement + " 入参：" + parameter);
    }

    @Override
    public <T> T getMapper(Class<T> type) {
        return mapperRegister.getMapper(type, this);
    }
}
