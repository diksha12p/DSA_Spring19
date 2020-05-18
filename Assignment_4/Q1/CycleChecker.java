public class CycleChecker {
    private boolean[] is_marked;
    private boolean is_acyclic;

    CycleChecker(Graph grph){
        is_marked = new boolean[grph.Vertex()];
        is_acyclic = true;
        for(int i = 0; i<grph.Vertex(); i++){
            if(!is_marked[i]){
                dfs(grph, i, i);  //First call to 'dfs' traversal of the given graph
            }
        }
    }

    // Defined function for 'Depth First Search' i.e 'dfs'
    private void dfs(Graph grph, int a, int b){
        is_marked[a] = true;
        for(int u : grph.adj(a)){
            if (!is_marked[u]){
                dfs(grph, u, a);
            }else if(u != b){
                is_acyclic = false; // If it contains a circle, it's cyclic
            }
        }
    }

    public boolean is_acyclic(){
        return is_acyclic;
    }

}
