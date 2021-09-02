package com.study.test.cglib;

import com.study.test.interceptor.TestService;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.junit.Test;

import java.lang.reflect.Method;

/**
 * @author：jicai
 * @description：
 * @date：created in 2021/8/31 10:04 下午
 * @modified by:
 */
public class CgLbibEnhancerTest {

    @Test
    public void cgbibEnhancer() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(TestService.class);
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                return proxy.invokeSuper(obj, args);
            }
        });

        TestService testService = (TestService) enhancer.create();
        testService.service("hello world");
    }

}
