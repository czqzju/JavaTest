public class StringDemo {
    public static void main(String args[]) {
    	String fs;
    	fs = String.format("�����ͱ�����ֵΪ " +
    	                   "%f, ���ͱ�����ֵΪ " +
    	                   " %d, �ַ���������ֵΪ " +
    	                   " %s", 1.5, 10, "haha");
    	System.out.println(fs.charAt(2));
   }
}