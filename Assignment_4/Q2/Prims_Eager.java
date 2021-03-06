public class Prims_Eager {

    private Edge[] edgeTo;
    private double[] distTo;
    private boolean[] marked;
    private IndexMinPQ<Double> priority_queue;

    public Prims_Eager(EdgeWeightedGraph Grph){
        edgeTo = new Edge[Grph.V()];
        distTo = new double[Grph.V()];
        marked = new boolean[Grph.V()];
        priority_queue = new IndexMinPQ<>(Grph.V());
        for(int j = 0; j<Grph.V(); j++){
            distTo[j] = Double.POSITIVE_INFINITY;
        }
        for(int i= 0; i<Grph.V(); i++)
            if(!marked[i]) prims(Grph, i);
    }

    private void prims(EdgeWeightedGraph Grph, int source){
        distTo[source] = 0.0;
        priority_queue.insert(source, distTo[source]);
        while(!priority_queue.isEmpty()){
            int vert = priority_queue.delMin();
            scan(Grph, vert);
        }
    }

    private void scan(EdgeWeightedGraph Grph, int vert1){
        marked[vert1] = true;
        for(Edge e:Grph.adj(vert1)){
            int vert2 = e.other(vert1);
            if(marked[vert2]) continue;
            if(e.getWeight() < distTo[vert2]){
                distTo[vert2] = e.getWeight();
                edgeTo[vert2] = e;
                if(priority_queue.contains(vert2)) priority_queue.decreaseKey(vert2, distTo[vert2]);
                else  priority_queue.insert(vert2, distTo[vert2]);
            }
        }
    }

    public Iterable<Edge> edges() {
        Queue<Edge> min_span_tree = new Queue<Edge>();
        for (int v = 0; v < edgeTo.length; v++) {
            Edge e = edgeTo[v];
            if (e != null) {
                min_span_tree.enqueue(e);
            }
        }
        return min_span_tree;
    }

    public double weight() {
        double weight = 0.0;
        for (Edge e : edges())
            weight += e.getWeight();
        return weight;
    }

}
