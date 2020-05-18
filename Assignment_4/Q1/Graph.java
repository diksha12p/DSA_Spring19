public class Graph{

    private final int Vertex; //'V' is invariable
    private int Edge;

    private Bag<Integer>[] adj;

    Graph(int Vert){
        this.Vertex = Vert;
        this.Edge = 0;
        adj = (Bag<Integer>[]) new Bag[Vert];

        for(int i = 0; i<Vert; i++){
            adj[i] = new Bag<>();
        }
    }

    public void addEdge(int a, int b){
        adj[a].add(b);
        adj[b].add(a);
        Edge++;
    }

    public int Vertex(){
        return Vertex;
    }

    public int Edge(){
        return Edge;
    }

    public Iterable<Integer> adj(int vert){
        return adj[vert];
    }

    public String toString(){
        String graph;
        graph = "Number of vertices are: " + Vertex + "\n"
                + "Number of edges are: " + Edge + "\n";
        for(int i = 0; i<Vertex; i++){
            graph = graph + "Vertex " + i ;
            for(int j: adj(i)){
                graph = graph + " -> " + j;
            }
            graph = graph + "\n";
        }
        return graph;
    }


}
