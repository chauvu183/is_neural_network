package gui;

import data.PositivePixels;
import data.PositiveResults;
import data.ReadWriteFile;
import neural_network.Train;
import neural_network.TrainingSet;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Main extends JFrame {
    private final int RESOLUTION = 20;

    private Train trainer;

    private JPanel mainPannel;
    private DrawingPannel drawingPannel;
    private CustomPanel resultPanel;

    private JButton deleteButton;
    private JButton trainButton;
    private JButton preditionButton;

    private JButton helpButton;
    private JButton trainNetworkButton;
    private JButton drawLetterButton;
    private JTextField trainingSetsAmount;

    private JComboBox<String> drawLetterCombo;
    private JComboBox<String> trainAsCombo;

    private JTextArea outputTextArea;

    private Main() {
        super("Recognize a Letter by using neural Network");

        trainer = new Train();

        setMainPannel();
        setLeftSide();
        setCenter();
        setRightSide();

        setOnClicks();

        setOutputPannel();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        setSize(1260,500);

        setLocationRelativeTo(null);
        setResizable(true);
    }

    private void setMainPannel(){
        mainPannel = new JPanel();
        mainPannel.setBackground(Color.LIGHT_GRAY);
        setContentPane(mainPannel);
    }

    public static void main(String[] args) {
        new Main();
    }

    private void setLeftSide(){
        JPanel panel = new JPanel();

        panel.setBackground(Color.LIGHT_GRAY);

        panel.setPreferredSize(new Dimension(410,440));


        drawingPannel = new DrawingPannel(400,400,RESOLUTION);

        helpButton = new JButton("Help");
        panel.add(helpButton);
        panel.add(drawingPannel);

        mainPannel.add(panel);
    }

    private void setCenter(){

        JPanel centerPannel = new JPanel();
        centerPannel.setLayout(new GridBagLayout());

        centerPannel.setPreferredSize(new Dimension(200, 400));

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridwidth = GridBagConstraints.REMAINDER;

        gbc.anchor = GridBagConstraints.CENTER;
        centerPannel.add(new JLabel("Draw: ", SwingConstants.CENTER),gbc );
        drawLetterCombo = new JComboBox<>(new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Z", "Y"});

        centerPannel.add(drawLetterCombo,gbc);
        centerPannel.add(Box.createVerticalStrut(50));

        trainNetworkButton = new JButton("Train X Times: ");
        trainingSetsAmount = new JFormattedTextField("5000");

        trainingSetsAmount.setMaximumSize(new Dimension(100, 30));

        trainingSetsAmount.setPreferredSize(new Dimension(100,30));

        //centerPannel.add(trainNetworkButton,gbc);
        //centerPannel.add(trainingSetsAmount,gbc);

        centerPannel.add(Box.createVerticalStrut(50));


        preditionButton = new JButton("Prediction >> ");
        centerPannel.add(preditionButton,gbc);

        centerPannel.add(Box.createVerticalStrut(50));

        deleteButton = new JButton("delete");

        deleteButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPannel.add(deleteButton,gbc);

        centerPannel.add(Box.createVerticalStrut(50));

        centerPannel.add(new JLabel("Train as: ", SwingConstants.CENTER),gbc );

        trainAsCombo = new JComboBox<>(new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Z", "Y"});
        trainAsCombo.setAlignmentX(Component.CENTER_ALIGNMENT);
        trainAsCombo.setMaximumSize(new Dimension((int) trainAsCombo.getPreferredSize().getWidth(), 30));
        centerPannel.add(trainAsCombo, gbc);

        trainButton = new JButton("Train");
        centerPannel.add(trainButton, gbc);

        mainPannel.add(centerPannel);

    }

    private void setRightSide() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.LIGHT_GRAY);
        panel.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));
        resultPanel = new CustomPanel(400, 400, RESOLUTION);
        panel.add(resultPanel);
        mainPannel.add(panel);
    }

    private void setOutputPannel(){
        JPanel outputPannel = new JPanel();
        outputPannel.setPreferredSize( new Dimension(200, 440));
        outputTextArea = new JTextArea();
        outputTextArea.setPreferredSize( new Dimension(200,440));

        outputPannel.add(outputTextArea);
        mainPannel.add(outputPannel);
    }

    private void setOnClicks() {

        deleteButton.addActionListener(e -> {
            drawingPannel.clear();
            resultPanel.clear();

        });

        trainButton.addActionListener(e -> {
            String letter = (String) trainAsCombo.getSelectedItem();

            trainer.addTrainingSet(new TrainingSet(drawingPannel.getPixels(), PositiveResults.getInstance().getPositiveOutput(letter)));

            ReadWriteFile.saveToFile(drawingPannel.getPixels(), letter);
        });

        preditionButton.addActionListener(e -> {
            trainer.setInputs(drawingPannel.getPixels());

            ArrayList<Double> out = trainer.getOutputs();

            int index = 0;

            for (int i = 0; i < out.size(); i++) {
                if (out.get(i) > out.get(index)) {
                    index = i;
                }
            }
            updateTextArea();

            trainAsCombo.setSelectedIndex(index);
            resultPanel.drawLetter(PositivePixels.getInstance().getPositivePixels(index));
        });


        helpButton.addActionListener(e -> {
            StringBuilder sb = new StringBuilder();

            sb.append("Use left/right mouse button to draw/erase\n");
            sb.append("\n");
            sb.append("Click \">>\" to see result\n");
            sb.append("\n");
            sb.append("Click \"Train\" to train specific letter\n");
            JOptionPane.showMessageDialog(this, sb.toString(), "Help", JOptionPane.PLAIN_MESSAGE);
        });

        trainButton.addActionListener(e -> {
            int number = 0;
            try {
                number = Integer.parseInt(trainingSetsAmount.getText());
            } catch (Exception x) {
                JOptionPane.showMessageDialog(this, "Wrong input", "Error", JOptionPane.PLAIN_MESSAGE);
            }
            trainer.train(number);
        });


        drawLetterCombo.addActionListener(e -> {
            String letter = (String) drawLetterCombo.getSelectedItem();
            ArrayList<Integer> positivePixels = PositivePixels.getInstance().getPositivePixels(letter);
            drawingPannel.drawLetter(positivePixels);
        });
    }
        private void updateTextArea() {
            StringBuilder sb = new StringBuilder();
            ArrayList<Double> outputs = trainer.getOutputs();
            for (int i = 0; i < outputs.size(); i++) {
                int letterValue = i + 65;
                sb.append((char) letterValue);
                double value = outputs.get(i);
                if (value < 0.01)
                    value = 0;
                if (value > 0.99)
                    value = 1;

                value *= 1000;
                int x = (int) (value);
                value = x / 1000.0;

                sb.append("\t " + Math.round(value*100.0f) + "%");
                sb.append("\n");
            }
            outputTextArea.setText(sb.toString());
        }
}
