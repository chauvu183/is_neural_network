package neural_network;

import java.util.ArrayList;

public class Network {
    private ArrayList<Neuron> neurons;

    public Network() {
        neurons = new ArrayList<>();
    }

    public void addNeurons(int count ){
        for(int i = 0; i < count; i++){
            neurons.add(new Neuron());
        }
    }

    public void setInput(ArrayList<Integer> input) {
        for(Neuron n : neurons){
            n.setInputs(input);
        }
    }

    public ArrayList<Double> getOutputs(){
        ArrayList<Double> outputs = new ArrayList<>();
        for(Neuron n : neurons){
            outputs.add(n.getOutput());
        }
        return outputs;
    }

    /*
    1. Calculation of the difference between the actual output of each output neuron and its corresponding desired output. This is the error associated with each output neuron.
    2. Back propagating this error through each connection by using the Backpropagation learning rule and thus determining the amount each weight has to be changed in order to decrease the error at the output layer.
    3. Correcting each weight by its individual weight update.
     */

    public void adjustWages(ArrayList<Double> targetOutput){
        for(int i = 0; i < neurons.size(); i++){
            double delta = targetOutput.get(i) - neurons.get(i).getOutput();
            neurons.get(i).adjustWeights(delta);
        }
    }

}
