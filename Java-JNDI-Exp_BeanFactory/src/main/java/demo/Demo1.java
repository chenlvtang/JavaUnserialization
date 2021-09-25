package demo;

public class Demo1 {
    /* debug to find out what is ref.getAll()、ra.getType()、ra.getContent() in BeanFactory
    * The payload in class Hacker:
    * ResourceRef ref = new ResourceRef("demo.Demo1", "", "", "", true,"org.apache.naming.factory.BeanFactory",null);
    * The Link:
    *https://chenlvtang.top/2021/09/15/JDK8u191-%E7%AD%89%E9%AB%98%E7%89%88%E6%9C%AC%E4%B8%8B%E7%9A%84JNDI%E6%B3%A8%E5%85%A5/
    */
    public Demo1(){
        System.out.println("hi!");
    }
}
