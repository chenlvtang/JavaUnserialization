package edu.myRMI.client;

import edu.myRMI.share.RemoteInterface;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class myClient {
    public static void main(String[] args) throws MalformedURLException, NotBoundException, RemoteException {
        //连接到注册中心，这里因为是本地创建的，所以IP写的是localhost
        Registry myRegistry = LocateRegistry.getRegistry("localhost", 1099);
        //向注册中心查询服务，这里也就体现了为什么要和服务端共用一个接口
        RemoteInterface myRemote = (RemoteInterface) myRegistry.lookup("myRMI");
        String result = myRemote.sayHello("chenlvtang");
        System.out.println(result);
    }
}
