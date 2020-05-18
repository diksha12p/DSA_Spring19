// BFS Implementation

public class BFS {
    private Queue<Integer> queue = new Queue<>();
    private boolean[] marked;
    private int[] edgeTo;
    private int count = 0;

    // Function Definition
    BFS(EdgeWeightedGraph Grph, int source){
        marked = new boolean[Grph.V()];
        edgeTo = new int[Grph.V()];
        queue.enqueue(source);
        marked[source] = true;
        while(!queue.isEmpty()){
            int vert1 = queue.dequeue();
            for (Edge e:Grph.adj(vert1)){
                int vert2 = e.other(vert1);
                if(!marked[vert2]){
                    count++;
                    queue.enqueue(vert2);
                    marked[vert2] = true;
                }
            }
        }
        System.out.println("Vertices visited by BFS : " + (count+1));
    }
}
