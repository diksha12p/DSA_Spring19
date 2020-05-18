/*
    Class for weighted & undirected edges
    Reference : https://algs4.cs.princeton.edu/code/edu/princeton/cs/algs4/Edge.java.html
 */

public class Edge implements Comparable<Edge>{
    private final int vert1;
    private final int vert2;
    private final double weight_edge;

    public Edge(int v1, int v2, double weight){
        this.vert1 = v1;
        this.vert2 = v2;
        this.weight_edge = weight;
    }

    public double getWeight(){
        return weight_edge;
    }

    public int either() {
        return vert1;
    }

    public int other(int vertex) {
        if      (vertex == vert1) return vert2;
        else if (vertex == vert2) return vert1;
        else throw new IllegalArgumentException("Illegal Endpoint reached !!");
    }

    @Override
    public int compareTo(Edge that) {
        return Double.compare(this.weight_edge, that.weight_edge);
    }

    public String toString() {
        return String.format("%d-%d %.5f", vert1, vert2, weight_edge);
    }

}
