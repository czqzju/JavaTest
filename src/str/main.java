package str;

import java.util.StringTokenizer;

public class main {
    public static void main(String[] args) {
 
        String str = "This is String , split by StringTokenizer, created by runoob";
        StringTokenizer st = new StringTokenizer(str);
 
        System.out.println("----- ͨ���ո�ָ� ------");
        while (st.hasMoreElements()) {
            System.out.println(st.nextElement());
        }
 
        System.out.println("----- ͨ�����ŷָ� ------");
        StringTokenizer st2 = new StringTokenizer(str, ",");
 
        while (st2.hasMoreElements()) {
            System.out.println(st2.nextElement().toString().replaceFirst(" ", ""));
        }
        
        String upper = str.toUpperCase();
        System.out.println(upper);
        String lower = str.toLowerCase();
        System.out.println(lower);
    }
}
