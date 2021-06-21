package org.example.qiuhx.proxy.statics;

import lombok.Data;
import org.example.qiuhx.proxy.People;

/**
 * 静态代理的实现比较简单：
 * 1、编写一个代理类，实现与目标对象相同的接口
 * 2、并在内部维护一个目标对象的引用。
 *    通过构造器塞入目标对象，在代理对象中调用目标对象的同名方法，并添加前拦截，后拦截等所需的业务功能。
 * @ClassName Boss
 * @Description 老板
 * @Author max
 * @Date 2021/2/7 14:55
 * @Version 1.0
 */
@Data
public class Boss implements People {
    private People secretary;

    public Boss(People secretary) {
        this.secretary = secretary;
    }

    @Override
    public void work() {
        //方法前的操作... 玩电脑

        //有事秘书干
        secretary.work();

        //方法后的操作... 喝咖啡
    }

    @Override
    public void love() {
        //工作可以秘书干，恋爱必须自己谈
        System.out.println("不知妻美刘强东！");
    }
}
