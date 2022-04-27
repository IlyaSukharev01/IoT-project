package ru.projectiot.app;

import java.net.URL;
import java.util.Locale;

enum ConstantPath{
    LOADINGPATH,
    DUCKPATH,
    LEDPATH,
    MOTORPATH,
    MAINMENUPATH,
}


public class Path {
    private URL loadingPath = getClass().getResource("/resources/loading.gif");

    private URL duckPath = getClass().getResource("/resources/duck.gif");
    private URL mainMenuPath = getClass().getResource("/resources/menu.gif");

    private URL ledPath = getClass().getResource("/resources/led.png");

    private URL motorPath = getClass().getResource("/resources/motor.png");



    public URL getPath(String path){

        path = path.toUpperCase(Locale.ROOT);

        if (path.equals(ConstantPath.DUCKPATH.toString())){
            return duckPath;
        }
        else if (path.equals(ConstantPath.LEDPATH.toString())){
            return ledPath;
        }
        else if (path.equals(ConstantPath.LOADINGPATH.toString())){
            return loadingPath;
        }
        else if (path.equals(ConstantPath.MOTORPATH.toString())){
            return motorPath;
        }
        else if (path.equals(ConstantPath.MAINMENUPATH.toString())){
            return mainMenuPath;
        }
        else{
            return null;
        }
    }
}
