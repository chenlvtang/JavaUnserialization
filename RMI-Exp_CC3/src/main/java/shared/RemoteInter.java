package shared;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteInter extends Remote {
    String sayHello(String name) throws RemoteException;
}