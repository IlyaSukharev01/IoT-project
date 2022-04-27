package ru.projectiot.app.Connection;

import ru.projectiot.app.MainWindow.MainWindow;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Connection extends JDialog{
    private JTextPane paneIPAdress;
    private JTextPane panePORT;
    private JButton submitForm;

    private JLabel imgLabel;
    private JTextPane paneWarningConnection;

    private Socket clientSocket;

    private Connection connection;
    public Connection(MainWindow mainWindow){
        //super("Connection");

        mainWindow.getBtn().setEnabled(false);

        JLabel panesFrame = new JLabel();

        paneIPAdress = new PaneIPAdress();
        panePORT = new PanePORT();
        submitForm = new SubmitForm();
        paneWarningConnection = new WarningConnection();
        imgLabel = new ImageLabel();

        submitForm.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                            String ip = paneIPAdress.getText();
                            String port = panePORT.getText();
                            ip = ip.replace(" ", "");
                            port = port.replace(" ", "");

                            new SocketConnect(
                                    "socketConnect",
                                    getConnection(),
                                    paneWarningConnection,
                                    ip,
                                    port
                            ).start();

                            if (clientSocket != null){
                                if (clientSocket.isConnected()){
                                    System.out.println("Sucessfully connected!");
                                    mainWindow.setClientSocket(clientSocket);
                                    mainWindow.setVisible(false);
                                    mainWindow.getNewClientPage();
                                }
                                if (clientSocket.isClosed()){
                                    System.out.println("Trying to reconnect!");
                                }
                            }
                    }
                }
        );
        panesFrame.setOpaque(true);
        panesFrame.setBackground(
                Color.BLACK
        );
        panesFrame.setForeground(Color.LIGHT_GRAY);
        panesFrame.setLayout(new GridLayout(
                4, 0, 25, 25
        ));
        panesFrame.add(
                paneIPAdress, panesFrame.getLayout()
        );
        panesFrame.add(
                panePORT, panesFrame.getLayout()
        );
        panesFrame.add(
                submitForm, panesFrame.getLayout()
        );
        panesFrame.add(
                paneWarningConnection, panesFrame.getLayout()
        );

        setLayout(new BorderLayout());

        add(imgLabel, BorderLayout.NORTH);
        add(panesFrame, BorderLayout.CENTER);


        setBackground(Color.black);
        setSize(
                800,
                500
        );
        panesFrame.setVisible(true);
        setVisible(true);
    }
    public void setConnection(Connection connection){
        this.connection = connection;
    }
    public Connection getConnection(){
        return connection;
    }
    public void setClientSocket(Socket clientSocket){
        this.clientSocket = clientSocket;
    }

}
class SocketConnect extends Thread {
    private JTextPane warningPane;
    private String ipAdress;
    private String port;

    private Connection connection;

    public SocketConnect(
            String threadName,
            Connection connection,
            JTextPane warningPane,
            String ipAdress,
            String port
    ) {
        super(threadName);
        this.connection = connection;
        this.warningPane = warningPane;
        this.ipAdress = ipAdress;
        this.port = port;
    }

    @Override
    public void run() {
        Socket clientSocket = null;
        try {
            clientSocket = new Socket(
                    ipAdress,
                    Integer.parseInt(port)
            );
        } catch (Exception exceptions) {
            System.out.println(
                    "Can`t connect to this ip, port!\n" + exceptions
            );
            warningPane.setText("");
            if (exceptions instanceof IOException) {
                warningPane.setText(
                        "The I/O error"
                );
            } else if (exceptions instanceof SecurityException) {
                warningPane.setText(
                        "checkConnect method doesn't allow the operation"
                );
            } else if (exceptions instanceof IllegalArgumentException) {
                warningPane.setText(
                        "The port is incorrect"
                );
            }
        }
        connection.setClientSocket(
                clientSocket
        );
    }
}
