package ChessGame.main.ui;

import ChessGame.main.util.GameManager;
import ChessGame.main.util.Preferences;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Observable;
import java.util.Observer;
import java.util.TimeZone;
import java.util.Timer;

public class TimerPanel extends JPanel implements Observer{
    private GameManager gameManager;
    private Preferences preferences;

    private Time whiteTime;
    //private int whiteTime;
    //private int blackTime;
    private Time blackTime;

    private JPanel TimerPanel;
    private JLabel jlTimeBlack;
    private JLabel jlTimeWhite;
    private JLabel whiteTimeLabel;
    private JLabel blackTimeLabel;
    private JPanel WhiteTimerPanel;
    private JPanel BlackTimerPanel;


    public TimerPanel(GameManager gameManager){
        this.gameManager = gameManager;
        this.preferences = gameManager.getPreferences();

        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        whiteTime = Time.valueOf("00:00:00");
        blackTime = Time.valueOf("00:00:00");


        InitializedTime();
        InitializedTimeUI();

        TitledBorder titled;
        titled = BorderFactory.createTitledBorder("Timer");
        titled.setTitleFont(titled.getTitleFont().deriveFont(15f));
        this.setBorder(titled);
        //this.setPreferredSize(new Dimension(200,200));


    }

    private void InitializedTimeUI() {

        TimerPanel = new JPanel(new BorderLayout());
        SimpleDateFormat formatter = new SimpleDateFormat("mm:ss");
        WhiteTimerPanel = new JPanel();
        BlackTimerPanel = new JPanel();

        System.out.println("initializing timer");
        whiteTimeLabel = new JLabel("White: ");
        whiteTimeLabel.setFont(whiteTimeLabel.getFont().deriveFont(48f));;
        blackTimeLabel = new JLabel("Black: ");
        blackTimeLabel.setFont(blackTimeLabel.getFont().deriveFont(48f));;
        jlTimeBlack = new JLabel(formatter.format(blackTime));
        jlTimeBlack.setFont(jlTimeBlack.getFont().deriveFont(48f));;
        jlTimeWhite = new JLabel(formatter.format(whiteTime));
        jlTimeWhite.setFont(jlTimeWhite.getFont().deriveFont(48f));;

        WhiteTimerPanel.add(whiteTimeLabel, BorderLayout.LINE_START);
        WhiteTimerPanel.add(jlTimeWhite, BorderLayout.CENTER);
        BlackTimerPanel.add(blackTimeLabel,BorderLayout.LINE_START);
        BlackTimerPanel.add(jlTimeBlack,BorderLayout.CENTER);

        TimerPanel.add(BlackTimerPanel,BorderLayout.NORTH);
        TimerPanel.add(WhiteTimerPanel,BorderLayout.SOUTH);
        this.add(TimerPanel, BorderLayout.CENTER);
    }

    private void InitializedTime(){

        if(preferences.getTimerMode() ==  Preferences.TimerMode.COUNTDOWN){
            whiteTime.setTime(preferences.getTimeLimit()* 1000*60);
            blackTime.setTime(preferences.getTimeLimit()* 1000*60);
        }
        else{
            whiteTime.setTime(0);
            blackTime.setTime(0);
        }
    }

    public void Start(){

    }
    public void Stop(){

    }
    public void UpdateTimeWhite(){
        if (preferences.getTimerMode() == Preferences.TimerMode.COUNTDOWN)  whiteTime.setTime(whiteTime.getTime() - 1000);
        else whiteTime.setTime(whiteTime.getTime()+1000);
        SimpleDateFormat formatter = new SimpleDateFormat("mm:ss");

        this.jlTimeWhite.setText(formatter.format(whiteTime));
    }
    public void UpdateTimeBlack(){
        if (preferences.getTimerMode() == Preferences.TimerMode.COUNTDOWN) blackTime.setTime(blackTime.getTime() - 1000);
        else blackTime.setTime(blackTime.getTime()+1000);
        SimpleDateFormat formatter = new SimpleDateFormat("mm:ss");
        this.jlTimeBlack.setText(formatter.format(blackTime));
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
