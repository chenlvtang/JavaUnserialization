package com.edu;

public class ExecDemo {
    public static void  main(String[] args) throws Exception{
//        Runtime.getRuntime().exec("calc.exe");//打开一个记事本
        Runtime run  = Runtime.getRuntime();
        run.exec("calc.exe");
    }

}
