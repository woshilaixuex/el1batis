package com.elyr1c.el1batis.mapper;

import com.elyr1c.el1batis.Configuration;
import com.elyr1c.el1batis.mapping.MappedStatement;
import com.elyr1c.el1batis.mapping.SqlCommandType;
import com.elyr1c.el1batis.session.SqlSession;

import java.lang.reflect.Method;

/**
 * @ClassName MapperMethod
 * @Description TODO
 * @Author Elyr1c
 * @Date 2025/6/28 23:09
 */
public class MapperMethod {
    private final SqlCommand command;
    public MapperMethod(Class<?> mapperInterface,Method method,Configuration configuration){
        this.command = new SqlCommand(configuration,mapperInterface,method);
    }

    public Object execute(SqlSession sqlSession, Object[] args) {
        Object result = null;
        switch (command.getType()) {
            case INSERT:
                break;
            case DELETE:
                break;
            case UPDATE:
                break;
            case SELECT:
                result = sqlSession.selectOne(command.getName(), args);
                break;
            default:
                throw new RuntimeException("Unknown execution method for: " + command.getName());
        }
        return result;
    }

    public static class SqlCommand {
        private final String name;
        private final SqlCommandType type;
        public SqlCommand(Configuration configuration, Class<?> mapperInterface, Method method) {
            String statementName = mapperInterface.getName() + "." + method.getName();
            MappedStatement ms = configuration.getMappedStatement(statementName);
            name = ms.getId();
            type = ms.getSqlCommandType();
        }
        public String getName() {
            return name;
        }

        public SqlCommandType getType() {
            return type;
        }
    }
}
