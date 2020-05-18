/*
    Reference: https://algs4.cs.princeton.edu/code/edu/princeton/cs/algs4/DirectedEdge.java.html
 */

public class Dijkstra {

    private double[] distTo;
    private DirectedEdge[] edgeTo;
    private IndexMinPQ<Double> priority_queue;

    public Dijkstra(EdgeWeightedDigraph Grph, int source){
        distTo = new double[Grph.V()];
        edgeTo = new DirectedEdge[Grph.V()];

        for (int j=0; j<Grph.V(); j++){
            distTo[j] = Double.POSITIVE_INFINITY;
        }
        distTo[source] = 0;

        priority_queue = new IndexMinPQ<>(Grph.V());
        priority_queue.insert(source, distTo[source]);
        while (!priority_queue.isEmpty()){
            int vert1 = priority_queue.delMin();
            for(DirectedEdge e:Grph.adj(vert1)){
                relax(e);
            }
        }

        printResult(Grph, source);
    }

    private void relax(DirectedEdge edge1){
        int vert1 = edge1.from();
        int vert2 = edge1.to();
        if(distTo[vert2] > distTo[vert1] + edge1.getWeight()){
            distTo[vert2] = distTo[vert1] + edge1.getWeight();
            edgeTo[vert2] = edge1;
            if(priority_queue.contains(vert2))
                priority_queue.decreaseKey(vert2, distTo[vert2]);
            else
                priority_queue.insert(vert2, distTo[vert2]);
        }
    }

    private double distTo(int vert1){
        return distTo[vert1];
    }

    private boolean hasPathTo(int vert1) {
        return distTo[vert1] < Double.POSITIVE_INFINITY;
    }

    private Iterable<DirectedEdge> pathTo(int vert1){
        if(!hasPathTo(vert1)) return null;
        Stack<DirectedEdge> path = new Stack<>();
        for(DirectedEdge edge1 = edgeTo[vert1]; edge1!=null; edge1=edgeTo[edge1.from()]){
            path.push(edge1);
        }
        return path;
    }

    private void printResult(EdgeWeightedDigraph Grph, int source){
        for (int term = 0; term < Grph.V(); term++) {
            if (hasPathTo(term)) {
                System.out.printf("%d to %d (%.2f)  ", source, term, distTo(term));
                for (DirectedEdge edge1 : pathTo(term)) {
                    System.out.print(edge1 + "   ");
                }
                System.out.println();
            }
            else {
                System.out.printf("No path from %d to %d !!\n", source, term);
            }
        }
    }

}
