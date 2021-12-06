package com.edu;
import java.lang.reflect.Method;

public class ReflectExecDemo {
    public static void main(String[] args) throws Exception{
        //equal: Object run = Runtime.getRuntime();
        Object myRuntime = Class.forName("java.lang.Runtime").getMethod("getRuntime").invoke(null);
        //得到方法exec, 其参数为String类型
        Method exec = Class.forName("java.lang.Runtime").getMethod("exec",String.class);
        //equal: myruntime.exec("calc.exe");
        exec.invoke(myRuntime, "calc.exe");
    }
}
