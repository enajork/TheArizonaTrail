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
  private static Media huntedMenu;
  private static Media huntedTheme;
  private static Media huntedCredits;
  private static Media crickets;
  private static Media moving;

  public Sounds() {
    huntedMenu = new Media(getClass().getResource("assets/sounds/music/hunted-menu.wav").toExternalForm());
    huntedTheme = new Media(getClass().getResource("assets/sounds/music/hunted-theme.wav").toExternalForm());
    huntedCredits = new Media(getClass().getResource("assets/sounds/music/hunted-special-credits.wav").toExternalForm());
    crickets = new Media(getClass().getResource("assets/sounds/fx/crickets.wav").toExternalForm());
    moving = new Media(getClass().getResource("assets/sounds/fx/wagon-moving.wav").toExternalForm());

    for (int i = 0; i < NUM_SONGS; i++) {
      String audioFile = "assets/sounds/music/" + (i + 1) + ".wav";
      final Media clip = new Media(getClass().getResource(audioFile).toExternalForm());
      themes.add(clip);
    }
    music.put("themes", themes);

    sounds.put("hunted", new AudioClip(getClass().getResource("assets/sounds/fx/hunted-mode.wav").toExternalForm()));
    sounds.put("musket", new AudioClip(getClass().getResource("assets/sounds/fx/musket-cleaned.wav").toExternalForm()));
    sounds.put("register", new AudioClip(getClass().getResource("assets/sounds/fx/cash-register.wav").toExternalForm()));
    sounds.put("oxen", new AudioClip(getClass().getResource("assets/sounds/fx/oxen.wav").toExternalForm()));
    sounds.put("breakdown", new AudioClip(getClass().getResource("assets/sounds/fx/wagon-breakdown.wav").toExternalForm()));
    sounds.put("sizzle", new AudioClip(getClass().getResource("assets/sounds/fx/sizzle.wav").toExternalForm()));
    sounds.put("hunt-bg", new AudioClip(getClass().getResource("assets/sounds/fx/hunting-background.wav").toExternalForm()));
    sounds.put("ow-xl", new AudioClip(getClass().getResource("assets/sounds/fx/ow-xl.wav").toExternalForm()));
    sounds.put("ow1", new AudioClip(getClass().getResource("assets/sounds/fx/ow1.wav").toExternalForm()));
    sounds.put("ow2", new AudioClip(getClass().getResource("assets/sounds/fx/ow2.wav").toExternalForm()));
    sounds.put("wow1", new AudioClip(getClass().getResource("assets/sounds/fx/wow1.wav").toExternalForm()));
    sounds.put("wow2", new AudioClip(getClass().getResource("assets/sounds/fx/wow2.wav").toExternalForm()));
    sounds.put("wow3", new AudioClip(getClass().getResource("assets/sounds/fx/wow3.wav").toExternalForm()));
  }

  public static void startThemeLoop() {
    ObservableList<Media> themes = music.get("themes");
    Media theme = themes.remove(0);
    player = new MediaPlayer(theme);
    player.play();
    player.setVolume(MAX_MUSIC_VOLUME);
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
    player.setVolume(MAX_MUSIC_VOLUME);
    if (!AZTrailController.sound) {
      player.setVolume(0);
    }
    player.play();
    player.setOnEndOfMedia(new Runnable() {
      @Override
      public void run() {
        huntedMenuTheme();
      }
    });
  }

  public static void huntedModeSFX() {
    if (AZTrailController.sound) {
      sounds.get("hunted").play(MAX_EFFECT_VOLUME);
    }
  }

  public static void cashOutSFX() {
    if (AZTrailController.sound) {
      sounds.get("register").play(MAX_EFFECT_VOLUME);
    }
  }

  public static void cricketsSFX() {
    player = new MediaPlayer(crickets);
    player.setVolume(MAX_EFFECT_VOLUME);
    if (!AZTrailController.sound) {
      player.setVolume(0);
    }
    player.play();
    player.setOnEndOfMedia(new Runnable() {
      @Override
      public void run() {
        cricketsSFX();
      }
    });
  }

  public static void musketSFX() {
    if (AZTrailController.sound) {
      sounds.get("musket").play(MAX_EFFECT_VOLUME + 0.05);
    }
  }

  public static void oxenSFX() {
    if (AZTrailController.sound) {
      sounds.get("register").play(MAX_EFFECT_VOLUME - 0.05);
    }
  }

  public static void movingSFX() {
    player = new MediaPlayer(moving);
    player.setVolume(MAX_EFFECT_VOLUME - 0.1);
    if (!AZTrailController.sound) {
      player.setVolume(0);
    }
    player.play();
    player.setOnEndOfMedia(new Runnable() {
      @Override
      public void run() {
        movingSFX();
      }
    });
  }

  public static void breakdownSFX() {
    if (AZTrailController.sound) {
      sounds.get("breakdown").play(MAX_EFFECT_VOLUME);
    }
  }

  public static void sizzleSFX() {
    if (AZTrailController.sound) {
      sounds.get("sizzle").play(MAX_EFFECT_VOLUME - 0.1);
    }
  }

  public static void huntBackgroundSFX() {
    if (AZTrailController.sound) {
      sounds.get("hunt-bg").play(MAX_EFFECT_VOLUME - 0.08);
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
