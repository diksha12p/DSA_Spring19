// P.S: REFERENCE for Queue.java & BST.java: http://algs4.cs.princeton.edu

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class Main {

    private final static int NUM_NODES = 100;

    public static void main(String[] args) {

        //Developing num as 2^k where k belongs to [0,12]
        int[] list = new int[]{1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048, 4096, 8192};

        for(int num:list){
            //Variable definition to store the results of average length calculation
            double avglen1 = 0.0;
            double avglen2 = 0.0;
            // long startTime = System.currentTimeMillis();
            for(int i = 0; i<NUM_NODES; i++){
                //Calculate the length of tree for ordered input
                TwoThreeTree<Integer, Integer> twothree1 = genOrderedInput(num);
                avglen1 += twothree1.getSumWithoutRed() * 1.0 / num;
                // avglen1 += twothree1.getLenSum() * 1.0 / num;

                //Calculate the length of  tree for random input
                TwoThreeTree<Integer, Integer> twothree2 = genRandomInput(num);
                avglen2 += twothree2.getSumWithoutRed() * 1.0 / num;
                // avglen2 += twothree2.getLenSum() * 1.0 / num;
            }
            //Computing the actual average
            avglen1 = avglen1/NUM_NODES;
            avglen2 = avglen2/NUM_NODES;
            // long endTime = System.currentTimeMillis();
            // long duration = (endTime - startTime);

            System.out.println("Number of nodes = " + num);
            System.out.println("The average length of ordered input = "+avglen1);
            // System.out.println("Duration = "+duration);
            System.out.println("The average length of random input = "+avglen2);
        }
    }

    //Function to generate 'num' of inputs
    private static TwoThreeTree<Integer, Integer> genOrderedInput(int num){
        // methodToTime();
        TwoThreeTree<Integer, Integer> samp = new TwoThreeTree<>();
        for(int i=0;i<num;i++){
            samp.insert(i, i);
        }
        return samp;
    }

    //Helper function to generate the above mentioned inputs
    private static TwoThreeTree<Integer, Integer> genRandomInput(int num){
        // methodToTime();
        TwoThreeTree<Integer, Integer> samp = new TwoThreeTree<>();
        int[] data = new int[num];
        for(int i=0;i<num;i++){
            data[i] = i;
        }
        shuffle(data);
        for (int i=0;i<num;i++){
            samp.insert(data[i], i);
        }
        return samp;
    }

    // REFERENCE: https://stackoverflow.com/questions/1519736/random-shuffling-of-an-array
    private static void shuffle(int[] array1){
        Random rand = ThreadLocalRandom.current();
        for (int i = array1.length - 1; i > 0; i--)
        {
            int index = rand.nextInt(i + 1);
            //Swap function
            int a = array1[index];
            array1[index] = array1[i];
            array1[i] = a;
        }
    }

    //REFERENCE: https://howtodoinjava.com/java/date-time/execution-elapsed-time/
    private static void methodToTime() {
    try {
      TimeUnit.SECONDS.sleep(3);
    } catch (InterruptedException exp) {
      exp.printStackTrace();
    }
  }

}
