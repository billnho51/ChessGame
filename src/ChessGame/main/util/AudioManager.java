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

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import static com.sun.javafx.scene.control.skin.Utils.getResource;

public class AudioManager {
    public AudioManager(GameManager move){
        JFXPanel fxPanel = new JFXPanel();
    }

    public void PlaySound(){
        Media pick = new Media(Paths.get("resources/Sound/capture.mp3").toUri().toString()); // replace this with your own audio file
        MediaPlayer player = new MediaPlayer(pick);

        // Play the media once the stage is shown
        player.play();
    }
}
