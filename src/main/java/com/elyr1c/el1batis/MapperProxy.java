package com.elyr1c.el1batis;

import java.io.Serial;
import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;

/**
 * @ClassName MapperProxy
 * @Description Custom proxy class
 * @Author Elyr1c
 * @Date 2025/6/24 17:19
 */
public class MapperProxy<T> implements InvocationHandler, Serializable {
    @Serial
    private static final long serialVersionUID = -1L;

    private final Map<String, String> sqlSession;
    private final Class<T> mapperInterface;
    public MapperProxy(Map<String, String> sqlSession,Class<T> mapperInterface) {
        this.sqlSession = sqlSession;
        this.mapperInterface = mapperInterface;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws InvocationTargetException, IllegalAccessException {
        // Method的getDeclaringClass是获取定义他的Class对象而不是拥有它的Class对象
        if (Object.class.equals(method.getDeclaringClass())) {
            return method.invoke(this,args);
        }else {
            return "Mapper proxy:" + sqlSession.get(mapperInterface.getName()) + "-" + method.getName();
        }
    }
}
