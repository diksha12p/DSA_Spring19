// P.S. The reference for Queue.java is https://algs4.cs.princeton.edu/

public class Main {

    public static void main(String[] argumentArr) {
        BinarySearchTree<Integer, Integer> bst;
        ScrapeData readData = new ScrapeData();

        String file;
        if(argumentArr.length == 0){
            System.out.println("ERROR: Please determine the data source !!");
            return;
        }else{
            file = argumentArr[0];
        }

        bst = readData.readFile(file);
        //Printing out the results to the user
        System.out.println("Result of Select(7) is :"+bst.select(7));
        System.out.println("Result of Rank(7) is :"+bst.rank(7));
    }
}
