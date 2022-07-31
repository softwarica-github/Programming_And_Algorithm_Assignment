package pipeNetwork;

import java.io.*;

import javax.swing.JFrame;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class InfoPage extends JFrame {

    InfoPage() {

        setTitle("InfoPage");
        getContentPane().setBackground(Color.CYAN);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        setSize(600, 600);

        JLabel infoHomeLbl = new JLabel();
        infoHomeLbl.setText("Info for:");
        infoHomeLbl.setBounds(100, 210, 180, 20);

        JTextField infoHomeFld = new JTextField();
        infoHomeFld.setBounds(180, 240, 220, 25);

        JButton backbtn = new JButton();
        backbtn.setText("Back");
        backbtn.setBounds(120, 300, 150, 25);
        backbtn.addActionListener(e -> {
            this.dispose();
            new Home();
        });

        JLabel pressure = new JLabel();
        pressure.setText("Pressure(PSI):");
        pressure.setBounds(100, 340, 180, 20);

        JLabel state = new JLabel();
        state.setText("State:");
        state.setBounds(100, 360, 180, 20);

        JButton getInfo = new JButton();
        getInfo.setText("Get info");
        getInfo.setBounds(120, 270, 220, 25);
        getInfo.addActionListener(e -> {

            String[] states = statesReturner(infoHomeFld.getText());

            // states[0] consits pressure-1 where 1 is healthy, here 1 is splitted out to
            // show pressure only
            pressure.setText("Pressure: " + states[0].split("-")[0]);

            // For state, conditional rendering is done, if "1"=> Broken else Running
            if (states[1] == "1") {

                state.setText("State: Broken");
            } else {
                state.setText("State: Running");
            }

        });

        add(infoHomeLbl);
        add(infoHomeFld);
        add(getInfo);
        add(backbtn);
        add(pressure);
        add(state);

        setVisible(true);

    }

    String[] statesReturner(String infoHouse) {
        try {

            // gets the path up to the source

            String path = checkConnect(infoHouse, "1");

            System.out.println(path);

            // path data is splitted into array format
            String[] pathItterable = path.split("-");
            // System.out.println(pathItterable[0]);

            BufferedReader br = new BufferedReader(new FileReader("D:\\DSA\\pipeNetwork\\brekage.txt"));

            String brokenJoints = br.readLine();// whole brekage data is read

            br.close();

            System.out.println(brokenJoints);

            // brekage data is also splitted into array
            String[] brokenJointsItterable = brokenJoints.split("-");

            boolean broken = false;// determines if the connection is broken or not

            // itteratively the path nodes are compared with broken nodes
            // if found the broken turns into true else it remains same(false)
            for (int i = 0; i < pathItterable.length; i++) {

                for (int j = 0; j < brokenJointsItterable.length; j++) {

                    boolean same = (Integer.parseInt(pathItterable[i]) == Integer.parseInt(brokenJointsItterable[j]))
                            ? true
                            : false;

                    if (same) {
                        broken = true;
                        break;
                    }

                }

            }

            // Reads pressure data from info.txt
            BufferedReader pbr = new BufferedReader(new FileReader("D:\\DSA\\pipeNetwork\\info.txt"));

            String pressure = "";

            // itterates up to the certain index where our house exists
            for (int k = 0; k < Integer.parseInt(infoHouse); k++) {

                String s = pbr.readLine();

                if (k == (Integer.parseInt(infoHouse) - 1)) {

                    pressure = s;

                }

            }

            pbr.close();

            if (broken) {// if the connection is broken, this is returned

                String[] val = { pressure, "1" };

                return val;
            }

            String[] val = { pressure, "0" };// if the connection is not broken, 1 index has "0"

            return val;

        } catch (Exception e) {

            throw new Error(e);
        }

    }

    // checkConnect is same method as checkConnect from sortestpath class
    // the end point is also considered in the same manner

    String checkConnect(String connectFrom, String connectTo) {

        int arrSize = Integer.parseInt(connectFrom);
        String[][] requiredGraph = new String[arrSize][arrSize];
        int connTo = Integer.parseInt(connectTo);

        try {

            BufferedReader br = new BufferedReader(new FileReader("D:\\DSA\\pipeNetwork\\graphFileCirculator.txt"));
            String mainFile = br.readLine();

            br.close();

            BufferedReader brMain = new BufferedReader(new FileReader("D:\\DSA\\pipeNetwork\\" + mainFile));

            for (int i = 0; i < arrSize; i++) {
                String curr = brMain.readLine();
                String[] inn = curr.split("-");
                requiredGraph[i] = inn;
                // System.out.println(requiredGraph[i][2]);
            }

            int lenghtFirst = 0;
            int nextCheck = arrSize - 1;
            int connectedTo = 0;
            String path = "" + arrSize + "-";

            for (int i = arrSize - 1; i > connTo - 1; i--) {

                int shortest = 99999;

                if (i > nextCheck) {
                    continue;
                }

                for (int j = arrSize - 2; j >= 0; j--) {

                    if (Integer.parseInt(requiredGraph[i][j]) < shortest &&
                            Integer.parseInt(requiredGraph[i][j]) > 0) {

                        shortest = Integer.parseInt(requiredGraph[i][j]);
                        nextCheck = j;
                        // System.out.println(shortest);
                        lenghtFirst += shortest;
                        if (j + 1 != connTo) {
                            path = path + (j + 1) + "-";
                        }
                        if (i == arrSize - 1) {
                            connectedTo = j;
                        }
                        break;

                    }

                }

            }
            path = path + connTo;

            // System.out.println(lenghtFirst);
            // System.out.println(connectedTo);

            int[] resultant = { lenghtFirst, connectedTo };

            // System.out.println(path);
            // System.out.println(lenghtFirst);

            // return resultant;
            return path;
        } catch (Exception e) {
            throw new Error(e);
        }
    }

}
