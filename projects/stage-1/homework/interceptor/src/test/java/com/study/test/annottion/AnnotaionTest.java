package com.study.test.annottion;

import com.study.test.interceptor.TestService;
import org.eclipse.microprofile.faulttolerance.Bulkhead;
import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @Author: jicai
 * @Date: 2021/8/31
 * @Description:
 */
public class AnnotaionTest {

    @Test
    public void test() throws NoSuchMethodException {
        Annotation[] annotations = TestService.class.getAnnotations();
        System.out.printf("class annotations : %s \n", annotations);
        Annotation annotation = TestService.class.getAnnotation(Bulkhead.class);
        System.out.printf("class annotation : %s\n", annotation);
        Annotation[] declaredAnnotations = TestService.class.getDeclaredAnnotations();
        System.out.printf("class declaredAnnotations : %s\n", declaredAnnotations);


        Method method = TestService.class.getMethod("service", null);
        Annotation[] methodAnnotations = method.getAnnotations();
        System.out.printf("method annotations : %s\n", methodAnnotations);
        Annotation methodAnnotation = method.getAnnotation(Bulkhead.class);
        System.out.printf("method annotation : %s\n", methodAnnotation);
        Annotation[] methodDeclaredAnnotations = method.getDeclaredAnnotations();
        System.out.printf("method declaredAnnotations : %s\n", methodDeclaredAnnotations);
    }

}
