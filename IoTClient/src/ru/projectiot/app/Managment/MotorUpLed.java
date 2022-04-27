package ru.projectiot.app.Managment;

import ru.projectiot.app.Fonts;
import ru.projectiot.app.Path;

import javax.swing.*;
import java.awt.*;

public class MotorUpLed extends JCheckBox {
    public MotorUpLed(){
        super("Motor ↑");

        setFont(
                new Fonts().getFontConnectionFormText()
        );

        setToolTipText(
                "Motor up"
        );

        setIcon(
                new ImageIcon(
                        new ImageIcon(
                                new Path().getPath(
                                        "motorpath"
                                )).getImage().getScaledInstance(
                                150,
                                150,
                                Image.SCALE_DEFAULT
                        )));

        setSelected(false);
        setOpaque(true);
        setBackground(Color.darkGray);
        setForeground(Color.white);

    }
}
