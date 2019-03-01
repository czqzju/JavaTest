//https://www.hackerrank.com/challenges/clique/problem
package Graphs;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;

import str.main;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result_ {

    /*
     * Complete the 'clique' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER n
     *  2. INTEGER m
     */
	static long turan(long n, long r) {
//		q,m=divmod(n,r)
//		return m*(m-1)//2+(2*m+r*q)*(r-1)*q//2 if m else n*q*(r-1)//2
		long q = n / r;
		long m = n % r;
		
		if(m > 0) return m * (m - 1) / 2 +(2 * m + r * q) * (r - 1) * q /2;
		else return n * q *(r - 1) / 2;

	}
    public static long clique(long n, long m) {
//    	l,r=1,3*n*n//(n*n-2*m)
//    		    while l<r:
//    		        x=(l+r)//2
//    		        if turan(n,x)>=m: r=x
//    		        else: l=x+1
//    		    return r
    	long l = 1;
    	long r = 3 * n *n / (n * n - 2 * m);
    	while(l < r) {
    		long x = (l + r) / 2;
    		if(turan(n , x) >= m) r = x;
    		else l = x + 1;
    	}
    	return r;
    }

}

public class Clique {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, t).forEach(tItr -> {
            try {
                String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

                long n = Integer.parseInt(firstMultipleInput[0]);

                long m = Integer.parseInt(firstMultipleInput[1]);

                long result = Result_.clique(n, m);
                System.out.println(result);
//                bufferedWriter.write(String.valueOf(result));
//                bufferedWriter.newLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
//        bufferedWriter.close();
    }
}
