/*
    Read data from files
    Written by Zhongze Tang for DSA HW

    modified for undirected graph with weight
*/

import java.io.*;

public class ScrapeData {
    // public EdgeWeightedGraph readFile(String fileName){
    //     EdgeWeightedGraph grph = null;
    //     try{
    //         FileInputStream fileInpStream = new FileInputStream(fileName);
    //         InputStreamReader inpStrmReader = new InputStreamReader(fileInpStream);
    //         BufferedReader bufferedReader = new BufferedReader(inpStrmReader);
    //         String line;
    //         try{
    //             line = bufferedReader.readLine();
    //             grph = new EdgeWeightedGraph(Integer.parseInt(line));
    //             line = bufferedReader.readLine();
    //             int E = Integer.parseInt(line);
    //
    //             for(int i = 0; i<E; i++){
    //                 line = bufferedReader.readLine();
    //                 String[] s = line.split(" ");
    //                 // Storing the corresponding vertex and edge data
    //                 int ver1 = Integer.parseInt(s[0]);
    //                 int ver2 = Integer.parseInt(s[1]);
    //                 double weight_edge = Double.parseDouble(s[2]);
    //                 Edge edge1 = new Edge(ver1, ver2, weight_edge);
    //                 grph.addEdge(edge1);
    //             }
    //             bufferedReader.close();
    //         }catch (IOException exp){
    //             System.out.println(exp.toString());
    //         }
    //     } catch (FileNotFoundException exp){
    //         System.out.println(exp.toString());
    //     }
    //
    //     return grph;
    public EdgeWeightedGraph readFile(String fileName){
        EdgeWeightedGraph g = null;
        try{
            FileInputStream fileInputStream = new FileInputStream(fileName);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader buffReader = new BufferedReader(inputStreamReader);
            String line;
            try{
                line = buffReader.readLine();
                g = new EdgeWeightedGraph(Integer.parseInt(line));
                line = buffReader.readLine();
                int E = Integer.parseInt(line);

                for(int i = 0; i<E; i++){
                    line = buffReader.readLine();
                    String[] s = line.split(" ");
                    int v = Integer.parseInt(s[0]);
                    int w = Integer.parseInt(s[1]);
                    double we = Double.parseDouble(s[2]);
                    Edge e = new Edge(v, w, we);
                    g.addEdge(e);
                }
                buffReader.close();
            }catch (IOException e){
                System.out.println(e.toString());
            }
        } catch (FileNotFoundException e){
            System.out.println(e.toString());
        }

        return g;
    }

}
