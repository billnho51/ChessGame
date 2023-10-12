package ChessGame.main.ui;

import ChessGame.main.util.GameManager;
import ChessGame.main.util.Preferences;

import javax.swing.*;
import java.sql.Time;
import java.util.Observable;
import java.util.Observer;
import java.util.Timer;

public class TimerPanel extends JPanel implements Observer{
    private GameManager gameManager;
    private Preferences preferences;

    //private Time whiteTime;
    private int whiteTime;
    private int blackTime;
    //private Time blackTime;


    public TimerPanel(GameManager gameManager){
        this.gameManager = gameManager;
        this.preferences = gameManager.getPreferences();
        InitializedTime();


    }

    private void InitializedTime(){
        if(preferences.getTimerMode() ==  Preferences.TimerMode.COUNTDOWN){
            whiteTime = preferences.getTimeLimit();
            blackTime = preferences.getTimeLimit();
        }
        else{
            whiteTime = 0;
            blackTime = 0;
        }
    }

    public void Start(){

    }
    public void Stop(){

    }
    public void UpdateTimeWhite(){
        if (preferences.getTimerMode() == Preferences.TimerMode.COUNTDOWN) System.out.println("white Time is: " + --whiteTime);
        else System.out.println("white Time is: " + (++whiteTime));
    }
    public void UpdateTimeBlack(){
        if (preferences.getTimerMode() == Preferences.TimerMode.COUNTDOWN) System.out.println("black Time is: " + --blackTime);
        else System.out.println("black Time is: " + (++blackTime));
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
