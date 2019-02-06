package search;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

class Point{
	int x, y;
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public int getX() {
		return this.x;
	}
	public int getY() {
		return this.y;
	}
}

public class Count_Luck {
	
	static boolean isRight, found;
	
	private static boolean isSame(Point a, Point b) {
		if(a.getX() == b.getX() && a.getY() == b.getY()) return true;
		else return false;
	}
	
	private static boolean canGo(int x, int y, char[][] c, int[][] visited) {
		if(x < 0 || x >= c.length || y < 0 || y >= c[0].length ||visited[x][y] == 1 || c[x][y] == 'X') return false;
		else return true;
	}
	
	private static void bfs(Point start, Point end, char[][] c, int[][] visited, int cnt, int k) {
		if(found == true) return;
		
		if(isSame(start, end)) {
			if(cnt == k) {
				isRight = true;
				found = true;
				return;
			}
			else {
				isRight = false;
				found = true;
				return;
			}
			
		}
		int x = start.getX();
		int y = start.getY();
		visited[x][y] = 1;
		int count = 0;
		if(canGo(x, y - 1, c, visited)) {
			count++;
		}
		if(canGo(x, y + 1, c, visited)) {
			count++;
		}
		if(canGo(x - 1, y, c, visited)) {
			count++;
		}
		if(canGo(x + 1, y, c, visited)) {
			count++;
		}
		if(count > 1) cnt++;
		
		if(canGo(x, y - 1, c, visited)) {
			Point tmp = new Point(x, y- 1);
			bfs(tmp, end, c, visited, cnt, k);
		}
		if(canGo(x, y + 1, c, visited)) {
			Point tmp = new Point(x, y + 1);
			bfs(tmp, end, c, visited, cnt, k);
		}
		if(canGo(x - 1, y, c, visited)) {
			Point tmp = new Point(x - 1, y);
			bfs(tmp, end, c, visited, cnt, k);
		}
		if(canGo(x + 1, y, c, visited)) {
			Point tmp = new Point(x + 1, y);
			bfs(tmp, end, c, visited, cnt, k);
		}
		
	}
	
	private static String count_luck(String[] s, int k) {
		int n = s.length;
		int m = s[0].length();
		int[][] visited = new int[n][m];
		char[][] c = new char[n][m];
		int index = -1;
		Point start = null;
		Point end = null;
		
		for(int i = 0; i < n; i ++) c[i] = s[i].toCharArray();
		
		int foundSE = 0;
		for(int i = 0 ; i < n; i ++) {
			if(foundSE == 2) break;
			for(int j = 0; j < m; j++) {
				if(foundSE == 2) break;
				if(c[i][j] == '*') {
					end = new Point(i, j);
					foundSE++;
				}
				else if(c[i][j] == 'M') {
					start = new Point(i, j);
					foundSE++;
				}
			}
		}
		int cnt = 0;
		isRight = false;
		found = false;
		bfs(start, end, c, visited, cnt, k);
		
		
		if(isRight == true) return "Impressed";
		else return "Oops!";

	}
	
	private static Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) {
		int t = scanner.nextInt();
		scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
		for(int tItr = 0; tItr < t; tItr++) {
			int n = scanner.nextInt();
			int m = scanner.nextInt();
			scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
			String[] s = new String[n];
			for(int nItr = 0; nItr < n; nItr++) s[nItr] = scanner.nextLine();
			scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
			int k = scanner.nextInt();
			String res = count_luck(s, k);
			System.out.println(res);
		}
	}

}
