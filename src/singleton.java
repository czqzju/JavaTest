import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import java.lang.reflect.*;
import org.apache.commons.codec.digest.DigestUtils;


class Singleton{
    private volatile static Singleton instance;
    public static String str;
    private Singleton() {}
    
    public static Singleton getSingleInstance(){
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    } 

}