package com.github.mhewedy.condition.module;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
class ModuleUtil {

    private static List<String> classpathDepends;
    private static final Map<String, Boolean> MODULES_MAP = new ConcurrentHashMap<>();

    public static boolean isModuleExists(String moduleName) {

        Boolean moduleFound = MODULES_MAP.get(moduleName);
        if (moduleFound != null) {
            return moduleFound;
        }

        loadClasspathDepends();

        var anyMatch = classpathDepends.stream().anyMatch(it -> it.contains(moduleName));
        MODULES_MAP.put(moduleName, anyMatch);

        if (anyMatch) {
            log.trace("module {} found on classpath", moduleName);
        } else {
            log.warn("module {} not found on classpath", moduleName);
        }

        return anyMatch;
    }

    @SneakyThrows
    private static void loadClasspathDepends() {
        if (classpathDepends == null) {
            classpathDepends = Arrays.stream(System.getProperty("java.class.path").split(":")).toList();
        }
    }
}
