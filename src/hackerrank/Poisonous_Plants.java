package hackerrank;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Poisonous_Plants {

    // Complete the poisonousPlants function below.
    static int poisonousPlants(int[] p) {
//    	int res = 0;
//        Queue<Integer> q1 = new LinkedList<Integer>();
//        Queue<Integer> q2 = new LinkedList<Integer>();
//        
//        for(int i = p.length - 1; i >= 0 ; i--) q1.add(p[i]);
//        
//        int l1;
//        int l2;
//        do {
//            res++;
//            l1 = q1.size();
//            while(!q1.isEmpty()) {
//                int tmp = q1.remove();
//                if(!q1.isEmpty()) {
//                    int left = q1.peek();
//                    if(tmp <= left) {
//                        q2.add(tmp);
//                    }
//                }
//                else {
//                    q2.add(tmp);
//                }
//            }
//            l2 = q2.size();
//            Queue<Integer> t = q1;
//            q1 = q2;
//            q2 = t;
//            if(l1 == l2) res--;
//        }while(l2 < l1);
    	
    	Stack<Integer> s = new Stack<Integer>();
    	for(int i = p.length - 1 ; i >= 0 ; i--) {
    		if(i - 1 >= 0) {
    			if(p[i] <= p[i-1]) {
    				s.add(p[i]);
    			}
    		}
    		else s.add(p[i]);
    	}
    	if(s.size() == p.length) return 0;
    	int [] pp = new int[s.size()];
    	int idx = 0;
    	while(!s.isEmpty()) {
    		pp[idx] = s.pop();
    		idx++;
    	}
    	
    	boolean upOrder = false;
    	int maxC = 0;
    	int tmp = 0;
    	for(int i =0 ; i < pp.length; i++) {
    		if(s.isEmpty()) {
    			s.add(pp[i]);
    			tmp = 0;
    		}
    		else {
    			if(s.peek() < pp[i]) {
					if(tmp == 0) {
    					tmp = 1;
    				}
    				upOrder = true;
					s.add(pp[i]);
    			}
    			else {
    				while(!s.isEmpty() && s.peek() >= pp[i]) {
        				upOrder = false;
        				s.pop();
        			}
    				if(s.isEmpty()) {
        				s.add(pp[i]);
        				tmp = 0;
        				
        			}
        			else {
        				if(upOrder == false) {
        					tmp++;
        					upOrder = true;
        					s.add(pp[i]);
        				}
        				else {
        					s.add(pp[i]);
        				}
        			}
    			}
    		}
    		if(tmp > maxC) maxC = tmp;
    	}

        return maxC+1;

    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] p = new int[n];

        String[] pItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int pItem = Integer.parseInt(pItems[i]);
            p[i] = pItem;
        }

        int result = poisonousPlants(p);
        System.out.println(result);

//        bufferedWriter.write(String.valueOf(result));
//        bufferedWriter.newLine();
//
//        bufferedWriter.close();

        scanner.close();
    }
}
