package pipeNetwork;

import java.io.*;

import javax.swing.JFrame;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class BrekageFix extends JFrame {

    BrekageFix() {

        setTitle("Brekage Fix");
        getContentPane().setBackground(Color.CYAN);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        setSize(600, 600);

        JLabel breakageFixConnLbl = new JLabel();
        breakageFixConnLbl.setText("Fixing node(House):");
        breakageFixConnLbl.setBounds(100, 210, 180, 20);

        JTextField breakageFixConnFld = new JTextField();
        breakageFixConnFld.setBounds(180, 240, 220, 25);

        JButton fix = new JButton();
        fix.setText("Fix");
        fix.setBounds(120, 270, 220, 25);
        fix.addActionListener(e -> {
            if (brekageFix(breakageFixConnFld.getText())) {
                JOptionPane.showMessageDialog(this, "House brekage fixed!");
            } else {
                JOptionPane.showMessageDialog(this, "Such point of brekage doesn't exist");
            }

        });

        JButton backbtn = new JButton();
        backbtn.setText("Back");
        backbtn.setBounds(120, 300, 150, 25);
        backbtn.addActionListener(e -> {
            this.dispose();
            new Home();
        });

        add(breakageFixConnLbl);
        add(breakageFixConnFld);
        add(fix);
        add(backbtn);

        setVisible(true);

    }

    // remove the house from the brekage file
    boolean brekageFix(String fixingNode) {

        try {

            BufferedReader br = new BufferedReader(new FileReader("D:\\DSA\\pipeNetwork\\brekage.txt"));
            String data = br.readLine();

            br.close();

            // stores information from the file
            String[] itterableData = data.split("-");

            boolean first = true;
            // System.out.println(fixingNode);
            // System.out.println(itterableData[1]);

            boolean found = false;// shows if the house even exists or not

            String finalResultant = "";

            for (int i = 0; i < itterableData.length; i++) {

                // checks itteratively if file contains the house data
                boolean equal = Integer.parseInt(itterableData[i]) == Integer.parseInt(fixingNode) ? true : false;
                // System.out.println(equal);
                // System.out.println(itterableData[i]);

                if (equal) {
                    found = true;
                    continue;
                } else {

                    // inputs into final write string
                    // only when the house is not found
                    // By that way, there is nor exlusive
                    // exclusion process

                    if (first) {
                        finalResultant += itterableData[i];
                        first = false;
                    } else {
                        finalResultant += "-" + itterableData[i];
                    }

                }

            }

            BufferedWriter bw = new BufferedWriter(new FileWriter("D:\\DSA\\pipeNetwork\\brekage.txt"));
            bw.write(finalResultant);// re-writing the whole brekage file

            bw.close();

            return found;

        } catch (Exception e) {
            throw new Error(e);
        }

    }

}
