package com.study.interceptor;

import org.eclipse.microprofile.faulttolerance.Timeout;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.*;

import static com.study.utils.AnnotationUtils.getAnnotation;

/**
 * @Author: jicai
 * @Date: 2021/9/1
 * @Description:
 */
@Timeout
@Interceptor
public class TimeoutInterceptor {

    private static final ExecutorService executorService = Executors.newCachedThreadPool();

    @AroundInvoke
    public Object execute(InvocationContext invocationContext) throws Exception {
        Timeout timeout = getAnnotation(invocationContext.getMethod(), Timeout.class);
        if (timeout == null) {
            throw new IllegalArgumentException("no @Timeout annotation");
        }
        long value = timeout.value();
        ChronoUnit chronoUnit = timeout.unit();
        Duration duration = chronoUnit.getDuration();
        Future<Object> future = executorService.submit(invocationContext::proceed);
        return future.get(value, TimeUnit.MILLISECONDS);
    }

}
