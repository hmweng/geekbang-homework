package com.study.test.interceptor;

import org.eclipse.microprofile.faulttolerance.Asynchronous;
import org.eclipse.microprofile.faulttolerance.Bulkhead;

/**
 * @Author: jicai
 * @Date: 2021/8/31
 * @Description:
 */
@Bulkhead
public class TestService extends TestSuperService {

    public void service(String str) {
        System.out.println(str);
    }

    @Asynchronous
    public String serviceAsyn(String str) {
        System.out.println(str);
        return str + " thank";
    }

}
