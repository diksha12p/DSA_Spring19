import java.io.*;

public class ScrapeData {
    public BinarySearchTree<Integer, Integer> readFile(String fileName){
        BinarySearchTree<Integer, Integer> bst = new BinarySearchTree<>();

        try{
            FileInputStream fileInpStream = new FileInputStream(fileName);
            InputStreamReader inpStrmReader = new InputStreamReader(fileInpStream);
            BufferedReader bufferedReader = new BufferedReader(inpStrmReader);
            String index;

            try{
                int j = 0;
                while((index = bufferedReader.readLine())!=null){
                    int number = Integer.parseInt(index);
                    bst.insert(number, j++);
                }
                bufferedReader.close();
            }catch (IOException exp){
                System.out.println(exp.toString());
            }
        } catch (FileNotFoundException exp){
            System.out.println(exp.toString());
        }

        return bst;
    }

}
