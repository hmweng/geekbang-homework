package com.study.interceptor;

import org.eclipse.microprofile.faulttolerance.Asynchronous;
import org.eclipse.microprofile.faulttolerance.Bulkhead;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.concurrent.*;

/**
 * @Author: jicai
 * @Date: 2021/8/31
 * @Description:
 */
@Bulkhead
@Interceptor
public class BulkheadInterceptor {

    private final ConcurrentMap<Bulkhead, ExecutorService> threadPoolCache = new ConcurrentHashMap<>();
    private final ConcurrentMap<Bulkhead, Semaphore> semaphoreCache = new ConcurrentHashMap<>();

    @AroundInvoke
    public Object execute(InvocationContext invocationContext) throws Exception {
        Method method = invocationContext.getMethod();
        Bulkhead bulkhead = getBulkheadByType(method, Bulkhead.class);
        if (bulkhead == null) {
            throw new IllegalArgumentException("no @Bulkhead annotation");
        }
        Asynchronous asynchronous = getBulkheadByType(method, Asynchronous.class);
        int coreSize = bulkhead.value();
        if (asynchronous != null) {
            ExecutorService executorService = threadPoolCache.computeIfAbsent(bulkhead, (key) -> {
                int waitingTaskQueue = bulkhead.waitingTaskQueue();
                return new ThreadPoolExecutor(coreSize, coreSize, 0, TimeUnit.SECONDS, new ArrayBlockingQueue<>(waitingTaskQueue));
            });
            Future<Object> submit = executorService.submit(invocationContext::proceed);
            return submit.get();
        } else {
            Semaphore semaphore = semaphoreCache.computeIfAbsent(bulkhead, (key) -> new Semaphore(coreSize));
            if (semaphore.tryAcquire()) {
                invocationContext.proceed();
                try {
                    return invocationContext.proceed();
                } finally {
                    semaphore.release();
                }
            }
        }

        return null;
    }

    private <T extends Annotation> T getBulkheadByType(Method method, Class<T> clazz) {
        T annotation;
        if (method.isAnnotationPresent(clazz)) {
            annotation = method.getAnnotation(clazz);
        } else {
            annotation = method.getDeclaringClass().getAnnotation(clazz);
        }
        return annotation;
    }

}
