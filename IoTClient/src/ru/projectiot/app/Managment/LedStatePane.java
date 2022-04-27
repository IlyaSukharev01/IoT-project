package ru.projectiot.app.Managment;

import ru.projectiot.app.Fonts;

import javax.swing.*;
import java.awt.*;
import java.util.Locale;


public class LedStatePane extends JTextPane {
    public LedStatePane(){
        setFont(
                new Fonts().getFontConnectionFormText()
        );

        setEnabled(false);
        setBackground(Color.darkGray);
        setForeground(Color.white);
        setOpaque(true);
    }

}
