/*
    Reference: https://algs4.cs.princeton.edu/code/edu/princeton/cs/algs4/DirectedEdge.java.html
 */

public class DirectedEdge {

    private final int vert1;
    private final int vert2;
    private final double weight;

    public DirectedEdge(int vert1, int vert2, double weight){
        this.vert1 = vert1;
        this.vert2 = vert2;
        this.weight = weight;
    }

    public int from(){
        return vert1;
    }

    public int to(){
        return vert2;
    }

    public double getWeight(){
        return weight;
    }

    @Override
    public String toString() {
        return vert1 + " -> " + vert2 + " " + String.format("%5.2f", weight);
    }
}
