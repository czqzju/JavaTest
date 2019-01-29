package tree;

import java.util.*;

import javax.xml.ws.AsyncHandler;
 
abstract class NodeC implements Comparable<NodeC> {
    public  int frequency; // the frequency of this tree
    public  char data;
    public  NodeC left, right; 
    public NodeC(int freq) { 
      frequency = freq; 
    }
 
    // compares on the frequency
    public int compareTo(NodeC tree) {
        return frequency - tree.frequency;
    }
}
 
class HuffmanLeaf extends NodeC {
    
 
    public HuffmanLeaf(int freq, char val) {
        super(freq);
        data = val;
    }
}
 
class HuffmanNode extends NodeC {
    
    public HuffmanNode(NodeC l, NodeC r) {
        super(l.frequency + r.frequency);
        left = l;
        right = r;
    }

}


class Decoding {

/*  
	class NodeC
		public  int frequency; // the frequency of this tree
    	public  char data;
    	public  NodeC left, right;
    
*/ 

	void decode(String s, NodeC root) {
		String res = "";
        int i = 0;
        while(i < s.length()) {
        	NodeC tmp = root;
        	while(tmp.left != null || tmp.right != null) {
        		if(s.charAt(i) == '0') {
        			if(tmp.left != null) {
        				tmp = tmp.left;
        				if(tmp.left == null && tmp.right == null) res += String.valueOf(tmp.data);
        				i++;
        			}
        			else{
        				res += String.valueOf(tmp.data);
        				break;
        			}
        		}
        		else {
        			if(tmp.right != null) {
        				tmp = tmp.right;
        				if(tmp.left == null && tmp.right == null) res += String.valueOf(tmp.data);
        				i++;
        			}
        			else{
        				res += String.valueOf(tmp.data);
        				break;
        			}
        		}
        	}
        }
        System.out.println(res);

       
    }



}

 
public class Huffman_Decoding {
  
    // input is an array of frequencies, indexed by character code
    public static NodeC buildTree(int[] charFreqs) {
      
        PriorityQueue<NodeC> trees = new PriorityQueue<NodeC>();
        // initially, we have a forest of leaves
        // one for each non-empty character
        for (int i = 0; i < charFreqs.length; i++)
            if (charFreqs[i] > 0)
                trees.offer(new HuffmanLeaf(charFreqs[i], (char)i));
 
        assert trees.size() > 0;
      
        // loop until there is only one tree left
        while (trees.size() > 1) {
            // two trees with least frequency
            NodeC a = trees.poll();
            NodeC b = trees.poll();
 
            // put into new NodeC and re-insert into queue
            trees.offer(new HuffmanNode(a, b));
        }
      
        return trees.poll();
    }
  
    public static Map<Character,String> mapA=new HashMap<Character ,String>();
  
    public static void printCodes(NodeC tree, StringBuffer prefix) {
      
        assert tree != null;
      
        if (tree instanceof HuffmanLeaf) {
            HuffmanLeaf leaf = (HuffmanLeaf)tree;
 
            // print out character, frequency, and code for this leaf (which is just the prefix)
            //System.out.println(leaf.data + "\t" + leaf.frequency + "\t" + prefix);
            mapA.put(leaf.data,prefix.toString());

        } else if (tree instanceof HuffmanNode) {
            HuffmanNode NodeC = (HuffmanNode)tree;
 
            // traverse left
            prefix.append('0');
            printCodes(NodeC.left, prefix);
            prefix.deleteCharAt(prefix.length()-1);
 
            // traverse right
            prefix.append('1');
            printCodes(NodeC.right, prefix);
            prefix.deleteCharAt(prefix.length()-1);
        }
    }
 
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
    
        String test= input.next();
 
        // we will assume that all our characters will have
        // code less than 256, for simplicity
        int[] charFreqs = new int[256];
      
        // read each character and record the frequencies
        for (char c : test.toCharArray())
            charFreqs[c]++;
 
        // build tree
        NodeC tree = buildTree(charFreqs);
 
        // print out results
        printCodes(tree, new StringBuffer());
        StringBuffer s = new StringBuffer();
      
        for(int i = 0; i < test.length(); i++) {
          	char c = test.charAt(i);
            s.append(mapA.get(c));
        }
      
        //System.out.println(s);
        Decoding d = new Decoding();
        d.decode(s.toString(), tree);

    }
}