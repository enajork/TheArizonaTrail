package view;

import javafx.scene.media.*;
import javafx.collections.*;
import java.util.*;
import java.io.*;
import controller.*;

public class Sounds {
  private static final ObservableList<Media> themes = FXCollections.observableArrayList();
  private static final HashMap<String, ObservableList<Media>> music = new HashMap<String, ObservableList<Media>>();
  private static final HashMap<String, AudioClip> sounds = new HashMap<String, AudioClip>();
  private static volatile Boolean isPlayingThemes;
  private static final int NUM_SONGS = 5;
  private static final double MAX_EFFECT_VOLUME = 0.15;
  private static final double MAX_MUSIC_VOLUME = 0.3;
  private static MediaPlayer player;
  private static final Media huntedMenu;
  private static final Media huntedTheme;
  private static final Media huntedCredits;

  public Sounds() {
    huntedMenu = new Media(getClass().getResource("assets/sounds/music/hunted-menu.wav").toExternalForm());
    huntedTheme = new Media(getClass().getResource("assets/sounds/music/hunted-theme.wav").toExternalForm());
    huntedCredits = new Media(getClass().getResource("assets/sounds/music/hunted-special-credits.wav").toExternalForm());

    for (int i = 0; i < NUM_SONGS; i++) {
      String audioFile = "assets/sounds/music/" + (i + 1) + ".wav";
      final Media clip = new Media(getClass().getResource(audioFile).toExternalForm());
      themes.add(clip);
    }
    music.put("themes", themes);
    // final ObservableList<Media> hunted = FXCollections.observableArrayList();
    // final Media huntedMenu = new Media(getClass().getResource("assets/sounds/music/hunted-menu.wav").toExternalForm());
    // final Media huntedTheme = new Media(getClass().getResource("assets/sounds/music/hunted-theme.wav").toExternalForm());
    // final Media huntedCredits = new Media(getClass().getResource("assets/sounds/music/hunted-special-credits.wav").toExternalForm());
    // hunted.addAll(huntedMenu, huntedTheme, huntedCredits);

    // music.put("hunted", hunted);

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

    // if (AZTrailController.huntedMode) {
    sounds.put("ow-xl", new AudioClip(getClass().getResource("assets/sounds/fx/ow-xl.wav").toExternalForm()));
    sounds.put("ow1", new AudioClip(getClass().getResource("assets/sounds/fx/ow1.wav").toExternalForm()));
    sounds.put("ow2", new AudioClip(getClass().getResource("assets/sounds/fx/ow2.wav").toExternalForm()));
    sounds.put("wow1", new AudioClip(getClass().getResource("assets/sounds/fx/wow1.wav").toExternalForm()));
    sounds.put("wow2", new AudioClip(getClass().getResource("assets/sounds/fx/wow2.wav").toExternalForm()));
    sounds.put("wow3", new AudioClip(getClass().getResource("assets/sounds/fx/wow3.wav").toExternalForm()));
    // }
  }

  public static void startThemeLoop() {
    ObservableList<Media> themes = music.get("themes");
    Media theme = themes.remove(0);
    player = new MediaPlayer(theme);
    player.play();
    player.setVolume(0.4);
    if (!AZTrailController.sound) {
      player.setVolume(0);
    }
    themes.add(theme);
    player.setOnEndOfMedia(new Runnable() {
      @Override
      public void run() {
        startThemeLoop();
      }
    });
  }

  public static void huntedMenuTheme() {
    player = new MediaPlayer(huntedMenu);
    player.play();
  }

  public static void cashOutSFX() {
    if (AZTrailController.sound) {
      sounds.get("register").play(0.2);
    }
  }

  public static void mute() {
    if (AZTrailController.sound) {
      AZTrailController.sound = false;
      if (player != null) {
        player.setVolume(0);
      }
      sounds.entrySet().stream().forEach(entry -> {
        entry.getValue().setVolume(0);
      });
    } else {
      AZTrailController.sound = true;
      if (player != null) {
        player.setVolume(MAX_MUSIC_VOLUME);
      }
      sounds.entrySet().stream().forEach(entry -> {
        entry.getValue().setVolume(MAX_EFFECT_VOLUME);
      });
    }
  }

  public static void stop() {
    if (player != null) {
      player.stop();
    }
  }
}
