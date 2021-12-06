package server;

import shared.RemoteInter;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RemoteImpl extends UnicastRemoteObject implements RemoteInter {

    protected RemoteImpl() throws RemoteException {
        //看网上的博客说，因为父类的构造器有抛出RemoteException，所以这里也必须写
    }

    @Override
    public String sayHello(String name) throws RemoteException {
        return "hi," +  name + ",chenlvtang_is_Fool";
    }
}