package ru.projectiot.app.MainWindow;

import ru.projectiot.app.Connection.Connection;
import ru.projectiot.app.Managment.ClientPage;
import ru.projectiot.app.Path;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;
import java.net.URL;

public class MainWindow extends JFrame {
    private JButton btn;
    private Socket clientSocket;

    private Connection connection;

    private JLabel mainMenuImg;


    public MainWindow() {
        super("Main");

        btn = new JButton("Click me");
        btn.setToolTipText(
                "Click here to start the program"
        );
        btn.setSize(
                50,
                50
        );
        btn.setBackground(Color.black);
        btn.setForeground(Color.MAGENTA);


        setLayout(new BorderLayout());

        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                connection = new Connection(MainWindow.this);
                connection.setConnection(connection);
            }
        });
        mainMenuImg = new MainMenuImg();

        add(mainMenuImg, BorderLayout.CENTER);
        add(btn, BorderLayout.PAGE_END);


        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );
        setResizable(false);
        setSize(
                1280,
                720
        );
        //pack();
        setVisible(true);
    }

    public void setClientSocket(Socket clientSocket){
        this.clientSocket = clientSocket;
    }
    public void getNewClientPage(){
        new ClientPage(clientSocket);
        connection.setVisible(false);
    }
    public JButton getBtn(){
        return btn;
    }
}