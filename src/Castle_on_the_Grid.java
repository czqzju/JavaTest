import java.io.*;
import java.math.*;
import java.security.*;
import java.sql.SQLNonTransientConnectionException;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

class Point{
    int x, y;
    Point(int x, int y){
        this.x = x;
        this.y = y;
    }
}
public class Castle_on_the_Grid {
	static int game[][], visited[][];
	static Queue<Point> move = new LinkedList<Point>();
	static int n;
    
	static int minimumMoves(String [] grid, int startX, int startY, int goalX, int goalY) {
		game = new int[n][n];
		visited = new int[n][n];
		
		
		for(int i = 0; i < n; i++)
			for(int j = 0; j < n; j++) {
				if(grid[i].charAt(j) == '.') {
					game[i][j] = 100;
				}
				else {
					game[i][j] = -1;
				}
			}
		return;
	}

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        String[] grid = new String[n];
        for(int grid_i = 0; grid_i < n; grid_i++){
            grid[grid_i] = in.next();
        }
        int startX = in.nextInt();
        int startY = in.nextInt();
        int goalX = in.nextInt();
        int goalY = in.nextInt();
        int result = minimumMoves(grid, startX, startY, goalX, goalY);
        System.out.println(result);
        in.close();
    }

}
