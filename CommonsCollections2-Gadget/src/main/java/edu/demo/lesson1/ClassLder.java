package edu.demo.lesson1;

public class ClassLder extends ClassLoader{
    public ClassLder(ClassLoader parent){
        super(parent);
    }

    public Class define(byte []b){
        return super.defineClass(b,0,b.length);//加载字节码
    }
}
