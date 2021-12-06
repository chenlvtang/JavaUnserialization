package edu.myJNDI.server;

import edu.myJNDI.share.RemoteInterface;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.Properties;

public class myServer {
    public static void main(String[] args) throws RemoteException, MalformedURLException {
        try {
            LocateRegistry.createRegistry(1099);

            Properties env = new Properties();
            env.put(Context.INITIAL_CONTEXT_FACTORY,
                    "com.sun.jndi.rmi.registry.RegistryContextFactory");
            env.put(Context.PROVIDER_URL,
                    "rmi://127.0.0.1:1099");
            Context ctx = new InitialContext(env);
            RemoteInterface myRemote = new RemoteImpl();

            ctx.rebind("myRMI", myRemote);
            ctx.close();
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }
}
