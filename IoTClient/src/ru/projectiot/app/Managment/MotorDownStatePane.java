package ru.projectiot.app.Managment;

import ru.projectiot.app.Fonts;

import javax.swing.*;
import java.awt.*;

public class MotorDownStatePane extends JTextPane {
    public MotorDownStatePane(){
        setFont(
                new Fonts().getFontConnectionFormText()
        );

        setEnabled(false);
        setBackground(Color.darkGray);
        setForeground(Color.white);
        setOpaque(true);
    }
}
