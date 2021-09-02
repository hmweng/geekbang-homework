package com.study.test.interceptor;

import com.study.interceptor.BulkheadInterceptor;
import com.study.interceptor.TimeoutInterceptor;
import com.study.reflect.ReflectInvocationContext;
import org.junit.Test;

import java.lang.reflect.Method;

/**
 * @Author: jicai
 * @Date: 2021/9/1
 * @Description:
 */
public class TimeoutInterceptorTest {

    @Test
    public void testTimeout() throws Exception {
        TimeoutInterceptor bulkheadInterceptor = new TimeoutInterceptor();
        TestService testService = new TestService();
        Method serviceTime = TestService.class.getMethod("serviceTime", String.class);
        ReflectInvocationContext reflectInvocationContext = new ReflectInvocationContext(testService, serviceTime, "hello world");
        bulkheadInterceptor.execute(reflectInvocationContext);
    }

}
