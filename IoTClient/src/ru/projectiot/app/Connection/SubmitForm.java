package ru.projectiot.app.Connection;

import ru.projectiot.app.Fonts;

import javax.swing.*;
import java.awt.*;

public class SubmitForm extends JButton {
    public SubmitForm(){
        super("CONNECT");

        setBackground(Color.MAGENTA);
        setMargin(new Insets(0, 25, 0, 0));
        setFont(new Fonts().getFontConnectionFormText());
    }
}
