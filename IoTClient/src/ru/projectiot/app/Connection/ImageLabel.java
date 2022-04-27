package ru.projectiot.app.Connection;

import ru.projectiot.app.Path;

import javax.swing.*;
import java.awt.*;

public class ImageLabel extends JLabel {
    public ImageLabel(){
        super(new ImageIcon(
                new ImageIcon(
                        new Path().getPath(
                                "loadingpath"
                        )).getImage().getScaledInstance(
                                        250,
                                        250,
                                        Image.SCALE_DEFAULT
                )));

        setOpaque(true);
        setBackground(Color.black);
        setVisible(true);
    }
}
