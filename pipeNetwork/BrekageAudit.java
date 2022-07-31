package pipeNetwork;

import java.io.*;

import javax.swing.JFrame;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class BrekageAudit extends JFrame {

    BrekageAudit() {

        setTitle("Audit Brekage");
        getContentPane().setBackground(Color.CYAN);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        setSize(600, 600);

        JLabel breakageConnLbl = new JLabel();
        breakageConnLbl.setText("Broken node(House):");
        breakageConnLbl.setBounds(100, 210, 180, 20);

        JTextField breakageConnFld = new JTextField();
        breakageConnFld.setBounds(180, 240, 220, 25);

        JButton audit = new JButton();
        audit.setText("audit");
        audit.setBounds(120, 270, 220, 25);
        audit.addActionListener(e -> {

            if (breakageAudit(breakageConnFld.getText())) {

                JOptionPane.showMessageDialog(this, "Brekage point Audited!");

            }

        });

        JButton backbtn = new JButton();
        backbtn.setText("Back");
        backbtn.setBounds(120, 300, 150, 25);
        backbtn.addActionListener(e -> {
            this.dispose();
            new Home();
        });

        add(breakageConnLbl);
        add(breakageConnFld);
        add(audit);
        add(backbtn);

        setVisible(true);

    }

    boolean breakageAudit(String house) {

        try {

            // first the brekage.txt is read
            BufferedReader br = new BufferedReader(new FileReader("D:\\DSA\\pipeNetwork\\brekage.txt"));

            // if there is existing value, exists becomes true else false
            boolean exists = (br.readLine() != null) ? true : false;

            br.close();

            // Appending to the brekage file
            BufferedWriter bw = new BufferedWriter(new FileWriter("D:\\DSA\\pipeNetwork\\brekage.txt", true));

            if (!exists) {// if does not exists, write simply the house
                bw.write(house);
            } else {// else, write house with "-" in front
                bw.append("-" + house);
            }

            bw.close();

            return true;
        } catch (Exception e) {
            throw new Error(e);
        }
    }

}
