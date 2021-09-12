package edu.myRMI.server;

import edu.myRMI.share.RemoteInterface;

import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class myServer {
    public static void main(String[] args) throws RemoteException, MalformedURLException {
        //实例化一个远程对象
        RemoteInterface myRemote = new RemoteImpl();
        /*如果没有继承UnicastRemoteObject,则需要手动导出
        *RemoteInterface myRemote = new RemoteImpl();
        *UnicastRemoteObject.exportObject(myRemote, 0);
        * */
        //将注册中心创建在1099端口, 可以再加一个参数IP
//        Registry myRegistry =
        LocateRegistry.createRegistry(1099);
        Registry myRegistry = LocateRegistry.getRegistry(1099);
        //向注册中心中注册Stub,第一个参数为名字，要求客户端服务端相同
        myRegistry.rebind("myRMI", myRemote);
    }
}
