package view;

import javafx.scene.control.Alert.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.media.*;
import javafx.scene.input.*;
import javafx.scene.text.*;
import javafx.application.*;
import javafx.scene.input.*;
import javafx.scene.image.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.collections.*;
import javafx.scene.*;
import javafx.beans.value.*;
import javafx.geometry.*;
import javafx.stage.*;
import javafx.event.*;
import java.util.*;
import java.io.*;

import controller.*;
import model.*;

public class AZTrailView extends Application {
  protected static boolean sound = true;
  protected static final HashMap<String, AudioClip> sounds = new HashMap<String, AudioClip>();
  protected static final HashMap<String, ObservableList<Media>> music = new HashMap<String, ObservableList<Media>>();
  protected static boolean escape = false;
  protected static AZTrailController controller;
  protected static AZTrailModel model;
  protected static Stage stage;
  protected static final int HEIGHT = 408;
  protected static final int WIDTH = 650;
  protected static String styleSheet;
  protected static MediaPlayer player;
  private static volatile Boolean isPlayingThemes;

  /**
   * Main entry point for the view
   * @param  stage     [description]
   * @throws Exception [description]
   */
  @Override
  public void start(Stage stage) throws Exception {
    final ObservableList<Media> themes = FXCollections.observableArrayList();
    for (int i = 0; i < 5; i++) {
      String audioFile = "assets/sounds/music/" + (i + 1) + ".wav";
      final Media clip = new Media(getClass().getResource(audioFile).toExternalForm());
      themes.add(clip);
    }
    music.put("themes", themes);
    final ObservableList<Media> hunted = FXCollections.observableArrayList();
    final Media huntedTheme = new Media(getClass().getResource("assets/sounds/music/hunted-theme.wav").toExternalForm());
    final Media huntedCredits = new Media(getClass().getResource("assets/sounds/music/hunted-special-credits.wav").toExternalForm());
    hunted.addAll(huntedTheme, huntedCredits);

    music.put("hunted", hunted);

    boolean huntedMode = true;

    sounds.put("hunted", new AudioClip(getClass().getResource("assets/sounds/fx/hunted-mode.wav").toExternalForm()));
    sounds.put("crickets", new AudioClip(getClass().getResource("assets/sounds/fx/crickets.wav").toExternalForm()));
    sounds.put("musket", new AudioClip(getClass().getResource("assets/sounds/fx/musket-cleaned.wav").toExternalForm()));
    sounds.put("register", new AudioClip(getClass().getResource("assets/sounds/fx/cash-register.wav").toExternalForm()));
    sounds.put("oxen", new AudioClip(getClass().getResource("assets/sounds/fx/oxen.wav").toExternalForm()));
    sounds.put("moving", new AudioClip(getClass().getResource("assets/sounds/fx/wagon-moving.wav").toExternalForm()));
    sounds.put("breakdown", new AudioClip(getClass().getResource("assets/sounds/fx/wagon-breakdown.wav").toExternalForm()));
    sounds.put("sick1", new AudioClip(getClass().getResource("assets/sounds/fx/sick1.wav").toExternalForm()));
    sounds.put("sick2", new AudioClip(getClass().getResource("assets/sounds/fx/sick2.wav").toExternalForm()));
    sounds.put("fracture", new AudioClip(getClass().getResource("assets/sounds/fx/fracture.wav").toExternalForm()));
    sounds.put("sizzle", new AudioClip(getClass().getResource("assets/sounds/fx/sizzle.wav").toExternalForm()));
    sounds.put("hunt-bg", new AudioClip(getClass().getResource("assets/sounds/fx/hunting-background.wav").toExternalForm()));

    if (huntedMode) {
      sounds.put("ow-xl", new AudioClip(getClass().getResource("assets/sounds/fx/ow-xl.wav").toExternalForm()));
      sounds.put("ow1", new AudioClip(getClass().getResource("assets/sounds/fx/ow1.wav").toExternalForm()));
      sounds.put("ow2", new AudioClip(getClass().getResource("assets/sounds/fx/ow2.wav").toExternalForm()));
      sounds.put("wow1", new AudioClip(getClass().getResource("assets/sounds/fx/wow1.wav").toExternalForm()));
      sounds.put("wow2", new AudioClip(getClass().getResource("assets/sounds/fx/wow2.wav").toExternalForm()));
      sounds.put("wow3", new AudioClip(getClass().getResource("assets/sounds/fx/wow3.wav").toExternalForm()));
    }

    styleSheet = getClass().getResource("assets/font/style.css").toExternalForm();

    this.stage = stage;
    model = new AZTrailModel();
    controller = new AZTrailController(model);
    this.stage.setTitle("Arizona Trail");
    this.stage.setResizable(false);
    //stage.initStyle(StageStyle.UNDECORATED);
    this.stage.setScene(new SplashMenu());
    this.stage.show();
  }

  public static synchronized void startThemeLoop() {
    if (sound) {
      ObservableList<Media> themes = music.get("themes");
      Media theme = themes.remove(0);
      player = new MediaPlayer(theme);
      player.setVolume(0.4);
      player.play();
      themes.add(theme);
      player.setOnEndOfMedia(new Runnable() {
        @Override
        public void run() {
          startThemeLoop();
        }
      });
    }
  }

  public static void cashOutSFX() {
    sounds.get("register").play(0.2);
  }
}
