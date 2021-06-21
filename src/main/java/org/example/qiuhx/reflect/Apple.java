package org.example.qiuhx.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * @ClassName Apple
 * @Description 反射理解
 * @Author max
 * @Date 2021/3/11 10:41
 * @Version 1.0
 */
public class Apple {

    private int price;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * 两段代码输出结果一致，但是其思路完全不一样
     * 第一段代码在未运行时就已经确定了要运行的类（Apple），而第二段代码则是在运行时通过字符串值才得知要运行的类（org.example.qiuhx.reflect.Apple）。
     *
     * 所以说什么是反射？
     * 反射就是在运行时才知道要操作的类是什么，并且可以在运行时获取类的完整构造，并调用对应的方法。
     */
    public static void main(String[] args) throws Exception{
        //正常的调用，可以理解为 “正”射
        Apple apple = new Apple();
        apple.setPrice(5);
        System.out.println("Apple Price:" + apple.getPrice());

        //使用反射调用
        Class<?> clz = Class.forName("org.example.qiuhx.reflect.Apple");
        Constructor<?> appleConstructor = clz.getConstructor();
        Object appleObj = appleConstructor.newInstance();
        Method setPriceMethod = clz.getMethod("setPrice", int.class);
        setPriceMethod.invoke(appleObj, 5);
        Method getPriceMethod = clz.getMethod("getPrice");
        System.out.println("Apple Price:" + getPriceMethod.invoke(appleObj));
    }
}
