package ru.projectiot.app.Managment;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Panel extends JPanel implements ActionListener {
    private ClientPage clientPage;
    private JCheckBox checkboxLed;
    private JCheckBox checkboxMotorUp;
    private JCheckBox checkboxMotorDown;
    private JTextPane ledStatePane;
    private JTextPane motorUpStatePane;
    private JTextPane motorDownStatePane;

    private Socket clientSocket;

    public Panel(ClientPage clientPage) {

        this.clientPage = clientPage;
        this.clientSocket = clientPage.getClientSocket();

        setLayout(new GridLayout(
                3, 1, 2, 5
        ));

        checkboxLed = new CheckBoxLed(this);
        checkboxMotorUp = new MotorUpLed();
        checkboxMotorDown = new MotorDownLed();
        ledStatePane = new LedStatePane();
        motorUpStatePane = new MotorUpStatePane();
        motorDownStatePane = new MotorDownStatePane();

        checkboxMotorUp.addActionListener(this);
        checkboxMotorDown.addActionListener(this);
        checkboxLed.addActionListener(this);

        add(checkboxMotorUp, getLayout());
        add(motorUpStatePane, getLayout());
        add(checkboxLed, getLayout());
        add(ledStatePane, getLayout());
        add(checkboxMotorDown, getLayout());
        add(motorDownStatePane, getLayout());

    }
    @Override
    public void actionPerformed(ActionEvent e){
        JCheckBox checkBox = (JCheckBox)e.getSource();

        if(clientSocket.isConnected()){
            try{
                BufferedWriter bw = new BufferedWriter(
                        new OutputStreamWriter(
                                clientSocket.getOutputStream()
                        ));
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(
                                clientSocket.getInputStream()
                        )
                );
                if (checkBox == checkboxLed){
                    bw.write('1');
                }
                if (checkBox == checkboxMotorDown){
                    bw.write('0');
                }
                if (checkBox == checkboxMotorUp){
                    bw.write('0');
                }
                bw.flush();

                char[] buff = new char[10];
                br.read(buff);
                String str = "";
                for (char elem : buff){
                    if (elem != 0){
                        str += String.valueOf(elem);
                    }
                }
                System.out.println(str);
                if (str.equals("LEDON")){
                    ledStatePane.setText("'LED' is on");
                }
                else if (str.equals("LEDOFF")){
                    ledStatePane.setText("'LED' is off");
                }
                else if (str.equals("MOTORUPON")){
                    motorUpStatePane.setText("'Motor UP' is on");
                }
                else if (str.equals("MOTORUPOFF")){
                    motorUpStatePane.setText("'Motor UP' is off");
                }

            }catch(Exception exception){
                System.out.println("Warning to deliever and arrive pockets");
            }

        }

    }
}