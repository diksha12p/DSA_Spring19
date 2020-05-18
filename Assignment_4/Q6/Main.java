public class Main {

    public static void main(String[] args) {
        ScrapeData data_read = new ScrapeData();
        if(args.length != 1){
            System.out.println("ERROR: Data is required !!");
            System.exit(1);
        }

        EdgeWeightedDigraph Grph = data_read.readFile(args[0]);
        Dijkstra djk = new Dijkstra(Grph, 0);
    }
}
