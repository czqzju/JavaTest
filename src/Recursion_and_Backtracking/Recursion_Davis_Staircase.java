package Recursion_and_Backtracking;

import java.io.*;
import java.math.*;
import java.security.*;
import java.sql.SQLNonTransientConnectionException;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

import javax.swing.plaf.multi.MultiButtonUI;

public class Recursion_Davis_Staircase {

    // Complete the stepPerms function below.
	
	private static int[][] mult(int[][] a){
		int[][] b = {{0,1,0}, {0,0,1}, {1,1,1}};
		int[][] res = {{0,0,0}, {0,0,0}, {0,0,0}};
		for(int i = 0; i < 3; i++)
			for(int j = 0; j < 3; j++)
				for(int k = 0; k < 3; k++) {
					res[i][j] += a[i][k]*b[k][j];
				}
		return res;
	}

    static int stepPerms(int n) {
    	int[][] a = {{0,1,0}, {0,0,1}, {1,1,1}};
    	if(n == 1) return 1;
    	else if(n == 2) return 2;
    	else if(n == 3) return 4;
    	else {
    		for(int i = 0; i < n-4; i++)
    			a = mult(a);
    	}
    	return a[2][0]*1 + a[2][1]*2 + a[2][2]*4;

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

