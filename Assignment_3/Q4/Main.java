import java.io.*;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
    //As per requirements of the question
    private static final int N_RANGE = 10000;
    private static final int NUM_TRIAL = 1000;

    public static void main(String[] args) {
        RedBlackBST<Integer, Integer> sample;
        double[] avgLenResult = new double[N_RANGE];
        double[] stdDevResult = new double[N_RANGE];
        for(int n=1;n<=N_RANGE;n++){
            System.out.println("Current N is : " + n);
            double[] arr = new double[NUM_TRIAL];
            for(int i=0;i<NUM_TRIAL;i++){
                sample = genRandomInput(n);
                //Usage of method defined in RedBlackBST.java
                double ans = sample.getQ4Res();

                avgLenResult[n-1] += ans;
                arr[i] = ans;
            }
            // Computing the 'average' result
            avgLenResult[n-1] = avgLenResult[n-1] / (NUM_TRIAL);

            double stdDev = 0.0;
            for (int i=0;i<NUM_TRIAL;i++){
                double difference = arr[i] - avgLenResult[n-1];
                //Implementation of mathematical formula to calculate the std devaition
                stdDev += difference * difference;
            }
            stdDev = Math.sqrt(stdDev/(NUM_TRIAL*1.0-1.0));
            stdDevResult[n-1] = stdDev;

            //Writing the output to a csv file named 'output.csv'
            try {
                File csv = new File("output.csv");
                BufferedWriter bwrt = new BufferedWriter(new FileWriter(csv,true));
                bwrt.write("N     ,   Standard Deviation  ,   Average   ");
                bwrt.write(n +"  , "+stdDevResult[n-1]+"  , "+avgLenResult[n-1]);
                bwrt.newLine();
                bwrt.close();
            } catch (IOException exp) {
                exp.printStackTrace();
                System.out.println(exp.toString());
            }

        }

        //Simulatenously printing the output to the users
        for(int j=0;j<N_RANGE;j++){
            System.out.println("N = " + (j+1) + ", "
                                + "Average Length : "+ avgLenResult[j] + ", "
                                + "Std Deviation : "+ stdDevResult[j]);
        }


    }


// Function to generate 'num' of random inputs
    private static RedBlackBST<Integer, Integer> genRandomInput(int num){
        RedBlackBST<Integer, Integer> samp = new RedBlackBST<>();
        int[] data = new int[num];
        for(int i=0;i<num;i++){
            data[i] = i;
        }
        shuffle(data);
        for (int i=0;i<num;i++){
            samp.put(data[i], i);
        }
        return samp;
    }

// REFERENCE: https://stackoverflow.com/questions/1519736/random-shuffling-of-an-array
    private static void shuffle(int[] array){
        Random rand = ThreadLocalRandom.current();
        int n = array.length - 1;
        for (int i = n; i > 0; i--)
        {
            int index = rand.nextInt(i + 1);
            int a = array[index];
            array[index] = array[i];
            array[i] = a;
        }
    }
}
