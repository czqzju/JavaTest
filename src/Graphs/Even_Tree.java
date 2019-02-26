package Graphs;
//https://www.hackerrank.com/challenges/even-tree/problem
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



public class Even_Tree {

    // Complete the evenForest function below.
	static int calcNumOfSub(int root, int[] numOfSubs, int[] visited, Map<Integer, Set<Integer>> edges) {
		visited[root] = 1;
		if(edges.containsKey(root) == true) {
			Set<Integer> tmp = edges.get(root);
			for( int child : tmp) {
				if(visited[child] == 0) {
					numOfSubs[root] += calcNumOfSub(child, numOfSubs, visited, edges);
				}
			}
		}
		return numOfSubs[root];
	}
    static int evenForest(int t_nodes, int t_edges, List<Integer> t_from, List<Integer> t_to) {
    	Map<Integer, Set<Integer>> edges = new HashMap<>();
    	int[] numOfSubs = new int[t_nodes + 1];
    	int[] visited = new int[t_nodes + 1];
    	
    	for(int i = 1; i < t_nodes + 1; i++) numOfSubs[i] = 1;
    	
    	int root = 0;
    	int numOfChildren = 0;
    	for(int i = 0; i < t_edges; i++) {
    		int a = t_from.get(i);
    		int b = t_to.get(i);
    		if(edges.containsKey(a) ==true) edges.get(a).add(b);
    		else {
    			Set<Integer> s = new HashSet<>();
    			edges.put(a, s);
    			edges.get(a).add(b);
    		}
    		if(edges.containsKey(b) ==true) edges.get(b).add(a);
    		else {
    			Set<Integer> s = new HashSet<>();
    			edges.put(b, s);
    			edges.get(b).add(a);
    		}
    		if(edges.get(a).size() > numOfChildren) {
    			root = a;
    			numOfChildren = edges.get(a).size();
    		}
    		if(edges.get(b).size() > numOfChildren) {
    			root = b;
    			numOfChildren = edges.get(b).size();
    		}
    	}
    	calcNumOfSub(root, numOfSubs, visited, edges);
    	int cnt = 0;
    	for(int i = 1; i < t_nodes + 1; i++)
    		if(numOfSubs[i] % 2 == 0)
    			cnt++;
    	return cnt - 1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] tNodesEdges = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int t_nodes = Integer.parseInt(tNodesEdges[0]);
        int t_edges = Integer.parseInt(tNodesEdges[1]);

        List<Integer> t_from = new ArrayList<>();
        List<Integer> t_to = new ArrayList<>();

        IntStream.range(0, t_edges).forEach(i -> {
            try {
                String[] tFromTo = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

                t_from.add(Integer.parseInt(tFromTo[0]));
                t_to.add(Integer.parseInt(tFromTo[1]));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        int res = evenForest(t_nodes, t_edges, t_from, t_to);
        System.out.println(res);

//        bufferedWriter.write(String.valueOf(res));
//        bufferedWriter.newLine();

        bufferedReader.close();
//        bufferedWriter.close();
    }
}
