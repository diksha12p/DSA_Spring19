import java.io.*;

public class ScrapeData {
    public Graph readFile(String fileName){
        Graph grph = null;
        try{
            FileInputStream fileInpStream = new FileInputStream(fileName);
            InputStreamReader inpStrmReader = new InputStreamReader(fileInpStream);
            BufferedReader bufferedReader = new BufferedReader(inpStrmReader);
            String line;
            try{
                line = bufferedReader.readLine();
                grph = new Graph(Integer.parseInt(line));
                line = bufferedReader.readLine();
                int L = Integer.parseInt(line);

                for(int i = 0; i<L; i++){
                    line = bufferedReader.readLine();
                    String[] samp = line.split(" ");
                    int a = Integer.parseInt(samp[0]);
                    int b = Integer.parseInt(samp[1]);
                    grph.addEdge(a, b);
                }
                bufferedReader.close();
            }catch (IOException e){
                System.out.println(e.toString());
            }
        } catch (FileNotFoundException e){
            System.out.println(e.toString());
        }

        return grph;
    }

}
