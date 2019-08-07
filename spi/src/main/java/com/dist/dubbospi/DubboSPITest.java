package com.dist.dubbospi;

import com.alibaba.dubbo.common.extension.ExtensionLoader;

/**
 * Created by Administrator on 2019/8/7.
 */
public class DubboSPITest {

    public static void main(String[] args) throws Exception{
        ExtensionLoader<DubboRobot> extensionLoader =
                ExtensionLoader.getExtensionLoader(DubboRobot.class);
        DubboRobot optimusPrime = extensionLoader.getExtension("OptimusPrime");
        optimusPrime.sayHello();
        DubboRobot bumblebee = extensionLoader.getExtension("Bumblebee");
        bumblebee.sayHello();
    }
}
