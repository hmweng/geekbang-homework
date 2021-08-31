package com.study.test.interceptor;

import com.study.interceptor.BulkheadInterceptor;
import com.study.reflect.ReflectInvocationContext;
import org.junit.Test;

import java.lang.reflect.Method;

/**
 * @Author: jicai
 * @Date: 2021/8/31
 * @Description:
 */
public class BulkheadInterceptorTest {

    @Test
    public void testBulkhead() throws Exception {
        BulkheadInterceptor bulkheadInterceptor = new BulkheadInterceptor();
        TestService testService = new TestService();
        Method service = TestService.class.getMethod("service", String.class);
        ReflectInvocationContext reflectInvocationContext = new ReflectInvocationContext(testService, service, "hello world");
        bulkheadInterceptor.execute(reflectInvocationContext);
    }

}
