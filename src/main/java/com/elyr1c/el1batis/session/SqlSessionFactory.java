package com.elyr1c.el1batis.session;

/**
 * @ClassName SqlSessionFactory
 * @Description TODO
 * @Author Elyr1c
 * @Date 2025/6/25 21:39
 */
public interface SqlSessionFactory {
    SqlSession openSqlSession();
}
