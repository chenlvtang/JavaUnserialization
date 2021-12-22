package edu.demo.makeTiedMap;

import edu.demo.makeLzMap.MakeLzMap;
import org.apache.commons.collections.keyvalue.TiedMapEntry;
import org.apache.commons.collections.map.LazyMap;

import java.util.HashMap;

//lesson-2
public class MakeTiedMap {
    public static void main(String[] args){
        TiedMapEntry tiedMap = (TiedMapEntry)MakeTiedMap.makeTiedMap();
        //test
        tiedMap.toString();
    }

    public static Object makeTiedMap(){
        //make temp tiedMap(don't put lzMap to it) to avoid RCE during making Payload
        return new TiedMapEntry(new HashMap(),"foobar");
    }
}
