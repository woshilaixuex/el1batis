package com.elyr1c.el1batis.session.defaults;

import com.elyr1c.el1batis.Configuration;
import com.elyr1c.el1batis.mapper.MapperRegister;
import com.elyr1c.el1batis.session.SqlSession;
import com.elyr1c.el1batis.session.SqlSessionFactory;

/**
 * @ClassName DefaultSqlSessionFactory
 * @Description TODO
 * @Author Elyr1c
 * @Date 2025/6/25 21:42
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory {
    private final Configuration configuration;

    public DefaultSqlSessionFactory(Configuration config) {
        this.configuration = config;
    }

    @Override
    public SqlSession openSqlSession() {
        return new DefaultSqlSession(configuration);
    }
}
