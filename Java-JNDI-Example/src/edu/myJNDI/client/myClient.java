package edu.myJNDI.client;

import edu.myJNDI.share.RemoteInterface;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class myClient {
    public static void main(String[] args) throws MalformedURLException, NotBoundException, RemoteException {
        try {
            String url = "rmi://127.0.0.1:1099/myRMI";
            Context ctx = new InitialContext();
            RemoteInterface myRemote = (RemoteInterface)ctx.lookup(url);
            String result = myRemote.sayHello("chenlvtang");
            System.out.println(result);
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }
}
