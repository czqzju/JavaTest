public class StringDemo {
    public static void main(String args[]) {
    	String fs;
    	fs = String.format("浮点型变量的值为 " +
    	                   "%f, 整型变量的值为 " +
    	                   " %d, 字符串变量的值为 " +
    	                   " %s", 1.5, 10, "haha");
    	System.out.println(fs.charAt(2));
   }
}