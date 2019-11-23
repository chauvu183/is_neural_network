package neural_network;

import java.util.ArrayList;
/*
    1. Radomly initilize the weights
    2. Calculate the Weight Sum by iterate the whole weights from one neuron connects to other neurons
    3.
 */

public class Neuron {
    private static final int BIAS = 1;
    /*
    the rate at which the neural network learns (from 0 to 1)
     */
    private static final double LEARNING_RATIO = 0.1;

    private ArrayList<Integer> inputs;
    private ArrayList<Double> weights;
    private double biasWeight;
    private double output;

    public Neuron() {
        this.inputs = new ArrayList<>();
        this.weights = new ArrayList<>();
        this.biasWeight = Math.random();
    }

    public void setInputs(ArrayList<Integer> inputs) {
        if(this.inputs.size() == 0){
            this.inputs = new ArrayList<>(inputs);
            generateWeights();
        }
        this.inputs = new ArrayList<>(inputs);
    }
    /*
    This method generate a random weight for the neuron
     */
    public void generateWeights(){
        for(int i = 0; i< inputs.size(); i++){
            weights.add(Math.random());
        }
    }
    /*

     */
    public void calculateOutput(){
        double sum = 0;

        for(int i = 0; i < inputs.size(); i++ ){
            sum += inputs.get(i) * weights.get(i);
        }
        sum += BIAS * biasWeight;

        output = Sigmoid.sigmoidValue(sum);
    }

    public void adjustWeights(double delta){
        for(int i = 0; i < inputs.size(); i++){
            double d = weights.get(i);
            d += LEARNING_RATIO * delta * inputs.get(i);
            weights.set(i,d);
        }
        biasWeight += LEARNING_RATIO * delta * BIAS;
    }

    public double getOutput() {
        calculateOutput();
        return output;
    }
}
