package Recursion_and_Backtracking;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Recursive_Digit_Sum {

    // Complete the superDigit function below.
    static int superDigit(String n, int k) {
    	if(n.length() == 1 && k == 1) return Integer.valueOf(n);
    	long superD = 0;
    	for(int i = 0; i < n.length(); i++) {
    		superD += Integer.valueOf(n.substring(i, i+1));
    	}
    	superD *= k;
    	while(superD / 10 != 0) {
    		superD = superDi(superD);
    	}
    	return (int) superD;

    }
    
    static int superDi(long superD) {
    	int tmpS = 0;
    	do {
    		tmpS += superD % 10;
    		superD /= 10;
    	}while(superD != 0);
    	return tmpS;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nk = scanner.nextLine().split(" ");

        String n = nk[0];

        int k = Integer.parseInt(nk[1]);

        int result = superDigit(n, k);
        
        System.out.println(result);

//        bufferedWriter.write(String.valueOf(result));
//        bufferedWriter.newLine();
//
//        bufferedWriter.close();

        scanner.close();
    }
}

