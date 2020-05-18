// Basic symbol-table API that uses 2-3 trees (not necessarily balanced)
import java.io.*;

public class TwoThreeTree<Key extends Comparable<Key>, Value> {

    private final static boolean RED = true;
    private final static boolean BLACK = false;

    private Node root;

    private class Node{
        private Key key;
        private Value val;
        private Node left, right;
        private boolean colour;
        private int N;

        public Node(Key key, Value val, boolean colour, int N){
            this.key = key;
            this.val = val;
            this.colour = colour;
            this.N = N;
        }
    }

    public TwoThreeTree(){
    }

// Functions defined below are for Q2 where average path length is desired

    public int getSumLen(){
        return getSumLen(root);
    }

    private int getSumLen(Node x){
        if (x==null) return 0;
        return size(x) + getSumLen(x.left) +getSumLen(x.right);
    }


    private boolean isRedFlag(Node x){
        if(x==null) return false;
        return x.colour == RED;
    }

    public int countRedLinks(){
        return countRedLinks(root);
    }

    private int countRedLinks(Node x){
        if(x==null) return 0;
        int count = countRedLinks(x.left) + countRedLinks(x.right);
        if (isRedFlag(x)) count++;
        return count;
    }

    public void inOrderTraverse(){
        inOrderTraverse(root);
        System.out.println();
    }

    private void inOrderTraverse(Node x){
        if(x==null) return;
        inOrderTraverse(x.left);
        System.out.print(x.key+ ", ");
        inOrderTraverse(x.right);
    }

    public int size(){
        return size(root);
    }

    private int size(Node x){
        if(x == null) return 0;
        else return x.N;
    }

    public boolean contains(Key key){
        return (get(key) != null);
    }

    public boolean isEmptyFlag(){
        return root == null;
    }

    public void insert(Key key, Value val){
        if(key == null) throw new IllegalArgumentException("ERROR: The key cannot be null !! ");
        root = insert(root, key, val);
        root.colour = BLACK;
    }

    private Node insert(Node x, Key key, Value val){
        if(x==null) return new Node(key, val, RED, 1);
        int cmpResult = key.compareTo(x.key);
        if(cmpResult < 0){
            x.left = insert(x.left, key, val);
            if(isRedFlag(x) || isRedFlag(x.right)) x.left.colour = BLACK;
        }else if(cmpResult > 0){
            x.right = insert(x.right, key ,val);
            if(isRedFlag(x) || isRedFlag(x.left)) x.right.colour = BLACK;
        }else{
            x.val = val;
        }
        x.N = 1 + size(x.left) + size(x.right);
        return x;
    }

    public Value get(Key key){
        Node x = root;
        while(x != null){
            int cmpResult = key.compareTo(x.key);
            if(cmpResult<0){
                x = x.left;
            }else if(cmpResult>0){
                x = x.right;
            }else return x.val;
        }
        return null;
    }

}
