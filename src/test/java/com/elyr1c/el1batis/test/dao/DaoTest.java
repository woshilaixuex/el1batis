package com.elyr1c.el1batis.test.dao;

import com.elyr1c.el1batis.Configuration;
import com.elyr1c.el1batis.common.lang.ClassScanner;
import com.elyr1c.el1batis.mapper.MapperProxy;
import com.elyr1c.el1batis.mapper.MapperProxyFactory;
import com.elyr1c.el1batis.mapper.MapperRegister;
import com.elyr1c.el1batis.session.SqlSession;
import com.elyr1c.el1batis.session.SqlSessionFactory;
import com.elyr1c.el1batis.session.defaults.DefaultSqlSessionFactory;
import org.junit.Test;

import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Set;

/**
 * @ClassName DaoTest
 * @Description Test Mapper base function
 * @Author Elyr1c
 * @Date 2025/6/24 17:34
 */
public class DaoTest {
    @Test
    public void test() throws NoSuchMethodException {
        Class classInfo = MapperProxy.class;
        Method method = classInfo.getMethod("invoke",Object.class, Method.class,Object[].class);
        Parameter[] parmes = method.getParameters();
        for (int i = 0; i < parmes.length; i++) {
            System.out.println(parmes[i].getName());
        }
        System.out.println(method.getDeclaringClass());
        System.out.println(Object.class);
        System.out.println(Object.class.equals(method.getDeclaringClass()));
    }
    @Test
    public void testMapperProxy(){
        MapperProxyFactory<ITestDao> factory = new MapperProxyFactory<>(ITestDao.class);
        SqlSession sqlSession = new SqlSession() {
            @Override
            public <T> T selectOne(String statement) {
                return null;
            }

            @Override
            public <T> T selectOne(String statement, Object parameter) {
                return null;
            }

            @Override
            public Configuration getConfiguration() {
                return null;
            }

            @Override
            public <T> T getMapper(Class<T> type) {
                return null;
            }
        };
        ITestDao proxy = factory.getMapperInstance(sqlSession);
        String res = proxy.toString();
        System.out.println(res);
    }
    @Test
    public void TestClassScanner() throws IOException {
        Set<Class<?>> srt = ClassScanner.scanPackage("com.elyr1c.el1batis.mapper");
        srt.forEach(cls -> System.out.println(cls.getName()));
        Set<Class<?>> srt2 = ClassScanner.scanPackage("com.elyr1c.el1batis");
        srt2.forEach(cls2 -> System.out.println(cls2.getName()));
    }

    /**
     * 每一个被代理的Mapper都一个SqlSession与其对应
     * @throws IOException
     */
    @Test
    public void TestSqlSession() throws IOException {
//        // register
//        MapperRegister register = new MapperRegister(Configuration.getConfigurationInfo());
//        register.addMappers("com.elyr1c.el1batis.test.dao");
//        // sql session
//        SqlSessionFactory factory = new DefaultSqlSessionFactory(Configuration.getConfigurationInfo());
//        SqlSession sqlSession = factory.openSqlSession();
//        // mapper
//        ITestDao mapper = sqlSession.getMapper(ITestDao.class);
//        String result = mapper.doSomething();
//        System.out.println(result);
    }
}
