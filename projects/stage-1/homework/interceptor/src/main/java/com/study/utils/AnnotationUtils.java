package com.study.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @Author: jicai
 * @Date: 2021/9/1
 * @Description:
 */
public class AnnotationUtils {

    public static <T extends Annotation> T getBulkheadByType(Method method, Class<T> clazz) {
        T annotation;
        if (method.isAnnotationPresent(clazz)) {
            annotation = method.getAnnotation(clazz);
        } else {
            annotation = method.getDeclaringClass().getAnnotation(clazz);
        }
        return annotation;
    }

}
