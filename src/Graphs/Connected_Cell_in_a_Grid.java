package Graphs;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

import javax.xml.soap.Node;

class Point{
    int x, y;
    Point(int x, int y){
        this.x = x;
        this.y = y;
    }
}

public class Connected_Cell_in_a_Grid {

    static int[][] visited;
    static int n, m;
    static int res = 0;

    // Complete the maxRegion function below.
    static int maxRegion(int[][] grid) {
    	visited = new int[n][m];
    	Queue<Point> q = new ArrayDeque<>();
    	
    	
    	for(int i = 0; i < n; i++)
    		for(int j = 0; j < m; j++) {
    			if(grid[i][j] == 0 || visited[i][j] == 1) continue;
    			
    			int cnt = 1;
    			visited[i][j] = 1;
    			q.add(new Point(i, j));
    			
    			while(!q.isEmpty()) {
    				Point node = q.remove();
    				if(isRightCell(node.x - 1, node.y - 1, grid)) {
    					cnt++;
    					visited[node.x - 1][node.y - 1] = 1;
    					q.add(new Point(node.x - 1, node.y - 1));
    				}
    				if(isRightCell(node.x - 1, node.y, grid)) {
    					cnt++;
    					visited[node.x - 1][node.y] = 1;
    					q.add(new Point(node.x - 1, node.y));
    				}
    				if(isRightCell(node.x - 1, node.y + 1, grid)) {
    					cnt++;
    					visited[node.x - 1][node.y + 1] = 1;
    					q.add(new Point(node.x - 1, node.y + 1));
    				}
    				if(isRightCell(node.x, node.y - 1, grid)) {
    					cnt++;
    					visited[node.x][node.y - 1] = 1;
    					q.add(new Point(node.x, node.y - 1));
    				}
    				if(isRightCell(node.x, node.y + 1, grid)) {
    					cnt++;
    					visited[node.x][node.y + 1] = 1;
    					q.add(new Point(node.x, node.y + 1));
    				}
    				if(isRightCell(node.x + 1, node.y - 1, grid)) {
    					cnt++;
    					visited[node.x + 1][node.y - 1] = 1;
    					q.add(new Point(node.x + 1, node.y - 1));
    				}
    				if(isRightCell(node.x + 1, node.y, grid)) {
    					cnt++;
    					visited[node.x + 1][node.y] = 1;
    					q.add(new Point(node.x + 1, node.y));
    				}
    				if(isRightCell(node.x + 1, node.y + 1, grid)) {
    					cnt++;
    					visited[node.x + 1][node.y + 1] = 1;
    					q.add(new Point(node.x + 1, node.y + 1));
    				}
    			}
    			res = cnt > res ? cnt : res;
    		}
    	return res;

    }
    
    private static boolean isRightCell(int x, int y, int[][] grid) {
    	if(x < 0 || x >= n || y < 0 || y >= m || grid[x][y] == 0 || visited[x][y] == 1) {
    		return false;
    	}
    	else {
    		return true;
    	}
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        m = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[][] grid = new int[n][m];

        for (int i = 0; i < n; i++) {
            String[] gridRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < m; j++) {
                int gridItem = Integer.parseInt(gridRowItems[j]);
                grid[i][j] = gridItem;
            }
        }

        int res = maxRegion(grid);
        System.out.println(res);

//        bufferedWriter.write(String.valueOf(res));
//        bufferedWriter.newLine();
//
//        bufferedWriter.close();

        scanner.close();
    }
}
