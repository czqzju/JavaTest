import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.FormatFlagsConversionMismatchException;
import java.util.Scanner;

import animals.animal;

public class Palindrome {
	public static void main(String [] args) {
		Scanner scan = new Scanner(System.in);
		int num = scan.nextInt();
		if(num < 0) System.out.println(false);
		else if(num == 0) System.out.println(true);
		else {
			String tmp = Integer.toString(num);
			boolean flag = true;
			for(int l = 0, r = tmp.length() - 1; l <= r; l++, r--) {
				if(tmp.charAt(l) != tmp.charAt(r)) {
					flag = false;
					break;
				}
			}
			System.out.println(flag);
		}
		BigInteger a = new BigInteger(Integer.toString(num));
		
		
	}
	
	

}
