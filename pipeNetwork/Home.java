package pipeNetwork;

import javax.swing.JFrame;

import java.awt.Color;

import javax.swing.JButton;
// import javax.swing.JFrame;
// import javax.swing.JLabel;
// import javax.swing.JTextField;

public class Home extends JFrame {

    Home() {

        setTitle("Home");
        getContentPane().setBackground(Color.CYAN);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        setSize(600, 600);

        JButton addHome = new JButton();
        addHome.setText("Add-Home");
        addHome.setBounds(225, 200, 150, 25);
        addHome.addActionListener((e) -> {
            this.dispose();
            new AddHome();
        });

        JButton editHome = new JButton();
        editHome.setText("Edit-Home");
        editHome.setBounds(225, 240, 150, 25);
        editHome.addActionListener((e) -> {
            this.dispose();
            new EditHome();
        });

        JButton infoPage = new JButton();
        infoPage.setText("Info-Page");
        infoPage.setBounds(225, 280, 160, 25);
        infoPage.addActionListener(e -> {
            this.dispose();
            new InfoPage();
        });

        JButton network = new JButton();
        network.setText("Network");
        network.setBounds(225, 320, 150, 25);
        network.addActionListener(e -> {
            this.dispose();
            new Network();
        });

        JButton shortestPath = new JButton();
        shortestPath.setText("Shortest-Path");
        shortestPath.setBounds(225, 360, 170, 25);
        shortestPath.addActionListener(e -> {
            this.dispose();
            new ShortestPath();
        });

        JButton brekageAud = new JButton();
        brekageAud.setText("Brekage audit");
        brekageAud.setBounds(225, 400, 170, 25);
        brekageAud.addActionListener(e -> {
            this.dispose();
            new BrekageAudit();
        });

        JButton brekageFix = new JButton();
        brekageFix.setText("Fix Brekage");
        brekageFix.setBounds(225, 440, 170, 25);
        brekageFix.addActionListener(e -> {
            this.dispose();
            new BrekageFix();
        });

        add(addHome);
        add(editHome);
        add(infoPage);
        add(network);
        add(shortestPath);
        add(brekageAud);
        add(brekageFix);

        setVisible(true);

    }

}
