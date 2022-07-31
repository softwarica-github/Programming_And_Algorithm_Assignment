package pipeNetwork;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileReader;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Login extends JFrame {

    Login() {

        setTitle("Login");
        getContentPane().setBackground(Color.CYAN);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        setSize(600, 600);

        JLabel title = new JLabel();

        title.setText("Login");
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

        JButton login = new JButton("Login");
        login.setBounds(225, 280, 120, 25);
        login.addActionListener(e -> {

            if (loginUser(usernameField.getText(), passwordField.getText())) {

                this.dispose();
                new Home();
            } else {

                JOptionPane.showMessageDialog(this, "Credentials didn't match");

            }

        });

        add(title);
        add(username);
        add(usernameField);
        add(password);
        add(passwordField);
        add(login);

        setVisible(true);

    }

    boolean loginUser(String username, String pass) {

        try {

            String givenData = username + pass;

            BufferedReader br = new BufferedReader(new FileReader("D:\\DSA\\pipeNetwork\\users.txt"));

            String s;

            boolean found = false;

            // the whole users file is read and itteratively
            // compared with the givenData which is the user inputed
            // data

            while ((s = br.readLine()) != null) {

                if (s.equals(givenData)) {
                    found = true;
                    break;
                }

            }

            br.close();

            return found;

        } catch (Exception e) {
            throw new Error(e);
        }

    }

}
