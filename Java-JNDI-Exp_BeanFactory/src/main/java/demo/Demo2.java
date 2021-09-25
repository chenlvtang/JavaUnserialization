package demo;

import javax.el.ELProcessor;
/*
* to test the ElProcessor
*/
public class Demo2 {
    public static void main(String[] args){
        ELProcessor myELpro = new ELProcessor();
        myELpro.eval("Runtime.getRuntime().exec(\"calc.exe\")");
    }
}
