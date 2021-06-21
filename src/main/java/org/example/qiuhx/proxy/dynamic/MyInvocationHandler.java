package org.example.qiuhx.proxy.dynamic;

import org.example.qiuhx.proxy.People;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @ClassName MyInvocationHandler
 * @Description 动态代理类（JDK版） 是InvocationHandler的子类，必须实现invoke方法
 * @Author max
 * @Date 2021/2/7 15:14
 * @Version 1.0
 */
public class MyInvocationHandler implements InvocationHandler {
    //要代理的真实对象（被代理对象）
    private People animal;

    //给要代理的真实对象赋值
    public MyInvocationHandler(People animal) {
        this.animal = animal;
    }

    /**
     * @param proxy     指创造的代理对象
     * @param method    指我们要代理的真实对象的某个方法的Method对象
     * @param args      指真实对象的某个方法的参数
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //调用被代理对象的方法前的业务
        System.out.println("当前调用的方法为：" + method.getName());

        //调用被代理对象方法
        //当代理对象调用真实对象的方法时，会自动跳转到动态代理类即InvocationHandler的子类的invoke方法来调用
        method.invoke(animal);

        //调用被代理对象的方法后的业务
        System.out.println("被代理的方法调用结束");
        return null;
    }
}
