package search;
//https://www.hackerrank.com/challenges/red-knights-shortest-path/problem

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

class Pos extends Point{
	
	List<Integer> path;

	public Pos(int x, int y) {
		super(x, y);
		this.path = new ArrayList<>();
	}
	
	public boolean isSame(Pos a) {
		if(this.x == a.getX() && this.y == a.getY()) return true;
		else return false;
	}
	
	public void copyPath(List<Integer> path) {
		this.path.addAll(path);
	}
	
	public void addPath(int idx) {
		this.path.add(idx);
	}
	
	public List<Integer> getPath() {
		return this.path;
	}
	
}

public class Red_Knight_Shortest_Path_Old {
	
	static final String[] move = {"L", "UL", "UR", "R", "LR", "LL"};
	static boolean canGo(int x, int y, int n, int[][] visited) {
		if(x < 0 || x >= n || y < 0 || y >= n || visited[x][y] == 1) return false;
		else return true;
	}
    // Complete the printShortestPath function below.
	static boolean canReach(int di, int dj) {
		if(di % 2 != 0) return false;
		else if(di % 4 == 0) {
			if(dj % 2 != 0) return false;
			else return true;
		}
		else {
			if(dj % 2 != 0) return true;
			else return false;
		}
	}
    static void printShortestPath(int n, int i_start, int j_start, int i_end, int j_end) {
    	if(!canReach(Math.abs(i_start - i_end), Math.abs(j_start - j_end))) {
    		System.out.println("Impossible");
    		return;
    	}
        Pos start = new Pos(i_start, j_start);
        Pos end = new Pos(i_end, j_end);
        if(end.isSame(start)) {
        	System.out.println(0);
        	return;
        }
        int[][] visited = new int[n][n];
        Queue<Pos> q = new ArrayDeque<>();
        Queue<Pos> qt = new ArrayDeque<>();
        q.add(start);
        
        
        while(!q.isEmpty()) {
        	while(!q.isEmpty()) {
        		Pos tmpNode = q.remove();
        		if(end.isSame(tmpNode)) {
        			List<Integer> res = tmpNode.getPath();
        			System.out.println(res.size());
        			for(int i = 0; i < res.size(); i++) System.out.printf("%s ", move[res.get(i)]);
        			return;
        		}
        		int x = tmpNode.getX();
        		int y = tmpNode.getY();
        		visited[x][y] = 1;
        		if(canGo(x - 2, y - 1, n, visited)) {
        			Pos tmp = new Pos(x - 2, y - 1);
        			tmp.copyPath(tmpNode.getPath());
        			tmp.addPath(1);
        			qt.add(tmp);
        		} 
        		if(canGo(x - 2, y + 1, n, visited)) {
        			Pos tmp = new Pos(x - 2, y + 1);
        			tmp.copyPath(tmpNode.getPath());
        			tmp.addPath(2);
        			qt.add(tmp);
        		} 
        		if(canGo(x, y + 2, n, visited)) {
        			Pos tmp = new Pos(x, y + 2);
        			tmp.copyPath(tmpNode.getPath());
        			tmp.addPath(3);
        			qt.add(tmp);
        		} 
        		if(canGo(x + 2, y + 1, n, visited)) {
        			Pos tmp = new Pos(x + 2, y + 1);
        			tmp.copyPath(tmpNode.getPath());
        			tmp.addPath(4);
        			qt.add(tmp);
        		}
        		if(canGo(x + 2, y - 1, n, visited)) {
        			Pos tmp = new Pos(x + 2, y - 1);
        			tmp.copyPath(tmpNode.getPath());
        			tmp.addPath(5);
        			qt.add(tmp);
        		} 
        		if(canGo(x, y - 2, n, visited)) {
        			Pos tmp = new Pos(x, y - 2);
        			tmp.copyPath(tmpNode.getPath());
        			tmp.addPath(0);
        			qt.add(tmp);
        		}
        	}
        	Queue<Pos> t = qt;
        	qt = q;
        	q = t;
        }
        System.out.println("Impossible");

    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        String[] i_startJ_start = scanner.nextLine().split(" ");

        int i_start = Integer.parseInt(i_startJ_start[0]);

        int j_start = Integer.parseInt(i_startJ_start[1]);

        int i_end = Integer.parseInt(i_startJ_start[2]);

        int j_end = Integer.parseInt(i_startJ_start[3]);

        printShortestPath(n, i_start, j_start, i_end, j_end);

        scanner.close();
    }
}

