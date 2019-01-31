import java.text.DateFormatSymbols;
import java.util.ArrayList;

public class sample {
	public static void main(String[] args) {
		ArrayList<Integer> path = new ArrayList<Integer>();
		
		path.add(0);
		System.out.println(path.size());
		dfs(path);
		System.out.println(path.size());
		
	}
	
	private static void dfs(ArrayList<Integer> path) {
		path.add(1);
		path.add(2);
	}

}
