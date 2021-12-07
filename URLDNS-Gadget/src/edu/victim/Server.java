package edu.victim;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class Server {
    public static void main(String[] args) throws Exception{
        //deSerialization
        FileInputStream file1 = new FileInputStream("chenlvtang.bin");
        ObjectInputStream unser = new ObjectInputStream(file1);
        unser.readObject();
        unser.close();
    }
}
