package com.elyr1c.el1batis.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;


/**
 * @ClassName Resourcese
 * @Description TODO
 * @Author Elyr1c
 * @Date 2025/6/28 22:06
 */
public class Resources {
    public static Reader getResourceAsReader(String resource) throws IOException {
        return new InputStreamReader(getResourceAsStream(resource));
    }
    private static InputStream getResourceAsStream(String resource) throws IOException {
        ClassLoader[] classLoaders = getClassLoaders();
        for (ClassLoader classLoader : classLoaders) {
            InputStream inputStream = classLoader.getResourceAsStream(resource);
            if (null != inputStream) {
                return inputStream;
            }
        }
        throw new IOException("Could not find resource. Case: " + resource + "was not found in java.class.path or other class resource.");
    }

    private static ClassLoader[] getClassLoaders() {
        return new ClassLoader[]{
                // Return $AppClassLoader,It`s used that load java.class.path(target/classes) resources
                ClassLoader.getSystemClassLoader(),
                // May be return other ClassLoader
                Thread.currentThread().getContextClassLoader()};
    }
    /*
     * Loads a class
     *
     * @param className - the class to fetch
     * @return The loaded class
     * @throws ClassNotFoundException If the class cannot be found (duh!)
     */
    public static Class<?> classForName(String className) throws ClassNotFoundException {
        return Class.forName(className);
    }

}
