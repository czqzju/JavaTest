import java.util.ArrayList;
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

import java.util.ArrayList;
import java.util.Scanner;

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
    private int productResult;

    public ProductOfRedNodesVisitor(){
        productResult = 1;
    }

    public int getResult() {
        return productResult;
    }

    public void visitNode(TreeNode node) {
      	if(node.getColor() == 0){
           productResult = productResult * node.getValue()   
        }
    }

    public void visitLeaf(TreeLeaf leaf) {
      	if(leaf.getColor() == 0){
           productResult = productResult * leaf.getValue()   
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
    	if(leaf.getColor() == 0){
            sumOfLeafNodeRed += leaf.getValue();
        }
    }
}

public class Trees {
  
    public static Tree solve() {
        Scanner scan = new Scanner(system.in);
        int n = scan.nextInt();
        int [] values = new int[n];
        int [] colors = new int[n];
        for(int i = 0; i < n; i++) values[i] = scan.nextInt();
        for(int i = 0; i < n; i++) colors[i] = scan.nextInt();
        
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