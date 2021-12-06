package client;

import javax.naming.*;

public class myClient {
    public static void main(String[] args) throws Exception {
        String uri = "rmi://127.0.0.1:1097/hacker";
        Context ctx = new InitialContext();
        ctx.lookup(uri);
    }
}