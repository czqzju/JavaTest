import java.util.regex.Matcher;
import java.util.regex.Pattern;
 
public class RegexMatches
{
   private static String REGEX = "a*b";
   private static String INPUT = "aabfooaabfooabfoobkkk";
   private static String REPLACE = "-";
   public static void main(String[] args) {
      Pattern p = Pattern.compile(REGEX);
      // 获取 matcher 对象
      Matcher m = p.matcher(INPUT);
      StringBuffer sb = new StringBuffer();
      int i = 1;
      while(m.find()){
         m.appendReplacement(sb,REPLACE);
         if(i == 2) {
        	 break;
         }
         i++;
         
      }
//      m.appendTail(sb);
      System.out.println(sb.toString());
   }
}