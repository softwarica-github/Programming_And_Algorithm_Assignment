package pipeNetwork;

import javax.swing.JFrame;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileReader;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ShortestPath extends JFrame {

    ShortestPath() {

        setTitle("ShortestPath");
        getContentPane().setBackground(Color.CYAN);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        setSize(600, 600);

        JTextArea note = new JTextArea();
        note.setText(
                "Source is house 1=>1\n Always while connecting go descending\n example: if you need to find path from 2-4 always go\n From:4 To:2");
        note.setBounds(80, 100, 275, 80);

        JLabel from = new JLabel();
        from.setText("From:");
        from.setBounds(100, 210, 180, 20);

        JTextField fromFld = new JTextField();
        fromFld.setBounds(180, 240, 220, 25);

        JLabel tolb = new JLabel();
        tolb.setText("To:");
        tolb.setBounds(100, 270, 180, 20);

        JTextField toFld = new JTextField();
        toFld.setBounds(180, 300, 220, 25);

        JLabel path = new JLabel();
        path.setText("Path:[Initiate]");
        path.setBounds(120, 405, 180, 20);

        JButton find = new JButton();
        find.setText("Find");
        find.setBounds(120, 345, 220, 25);

        find.addActionListener(e -> {
            // after finding shortest path, the value is shown in the label
            String pathOut = checkConnect(fromFld.getText(), toFld.getText());
            path.setText("Path:" + pathOut);

        });

        JButton backbtn = new JButton();
        backbtn.setText("Back");
        backbtn.setBounds(120, 375, 150, 25);
        backbtn.addActionListener(e -> {
            this.dispose();
            new Home();
        });

        add(note);
        add(from);
        add(fromFld);
        add(tolb);
        add(toFld);
        add(find);
        add(backbtn);
        add(path);

        setVisible(true);

    }

    // here the check connect is same as in edithome,but the enpoint is not always
    // source
    String checkConnect(String connectFrom, String connectTo) {

        int arrSize = Integer.parseInt(connectFrom);
        String[][] requiredGraph = new String[arrSize][arrSize];
        int connTo = Integer.parseInt(connectTo);

        try {

            BufferedReader br = new BufferedReader(new FileReader("D:\\DSA\\pipeNetwork\\graphFileCirculator.txt"));
            String mainFile = br.readLine();

            br.close();

            BufferedReader brMain = new BufferedReader(new FileReader("D:\\DSA\\pipeNetwork\\" + mainFile));

            // Again, the array is build from the data of existing
            // graph read from the file
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

            // Here, the shortest among row is selected unless, it
            // reaches the end as connectt -1, the data is taken from
            // reverse side

            for (int i = arrSize - 1; i > connTo - 1; i--) {

                int shortest = 99999;// variable just for the itterative
                // comparision in the respective row

                if (i > nextCheck) {
                    continue;
                }

                for (int j = arrSize - 2; j >= 0; j--) {

                    // the comparision value should be smallest but greater than 0
                    if (Integer.parseInt(requiredGraph[i][j]) < shortest &&
                            Integer.parseInt(requiredGraph[i][j]) > 0) {

                        shortest = Integer.parseInt(requiredGraph[i][j]);
                        nextCheck = j;
                        // System.out.println(shortest);
                        lenghtFirst += shortest;// lengthFirst records the shortest path length
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
