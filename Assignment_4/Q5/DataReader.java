// Reading data from the provided datasets

import java.io.*;

public class DataReader {
    public EdgeWeightedGraph readFile(String file_Name){
        EdgeWeightedGraph grph = null;
        try{
            FileInputStream fileInpStream = new FileInputStream(file_Name);
            InputStreamReader inputStrmReader = new InputStreamReader(fileInpStream);
            BufferedReader bufferedReader = new BufferedReader(inputStrmReader);
            String line;
            try{
                line = bufferedReader.readLine();
                grph = new EdgeWeightedGraph(Integer.parseInt(line));
                line = bufferedReader.readLine();
                int E = Integer.parseInt(line);

                for(int i = 0; i<E; i++){
                    line = bufferedReader.readLine();
                    String[] samp = line.split(" ");
                    int v = Integer.parseInt(samp[0]);
                    int w = Integer.parseInt(samp[1]);
                    double weight_edge = Double.parseDouble(samp[2]);
                    Edge e = new Edge(v, w, weight_edge);
                    grph.addEdge(e);
                }
                bufferedReader.close();
            }catch (IOException exp){
                System.out.println(exp.toString());
            }
        } catch (FileNotFoundException exp){
            System.out.println(exp.toString());
        }

        return grph;
    }

}
