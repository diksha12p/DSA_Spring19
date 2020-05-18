/*
    Implementation of Kruskal's Algo for calculating Minimum Spanning Tree
    Reference for some codes: https://algs4.cs.princeton.edu/code/edu/princeton/cs/algs4/KruskalMST.java.html
 */

public class Kruskal_MST {

    private double weight_MST;
    private Queue<Edge> min_span_tree = new Queue<>();

    public Kruskal_MST(EdgeWeightedGraph Grph){
        MinPQ<Edge> priority_queue = new MinPQ<>();
        for(Edge edg:Grph.edges()){
            priority_queue.insert(edg);
        }

        UF union_find = new UF(Grph.V());
        while(!priority_queue.isEmpty() && min_span_tree.size() < Grph.V() - 1){
            Edge edg = priority_queue.delMin();
            int vert1 = edg.either();
            int vert2 = edg.other(vert1);
            if(!union_find.connected(vert1, vert2)){  //Check if addition would create a cycle
                union_find.union(vert1, vert2); //If no cycle creation, establish a path between the two
                min_span_tree.enqueue(edg); //Make a part of the Minimum Spanning Tree i.e. MST
                weight_MST += edg.getWeight();  //Update the weight of the MST
            }
        }
    }

    public double getWeight(){
        return weight_MST;
    }

    public Iterable<Edge> edges(){
        return min_span_tree;
    }


}
