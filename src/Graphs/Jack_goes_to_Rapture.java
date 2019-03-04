//https://www.hackerrank.com/challenges/jack-goes-to-rapture/problem
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
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

import java.io.BufferedReader; 
import java.io.IOException; 
import java.io.InputStreamReader; 
import java.util.Scanner; 
import java.util.StringTokenizer; 

class Result_1 {

	public static void getCost(int gNodes, Map<Integer, HashMap<Integer, Integer>> relations) {

    	int end = gNodes - 1;
    	int start = 0;

    	Set<Integer> visited = new HashSet<>();
    	Set<Integer> waited = new HashSet<>();
    	int tmpMin = 10000001;
    	int minNode = -1;
    	int[] dis = new int[gNodes];
    	Arrays.fill(dis, 10000001);
    	dis[start] = 0;
    	visited.add(start);
    	if(relations.containsKey(start)) {
    		for(Map.Entry<Integer, Integer> each : relations.get(start).entrySet()) {
    			if(visited.contains(each.getKey())) continue;
    			dis[each.getKey()] = each.getValue();
    			waited.add(each.getKey());  			
    		}
    	}
    	
    	while(!visited.contains(end) && !waited.isEmpty()) {
    		boolean first = true;
    		for(int tmp : waited) {
    			if(first) {
    				tmpMin = dis[tmp];
    				minNode = tmp;
    				first = false;
    			}
    			if(dis[tmp] < tmpMin) {
    				tmpMin = dis[tmp];
    				minNode = tmp;
    			}
    		}
    		waited.remove(minNode);
    		visited.add(minNode);
    		if(relations.containsKey(minNode)) {
    			for(Map.Entry<Integer, Integer> each : relations.get(minNode).entrySet()) {
    				if(!visited.contains(each.getKey())) {
    					int tmpDiff = each.getValue() - dis[minNode] <= 0? 0 : each.getValue() - dis[minNode];   					
    					dis[each.getKey()] = dis[minNode] + tmpDiff < dis[each.getKey()]? dis[minNode] + tmpDiff : dis[each.getKey()];
    					waited.add(each.getKey());
    				}
    			}
    		}
    		
    	}
    	System.out.println(dis[end] == 10000001? "NO PATH EXISTS" : dis[end]);
    	

    }

}


public class Jack_goes_to_Rapture {
	static class Reader 
    { 
        final private int BUFFER_SIZE = 1 << 16; 
        private DataInputStream din; 
        private byte[] buffer; 
        private int bufferPointer, bytesRead; 
  
        public Reader() 
        { 
            din = new DataInputStream(System.in); 
            buffer = new byte[BUFFER_SIZE]; 
            bufferPointer = bytesRead = 0; 
        } 
  
        public Reader(String file_name) throws IOException 
        { 
            din = new DataInputStream(new FileInputStream(file_name)); 
            buffer = new byte[BUFFER_SIZE]; 
            bufferPointer = bytesRead = 0; 
        } 
  
        public String readLine() throws IOException 
        { 
            byte[] buf = new byte[64]; // line length 
            int cnt = 0, c; 
            while ((c = read()) != -1) 
            { 
                if (c == '\n') 
                    break; 
                buf[cnt++] = (byte) c; 
            } 
            return new String(buf, 0, cnt); 
        } 
  
        public int nextInt() throws IOException 
        { 
            int ret = 0; 
            byte c = read(); 
            while (c <= ' ') 
                c = read(); 
            boolean neg = (c == '-'); 
            if (neg) 
                c = read(); 
            do
            { 
                ret = ret * 10 + c - '0'; 
            }  while ((c = read()) >= '0' && c <= '9'); 
  
            if (neg) 
                return -ret; 
            return ret; 
        } 
  
        public long nextLong() throws IOException 
        { 
            long ret = 0; 
            byte c = read(); 
            while (c <= ' ') 
                c = read(); 
            boolean neg = (c == '-'); 
            if (neg) 
                c = read(); 
            do { 
                ret = ret * 10 + c - '0'; 
            } 
            while ((c = read()) >= '0' && c <= '9'); 
            if (neg) 
                return -ret; 
            return ret; 
        } 
  
        public double nextDouble() throws IOException 
        { 
            double ret = 0, div = 1; 
            byte c = read(); 
            while (c <= ' ') 
                c = read(); 
            boolean neg = (c == '-'); 
            if (neg) 
                c = read(); 
  
            do { 
                ret = ret * 10 + c - '0'; 
            } 
            while ((c = read()) >= '0' && c <= '9'); 
  
            if (c == '.') 
            { 
                while ((c = read()) >= '0' && c <= '9') 
                { 
                    ret += (c - '0') / (div *= 10); 
                } 
            } 
  
            if (neg) 
                return -ret; 
            return ret; 
        } 
  
        private void fillBuffer() throws IOException 
        { 
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE); 
            if (bytesRead == -1) 
                buffer[0] = -1; 
        } 
  
        private byte read() throws IOException 
        { 
            if (bufferPointer == bytesRead) 
                fillBuffer(); 
            return buffer[bufferPointer++]; 
        } 
  
        public void close() throws IOException 
        { 
            if (din == null) 
                return; 
            din.close(); 
        } 
    } 
    public static void main(String[] args) throws IOException {
    	Reader s=new Reader(); 

        int gNodes = s.nextInt();
        int gEdges = s.nextInt();
        Map<Integer, HashMap<Integer, Integer>> relations = new HashMap<>();
        
        for(int i = 0; i < gEdges; i++) {
    		int v1 = s.nextInt() -1;
    		int v2 = s.nextInt() - 1;
    		int weight = s.nextInt();
    		if(v1 == v2) continue;
    		if(relations.containsKey(v1)) {
    			if(relations.get(v1).containsKey(v2)) {
    				if(relations.get(v1).get(v2) > weight) relations.get(v1).put(v2, weight);
    			}
    			else relations.get(v1).put(v2, weight);
    		}
    		else {
    			HashMap<Integer, Integer> tmp = new HashMap<>();
    			relations.put(v1, tmp);
    			relations.get(v1).put(v2, weight);
    		}
    		if(relations.containsKey(v2)) {
    			if(relations.get(v2).containsKey(v1)) {
    				if(relations.get(v2).get(v1) > weight) relations.get(v2).put(v1, weight);
    			}
    			else relations.get(v2).put(v1, weight);
    		}
    		else {
    			HashMap<Integer, Integer> tmp = new HashMap<>();
    			relations.put(v2, tmp);
    			relations.get(v2).put(v1, weight);
    		}	
    	}



        Result_1.getCost(gNodes, relations);


    }
}
