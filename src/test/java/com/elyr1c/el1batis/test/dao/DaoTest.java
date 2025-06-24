package com.elyr1c.el1batis.test.dao;

import com.elyr1c.el1batis.MapperProxy;
import com.elyr1c.el1batis.MapperProxyFactory;
import org.junit.Test;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;

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
        Map<String,String> sqlSession = new HashMap<>();
        sqlSession.put("com.elyr1c.el1batis.test.dao.ITestDao","test");
        ITestDao proxy = factory.getMapperInstance(sqlSession);
        String res = proxy.doSomething();
        proxy.toString();
        System.out.println(res);
    }
}
