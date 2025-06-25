package com.elyr1c.el1batis.common.lang;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

/**
 * @ClassName ClassScanner
 * @Description Class package scanner
 * @Author Elyr1c
 * @Date 2025/6/25 15:05
 */
public class ClassScanner {
    private static final Map<String, Set<Class<?>>> PACKAGE_CACHE = new ConcurrentHashMap<>();
    private static final String CLASS_SUFFIX = ".class";

    public static Set<Class<?>> scanPackage(String packageName) throws IOException {
        if (PACKAGE_CACHE.containsKey(packageName)) {
            return PACKAGE_CACHE.get(packageName);
        }
        Set<Class<?>> classes = new HashSet<>();
        String path = packageName.replace('.', '/');
        Enumeration<URL> resources = Thread.currentThread()
                .getContextClassLoader().getResources(path);

        while (resources.hasMoreElements()) {
            URL resource = resources.nextElement();
            System.out.println(resource);
            if (resource.getProtocol().equals("file")) {
                classes.addAll(scanDirectory(new File(resource.getFile()), packageName));
            } else if (resource.getProtocol().equals("jar")) {
                classes.addAll(scanJar(resource));
            }
        }
        PACKAGE_CACHE.put(packageName, classes);
        return classes;
    }
    private static Set<Class<?>> scanDirectory(File directory, String packageName) {
        Set<Class<?>> classes = new HashSet<>();
        if (!directory.exists()) {
            return classes;
        }
        File[] files = directory.listFiles();
        if (files == null) {
            return classes;
        }
        for (File file : files) {
            if (file.isDirectory()) {
                classes.addAll(scanDirectory(file, packageName + "." + file.getName()));
            } else if (file.getName().endsWith(CLASS_SUFFIX)) {
                String className = packageName + '.' + file.getName().substring(0, file.getName().length() - CLASS_SUFFIX.length());
                try {
                    classes.add(Class.forName(className));
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException("Error scan directory info Cause: "+e,e);
                }
            }
        }
        return classes;
    }

    private static Set<Class<?>> scanJar(URL jarUrl) throws IOException {
        Set<Class<?>> classes = new HashSet<>();
        try (JarInputStream jarInputStream = new JarInputStream(jarUrl.openStream())) {
            JarEntry jarEntry;
            while ((jarEntry = jarInputStream.getNextJarEntry()) != null) {
                String entryName = jarEntry.getName();
                if (entryName.endsWith(CLASS_SUFFIX)) {
                    String className = entryName.replace('/', '.').substring(0, entryName.length() - CLASS_SUFFIX.length());
                    try {
                        classes.add(Class.forName(className));
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException("Error scan jar info Cause: "+e,e);
                    }
                }
            }
        }
        return classes;
    }
}
