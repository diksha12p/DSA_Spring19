// DFS Implementation
// Use an independently allocated stack

import java.util.Stack;

public class DFS {
    private boolean[] marked;
    private int[] edgeTo;
    private int count = 0;

    // Function Definition
    DFS(EdgeWeightedGraph Grph, int source){
        marked = new boolean[Grph.V()];
        edgeTo = new int[Grph.V()];
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        marked[0] = true;
        while(!stack.empty()){
            int vert1 = stack.pop();
            for(Edge edge1: Grph.adj(vert1)){
                int vert2 = edge1.other(vert1);
                if (!marked[vert2]){
                    count++;
                    marked[vert2] = true;
                    edgeTo[vert2] = vert1;
                    stack.push(vert2);
                }
            }
        }
        System.out.println("Numer of vertices DFS visits:" + (count+1));
    }
}
