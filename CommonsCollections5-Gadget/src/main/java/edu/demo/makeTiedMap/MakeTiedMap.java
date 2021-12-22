package edu.demo.makeTiedMap;

import edu.demo.makeLzMap.MakeLzMap;
import org.apache.commons.collections.keyvalue.TiedMapEntry;
import org.apache.commons.collections.map.LazyMap;

//lesson-2
public class MakeTiedMap {
    public static void main(String[] args){
        TiedMapEntry tiedMap = (TiedMapEntry)MakeTiedMap.makeTiedMap();
        //test
        tiedMap.toString();
    }

    public static Object makeTiedMap(){
        LazyMap lzMap = (LazyMap) MakeLzMap.makeLzMap();
        //make tiedMap
        return new TiedMapEntry(lzMap,"foobar");
    }
}
