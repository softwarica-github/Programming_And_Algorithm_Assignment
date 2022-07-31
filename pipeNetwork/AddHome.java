package pipeNetwork;

import java.io.*;

import javax.swing.JFrame;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class AddHome extends JFrame {

    AddHome() {

        setTitle("Add-Home");
        getContentPane().setBackground(Color.CYAN);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        setSize(600, 600);

        // JLabel nameforlb = new JLabel();
        // nameforlb.setText("Connection name:");
        // nameforlb.setBounds(100, 150, 180, 20);

        // JTextField namefor = new JTextField();
        // namefor.setBounds(180, 180, 220, 25);

        JTextArea note = new JTextArea();
        note.setText("Source is house 1=>1\n example: connect from: 1");
        note.setBounds(80, 100, 140, 80);

        JLabel namelb = new JLabel();
        namelb.setText("Connect from:");
        namelb.setBounds(100, 210, 180, 20);

        JTextField name = new JTextField();
        name.setBounds(180, 240, 220, 25);

        JLabel lengthlb = new JLabel();
        lengthlb.setText("Pipe-length:");
        lengthlb.setBounds(100, 270, 180, 20);

        JTextField length = new JTextField();
        length.setBounds(180, 300, 220, 25);

        JLabel pressurelb = new JLabel();
        pressurelb.setText("Water-Pressure(PSI):");
        pressurelb.setBounds(100, 330, 200, 20);

        JTextField pressure = new JTextField();
        pressure.setBounds(180, 360, 220, 25);

        JButton addbtn = new JButton();
        addbtn.setText("Add");
        addbtn.setBounds(120, 390, 150, 25);
        addbtn.addActionListener(e -> {
            if (addHouse(name.getText(), length.getText(), pressure.getText())) {

                // System.out.println("done");
                // System.out.println(length.getText());
                JOptionPane.showMessageDialog(this, "New house added!");
            }
        });

        JButton backbtn = new JButton();
        backbtn.setText("Back");
        backbtn.setBounds(120, 425, 150, 25);
        backbtn.addActionListener(e -> {
            this.dispose();
            new Home();
        });

        // add(nameforlb);
        // add(namefor);
        add(note);
        add(namelb);
        add(name);
        add(lengthlb);
        add(length);
        add(pressurelb);
        add(pressure);
        add(addbtn);
        add(backbtn);

        setVisible(true);

    }

    // function that adds new house
    boolean addHouse(String connectFrom, String pipeLen, String pressure) {

        try {

            // BufferedReader br = new BufferedReader(
            // new FileReader("D:\\DSA\\pipeNetwork\\graph.txt"));

            // int i = 0;

            // while (br.readLine() != null) {
            // i++;
            // }
            // // System.out.println(i);

            // br.close();

            // File graphFileCirculator stores two files name
            // and in that file, first line is always primary file
            // where in that instant data is stored and in the second line
            // the secondary file is stored which is used as buffer until next
            // simultaneous read-write

            BufferedReader brC = new BufferedReader(new FileReader(
                    "D:\\DSA\\pipeNetwork\\graphFileCirculator.txt"));

            String mainFile = brC.readLine();
            System.out.println(mainFile);
            String secondary = brC.readLine();
            System.out.println(secondary);

            brC.close();

            // After reading, alter the line position for next instance
            // of file access

            BufferedWriter bwC = new BufferedWriter(new FileWriter(
                    "D:\\DSA\\pipeNetwork\\graphFileCirculator.txt"));

            bwC.write(secondary);
            bwC.write("\n" + mainFile);

            bwC.close();

            BufferedReader br = new BufferedReader(
                    new FileReader("D:\\DSA\\pipeNetwork\\" + mainFile));

            BufferedWriter bw = new BufferedWriter(
                    new FileWriter("D:\\DSA\\pipeNetwork\\" + secondary));

            int connectWhere = Integer.parseInt(connectFrom);
            int i = 1;
            String s = br.readLine();
            int len = s.length();

            boolean start = true;
            boolean go = true;

            while (go) {// searches for position to connect and puts pipelength
                // System.out.println(s);
                if (start) {
                    if (i == connectWhere) {

                        bw.write(s + "-" + pipeLen);
                    } else {
                        bw.write(s + "-0");
                    }
                    // bw.flush();
                    start = false;
                } else {
                    s = br.readLine();

                    if (s == null) {

                        break;
                    } else {

                        i++;

                        // System.out.println(s.length());
                        // if (s.length() > len) {
                        // go = false;
                        // } else {
                        // System.out.println(s);

                        if (i == connectWhere) {
                            bw.write("\n" + s + "-" + pipeLen);
                        } else {

                            bw.write("\n" + s + "-0");
                        }

                        // bw.flush();

                    }
                    // }
                }

                // i++;
            }

            String[] lastLine = new String[i + 1];

            // lastLine[0] = "1";

            // since new house line will be added, data in to column, pipelenght is added
            for (int j = 0; j <= i; j++) {

                // Cause index start from 0 but pointer starts from 1
                if (j == connectWhere - 1) {

                    if (j == 0) {

                        lastLine[j] = pipeLen;
                    } else {

                        lastLine[j] = "-" + pipeLen;
                    }
                } else {

                    if (j == 0) {

                        lastLine[j] = "0";
                    } else {

                        lastLine[j] = "-0";
                    }

                }

            }

            String data = String.join("", lastLine);
            bw.write("\n" + data);

            br.close();
            bw.close();

            // clear out previoius primary or main file storage

            BufferedWriter bmw = new BufferedWriter(
                    new FileWriter("D:\\DSA\\pipeNetwork\\" + mainFile));

            bmw.close();

            // Adding pressure into info.txt by appending
            BufferedWriter infobw = new BufferedWriter(new FileWriter("D:\\DSA\\pipeNetwork\\info.txt", true));

            infobw.append("\n" + pressure + "-" + 1);
            infobw.close();

            return true;

        } catch (Exception e) {
            throw new Error("Something's wrong!");

        }

    }

}
