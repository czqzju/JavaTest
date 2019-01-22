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
    	
    	List<ArrayList<Integer>> s = new ArrayList<ArrayList<Integer>>();
    	int idx = 0;
    	s.add(new ArrayList<Integer>());
    	
    	while(idx < p.length) {
    		ArrayList<Integer> tmp = s.get(s.size()-1);
    		if(tmp.isEmpty()) {
    			tmp.add(p[idx]);
    			idx++;
    		}
    		else if(tmp.get(tmp.size()-1) >= p[idx]){
    			tmp.add(p[idx]);
    			idx++;
    		}
    		else {
    			ArrayList<Integer> tmpNew = new ArrayList<Integer>();
    			tmpNew.add(p[idx]);
    			s.add(tmpNew);
    			idx++;
    		}
    	}
    	int cnt = 0;
    	while(s.size() > 1) {
    		int length = s.size();
    		for(int i = length - 1; i > 0; i--) {
    			ArrayList<Integer> after = s.get(i);
    			ArrayList<Integer> before = s.get(i-1);
    					
    			if(after.get(0) > before.get(before.size()-1)) {
    				after.remove(0);
    				if(after.isEmpty() || after.get(0) <= before.get(before.size()-1)) {
    					before.addAll(after);
    					s.remove(after);
    				}
    			}
    		}
    		cnt++;
    	}
    	
    	return cnt;

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
