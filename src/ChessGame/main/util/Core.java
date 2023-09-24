package ChessGame.main.util;


//testing launch frame
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import ChessGame.main.ui.LaunchPage;
import ChessGame.main.ui.PreferencesFrame;


public class Core {



    //Preset value
    private static Core coreInstance = new Core();
    private static boolean isPLaying;
    private static LaunchPage launchPage;
    private static PreferencesFrame preferencesFrame;
    //private static GameModel gameModel;


    public static Core getCoreInstance()
    {
        return coreInstance;
    }


    public static void Launch(){
        isPLaying = false;
        launchPage = new LaunchPage();
        launchPage.setVisible(true);

    }





    public Core() {

    }


    public static LaunchPage getLaunchPage(){
        return launchPage;
    }

    public static PreferencesFrame getPreferencesFrame(){return preferencesFrame;}
    public static boolean isPLaying(){
        return isPLaying;
    }
}
