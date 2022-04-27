package ru.projectiot.app.MainWindow;

import ru.projectiot.app.Path;

import javax.swing.*;
import java.awt.*;

public class MainMenuImg extends JLabel {
    public MainMenuImg(){
        super(new ImageIcon(
                new ImageIcon(
                        new Path().getPath(
                                "mainmenupath"
                        )).getImage().getScaledInstance(
                        150,
                        150,
                        Image.SCALE_DEFAULT
                )));

        setBackground(Color.black);
        setOpaque(true);
        setVisible(true);
    }
}
