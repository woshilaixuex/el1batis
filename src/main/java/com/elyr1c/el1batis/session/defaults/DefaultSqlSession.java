package com.elyr1c.el1batis.session.defaults;

import com.elyr1c.el1batis.Configuration;
import com.elyr1c.el1batis.mapper.MapperRegister;
import com.elyr1c.el1batis.mapping.MappedStatement;
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
    private Configuration configuration;
    public DefaultSqlSession(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public <T> T selectOne(String statement) {
        return (T) ("你被代理了！" + statement);
    }

    @Override
    public <T> T selectOne(String statement, Object parameter) {
        MappedStatement mappedStatement = configuration.getMappedStatement(statement);
        return (T) ("你被代理了！" + "\n方法：" + statement + "\n入参：" + parameter + "\n待执行SQL：" + mappedStatement.getSql());
    }

    @Override
    public Configuration getConfiguration() {
        return configuration;
    }

    @Override
    public <T> T getMapper(Class<T> type) {
        return configuration.getMapper(type, this);
    }
}
