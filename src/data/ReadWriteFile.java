package data;

import neural_network.Train;
import neural_network.TrainingSet;

import java.io.*;
import java.util.ArrayList;

public class ReadWriteFile {

    public static ArrayList<TrainingSet> readTrainingSets(){
        ArrayList<TrainingSet> trainningSets = new ArrayList<>();

        for(int i = 0; i < 26; i ++){
            char letterValue = (char) (i + 65);
            String letter = String.valueOf(letterValue);
            for(ArrayList<Integer> list : readFromFile("/" + letter + ".txt")){
                trainningSets.add(new TrainingSet(list, PositiveResults.getInstance().getPositiveOutput(letter)));
            }
        }
        return trainningSets;
    }

    /*

     */
    private static ArrayList<ArrayList<Integer>> readFromFile(String fileName){
        ArrayList<ArrayList<Integer>> inputs = new ArrayList<>();

        try{
            // get the file name from the resources
            InputStream in = ReadWriteFile.class.getResourceAsStream(fileName);
            // read the data
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            String line;

            while((line = reader.readLine()) != null){
                ArrayList<Integer> input = new ArrayList<>();
                for(int i = 0; i < line.length(); i++){
                    int value = 0;
                    try {
                        value = Integer.parseInt(String.valueOf(line.charAt(i)));
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    input.add(value);
                }
                inputs.add(input);
            }
            reader.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
        return inputs;
    }

    public static void saveToFile(ArrayList<Integer> input, String filename){
        try {
            File file = new File("resources/" + filename + ".txt");
            PrintWriter pw = new PrintWriter(new FileOutputStream(file,true));
            for(Integer i : input){
                pw.write(Integer.toString(i));
            }
            pw.write("\n");
            pw.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
