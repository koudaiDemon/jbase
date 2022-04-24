package com.cwww.natives;

/**
 * 测试Native方法
 * <br/>
 * step1 生成头文件
 * <pre>{@code JDK11 运行命令 javac -encoding utf8 -h ./ NativeDemo.java
 * JDK8 运行命令}</pre>
 * <br/>
 * step2 编写C文件
 * {@code 编写com_cwww_natives_NativeDemo.cpp文件}
 * <br/>
 * <pre>{@code #include "jni.h"
 * #include "com_cwww_natives_NativeDemo.h"
 * #include <stdio.h>
 *
 * JNIEXPORT void JNICALL Java_com_cwww_natives_NativeDemo_sayHello
 * (JNIEnv *env, jclass obj) {
 *     printf("C say Hello!\n");
 *     return;
 * }}</pre>
 * <p>
 * step3 编译C文件
 * <br/>
 * gcc -dynamiclib -I /Library/Java/JavaVirtualMachines/jdk-11.0.2.jdk/Contents/Home/include -I /Library/Java/JavaVirtualMachines/jdk-11.0.2.jdk/Contents/Home/include/darwin  com_cwww_natives_NativeDemo.c -o libNativeDemo.dylib
 * <br/>
 * step4 执行
 * <pre>{@code java -cp ../../.. com.cwww.natives.NativeDemo}</pre>
 *
 * @author wei.cai@hand-china.com 2022/4/22
 */
public class NativeDemo {


    static {
        //设置查找路径为当前项目路径
        System.setProperty("java.library.path", ".");
        /*
         * 如果我这样加载一个动态链接库System.loadLibrary(“NativeDemo”)，
         * 那么系统会自动根据不同的平台拓展成真实的动态库文件名，
         * Linux系统上会拓展成libNativeDemo.so，
         * 而在Windows平台上则会拓展成NativeDemo.dll，
         * 而Mac系统上会拓展成libNativeDemo.jnilib或libNativeDemo.dylib
         */
        System.loadLibrary("NativeDemo");
    }

    public static native void sayHello();

    @SuppressWarnings("all")
    public static void main(String[] args) {
        new NativeDemo().sayHello();
    }

}
