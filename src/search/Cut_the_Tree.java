package search;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

class Node{
	int data, sum, id;
	List<Node> children;
	public Node(int data, int id) {
		this.data = data;
		this.sum = data;
		this.id = id;
		this.children = new ArrayList<>();
	}
}

public class Cut_the_Tree {
	
	private static void dfs(Node root, Node[] nodes, Map<Integer, Set<Integer>> edges, int[] visited) {
		visited[root.id] = 1;
		if(edges.containsKey(root.id)) {
			Set<Integer> children = edges.get(root.id);
			for(Integer child : children) {
				if(visited[child] == 0) {
					dfs(nodes[child], nodes, edges, visited);
					root.sum += nodes[child].sum;
					root.children.add(nodes[child]);
				}
			}
		}
		
	}
	
	private static Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) {
		int n = scanner.nextInt();
		scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
		Node[] nodes = new Node[n];
		int[] visited = new int[n];
		Map<Integer, Set<Integer>> edges = new HashMap<>();
		int rootIdx = -1;
		for(int i = 0; i < n; i++) {
			int data = scanner.nextInt();
			nodes[i] = new Node(data, i);
		}
		scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
		for(int i = 0; i < n - 1; i++) {
			String[] edge = scanner.nextLine().split(" ");
			int parent = Integer.parseInt(edge[0]) - 1;
			int child = Integer.parseInt(edge[1]) - 1;
			if(i == 0) {
				rootIdx = parent;
			}
			if(edges.containsKey(parent)) edges.get(parent).add(child);
			else {
				Set<Integer> tmp = new HashSet<>();
				tmp.add(child);
				edges.put(parent, tmp);
			}
			if(edges.containsKey(child)) edges.get(child).add(parent);
			else {
				Set<Integer> tmp = new HashSet<>();
				tmp.add(parent);
				edges.put(child, tmp);
			}
		}
		dfs(nodes[rootIdx], nodes, edges, visited);
		int totalSum = nodes[rootIdx].sum;
		int diff = totalSum;
		for(int i = 0; i < n; i++) {
			int tmpDiff = Math.abs(totalSum - nodes[i].sum - nodes[i].sum);
			if(tmpDiff < diff) diff = tmpDiff;
		}
		
		System.out.println(diff);
	}
}
