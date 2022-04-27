package ru.projectiot.app;


import java.awt.*;

public class Fonts{
    private Font fontMainText = new Font("Batang", Font.BOLD, 20);
    private Font fontToolbar =  new Font("Arial", Font.BOLD, 10);

    private Font fontConnectionFormText = new Font("Times new roman", Font.BOLD, 20);

    public Font getFontMainText(){
        return fontMainText;
    }

    public Font getFontToolbar(){
        return fontToolbar;
    }

    public Font getFontConnectionFormText() {return fontConnectionFormText;}
}
