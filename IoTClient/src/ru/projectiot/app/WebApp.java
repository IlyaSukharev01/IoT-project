package ru.projectiot.app;

import ru.projectiot.app.MainWindow.MainWindow;

import javax.swing.*;

public class WebApp {
    public void start(){
        SwingUtilities.invokeLater(
                new Runnable() {
                    @Override
                    public void run() {
                        new MainWindow();
                    }
                }
        );
    }
}
