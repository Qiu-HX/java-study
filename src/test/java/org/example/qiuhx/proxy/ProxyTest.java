package org.example.qiuhx.proxy;

import org.example.qiuhx.proxy.dynamic.MyInvocationHandler;
import org.example.qiuhx.proxy.statics.Boss;
import org.junit.Test;

import java.lang.reflect.Proxy;

/**
 * @ClassName StaticProxyTest
 * @Description 代理测试类
 * @Author max
 * @Date 2021/2/7 15:02
 * @Version 1.0
 */
public class ProxyTest {
    /**
     * 静态代理测试
     */
    @Test
    public void StaticProxyTest() {
        People secretary = new Secretary();
        Boss boss = new Boss(secretary);
        boss.work();
    }

    /**
     * 动态代理测试（JDK版）
     */
    @Test
    public void DynamicJdkTest() {
        /*
         * 通过Proxy的newProxyInstance方法创建代理对象
         * 1.第一个参数，表示用动态代理类的类加载器加载代理对象
         * 2.第二个参数，传入真实对象的接口，所以代理对象也可以调用接口，使用真实对象的方法
         * 3.第三个参数，让代理对象与动态代理类联系起来，当代理对象调用真实对象的方法时，自动跳转到执行handler的invoke方法
         */
        People proxyInstance = (People) Proxy.newProxyInstance(Secretary.class.getClassLoader(),
                Secretary.class.getInterfaces(),
                new MyInvocationHandler(new Secretary()));

        //实际执行 MyInvocationHandler.invoke 方法
        proxyInstance.work();

        proxyInstance.love();
    }
}