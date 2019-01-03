import java.lang.annotation.Annotation;
import java.lang.annotation.*;
import java.lang.reflect.Method;
import java.net.PasswordAuthentication;

//@Retentionע��ָ��Loginע����Ա������
//��Ԫע�ͺ��潲��
@Retention(RetentionPolicy.RUNTIME)
//@Targetע��ָ��ע�������ε�Ŀ��(ֻ���Ƿ���)
@Target(ElementType.METHOD)
@interface Login{
    String username() default "zhangsan";
    String password() default "123456";
}

public class Main {
    public static void main(String[] args) throws Exception{
        //1.1ͨ�������ȡinfo������
        Method [] methods = Main.class.getMethods();
        for(Method method : methods) {
        	//2.1�жϸ÷������Ƿ����@Loginע��
            boolean annotationPresent = method.isAnnotationPresent(Login.class);
            if(annotationPresent){
                System.out.println("info�����ϴ���@Loginע��");
            }else{
                System.out.println("info�����ϲ�����@Loginע��");
            }
            //3.1��ȡ�����ϵ�����ע��
            Annotation[] annotations = method.getAnnotations();
            for(Annotation a : annotations){
                //�����@Loginע�ͣ���ǿ��ת����������username��������password������
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