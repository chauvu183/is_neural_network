package neural_network;

import java.util.ArrayList;

public class TrainingSet {

    private ArrayList<Integer> inputs;
    private ArrayList<Double> positiveResults;

    public TrainingSet(ArrayList<Integer> inputs, ArrayList<Double> positiveResults) {
        this.inputs = inputs;
        this.positiveResults = positiveResults;
    }

    public ArrayList<Integer> getInputs(){ return inputs; }
    public ArrayList<Double> getPositiveResults(){ return positiveResults; }

}
