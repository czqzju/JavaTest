package Graphs;

import java.io.*;

import java.math.*;
import java.security.*;
import java.sql.SQLNonTransientConnectionException;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

import javax.swing.Painter;

import org.omg.IOP.ENCODING_CDR_ENCAPS;


public class Matrix {
	static boolean[] hasMachine;
	static int[] uset;
	
	private static void sort(int[][] ob, final int order) {    
        Arrays.sort(ob, new Comparator<Object>() {    
            public int compare(Object o1, Object o2) {    
                int[] one = (int[]) o1;    
                int[] two = (int[]) o2;    
  
                int k = order;    
                if (one[k] < two[k]) {    
                    return 1;    
                } else if (one[k] > two[k]) {    
                    return -1;    
                }    
   
                return 0;    
            }    
        });   
    }    


    static int minTime(int n, int[][] roads, int[] machines) {
    	hasMachine = new boolean[n];
    	uset = new int[n];
    	int res = 0;
    	
    	sort(roads, 2);
    	
    	for(int i = 0; i < n; i++) hasMachine[machines[i]] = true; 
    	for(int i = 0; i < n; i++) uset[i] = i;
    	
    	for(int i = 0; i < roads.length; i++) {
    		int beforeNode = roads[i][0];
    		int afterNode = roads[i][1];
    		int beforeRoot = findRoot(beforeNode);
    		int afterRoot = findRoot(afterNode);
    		if(hasMachine[beforeRoot] == true && hasMachine[afterRoot] == true) {
    			res += roads[i][2];
    		}
    		else if(hasMachine[beforeRoot] == true && hasMachine[afterRoot] == false) {
    			uset[afterRoot] = beforeRoot;
    		}
    		else if(hasMachine[beforeRoot] == false && hasMachine[afterRoot] == true) {
    			uset[afterRoot] = beforeRoot;
    			hasMachine[beforeRoot] = true;
    		}
    		else {
    			uset[afterRoot] = beforeRoot;
    		}
    		
    	}
    	
    	
    	
    	
    	
    	
    	return 0;
//    	

    }
    
    static int findRoot(int node) {
    	if(uset[node] == node) return node;
    	else return findRoot(uset[node]);
    }
    

    
    private static final Scanner scanner = new Scanner(System.in);
    

//    public static void main(String[] args) throws IOException {
//        BufferedReader bufferedReader = new BufferedReader(new FileReader("C:\\Users\\flora\\Desktop\\input.txt"));
//
//        String[] nk = bufferedReader.readLine().split(" ");
//
//        int n = Integer.parseInt(nk[0]);
//
//        int k = Integer.parseInt(nk[1]);
//
//        int[][] roads = new int[n - 1][3];
//
//        for (int i = 0; i < n - 1; i++) {
//            String[] roadsRowItems = bufferedReader.readLine().split(" ");
////            bufferedReader.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
//
//            for (int j = 0; j < 3; j++) {
//                int roadsItem = Integer.parseInt(roadsRowItems[j]);
//                roads[i][j] = roadsItem;
//            }
//        }
//
//        int[] machines = new int[k];
//
//        for (int i = 0; i < k; i++) {
//            int machinesItem = Integer.parseInt(bufferedReader.readLine());
////            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
//            machines[i] = machinesItem;
//        }
//
//        int result = minTime(n, roads, machines);
//        System.out.println(result);
//
//        
//
////        bufferedWriter.write(String.valueOf(result));
////        bufferedWriter.newLine();
////
////        bufferedWriter.close();
//
//        bufferedReader.close();
//    }
 
    public static void main(String[] args) throws IOException {

        String[] nk = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nk[0]);

        int k = Integer.parseInt(nk[1]);

        int[][] roads = new int[n - 1][3];

        for (int i = 0; i < n - 1; i++) {
            String[] roadsRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < 3; j++) {
                int roadsItem = Integer.parseInt(roadsRowItems[j]);
                roads[i][j] = roadsItem;
            }
        }

        int[] machines = new int[k];

        for (int i = 0; i < k; i++) {
            int machinesItem = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
            machines[i] = machinesItem;
        }

        int result = minTime(n, roads, machines);


        scanner.close();
    }
}
