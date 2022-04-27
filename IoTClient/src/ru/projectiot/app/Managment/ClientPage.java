package ru.projectiot.app.Managment;

import ru.projectiot.app.Fonts;

import javax.swing.*;
import java.awt.*;
import java.net.Socket;

public class ClientPage extends JFrame{
    private Socket clientSocket;
    private JTextPane textPanel;
    private JPanel panel;
    public ClientPage(Socket clientSocket){
        super("Management");

        this.clientSocket = clientSocket;

        setLayout(new BorderLayout());


        panel = new Panel(this);
        textPanel = new JTextPane();
        textPanel.setText(
                        "Центр управления умным домом"
        );
        textPanel.setOpaque(true);
        textPanel.setBackground(Color.gray);
        textPanel.setForeground(Color.white);
        textPanel.setFont(new Fonts().getFontMainText());
        textPanel.setEnabled(false);

        add(textPanel, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);

        setSize(
                1280,
                720
        );
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public Socket getClientSocket(){
        return clientSocket;
    }
}