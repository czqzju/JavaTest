import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class Test{
  public static void main(String args[]){
    
//	  String regex = "\\b(\\w+)(?:\\s+\\1\\b)+";
//      Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
//
//      Scanner in = new Scanner(System.in);
//      int numSentences = Integer.parseInt(in.nextLine());
//      
//      while (numSentences-- > 0) {
//          String input = in.nextLine();
//          
//          Matcher m = p.matcher(input);
//          
//          // Check for subsequences of input that match the compiled pattern
//          while (m.find()) {
//              input = input.replaceAll(m.group(), m.group(1));
//          }
//          
//          // Prints the modified sentence.
//          System.out.println(input);
//      }
//      
//      in.close();
	  int cnt = 0;
	  
	  for(int i =0; i <= 23; i++) {
		  List<Integer> res = generateAllDbReps(i);
		  System.out.println(res.toString());
//		  compute(res, i);
		  cnt += res.size();
	  }
	  
	  
	  System.out.println(cnt);
  }
  private static void compute(List<Integer> res, int num) {
	  List<Integer> resN = new ArrayList<Integer>();
	  int tmp = 0;
	  for(int i = 0; i < res.size(); i++) {
		  int cnt = 0;
		  int valueOfD = 0;
		  tmp = res.get(i);
		  while(tmp > 0) {
			  int rem = tmp % 10;
			  valueOfD += rem * (int)Math.pow(2, cnt);
			  cnt ++;
			  tmp /= 10;
		  }
		  if(num != valueOfD) resN.add(res.get(i));
	  }
	  System.out.println(resN.toString());
	
}
  public static List<Integer> generateAllDbReps(int n)
  {
      List<Integer> result = new ArrayList<Integer>();
      if (n == 0)
      {
          result.add(0);
          return result;
      }

      int place = (int)(Math.log(n)/Math.log(2));
      Integer dbNum = 0;

      internalGenerateAllDbReps(n, place, dbNum, result);
      return result;
  }

  private static void internalGenerateAllDbReps(Integer n, int place, Integer dbNum, List<Integer> result)
  {
      if (n == 0)
      {
          result.add(dbNum);
          return;
      }

      if (place < 0)
      {
          return;
      }

      Integer pp2 = (Integer)(int)Math.pow(2, place);
      Integer maxDigit = n / pp2;
      if (maxDigit > 9)
      {
          return;
      }

      Integer pp10 = (Integer)(int)Math.pow(10, place);
      for (int digit = 0; digit <= maxDigit; digit++)
      {
          internalGenerateAllDbReps(n - (digit * pp2), place-1, dbNum + (digit * pp10), result);
      }
  }
}