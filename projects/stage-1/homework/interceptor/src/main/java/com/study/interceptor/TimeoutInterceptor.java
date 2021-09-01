package com.study.interceptor;

import com.study.utils.AnnotationUtils;
import org.eclipse.microprofile.faulttolerance.Timeout;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @author：jicai
 * @description：
 * @date：created in 2021/9/1 10:32 下午
 * @modified by:
 */
@Timeout
public class TimeoutInterceptor {

    private static final ExecutorService executorService = Executors.newCachedThreadPool();

    @AroundInvoke
    public Object execute(InvocationContext invocationContext) throws Exception {
        Timeout timeout = AnnotationUtils.getAnnotation(invocationContext.getMethod(), Timeout.class);
        if (timeout == null) {
            throw new IllegalArgumentException("no @Timeout annotation");
        }
        long value = timeout.value();
        ChronoUnit unit = timeout.unit();
        Future<Object> future = executorService.submit(invocationContext::proceed);
        return future.get(value, TimeUnit.MILLISECONDS);
    }
}
