package org.geektimes.cache.interceptor;

import org.geektimes.interceptor.AnnotatedInterceptor;

import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.annotation.CacheRemove;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.spi.CachingProvider;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

/**
 * @Author: jicai
 * @Date: 2021/8/5
 * @Description: 实现CacheRemove注解
 */
@Interceptor
public class CacheRemoveInterceptor extends AnnotatedInterceptor<CacheRemove> {

    private CachingProvider cachingProvider = Caching.getCachingProvider();

    private CacheManager cacheManager = cachingProvider.getCacheManager();

    @Override
    protected Object execute(InvocationContext context, CacheRemove cacheRemove) throws Throwable {
        String cacheName = cacheRemove.cacheName();
        Cache cache = getCache(cacheName);
        boolean afterInvocation = cacheRemove.afterInvocation();
        // The result of target method
        Object result = null;
        Object[] parameters = context.getParameters();
        Object key = parameters[0];
        if (afterInvocation) {
            result = context.proceed();
            cache.remove(key);
        } else {
            try {
                cache.remove(key);
            } catch (Exception e) {
            }
            result = context.proceed();
        }
        return result;
    }

    private Cache getCache(String cacheName) {
        Cache cache = cacheManager.getCache(cacheName);
        if (cache == null) {
            cache = cacheManager.createCache(cacheName,
                    new MutableConfiguration().setTypes(Object.class, Object.class));
        }
        return cache;
    }

}
