package org.example.qiuhx.proxy;

/**
 * @ClassName secretary
 * @Description 秘书
 * @Author max
 * @Date 2021/2/7 14:55
 * @Version 1.0
 */
public class Secretary implements People {

    @Override
    public void work() {
        System.out.println("秘书在努力干活！");
    }

    @Override
    public void love() {
        System.out.println("秘书爱上清新美男子！");
    }
}
