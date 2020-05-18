public class Main {

    public static void main(String[] args) {
        DataReader data_read = new DataReader();
        if(args.length != 1){
            System.out.println("Error: Data is required !!");
            System.exit(1);
        }

        EdgeWeightedGraph Grph = data_read.readFile(args[0]);
        DFS dfs = new DFS(Grph, 0);
        BFS bfs = new BFS(Grph, 0);

    }
}
