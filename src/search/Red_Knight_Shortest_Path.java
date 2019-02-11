package search;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class Red_Knight_Shortest_Path {
	
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
	
	static boolean canGo(int x, int y, int n) {
		if(x < 0 || x >= n || y < 0 || y >= n) return false;
		else return true;
	}
	
	static void printShortestPath(int n, int i_start, int j_start, int i_end, int j_end) {
		if(!canReach(Math.abs(i_start - i_end), Math.abs(j_start - j_end))) {
    		System.out.println("Impossible");
    		return;
    	}
		Queue<String> res = new ArrayDeque<>();
		while(i_start != i_end || j_start != j_end) {
			int di = i_start - i_end;
			int dj = j_start - j_end;
			if(Math.abs(di) <= 2 * Math.abs(dj)) {
				if(di > 0) {
					int move = 1;
					String step = "";
					if(dj > 0) {
						move *= -1;
						step = "UL";
					}
					else step = "UR";
					while(i_start != i_end) {
						i_start -= 2;
						j_start += move;
						res.add(step);
					}
				}
				else if(di == 0) {
					int move = 2;
					String step = "";
					if(dj > 0) {
						move *= -1;
						step = "L";
					}
					else step = "R";
					while(j_start != j_end) {
						j_start += move;
						res.add(step);
					}
				}
				//di < 0
				else {
					if(dj > 0) {
						i_start += 2;
						j_start -= 1;
						res.add("LL");
					}
					//dj < 0
					else {
						if(Math.abs(di) < 2 * Math.abs(dj)) {
							if(canGo(i_start, j_start + 2, n)) {
								j_start += 2;
								res.add("R");
							}
							else {
								i_start += 2;
								j_start += 1;
								res.add("LR");
							}
							
						}
						//Math.abs(di) == 2 * Math.abs(dj)
						else {
							while(i_start != i_end) {
								i_start += 2;
								j_start += 1;
								res.add("LR");	
							}
								
						}
					}
					
				}
				
			}
			//Math.abs(di) > 2 * Math.abs(dj)
			else {
				if(dj > 0) {
					if(di > 0) {
						while(j_start != j_end) {
							i_start -= 2;
							j_start -= 1;
							res.add("UL");
						}
					}
					//di < 0
					else {
						if(canGo(i_start + 2, j_start + 1, n)) {
							i_start += 2;
							j_start += 1;
							res.add("LR");
						}
						else {
							i_start += 2;
							j_start -= 1;
							res.add("LL");
						}
					}
				}
				else if(dj == 0) {
					if(di > 0) {
						if(canGo(i_start - 2, j_start - 1, n)) {
							i_start -= 2;
							j_start -= 1;
							res.add("UL");
						}
						else if(canGo(i_start - 2, j_start + 1, n)) {
							i_start -= 2;
							j_start += 1;
							res.add("UR");
						}
					}
					//di < 0
					else {
						if(canGo(i_start + 2,  j_start + 1, n)) {
							i_start += 2;
							j_start += 1;
							res.add("LR");
						}
						else if(canGo(i_start + 2,  j_start - 1, n)) {
							i_start += 2;
							j_start -= 1;
							res.add("LL");
						}
					}
				}
				//dj < 0
				else {
					if(di > 0) {
						if(canGo(i_start - 2, j_start - 1, n)) {
							i_start -= 2;
							j_start -= 1;
							res.add("UL");
						}
						else {
							i_start -= 2;
							j_start += 1;
							res.add("UR");
						}
					}
					//di < 0
					else {
						i_start += 2;
						j_start += 1;
						res.add("LR");
					}
				}
			}
		}
		System.out.println(res.size());
		if(res.size() > 0) {
			while(!res.isEmpty()) System.out.printf("%s ", res.remove());
		}
		
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
