import java.lang.annotation.Annotation;
import java.lang.annotation.*;
import java.lang.reflect.Method;
import java.net.PasswordAuthentication;

//@Retention注解指定Login注解可以保留多久
//（元注释后面讲）
@Retention(RetentionPolicy.RUNTIME)
//@Target注解指定注解能修饰的目标(只能是方法)
@Target(ElementType.METHOD)
@interface Login{
    String username() default "zhangsan";
    String password() default "123456";
}

public class Main {
    public static void main(String[] args) throws Exception{
        //1.1通过反射获取info方法类
        Method [] methods = Main.class.getMethods();
        for(Method method : methods) {
        	//2.1判断该方法上是否存在@Login注释
            boolean annotationPresent = method.isAnnotationPresent(Login.class);
            if(annotationPresent){
                System.out.println("info方法上存在@Login注释");
            }else{
                System.out.println("info方法上不存在@Login注释");
            }
            //3.1获取方法上的所有注释
            Annotation[] annotations = method.getAnnotations();
            for(Annotation a : annotations){
                //如果是@Login注释，则强制转化，并调用username方法，和password方法。
                if(a !=null && a instanceof Login){
                    String username = ((Login)a).username();
                    String password = ((Login)a).password();
                    method.invoke(new Main(), username, password);
                }
                System.out.println(a);
            }
        }
        
    }
    
    @Login(username = "New", password = "654321")
    @Deprecated
    public void info(String user, String pass){
    	System.out.println("username:" + user);
        System.out.println("password:" + pass);
    }
}