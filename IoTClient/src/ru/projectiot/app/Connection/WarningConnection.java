package ru.projectiot.app.Connection;

import ru.projectiot.app.Fonts;

import javax.swing.*;
import java.awt.*;

public class WarningConnection extends JTextPane {
    public WarningConnection(){
        setToolTipText("Prints warning in connection");
        setBackground(Color.black);
        setFont(new Fonts().getFontConnectionFormText());
        setEnabled(false);
        setMargin(new Insets(0, 25, 0, 0));
    }
}
