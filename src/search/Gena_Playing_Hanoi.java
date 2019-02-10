package search;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Gena_Playing_Hanoi {
	private static final Scanner scanner = new Scanner(System.in);
	 
    public static void main(String[] args) {
        int N = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
 
        int[] a = new int[N];
 
        String[] aItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
 
        long tuple=0 ;
        for (int i = 0; i < N; i++) {
            int aItem = Integer.parseInt(aItems[i]);
            a[i] = aItem;
            tuple = Tuple.add(tuple,i+1,aItem) ;
        }
     
        System.out.println(BFS.countMin(tuple,N)) ;
        scanner.close();
    }
   
    static class BFS{
        static int countMin(long tuple,int Ndisc){
            Queue<Long> queue = new LinkedList<>() ;
            Map<Long,Boolean> map = new HashMap<>() ;
            queue.add(tuple) ;
            queue.add(-1L) ;
            map.put(tuple,true) ;
            int count=0 ;
            while(!queue.isEmpty()){
                long curr = queue.remove() ;
                if(curr==-1L){
                    if(!queue.isEmpty()) queue.add(-1L) ;
                    count++ ;
                    continue ;
                }
               
                if(Tuple.isInitial(curr,Ndisc)) return count ;
               
                for(int i=1;i<=4;i++){
                    for(int j=1;j<=4;j++){
                        if(i==j) continue ;
                        long child = Tuple.move(curr,i,j) ;
                        if(child!=-1 && !map.containsKey(child)){      
                            map.put(child,true) ;
                            queue.add(child) ;
                        }
                    }
                }
            }
            return -1 ;
        }
    }
   
    static class Tuple{
        static boolean isInitial(long tuple,int Ndisc){
            for(int i=2;i<=4;i++){
                long mask = (long)(((1L<<(10*i))-1) - ((1L<<(10*(i-1)))-1)) ;
                if((tuple & mask)!=0) return false ;
            }
            return true ;
        }
       
        static long add(long tuple,int size,int rod){
            tuple|=(long)(1L<<(10*(rod-1)+size-1)) ;
            return tuple ;
        }
       
        static long remove(long tuple,int size,int rod){
            long mask = (long)(1L<<(10*(rod-1)+size-1)) ;
            if((tuple&mask)==0) return -1 ;
            return mask^tuple ;
        }
       
        static int getDisc(long tuple, int rod){
            long mask = (long)(((1L<<(10*rod))-1) - ((1L<<(10*(rod-1)))-1)) ;
            tuple = tuple & mask ;
            tuple = tuple>>(10*(rod-1)) ;
            return (int)tuple ;
        }
       
        static long move(long tuple,int rod1,int rod2){
            int disc1 = getDisc(tuple,rod1) ;
            int disc2 = getDisc(tuple,rod2) ;
            if(disc1==0) return -1;
            int min1 = getMin(disc1) ;
            int min2 = getMin(disc2) ;
            if(disc2!=0 && min1>min2) return -1 ;
            tuple = remove(tuple,min1,rod1) ;
            tuple = add(tuple,min1,rod2) ;
            return tuple ;
        }
       
        static int getMin(int disc){
            int mask = disc&(-disc) ;
            int count=0 ;
            while(mask>0){
                count++ ;
                mask=mask>>1 ;
            }
            return count ;
        }
    }
}
