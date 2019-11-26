package neural_network;

public class Sigmoid {

    public static double sigmoidValue(Double arg) {

        return (1 / (1 + Math.exp(-arg)));
    }
}
