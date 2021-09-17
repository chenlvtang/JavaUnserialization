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
            //String url = "rmi://127.0.0.1:1099/myRMI"; //for user
            //String url = "rmi://127.0.0.1:1099/test"; //for hacker_RMI
            String url ="ldap://127.0.0.1:1389/xxx"; //for hacker_LDAP
            Context ctx = new InitialContext();
            RemoteInterface myRemote = (RemoteInterface)ctx.lookup(url);
            String result = myRemote.sayHello("chenlvtang");
            System.out.println(result);
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }
}
