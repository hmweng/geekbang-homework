package com.study.utils;

import com.study.cglib.AroundInvokeMethodInterceptor;
import net.sf.cglib.proxy.Enhancer;

/**
 * @Author: jicai
 * @Date: 2021/9/7
 * @Description:
 */
public class CglibEnhancerUtils {


    public static <T> T enhancerClass(T target, Object... interceptors) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(target.getClass());
        enhancer.setCallback(new AroundInvokeMethodInterceptor(interceptors));
        return (T)enhancer.create();
    }

    /*public static <T extends Class<?>> T enhancerClass(T clazz, Object... interceptors) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(new AroundInvokeMethodInterceptor(interceptors));
        return enhancer.create();
    }*/

}
