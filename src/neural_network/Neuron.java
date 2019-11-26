package neural_network;

import java.util.ArrayList;
/*
    1.Each neuron includes multiple inputs and weights but only one output.
      Often the desired outputs have to be normalized to the range [0: 1] since the sigmoid function only returns values in this range. The input patterns do not have to be normalized.
    2.Initialize all weights, including all biases if any, to small random values (normally in the range of -1 to +1).
    3. Forward propagation of the first input pattern of the training set from the input layer over the hidden layer(s) to the output layer, where each neuron sums the weighted inputs, passes them through the non-linearity and passes this weighted sum to the neurons in the next layer.
        Output = (Sum(each input* its weight) + (bias * Bias Weight)) * Sigmoid
    4. In order to adjust the weight to change the result of the output, we have adjust function
 */

public class Neuron {
    private static final int BIAS = 1;

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


    public void generateWeights(){
        for(int i = 0; i< inputs.size(); i++){
            weights.add(Math.random());
        }
    }


    public void calculateOutput(){
        double sum = 0;

        for(int i = 0; i < inputs.size(); i++ ){
            sum += inputs.get(i) * weights.get(i);
        }
        sum += BIAS * biasWeight;

        output = Sigmoid.sigmoidValue(sum);
    }
    /*
       the Delta rule is a gradient descent learning rule for
       updating the weights of the inputs to artificial neurons in single-layer neural network.
     */

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
