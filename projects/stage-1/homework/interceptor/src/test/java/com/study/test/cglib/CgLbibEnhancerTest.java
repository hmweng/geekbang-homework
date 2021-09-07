package com.study.test.cglib;

import com.study.interceptor.BulkheadInterceptor;
import com.study.interceptor.TimeoutInterceptor;
import com.study.test.interceptor.TestService;
import com.study.utils.CglibEnhancerUtils;
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

    /**
     * cglib提升
     */
    @Test
    public void testChain() {
        TestService testService = CglibEnhancerUtils.enhancerClass(new TestService(), new TimeoutInterceptor(), new BulkheadInterceptor());
        testService.service("hello world");
//        testService.serviceAsyn("hello world");
    }

}
