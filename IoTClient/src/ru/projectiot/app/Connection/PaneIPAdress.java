package ru.projectiot.app.Connection;

import ru.projectiot.app.Fonts;

import javax.swing.*;
import java.awt.*;

public class PaneIPAdress extends JTextPane {
    public PaneIPAdress(){
        setText(
                "IP address"
        );
        setToolTipText("Enter the ip address of server, which you want to connect");
        setBackground(
                Color.BLACK
        );
        setMargin(new Insets(5, 25, 0, 0));
        setFont(new Fonts().getFontConnectionFormText());

    }
}
