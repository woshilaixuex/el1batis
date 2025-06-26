package com.elyr1c.el1batis;

import com.elyr1c.el1batis.common.lang.ClassScanner;

/**
 * @ClassName Configuratiob
 * @Description TODO
 * @Author Elyr1c
 * @Date 2025/6/25 22:37
 */
public class Configuration {

    private static class Holder{
        private static final Configuration configuration = new Configuration();
    }
    public Configuration getConfigurationInfo(){
        return Holder.configuration;
    }
}
