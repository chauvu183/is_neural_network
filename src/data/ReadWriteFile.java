package data;

import neural_network.Train;
import neural_network.TrainingSet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ReadWriteFile {

    public static ArrayList<TrainingSet> readTrainingSets(){
        ArrayList<TrainingSet> trainningSets = new ArrayList<>();

        for(int i = 0; i < 26; i ++){
            char letterValue = (char) (i + 65);
            String letter = String.valueOf(letterValue);

        }
    }

    private static ArrayList<ArrayList<Integer>> readFromFile(String fileName){
        ArrayList<ArrayList<Integer>> inputs = new ArrayList<>();

        try{
            InputStream in = ReadWriteFile.class.getResourceAsStream(fileName);

            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            String line;

            while((line = reader.readLine()) != null))
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
