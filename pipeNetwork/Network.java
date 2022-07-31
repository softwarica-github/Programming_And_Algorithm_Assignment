package pipeNetwork;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Component;
import java.io.BufferedReader;
import java.io.FileReader;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Network extends JFrame {

    Network() {

        setTitle("Network");
        getContentPane().setBackground(Color.CYAN);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        setSize(600, 600);

        JButton backbtn = new JButton();
        backbtn.setText("Back");
        backbtn.setBounds(100, 150, 150, 25);
        backbtn.addActionListener(e -> {
            this.dispose();
            new Home();
        });

        JLabel titleLabel = new JLabel();
        titleLabel.setText("The whole network is displayed below:");
        titleLabel.setBounds(100, 200, 240, 25);

        String output = this.wholeGraph();// graph data is accessed

        JTextArea totalGraph = new JTextArea();
        totalGraph.setText("1---->\n" + output);// total graph is displayed
        totalGraph.setBounds(130, 300, 180, 180);
        // totalGraph.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(backbtn);
        add(titleLabel);
        add(totalGraph);

        setVisible(true);

    }

    String wholeGraph() {
        try {

            BufferedReader brC = new BufferedReader(new FileReader(
                    "D:\\DSA\\pipeNetwork\\graphFileCirculator.txt"));

            String mainFile = brC.readLine();
            // System.out.println(mainFile);
            // String secondary = brC.readLine();
            // System.out.println(secondary);

            brC.close();

            BufferedReader br = new BufferedReader(new FileReader("D:\\DSA\\pipeNetwork\\" + mainFile));
            // reading the whole graph main file
            // itteratively in a single text, in a
            // graph like representation
            String s;
            String totalText = br.readLine();

            while ((s = br.readLine()) != null) {
                totalText += "\n" + s;
            }

            br.close();

            System.out.println(totalText);

            return totalText;// same graph like text format is returned to
            // have displayed in textArea

        } catch (Exception e) {
            throw new Error(e);
        }
    }

}
