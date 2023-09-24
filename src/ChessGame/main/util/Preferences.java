package ChessGame.main.util;

public class Preferences {
    public enum GameMode{
        OFFLINE, ONLINE
    }
    public enum TimerMode{
        TIMER,
        COUNTDOWN
    }

    public boolean ReverseBoard;
    private GameMode gameMode = null;
    private TimerMode timerMode = null;
    private int timeLimit;

    public boolean isPreferenceComplete(){
        if (gameMode == null){
            return false;
        }
        switch (gameMode) {
            case ONLINE:
                System.out.println("not implemented yet");
                return false;

            case OFFLINE:
                if (timerMode == null) return false;
                return true;

        }
        System.out.println("should not reach here");
        //should not reach this case
        return false;
    }


    //getter setter section
    public GameMode getGameMode() {
        return gameMode;
    }

    public void setGameMode(GameMode gameMode) {
        this.gameMode = gameMode;
    }

    public int getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(int timeLimit) {
        this.timeLimit = timeLimit;
    }

    public TimerMode getTimerMode() {
        return timerMode;
    }

    public void setTimerMode(TimerMode timerMode) {
        this.timerMode = timerMode;
    }

    public boolean isReverseBoard() {
        return ReverseBoard;
    }

    public void setReverseBoard(boolean reverseBoard) {
        ReverseBoard = reverseBoard;
    }

}
