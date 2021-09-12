package com.edu;
import java.io.*;

public class SerializDemo{
    public static void main(String[] args) throws Exception{
        Chenlvtang chenlvtang = new Chenlvtang();
        //开始序列化
        FileOutputStream file = new FileOutputStream("chenlvtang.bin");
        ObjectOutputStream ser = new ObjectOutputStream(file);
        //ser.writeObject(Runtime.getRuntime().exec("calc.exe")); 会失败 没有继承serialize接口
        ser.writeObject(chenlvtang);
        ser.close();

        //开始反序列化
        FileInputStream file1 = new FileInputStream("chenlvtang.bin");
        ObjectInputStream unser = new ObjectInputStream(file1);
        Object result = unser.readObject();
        unser.close();
        System.out.println(result);
    }
}
