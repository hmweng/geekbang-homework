package com.study.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @author：jicai
 * @description：
 * @date：created in 2021/9/1 10:33 下午
 * @modified by:
 */
public class AnnotationUtils {

    public static <T extends Annotation> T getAnnotation(Method method, Class<T> clazz) {
        T annotation;
        if (method.isAnnotationPresent(clazz)) {
            annotation = method.getAnnotation(clazz);
        } else {
            annotation = method.getDeclaringClass().getAnnotation(clazz);
        }
        return annotation;
    }

}
