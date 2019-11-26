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
    //Loss Function, to determine how good our valuation function for the network
    public void adjustWages(ArrayList<Double> targetOutput){
        for(int i = 0; i < neurons.size(); i++){

            double delta = targetOutput.get(i) - neurons.get(i).getOutput();
            neurons.get(i).adjustWeights(delta);
        }
    }

}
