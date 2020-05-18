public class Main {

    public static void main(String[] args) {
        ScrapeData readData = new ScrapeData();
        if(args.length != 1){
            System.out.println("Error: Data is required !!");
            System.exit(1);
        }

        EdgeWeightedGraph Grph = readData.readFile(args[0]);

        NanoTimer nano_timer = new NanoTimer();
        Kruskal_MST kruskal_MST = new Kruskal_MST(Grph);
        long data1 = nano_timer.time_elpased();
        System.out.println("  KRUSKAL'S ALGORITHM ");
        System.out.println("Running time : " + data1 + " ns");
        System.out.println("Weight : " + kruskal_MST.getWeight());
        System.out.println("----------------------------------------"
                 + "----------------------------------------");

        NanoTimer nano_timedata2 = new NanoTimer();
        Prims_Lazy prims_lazy_MST = new Prims_Lazy(Grph);
        long data2 = nano_timedata2.time_elpased();
        System.out.println("  PRIM'S LAZY ALGORITHM ");
        System.out.println("The running time : " + data2 + " ns");
        System.out.println("The weight : " + prims_lazy_MST.weight());
        System.out.println("----------------------------------------"
                 + "----------------------------------------");

        NanoTimer nano_timedata3 = new NanoTimer();
        Prims_Eager prims_eager_MST = new Prims_Eager(Grph);
        long data3 = nano_timedata3.time_elpased();
        System.out.println("  PRIM'S EAGER ALGORITHM ");
        System.out.println("The running time : " + data3 + " ns");
        System.out.println("The weight : " + prims_eager_MST.weight());
        System.out.println("----------------------------------------"
                 + "----------------------------------------");

    }
}
