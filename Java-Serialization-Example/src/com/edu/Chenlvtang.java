package com.edu;
import java.io.Serializable;

public class Chenlvtang implements Serializable{
    private String name;
    private String level;

    public Chenlvtang(){
        this.level = "noob";
        this.name = "chenlvtang";
    }

    private void readObject(java.io.ObjectInputStream stream) throws Exception {
        stream.defaultReadObject();//调用默认的方法
        Runtime.getRuntime().exec("calc.exe");
    }
}
