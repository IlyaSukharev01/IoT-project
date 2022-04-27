package ru.projectiot.app.Connection;

import ru.projectiot.app.Fonts;

import javax.swing.*;
import java.awt.*;

public class PanePORT extends JTextPane {
    public PanePORT(){
        setText("Enter port");
        setToolTipText("Enter the port of server, which you want to connect");
        setBackground(
                Color.BLACK
        );

        setMargin(new Insets(0, 25, 0, 0));
        setFont(new Fonts().getFontConnectionFormText());
    }
}
