package com.project.legendsofleague.common.exception;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class GeneralExceptionFactory {

    private static final Map<Class<? extends CustomException>, CustomException> exceptionMap = new ConcurrentHashMap<>();

    private GeneralExceptionFactory() {
    }

    public static <T extends CustomException> T getInstance(Class<T> clazz) {
        synchronized (exceptionMap) {
            try {
                if (!exceptionMap.containsKey(clazz)) {
                    exceptionMap.put(clazz, clazz.getDeclaredConstructor().newInstance());
                }
                return clazz.cast(exceptionMap.get(clazz));
            } catch (Exception e) {
                throw new RuntimeException("Exception Occurred", e);
            }

        }
    }
}
