package com.elyr1c.el1batis.builder;

import com.elyr1c.el1batis.Configuration;

/**
 * @ClassName BaseBuilder
 * @Description TODO
 * @Author Elyr1c
 * @Date 2025/6/26 23:07
 */
public abstract class BaseBuilder {
    protected final Configuration configuration;
    public BaseBuilder(Configuration config){
        this.configuration = config;
    }
    public Configuration getConfiguration(){
        return this.configuration;
    }
}

