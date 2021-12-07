package edu.demo;

import java.net.MalformedURLException;
import java.net.URL;

/*Find out what's 'handler.hashcode'. Debug*/
public class Demo1 {
    public static void main(String[] args){
        try {
            URL url = new URL("http://c036c86e.dns.1433.eu.org."); //Ur DNSLOG paltform
            url.hashCode();//debug here
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
