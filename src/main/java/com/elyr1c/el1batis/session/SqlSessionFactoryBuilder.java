package com.elyr1c.el1batis.session;

import com.elyr1c.el1batis.Configuration;
import com.elyr1c.el1batis.builder.xml.XMLConfigBuilder;
import com.elyr1c.el1batis.session.defaults.DefaultSqlSessionFactory;

import java.io.Reader;


public class SqlSessionFactoryBuilder {

    public SqlSessionFactory build(Reader reader) {
        XMLConfigBuilder xmlConfigBuilder = new XMLConfigBuilder(reader);
        return build(xmlConfigBuilder.parse());
    }

    public SqlSessionFactory build(Configuration config) {
        return new DefaultSqlSessionFactory(config);
    }

}