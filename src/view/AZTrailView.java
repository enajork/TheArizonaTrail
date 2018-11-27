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
  protected static final HashMap<String, AudioClip[]> music = new HashMap<String, AudioClip[]>();
  protected static boolean escape = false;
  protected static AZTrailController controller;
  protected static AZTrailModel model;
  protected static Stage stage;
  protected static final int HEIGHT = 408;
  protected static final int WIDTH = 650;
  protected static String styleSheet;

  /**
   * Main entry point for the view
   * @param  stage     [description]
   * @throws Exception [description]
   */
  @Override
  public void start(Stage stage) throws Exception {
    // btn.setOnAction(e -> {
    //   Media m = new Media(Paths.get("04.mp3").toUri().toString());
    //   new MediaPlayer(m).play();
    // });

    final AudioClip themes[] = new AudioClip[5];
    for (int i = 0; i < themes.length; i++) {
      String audioFile = "assets/sounds/music/" + (i + 1) + ".wav";
      final AudioClip clip = new AudioClip(getClass().getResource(audioFile).toString());
      themes[i] = clip;
    }
    music.put("themes", themes);
    final AudioClip hunted[] = new AudioClip[2];
    final AudioClip huntedTheme = new AudioClip(getClass().getResource("assets/sounds/music/hunted-theme.wav").toString());
    final AudioClip huntedCredits = new AudioClip(getClass().getResource("assets/sounds/music/hunted-special-credits.wav").toString());
    // hunted[0] = huntedIntro;
    hunted[0] = huntedTheme;
    hunted[1] = huntedCredits;
    music.put("hunted", hunted);

    boolean huntedMode = true;

    sounds.put("hunted", new AudioClip(getClass().getResource("assets/sounds/fx/hunted-mode.wav").toString()));
    sounds.put("crickets", new AudioClip(getClass().getResource("assets/sounds/fx/crickets.wav").toString()));
    sounds.put("musket", new AudioClip(getClass().getResource("assets/sounds/fx/musket-cleaned.wav").toString()));
    sounds.put("register", new AudioClip(getClass().getResource("assets/sounds/fx/cash-register.wav").toString()));
    sounds.put("oxen", new AudioClip(getClass().getResource("assets/sounds/fx/oxen.wav").toString()));
    sounds.put("moving", new AudioClip(getClass().getResource("assets/sounds/fx/wagon-moving.wav").toString()));
    sounds.put("breakdown", new AudioClip(getClass().getResource("assets/sounds/fx/wagon-breakdown.wav").toString()));
    sounds.put("sick1", new AudioClip(getClass().getResource("assets/sounds/fx/sick1.wav").toString()));
    sounds.put("sick2", new AudioClip(getClass().getResource("assets/sounds/fx/sick2.wav").toString()));
    sounds.put("fracture", new AudioClip(getClass().getResource("assets/sounds/fx/fracture.wav").toString()));
    sounds.put("sizzle", new AudioClip(getClass().getResource("assets/sounds/fx/sizzle.wav").toString()));
    sounds.put("hunt-bg", new AudioClip(getClass().getResource("assets/sounds/fx/hunting-background.wav").toString()));

    if (huntedMode) {
      sounds.put("ow-xl", new AudioClip(getClass().getResource("assets/sounds/fx/ow-xl.wav").toString()));
      sounds.put("ow1", new AudioClip(getClass().getResource("assets/sounds/fx/ow1.wav").toString()));
      sounds.put("ow2", new AudioClip(getClass().getResource("assets/sounds/fx/ow2.wav").toString()));
      sounds.put("wow1", new AudioClip(getClass().getResource("assets/sounds/fx/wow1.wav").toString()));
      sounds.put("wow2", new AudioClip(getClass().getResource("assets/sounds/fx/wow2.wav").toString()));
      sounds.put("wow3", new AudioClip(getClass().getResource("assets/sounds/fx/wow3.wav").toString()));
    }

    // themes[0].play(1.0);
    // sounds.get("ow-xl").play(1.0);
    // music.get("themes")[0].play(1.0);
    // music.get("themes")[4].play(1.0);
    // sounds.get("crickets").play(1.0);
    // sounds.get("hunt-bg").play(1.0);
    // music.get("hunted")[0].play(1.0);
    // music.get("hunted")[1].play(1.0);
    // sounds.get("hunted").play(1.0);
    // huntedIntro.play(1.0);
    // huntedCredits.play(1.0);

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
    music.get("themes")[0].play(1.0);

    // if (sound) {
    //   int i = 0;
    //   while (true) {
    //     AudioClip theme = music.get("themes")[i];
    //     theme.play(1.0);
    //     volatile boolean isPlaying = theme.isPlaying();
    //     while (isPlaying) {
    //       check.
    //     }
    //     if (!isPlaying) {
    //       if (i == 4) {
    //         i = 0;
    //       } else {
    //         i++;
    //       }
    //     }
    //   }
    // }
  }
}
