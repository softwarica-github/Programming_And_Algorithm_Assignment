package pipeNetwork;

import java.io.*;

import javax.swing.JFrame;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class EditHome extends JFrame {

    EditHome() {

        setTitle("Edit-Home");
        getContentPane().setBackground(Color.CYAN);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        setSize(600, 600);

        JTextArea note = new JTextArea();
        note.setText(
                "Source is house 1=>1\n Always while connecting go descending\n example: if you need to connect 2-4 always go\n connect:4 to:2");
        note.setBounds(80, 100, 250, 80);

        JLabel connectlb = new JLabel();
        connectlb.setText("Connect:");
        connectlb.setBounds(100, 210, 180, 20);

        JTextField connectFld = new JTextField();
        connectFld.setBounds(180, 240, 220, 25);

        JLabel tolb = new JLabel();
        tolb.setText("To:");
        tolb.setBounds(100, 270, 180, 20);

        JTextField toFld = new JTextField();
        toFld.setBounds(180, 300, 220, 25);

        JLabel connLenlb = new JLabel();
        connLenlb.setText("Pipe-Length:");
        connLenlb.setBounds(100, 330, 180, 20);

        JTextField connLenFld = new JTextField();
        connLenFld.setBounds(180, 360, 220, 25);

        JButton doConnect = new JButton();
        doConnect.setText("Connect");
        doConnect.setBounds(120, 395, 220, 25);
        doConnect.addActionListener(e -> {

            int[] valOrg = checkConnectLen(connectFld.getText());
            int[] valCheck = checkConnectLen(toFld.getText());
            int pipeLen = Integer.parseInt(connLenFld.getText());
            // System.out.println(valOrg[0]);
            // System.out.println(valOrg[1]);

            // checks if present connection is larger than new connection,
            // if yes, re-establishment happens

            if (valOrg[0] > (valCheck[0] + pipeLen)) {

                System.out.println("Replace the path");
                // clear the existing connection at valOrg[1] pos
                // and make new connections

                int of = Integer.parseInt(connectFld.getText());
                int prevTo = valOrg[1];
                int nowTo = Integer.parseInt(toFld.getText());

                boolean done = clearAndUpdate(of, prevTo, nowTo, pipeLen);

                if (done) {
                    JOptionPane.showMessageDialog(this, "Newer connection established!");
                }

            } else {
                JOptionPane.showMessageDialog(this, "Connection would not make connection any shorter");

            }

        });

        JButton backbtn = new JButton();
        backbtn.setText("Back");
        backbtn.setBounds(120, 430, 150, 25);
        backbtn.addActionListener(e -> {
            this.dispose();
            new Home();
        });

        add(note);
        add(connectlb);
        add(connectFld);
        add(tolb);
        add(toFld);
        add(connLenlb);
        add(connLenFld);
        add(doConnect);
        add(backbtn);

        setVisible(true);

    }

    // Checking the existing length to the source
    int[] checkConnectLen(String connect) {

        int arrSize = Integer.parseInt(connect);
        String[][] requiredGraph = new String[arrSize][arrSize];

        try {

            BufferedReader br = new BufferedReader(new FileReader("D:\\DSA\\pipeNetwork\\graphFileCirculator.txt"));
            String mainFile = br.readLine();

            br.close();

            BufferedReader brMain = new BufferedReader(new FileReader("D:\\DSA\\pipeNetwork\\" + mainFile));

            for (int i = 0; i < arrSize; i++) {
                String curr = brMain.readLine();
                String[] inn = curr.split("-");
                requiredGraph[i] = inn;// inserting into 2d array for evaluation
                // System.out.println(requiredGraph[i][2]);
            }

            int lenghtFirst = 0;
            int nextCheck = arrSize - 1;
            int connectedTo = 0;

            // here every row is itterated over until it meets
            // the souce and chooses the minimum link from the row

            for (int i = arrSize - 1; i >= 0; i--) {

                int shortest = 99999;// just setting a big value, so that in comparision
                // the length in the row will be smaller
                // and since that value once again will be put
                // in the same variable to test other smaller
                // values in the same row

                if (i > nextCheck) {// nextcheck is variable that skips rows until the link made to smallest
                    // node doesn't reach
                    continue;
                }

                for (int j = arrSize - 2; j >= 0; j--) {
                    // checks if value is smallest but greater than 0
                    if (Integer.parseInt(requiredGraph[i][j]) < shortest &&
                            Integer.parseInt(requiredGraph[i][j]) > 0) {

                        shortest = Integer.parseInt(requiredGraph[i][j]);
                        nextCheck = j;
                        // System.out.println(shortest);
                        // lengthFirst will contain the length that the node already has
                        lenghtFirst += shortest;
                        if (i == arrSize - 1) {
                            connectedTo = j;
                        }
                        break;

                    }

                }

            }

            // System.out.println(lenghtFirst);
            // System.out.println(connectedTo);

            // connectedTo gives node that it has direct relation
            // before any editing

            int[] resultant = { lenghtFirst, connectedTo };

            return resultant;
        } catch (Exception e) {
            throw new Error(e);
        }
    }

    boolean clearAndUpdate(int of, int prevTo, int nowTo, int pipeLen) {
        try {

            BufferedReader brC = new BufferedReader(new FileReader(
                    "D:\\DSA\\pipeNetwork\\graphFileCirculator.txt"));

            String mainFile = brC.readLine();
            System.out.println(mainFile);
            String secondary = brC.readLine();
            System.out.println(secondary);

            brC.close();

            BufferedWriter bwC = new BufferedWriter(new FileWriter(
                    "D:\\DSA\\pipeNetwork\\graphFileCirculator.txt"));

            bwC.write(secondary);
            bwC.write("\n" + mainFile);

            bwC.close();

            BufferedReader br = new BufferedReader(
                    new FileReader("D:\\DSA\\pipeNetwork\\" + mainFile));

            BufferedWriter bw = new BufferedWriter(
                    new FileWriter("D:\\DSA\\pipeNetwork\\" + secondary));

            // Create a 2d array and itterate all the values

            // finding length of columns

            int column = br.readLine().split("-").length;
            // br.mark(0);
            // br.close();
            br.close();

            System.out.println(column);

            br = new BufferedReader(
                    new FileReader("D:\\DSA\\pipeNetwork\\" + mainFile));

            String[][] data = new String[of][column];

            String s;
            // All the data is read in the data 2d array

            for (int i = 0; i <= of - 1; i++) {

                s = br.readLine();

                String[] val = s.split("-");
                System.out.println(val[0]);

                data[i] = val;
            }

            // System.out.println(data[of][column]);

            // clear in previously written pipelength in pos of in
            // prevTo

            // Previous connections are removed

            data[prevTo][of - 1] = "0";
            data[of - 1][prevTo] = "0";

            // Input the new connection pos of nowTo and enter
            // pipeLen

            data[nowTo - 1][of - 1] = String.valueOf(pipeLen);
            data[of - 1][nowTo - 1] = String.valueOf(pipeLen);

            // itterating over and writing
            // br.reset();

            br = new BufferedReader(
                    new FileReader("D:\\DSA\\pipeNetwork\\" + mainFile));

            int itterator = 0;
            boolean moreExtenalPush = false;

            String pusher;
            // now writing whole mainfile once again, with newly found
            // information

            while ((pusher = br.readLine()) != null) {

                if (itterator <= of - 1) {

                    String val = String.join("-", data[itterator]);
                    bw.write(val + "\n");
                    itterator++;
                } else {
                    if (moreExtenalPush) {

                        bw.write("\n" + pusher);
                    } else {

                        bw.write(pusher);
                        moreExtenalPush = true;
                    }
                }

            }

            br.close();
            bw.close();

            // clear out previoius primary or main file storage

            BufferedWriter bmw = new BufferedWriter(
                    new FileWriter("D:\\DSA\\pipeNetwork\\" + mainFile));

            bmw.close();

            return true;

        } catch (Exception e) {
            throw new Error(e);
        }
    }

}
