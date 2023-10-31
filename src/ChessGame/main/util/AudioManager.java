package ChessGame.main.util;

import java.io.File;
import java.nio.file.Paths;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import static com.sun.javafx.scene.control.skin.Utils.getResource;

public class AudioManager {
    Media CaptureAudio;
    Media MoveAudio;
    Media CheckAudio;
    Media Promote;
    Media Castle;
    public AudioManager(GameManager move){
        JFXPanel fxPanel = new JFXPanel();
        //JFXPanel fxPanel = new JFXPanel();
        CaptureAudio = new Media(Paths.get("resources/Sound/capture.mp3").toUri().toString());
        MoveAudio = new Media(Paths.get("resources/Sound/move-self.mp3").toUri().toString());
        CheckAudio = new Media(Paths.get("resources/Sound/move-check.mp3").toUri().toString());
        Promote = new Media(Paths.get("resources/Sound/promote.mp3").toUri().toString());
        Castle = new Media(Paths.get("resources/Sound/castle.mp3").toUri().toString());
    }

    public void PlayMovePiece(){
        //Media pick = new Media(Paths.get("resources/Sound/capture.mp3").toUri().toString()); // replace this with your own audio file
        MediaPlayer MoveAudioPlayer = new MediaPlayer(MoveAudio);

        // Play the media once the stage is shown
        MoveAudioPlayer.play();
    }
    public void PlayCapturePiece(){
        //Media pick = new Media(Paths.get("resources/Sound/capture.mp3").toUri().toString()); // replace this with your own audio file
        MediaPlayer CaptureAudioPlayer = new MediaPlayer(CaptureAudio);

        // Play the media once the stage is shown
        CaptureAudioPlayer.play();
    }

}
