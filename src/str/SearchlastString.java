package str;

public class SearchlastString {
	   public static void main(String[] args) {
	      String strOrig = "Hello world ,Hello world";
	      int lastIndex = strOrig.indexOf("Runoob");
	      if(lastIndex == - 1){
	         System.out.println("û���ҵ��ַ��� Runoob");
	      }else{
	         System.out.println("Runoob �ַ��������ֵ�λ�ã� "+ lastIndex);
	      }
	      
	      System.out.println(strOrig.substring(2,5) + strOrig.substring(8));
	   }
	}
