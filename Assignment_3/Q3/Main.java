// P.S: REFERENCE for Queue.java & RedBlackBST.java: http://algs4.cs.princeton.edu

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.text.DecimalFormat;

public class Main {
    public static void main(String[] args) {
        RedBlackBST<Integer, Integer> samp;
        //Requirement of the question: N belongs to {10^4, 10^5, 10^6}
        int[] list = {10000, 100000, 1000000};
        for(int nums:list){
            double percent = 0.0;
            //To restrict the number to decimals in the output to ony 4 digits
            DecimalFormat numberFormat = new DecimalFormat("#.0000");
            for(int i=0;i<100;i++){
                samp = genRandomInput(nums);
                percent = percent + samp.redNodesNum() * 1.0 / nums;
            }
            percent = percent/100;
            System.out.println("The result of "+nums+" nodes is: "+numberFormat.format(100*percent)+"%");
        }
    }


// Function to generate 'nums' of random inputs
    private static RedBlackBST<Integer, Integer> genRandomInput(int num){
        RedBlackBST<Integer, Integer> samp = new RedBlackBST<>();
        int[] data = new int[num];
        //Filling in inputs
        for(int i=0;i<num;i++){
            data[i] = i;
        }
        shuffle(data);
        //Updating 'data' to contain the shuffled values
        for (int i=0;i<num;i++){
            samp.put(data[i], i);
        }
        return samp;
    }

// REFERENCE: https://stackoverflow.com/questions/1519736/random-shuffling-of-an-array
    private static void shuffle(int[] array){
        Random rand = ThreadLocalRandom.current();
        for (int i = array.length - 1; i > 0; i--)
        {
            int index = rand.nextInt(i + 1);
            int a = array[index];
            array[index] = array[i];
            array[i] = a;
        }
    }
}
