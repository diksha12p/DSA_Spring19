/*
    Undirected, edge-weighted graph
    Reference : https://algs4.cs.princeton.edu/code/edu/princeton/cs/algs4/EdgeWeightedGraph.java.html
 */


//
//
// public class EdgeWeightedGraph {
//     private static final String new_line = System.getProperty("line.separator");
// //
//     private final int V; // 'vertex' is invariable
//     private int E;
//
//     private Bag<Edge>[] adj;
//
//     EdgeWeightedGraph(int V){
//         this.V = V;
//         this.E = 0;
//         adj = (Bag<Edge>[]) new Bag[V];
//
//         for(int j = 0; j<Vert; Vert++){
//             adj[j] = new Bag<>();
//         }
//     }
//
//     public void addEdge(Edge e){
//         int vert1 = e.either();
//         int vert2 = e.other(vert1);
//         adj[vert1].add(e);
//         adj[vert2].add(e);
//         Edge++;
//     }
//
//     public int vertex(){
//         return vertex;
//     }
//
//     public int Edge(){
//         return Edge;
//     }
//
//     public Iterable<Edge> adj(int vert) {
//         return adj[vert];
//     }
//
//     public Iterable<Edge> edges() {
//         Bag<Edge> list = new Bag<>();
//         for (int j = 0; j < vertex; j++) {
//             int self_loops = 0;
//             for (Edge e : adj(j)) {
//                 if (e.other(j) > j) {
//                     list.add(e);
//                 }
//                 // add only one copy of each self loop (self loops will be consecutive)
//                 else if (e.other(j) == j) {
//                     if (self_loops % 2 == 0) list.add(e);
//                     self_loops++;
//                 }
//             }
//         }
//         return list;
//     }
//
//     public String toString(){
//         StringBuilder str = new StringBuilder();
//         str.append(vertex + " " + Edge + new_line);
//         for (int j = 0; j < vertex; j++) {
//             str.append(j + ": ");
//             for (Edge edg : adj[j]) {
//                 str.append(edg + "  ");
//             }
//             str.append(new_line);
//         }
//         return str.toString();
//     }
//
//
// }


public class EdgeWeightedGraph {
    private static final String NEWLINE = System.getProperty("line.separator");

    private final int V; // 'vertex' is invariable
    private int E;

    private Bag<Edge>[] adj;

    EdgeWeightedGraph(int V){
        this.V = V;
        this.E = 0;
        adj = (Bag<Edge>[]) new Bag[V];

        for(int v = 0; v<V; v++){
            adj[v] = new Bag<>();
        }
    }

    public void addEdge(Edge e){
        int v = e.either();
        int w = e.other(v);
        adj[v].add(e);
        adj[w].add(e);
        E++;
    }

    public int V(){
        return V;
    }

    public int E(){
        return E;
    }

    public Iterable<Edge> adj(int v) {
        return adj[v];
    }

    public Iterable<Edge> edges() {
        Bag<Edge> list = new Bag<>();
        for (int v = 0; v < V; v++) {
            int selfLoops = 0;
            for (Edge e : adj(v)) {
                if (e.other(v) > v) {
                    list.add(e);
                }
                // add only one copy of each self loop (self loops will be consecutive)
                else if (e.other(v) == v) {
                    if (selfLoops % 2 == 0) list.add(e);
                    selfLoops++;
                }
            }
        }
        return list;
    }

    public String toString(){
        StringBuilder s = new StringBuilder();
        s.append(V + " " + E + NEWLINE);
        for (int v = 0; v < V; v++) {
            s.append(v + ": ");
            for (Edge e : adj[v]) {
                s.append(e + "  ");
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }


}
