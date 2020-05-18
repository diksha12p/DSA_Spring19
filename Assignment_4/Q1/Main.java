
public class Main {

    public static void main(String[] args) {
        ScrapeData dataFinder = new ScrapeData();
        if(args.length != 1){
            System.out.println("Error: Data is Mandatory !!");
            System.exit(1);
        }

        Graph grph = dataFinder.readFile(args[0]);

        CycleChecker cycle_checker = new CycleChecker(grph);

        if(cycle_checker.is_acyclic()){
            System.out.println("Bravo! The given graph is acyclic !!");
        } else {
            System.out.println("Oops! The graph is cyclic !!");
        }

    }
}
