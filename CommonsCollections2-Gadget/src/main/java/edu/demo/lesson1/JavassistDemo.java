package edu.demo.lesson1;

import javassist.ClassPool;
import javassist.CtClass;

public class JavassistDemo {
    public static void main(String[] args) throws Exception{
        //获取类的搜索路径
        ClassPool pool = ClassPool.getDefault();
        //从搜索路径中获取对应类的CtClass对象，如果没有，则会自动加入
        CtClass ctClass = pool.get(Hello.class.getName());
        //定义要插入的代码
        String cmd = "Runtime.getRuntime().exec(\"calc.exe\");";
        //在static靠前位置插入
        ctClass.makeClassInitializer().insertBefore(cmd);
        //设置类名
        ctClass.setName("Hello");
        //写入到对应目录下
        ctClass.writeFile("testClass");
        //将hello对象转换为字节码
        final byte[] classBytes = ctClass.toBytecode();
        //获取loader
        ClassLder loader = new ClassLder(JavassistDemo.class.getClassLoader());
        //加载字节码，并实例化
        loader.define(classBytes).newInstance();
    }
}
