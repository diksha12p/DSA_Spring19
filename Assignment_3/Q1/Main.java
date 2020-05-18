import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;



public class Main {
    //Variable to hold the number of random inputs
    //It can be altered as per the requirements
    public static int k = 500;

    public static void main(String[] args) {
        TwoThreeTree<Integer, Integer> twothree = printOrderedInput(k);

        // try{
        //   File csv = new File("result.csv");
        //   BufferedWriter bw = new BufferedWriter(new FileWriter(csv,true));
        //   bw.write(ttt.inOrderTraverse());
        //   bw.newLine();
        //   bw.close();
        // } catch (IOException e) {
        //   e.printStackTrace();
        //   System.out.println(e.toString());
        // }

        twothree.inOrderTraverse();
    }


    private static TwoThreeTree<Integer, Integer> printOrderedInput(int num){
        TwoThreeTree<Integer, Integer> samp = new TwoThreeTree<>();
        for(int i=0;i<num;i++){
            samp.insert(i, i);
        }
        return samp;
    }

// Function to generate 'num' of random inputs
    private static TwoThreeTree<Integer, Integer> genRandomInput(int number){
        TwoThreeTree<Integer, Integer> samp = new TwoThreeTree<>();
        int[] data = new int[number];
        for(int i=0;i<number;i++){
            data[i] = i;
        }
        shuffle(data);
        for (int i=0;i<number;i++){
            samp.insert(data[i], i);
        }
        return samp;
    }

// REFERENCE: https://stackoverflow.com/questions/1519736/random-shuffling-of-an-array
    private static void shuffle(int[] ar){
        Random rand = ThreadLocalRandom.current();
        for (int i = ar.length - 1; i > 0; i--)
        {
            int index = rand.nextInt(i + 1);
            int a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }
    }

}
