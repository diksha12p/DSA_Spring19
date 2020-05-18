// Basic symbol-table API using 2-3 trees (not necessarily balanced)
// P.S: REFERENCE for Queue.java & BST.java: http://algs4.cs.princeton.edu

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

    // Functions to calculate the average path length for Question 2

    //Public function which calls to private function which acts on private data
    public int getSumWithoutRed(){
        return getSumWithoutRed(root);
    }

    // Same implementation as getLenSum() except for decrementing count when 'red' is found
    private int getSumWithoutRed(Node x){
        if (x==null) return 0;
        int count = size(x) + getSumWithoutRed(x.left) +getSumWithoutRed(x.right);
        if(isRedFlag(x)) count = count - size(x); // Decrement the count by size(x) if x is 'red'
        return count;
    }

    public int getLenSum(){
        return getLenSum(root);
    }

    // Function to calculate the sum of the sizes to get length
    private int getLenSum(Node x){
        if (x==null) return 0;
        int count = size(x) + getLenSum(x.left) +getLenSum(x.right);
        return count;
    }

    // Function to check if the encountered node is red and return True/False
    private boolean isRedFlag(Node x){
        if(x==null) return false;
        return x.colour == RED;
    }

    //Function to count the number of 'red' nodes
    public int countRed(){
        return countRed(root);
    }

    private int countRed(Node x){
        if(x==null) return 0;
        int count = countRed(x.left) + countRed(x.right);
        if (isRedFlag(x)) count++;
        return count;
    }

    //Function to calculate the size of the tree
    public int size(){
        return size(root);
    }

    private int size(Node x){
        if(x == null) return 0;
        else return x.N;
    }

    public void insert(Key key, Value val){
        if(key == null) throw new IllegalArgumentException("ERROR: The key can't be null !!");
        if (val == null) delete(key);
        root = insert(root, key, val);
        root.colour = BLACK;
    }

    private Node insert(Node x, Key key, Value val){
        if(x==null) return new Node(key, val, RED, 1);
        //Variable to trace the position of insertion of the provided data
        int compare = key.compareTo(x.key);
        //Insert to the Left if the value to be inserted is less than the traced root
        if(compare < 0){
            x.left = insert(x.left, key, val);
            if(isRedFlag(x) || isRedFlag(x.right)) {
                x.left.colour = BLACK;
            }
        }else if(compare > 0){
            x.right = insert(x.right, key ,val);
            if(isRedFlag(x) || isRedFlag(x.left)){
                x.right.colour = BLACK;
            }
        }else{
            x.val = val;
        }
        //Updating the size of the given tree
        x.N = 1 + size(x.left) + size(x.right);
        return x;
    }


    public void delete(Key key){
    //No implementation yet
    }

}
