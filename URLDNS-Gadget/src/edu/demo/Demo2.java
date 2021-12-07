package edu.demo;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class Demo2 {
    public static void main(String[] args){
        try {
            URL url = new URL("http://34419747.dns.1433.eu.org."); //Ur DNSLOG paltform

            //test if the put function can trigger DNS resolution
            HashMap hm = new HashMap();
            hm.put(url,"foobar");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}

