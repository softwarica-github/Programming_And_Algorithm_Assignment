package pipeNetwork;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Signup extends JFrame {

    Signup() {

        setTitle("Signup");
        getContentPane().setBackground(Color.CYAN);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        setSize(600, 600);

        JLabel title = new JLabel();

        title.setText("Signup");
        title.setBounds(250, 100, 120, 30);

        JLabel username = new JLabel();
        username.setText("Username:");
        username.setBounds(100, 160, 100, 20);

        JTextField usernameField = new JTextField();
        usernameField.setBounds(180, 160, 220, 25);

        JLabel password = new JLabel();
        password.setText("Password:");
        password.setBounds(100, 210, 100, 20);

        JTextField passwordField = new JTextField();
        passwordField.setBounds(180, 210, 220, 25);

        JButton signup = new JButton("Signup");
        signup.setBounds(225, 280, 120, 25);

        signup.addActionListener(e -> {

            boolean output = signupUser(usernameField.getText(), passwordField.getText());

            if (output) {

                JOptionPane.showMessageDialog(this, "Signup successful!");

            } else {
                JOptionPane.showMessageDialog(this, "Something un-expected happened");
            }

        });

        JButton logme = new JButton("Login");
        logme.setBounds(225, 310, 120, 25);
        logme.addActionListener(e -> {
            this.dispose();
            new Login();
        });

        add(title);
        add(username);
        add(usernameField);
        add(password);
        add(passwordField);
        add(signup);
        add(logme);

        setVisible(true);

    }

    // This method just adds data in the users.txt file
    boolean signupUser(String username, String pass) {

        try {

            BufferedReader br = new BufferedReader(new FileReader("D:\\DSA\\pipeNetwork\\users.txt"));

            // checks if there is already data
            boolean exists = br.readLine() != null ? true : false;

            br.close();

            // Appen mode is used while writing in users file
            BufferedWriter bw = new BufferedWriter(new FileWriter("D:\\DSA\\pipeNetwork\\users.txt", true));

            String input = username + pass;// whole data is turned into single string

            if (exists) {// if exists, line is changed while appending

                bw.append("\n" + input);

            } else {// if file doesn't contains any data, data is just normally wrote
                bw.write(input);
            }

            bw.close();

            return true;

        } catch (Exception e) {
            throw new Error(e);
        }

    }

}
