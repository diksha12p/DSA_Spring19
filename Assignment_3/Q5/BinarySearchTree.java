public class BinarySearchTree<Key extends Comparable<Key>, Value> {

    private Node root;

    private class Node{
        private Key key;
        private Value val;
        private Node left;
        private Node right;
        private int N;

        Node(Key key, Value val, int N){
            this.key = key;
            this.val = val;
            this.left = null;
            this.right = null;
            this.N = N;
        }
    }

    public BinarySearchTree(){
    }

    //Function to compute the size of the tree
    public int size(){
        return size(root);
    }

    private int size(Node x){
        if(x==null) return 0;
        return x.N;
    }

// Function to 'get' a particular value in a BST
    public Value get(Key key){
        Node node = root;
        while(node!=null){
            int compare = key.compareTo(node.key);
            if(compare<0) {
                node = node.left;
            }
            else if(compare>0){
                 node = node.right;
            }
            else {
                 return node.val;
            }
        }
        return null;
    }

// Function to insert a value in the BST
    public void insert(Key key, Value val){
        if(key == null) throw new IllegalArgumentException("The first argument of insert is NULL !!");
        if(val == null){
            delete(key);
            return;
        }
        root = insert(root, key, val);
    }

    private Node insert(Node node, Key key, Value val){
        if(node==null) return new Node(key, val, 1);
        int compare = key.compareTo(node.key);
        if(compare<0) {
            node.left = insert(node.left, key, val);
        }
        else if(compare>0) {
            node.right = insert(node.right, key, val);
        }
        else {
            node.val = val;
        }
        // Update the size of this new BST
        node.N = 1 + size(node.right) + size(node.left);
        return node;
    }

// Function to return the result of 'Rank'
    public int rank(Key key){
        return rank(root, key);
    }

    private int rank(Node x, Key key){
        // Compare the given key with the key of the node
        int cmp = key.compareTo(x.key);
        if(cmp<0){
            //If the key is less than the node, recursively return the rank of left sub-tree
            return rank(x.left, key);
        }else if(cmp>0){
            //If the key is greater than the node, recursively return the rank of right sub-tree alongwith the size of left
            return 1+size(x.left)+rank(x.right, key);
        }else{
            //If the key is equal to the node, return the size of left sub-tree
            return size(x.left);
        }
    }

// Function to return the result of 'Select'
    public Key select(int k){
        return select(root, k);
    }

    private Key select(Node node1, int k){
        if (node1==null) return null;
        int var = size(node1.left);
        //Compare the size of the left tree with 'k'
        if(var > k){
            //If the size of left sub-tree > k, select k of the left sub-tree
            return select(node1.left, k);
        }else if (var == k){
            //If the size of left sub-tree = k, select the left sub-tree
            return node1.key;
        }else {
            //If the size of left sub-tree < k, select all of the left sub-tree and remaining from right sub-tree
            return select(node1.right, k-var-1);
        }
    }

    private void delete(Key key){
    }

}
