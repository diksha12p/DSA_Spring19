// Reading the data from the dataset provided

import java.io.*;

public class ScrapeData {
    public EdgeWeightedDigraph readFile(String file_name){
        EdgeWeightedDigraph grph = null;
        try{
            FileInputStream fileInpStream = new FileInputStream(file_name);
            InputStreamReader inputStrmReader = new InputStreamReader(fileInpStream);
            BufferedReader bufferedReader = new BufferedReader(inputStrmReader);
            String line;
            try{
                line = bufferedReader.readLine();
                grph = new EdgeWeightedDigraph(Integer.parseInt(line));
                line = bufferedReader.readLine();
                int E = Integer.parseInt(line);

                for(int i = 0; i<E; i++){
                    line = bufferedReader.readLine();
                    String[] samp = line.split(" ");
                    int vert1 = Integer.parseInt(samp[0]);
                    int vert2 = Integer.parseInt(samp[1]);
                    double weight_edge = Double.parseDouble(samp[2]);
                    DirectedEdge directed_edge = new DirectedEdge(vert1, vert2, weight_edge);
                    grph.addEdge(directed_edge);
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
