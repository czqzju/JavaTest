import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

enum Color {
    RED, GREEN
}

abstract class Tree {

    private int value;
    private Color color;
    private int depth;

    public Tree(int value, Color color, int depth) {
        this.value = value;
        this.color = color;
        this.depth = depth;
    }

    public int getValue() {
        return value;
    }

    public Color getColor() {
        return color;
    }

    public int getDepth() {
        return depth;
    }

    public abstract void accept(TreeVis visitor);
}

class TreeNode extends Tree {

    private ArrayList<Tree> children = new ArrayList<>();

    public TreeNode(int value, Color color, int depth) {
        super(value, color, depth);
    }

    public void accept(TreeVis visitor) {
        visitor.visitNode(this);

        for (Tree child : children) {
            child.accept(visitor);
        }
    }

    public void addChild(Tree child) {
        children.add(child);
    }
}

class TreeLeaf extends Tree {

    public TreeLeaf(int value, Color color, int depth) {
        super(value, color, depth);
    }

    public void accept(TreeVis visitor) {
        visitor.visitLeaf(this);
    }
}

abstract class TreeVis
{
    public abstract int getResult();
    public abstract void visitNode(TreeNode node);
    public abstract void visitLeaf(TreeLeaf leaf);

}

class SumInLeavesVisitor extends TreeVis {
    private int leafResult;
    public SumInLeavesVisitor(){
        leafResult = 0;
    }
    public int getResult() {
        return leafResult;
    }

    public void visitNode(TreeNode node) {
      	return;
    }

    public void visitLeaf(TreeLeaf leaf) {
      	leafResult += leaf.getValue();
    }
}

class ProductOfRedNodesVisitor extends TreeVis {
	private long productResult = 1;
    private final int M = 1000000007;

    public int getResult() {
        return (int) productResult;
    }

    public void visitNode(TreeNode node) {
      	if(node.getColor() == Color.RED){
           productResult = (productResult * node.getValue()) % M;   
        }
    }

    public void visitLeaf(TreeLeaf leaf) {
      	if(leaf.getColor() == Color.RED){
           productResult = (productResult * leaf.getValue()) % M;   
        }
    }
}

class FancyVisitor extends TreeVis {
    private int sumOfNonleafEvenDepth;
    private int sumOfLeafNodeRed;

    public FancyVisitor(){
        sumOfNonleafEvenDepth = 0;
        sumOfLeafNodeRed = 0;
    }

    public int getResult() {
        return Math.abs(sumOfNonleafEvenDepth - sumOfLeafNodeRed);
    }

    public void visitNode(TreeNode node) {
    	if(node.getDepth() % 2 == 0){
            sumOfNonleafEvenDepth += node.getValue();
        }
    }

    public void visitLeaf(TreeLeaf leaf) {
    	if(leaf.getColor() == Color.GREEN){
            sumOfLeafNodeRed += leaf.getValue();
        }
    }
}

public class Trees {
	public static void createNodeOrLeaf(Tree [] trees, int [] parents, int i, int [] values, Color [] colors, Set nonleaf, Set leaves) {
		if(trees[parents[i]] == null) {
			createNodeOrLeaf(trees, parents, parents[i], values, colors, nonleaf, leaves);
		}
		
		if(trees[i] == null){
            if(!nonleaf.contains(i)) trees[i] = new TreeLeaf(values[i], colors[i], trees[parents[i]].getDepth() + 1);
            else trees[i] = new TreeNode(values[i], colors[i], trees[parents[i]].getDepth() + 1);
            ((TreeNode) trees[parents[i]]).addChild(trees[i]);
        }
        	
	}
  
    public static Tree solve() {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int [] values = new int[n];
        Color [] colors = new Color[n];
        int [] parents = new int[n];
        Tree [] trees = new Tree[n];
        ArrayList<int []> nodes = new ArrayList<int []>();
        Set<Integer> nonLeaf = new HashSet<>();
        Set<Integer> leaves = new HashSet<>();

        for(int i = 0; i < n; i++) values[i] = scan.nextInt();
        for(int i = 0; i < n; i++){
            if(scan.nextInt() == 0) colors[i] = Color.RED;
            else colors[i] = Color.GREEN;
            parents[i] = -1;
        }
        int count = 99;
        while(count > 0){
            int e1 = scan.nextInt();
            int e2 = scan.nextInt();
            if(e1 == 1) {
            	parents[e2 - 1] = e1 - 1;
            	nonLeaf.add(e1 - 1);
            	leaves.add(e2 - 1);
            }
            else {
            	if(leaves.contains(e1 - 1) || nonLeaf.contains(e1 - 1)) {
            		parents[e2 - 1] = e1 - 1;
            		nonLeaf.add(e1 - 1);
                	leaves.add(e2 - 1);
            	}
            	else if(leaves.contains(e2 - 1) || nonLeaf.contains(e2 - 1)) {
            		parents[e1 - 1] = e2 - 1;
            		nonLeaf.add(e2 - 1);
                	leaves.add(e1 - 1);
            	}
            	else {
            		int [] tmp = new int[2];
            		tmp[0] = e1;
            		tmp[1] = e2;
            		nodes.add(tmp);
            	}
            }
            count--;
            
        }
        while(!nodes.isEmpty()) {
        	Iterator<int []> iter = nodes.iterator();
        	while(iter.hasNext()) {
        		int [] tmp = (int [])iter.next();
        		int e1 = tmp[0];
        		int e2 = tmp[1];
        		if(leaves.contains(e1 - 1) || nonLeaf.contains(e1 - 1)) {
            		parents[e2 - 1] = e1 - 1;
            		nonLeaf.add(e1 - 1);
                	leaves.add(e2 - 1);
                	iter.remove();
            	}
            	else if(leaves.contains(e2 - 1) || nonLeaf.contains(e2 - 1)) {
            		parents[e1 - 1] = e2 - 1;
            		nonLeaf.add(e2 - 1);
                	leaves.add(e1 - 1);
                	iter.remove();
            	}
        	}
        	
        }
        Tree root = null;
        for(int i = 0; i < n; i++){           
            if(parents[i] == -1){
                if(trees[i] == null){
                    if(!nonLeaf.contains(i)) trees[i] = new TreeLeaf(values[i], colors[i], 0);
                    else trees[i] = new TreeNode(values[i], colors[i], 0);
                }
                root = trees[i];
            }
            else{
            	createNodeOrLeaf(trees, parents, i, values, colors, nonLeaf, leaves);     
            }
        }
        return root;

    }


    public static void main(String[] args) {
      	Tree root = solve();
		SumInLeavesVisitor vis1 = new SumInLeavesVisitor();
      	ProductOfRedNodesVisitor vis2 = new ProductOfRedNodesVisitor();
      	FancyVisitor vis3 = new FancyVisitor();

      	root.accept(vis1);
      	root.accept(vis2);
      	root.accept(vis3);

      	int res1 = vis1.getResult();
      	int res2 = vis2.getResult();
      	int res3 = vis3.getResult();

      	System.out.println(res1);
     	System.out.println(res2);
    	System.out.println(res3);
	}
}