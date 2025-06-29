package com.elyr1c.el1batis.mapper;

import com.elyr1c.el1batis.session.SqlSession;

import java.io.Serial;
import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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

    private final SqlSession sqlSession;
    private final Class<T> mapperInterface;
    private Map<Method,MapperMethod> methodCache;
    public MapperProxy(SqlSession sqlSession,Class<T> mapperInterface, Map<Method, MapperMethod> methodCache) {
        this.sqlSession = sqlSession;
        this.mapperInterface = mapperInterface;
        this.methodCache = methodCache;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws InvocationTargetException, IllegalAccessException {
        // Method的getDeclaringClass是获取定义他的Class对象而不是拥有它的Class对象
        if (Object.class.equals(method.getDeclaringClass())) {
            return method.invoke(this,args);
        }else {
            final MapperMethod mapperMethod = cachedMapperMethod(method);
            return mapperMethod.execute(sqlSession, args);
        }
    }
    private MapperMethod cachedMapperMethod(Method method) {
        MapperMethod mapperMethod = methodCache.get(method);
        if (mapperMethod == null) {
            //找不到才去new
            mapperMethod = new MapperMethod(mapperInterface, method, sqlSession.getConfiguration());
            methodCache.put(method, mapperMethod);
        }
        return mapperMethod;
    }
}
