package demo.makeChain;

import org.apache.commons.collections.functors.InvokerTransformer;

public class Demo1 {
    public static void main(String[] args) throws Exception{
        Object input = Class.forName("java.lang.Runtime").getMethod("getRuntime").invoke(null);
        String iMethodName = "exec";
        Class[] iParamTypes = {String.class};
        Object[] iArgs = {"calc.exe"};
        InvokerTransformer invokerTransformer = new InvokerTransformer(iMethodName, iParamTypes, iArgs);

        invokerTransformer.transform(input);
    }
}