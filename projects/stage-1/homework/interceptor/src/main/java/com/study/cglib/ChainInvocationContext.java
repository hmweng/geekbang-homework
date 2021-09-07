package com.study.cglib;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

/**
 * @Author: jicai
 * @Date: 2021/9/7
 * @Description:
 */
public class ChainInvocationContext implements InvocationContext {

    private final Object[] interceptors;

    private final InvocationContext declareMethod;

    private final Map<Integer, Method> cacheInterceptorMethodMap;

    private final int count;

    private int pos = 0;

    public ChainInvocationContext(InvocationContext declareMethod, Object... interceptors) {
        this.interceptors = interceptors;
        this.count = interceptors.length;
        this.cacheInterceptorMethodMap = initInterceptorMethod();
        this.declareMethod = declareMethod;
    }

    private Map<Integer, Method> initInterceptorMethod() {
        Map<Integer, Method> cacheInterceptorMethodMap = new HashMap<>();
        for (int i = 0; i < count; i++) {
            Object interceptor = interceptors[i];
            cacheInterceptorMethodMap.put(i, Stream.of(interceptor.getClass().getDeclaredMethods())
                    .filter(method -> {
                        int modifiers = method.getModifiers();
                        if (!Modifier.isPublic(modifiers)) {
                            return false;
                        }
                        if (Modifier.isStatic(modifiers)) {
                            return false;
                        }
                        if (!Object.class.equals(method.getReturnType())) {
                            return false;
                        }
                        if (method.getParameterCount() != 1) {
                            return false;
                        }
                        if (!method.getParameterTypes()[0].isAssignableFrom(InvocationContext.class)) {
                            return false;
                        }
                        return method.getAnnotation(AroundInvoke.class) != null;
                    })
                    .findAny().get()
            );
        }
        return cacheInterceptorMethodMap;
    }

    private Method getInterceptorMethod(Integer index) {
        return cacheInterceptorMethodMap.get(index);
    }

    @Override
    public Object getTarget() {
        return declareMethod.getTarget();
    }

    @Override
    public Object getTimer() {
        return declareMethod.getTimer();
    }

    @Override
    public Method getMethod() {
        return declareMethod.getMethod();
    }

    @Override
    public Constructor<?> getConstructor() {
        return declareMethod.getConstructor();
    }

    @Override
    public Object[] getParameters() {
        return declareMethod.getParameters();
    }

    @Override
    public void setParameters(Object[] params) {
        declareMethod.setParameters(params);
    }

    @Override
    public Map<String, Object> getContextData() {
        return declareMethod.getContextData();
    }

    @Override
    public Object proceed() throws Exception {
        if (pos < count) {
            int index = pos++;
            Method method = cacheInterceptorMethodMap.get(index);
            return method.invoke(interceptors[index], this);
        } else {
            return declareMethod.proceed();
        }
    }
}
