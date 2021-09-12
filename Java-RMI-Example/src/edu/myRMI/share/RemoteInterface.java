package edu.myRMI.share;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteInterface extends Remote {
    String sayHello(String name) throws RemoteException;
}
