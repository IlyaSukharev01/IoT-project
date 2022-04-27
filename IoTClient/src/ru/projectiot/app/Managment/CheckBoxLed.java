package ru.projectiot.app.Managment;

import ru.projectiot.app.Fonts;
import ru.projectiot.app.Path;

import javax.swing.*;
import java.awt.*;

public class CheckBoxLed extends JCheckBox {
    private Panel panel;

    public CheckBoxLed(Panel panel){
        super("Led");
        setToolTipText(
                "Led lamp"
        );
        setFont(
                new Fonts().getFontConnectionFormText()
        );
        this.panel = panel;


        setIcon(
                new ImageIcon(
                        new ImageIcon(
                                new Path().getPath(
                                        "ledpath"
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
