package Recursion_and_Backtracking;

import java.io.*;
import java.math.*;
import java.security.*;
import java.sql.SQLNonTransientConnectionException;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Recursion_Davis_Staircase {

    // Complete the stepPerms function below.
	static int cnt;
    static int stepPerms(int n) {
    	cnt = 0;
    	stepOne(n);
    	
    	return cnt % 1000000007;

    }
    
    static void stepOne(int n) {
    	if(n == 0) cnt++;
    	else if(n > 0) {
    		stepOne(n - 1);
    		stepOne(n - 2);
    		stepOne(n - 3);
    	}
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int s = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int sItr = 0; sItr < s; sItr++) {
            int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int res = stepPerms(n);
            System.out.println(res);
//            bufferedWriter.write(String.valueOf(res));
//            bufferedWriter.newLine();
        }

//        bufferedWriter.close();

        scanner.close();
    }
}

