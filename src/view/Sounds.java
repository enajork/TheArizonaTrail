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
  // private static volatile Boolean isPlayingThemes;
  // private static volatile Boolean isPlayingMoving;
  private static final int NUM_SONGS = 5;
  private static final double MAX_EFFECT_VOLUME = 0.4;
  private static final double MAX_MUSIC_VOLUME = 0.2;
  private static MediaPlayer musicPlayer;
  private static MediaPlayer sfxPlayer1;
  private static MediaPlayer sfxPlayer2;
  private static Media huntedMenu;
  private static Media huntedTheme;
  private static Media huntedFinal;
  private static Media huntedCredits;
  private static Media homeStretch;
  private static Media huntingSong;
  private static boolean isPlayingBackgroundSFX;
  private static Media crickets;
  private static Media coyote;
  private static Media chirp;
  private static Media eagle;
  // private static Media moving;

  public Sounds() {
    huntedMenu = new Media(getClass().getResource("assets/sounds/music/hunted-menu.wav").toExternalForm());
    huntedTheme = new Media(getClass().getResource("assets/sounds/music/hunted-theme.wav").toExternalForm());
    huntedFinal = new Media(getClass().getResource("assets/sounds/music/hunted-final-battle.wav").toExternalForm());
    huntedCredits = new Media(getClass().getResource("assets/sounds/music/hunted-special-credits.wav").toExternalForm());
    homeStretch = new Media(getClass().getResource("assets/sounds/music/homestretch-theme.wav").toExternalForm());
    huntingSong = new Media(getClass().getResource("assets/sounds/music/hunting-music.wav").toExternalForm());
    crickets = new Media(getClass().getResource("assets/sounds/fx/crickets.wav").toExternalForm());
    coyote = new Media(getClass().getResource("assets/sounds/fx/hunting-background.wav").toExternalForm());
    chirp = new Media(getClass().getResource("assets/sounds/fx/small-bird.wav").toExternalForm());
    eagle = new Media(getClass().getResource("assets/sounds/fx/big-bird.wav").toExternalForm());
    // moving = new Media(getClass().getResource("assets/sounds/fx/wagon-moving.wav").toExternalForm());

    for (int i = 0; i < NUM_SONGS; i++) {
      String audioFile = "assets/sounds/music/" + (i + 1) + ".wav";
      final Media clip = new Media(getClass().getResource(audioFile).toExternalForm());
      themes.add(clip);
    }
    music.put("themes", themes);
    sounds.put("moving", new AudioClip(getClass().getResource("assets/sounds/fx/wagon-moving.wav").toExternalForm()));
    sounds.put("hunted", new AudioClip(getClass().getResource("assets/sounds/fx/hunted-mode.wav").toExternalForm()));
    sounds.put("musket", new AudioClip(getClass().getResource("assets/sounds/fx/musket-cleaned.wav").toExternalForm()));
    sounds.put("register", new AudioClip(getClass().getResource("assets/sounds/fx/cash-register.wav").toExternalForm()));
    sounds.put("oxen", new AudioClip(getClass().getResource("assets/sounds/fx/oxen.wav").toExternalForm()));
    sounds.put("breakdown", new AudioClip(getClass().getResource("assets/sounds/fx/wagon-breakdown.wav").toExternalForm()));
    sounds.put("sizzle", new AudioClip(getClass().getResource("assets/sounds/fx/sizzle.wav").toExternalForm()));
    // sounds.put("hunt-bg", new AudioClip(getClass().getResource("assets/sounds/fx/hunting-background.wav").toExternalForm()));
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
    musicPlayer = new MediaPlayer(theme);
    musicPlayer.play();
    musicPlayer.setVolume(MAX_MUSIC_VOLUME);
    if (!AZTrailController.sound) {
      musicPlayer.setVolume(0);
    }
    themes.add(theme);
    musicPlayer.setOnEndOfMedia(new Runnable() {
      @Override
      public void run() {
        startThemeLoop();
      }
    });
  }

  public static void huntedMenuTheme() {
    musicPlayer = new MediaPlayer(huntedMenu);
    musicPlayer.setVolume(MAX_MUSIC_VOLUME);
    if (!AZTrailController.sound) {
      musicPlayer.setVolume(0);
    }
    musicPlayer.play();
    musicPlayer.setOnEndOfMedia(new Runnable() {
      @Override
      public void run() {
        huntedMenuTheme();
      }
    });
  }

  public static void huntedInitialTheme() {
    musicPlayer = new MediaPlayer(huntedTheme);
    musicPlayer.setVolume(MAX_MUSIC_VOLUME);
    if (!AZTrailController.sound) {
      musicPlayer.setVolume(0);
    }
    musicPlayer.play();
    musicPlayer.setOnEndOfMedia(new Runnable() {
      @Override
      public void run() {
        huntedInitialTheme();
      }
    });
  }

  public static void huntedFinalTheme() {
    musicPlayer = new MediaPlayer(huntedFinal);
    musicPlayer.setVolume(MAX_MUSIC_VOLUME);
    if (!AZTrailController.sound) {
      musicPlayer.setVolume(0);
    }
    musicPlayer.play();
    musicPlayer.setOnEndOfMedia(new Runnable() {
      @Override
      public void run() {
        huntedFinalTheme();
      }
    });
  }

  public static void huntedCreditsTheme() {
    musicPlayer = new MediaPlayer(huntedCredits);
    musicPlayer.setVolume(MAX_MUSIC_VOLUME);
    if (!AZTrailController.sound) {
      musicPlayer.setVolume(0);
    }
    musicPlayer.play();
    musicPlayer.setOnEndOfMedia(new Runnable() {
      @Override
      public void run() {
        huntedCreditsTheme();
      }
    });
  }

  public static void homeStretchTheme() {
    musicPlayer = new MediaPlayer(homeStretch);
    musicPlayer.setVolume(MAX_MUSIC_VOLUME);
    if (!AZTrailController.sound) {
      musicPlayer.setVolume(0);
    }
    musicPlayer.play();
    musicPlayer.setOnEndOfMedia(new Runnable() {
      @Override
      public void run() {
        homeStretchTheme();
      }
    });
  }

  public static void huntingSong() {
    musicPlayer = new MediaPlayer(huntingSong);
    musicPlayer.setVolume(MAX_MUSIC_VOLUME);
    if (!AZTrailController.sound) {
      musicPlayer.setVolume(0);
    }
    musicPlayer.play();
    musicPlayer.setOnEndOfMedia(new Runnable() {
      @Override
      public void run() {
        huntingSong();
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
    sfxPlayer1 = new MediaPlayer(crickets);
    sfxPlayer1.setVolume(MAX_EFFECT_VOLUME);
    if (!AZTrailController.sound) {
      sfxPlayer1.setVolume(0);
    }
    sfxPlayer1.play();
    sfxPlayer1.setOnEndOfMedia(new Runnable() {
      @Override
      public void run() {
        cricketsSFX();
      }
    });
  }

  public static void coyotesSFX() {
    sfxPlayer2 = new MediaPlayer(coyote);
    sfxPlayer2.setVolume(MAX_EFFECT_VOLUME);
    if (!AZTrailController.sound) {
      sfxPlayer2.setVolume(0);
    }
    sfxPlayer2.play();
    sfxPlayer2.setOnEndOfMedia(new Runnable() {
      @Override
      public void run() {
        coyotesSFX();
      }
    });
  }



  public static void chirpSFX() {
    sfxPlayer1 = new MediaPlayer(chirp);
    sfxPlayer1.setVolume(MAX_EFFECT_VOLUME + 0.1);
    if (!AZTrailController.sound) {
      sfxPlayer1.setVolume(0);
    }
    sfxPlayer1.play();
    sfxPlayer1.setOnEndOfMedia(new Runnable() {
      @Override
      public void run() {
        chirpSFX();
      }
    });
  }

  public static void eagleSFX() {
    sfxPlayer2 = new MediaPlayer(eagle);
    sfxPlayer2.setVolume(MAX_EFFECT_VOLUME);
    if (!AZTrailController.sound) {
      sfxPlayer2.setVolume(0);
    }
    sfxPlayer2.play();
    sfxPlayer2.setOnEndOfMedia(new Runnable() {
      @Override
      public void run() {
        eagleSFX();
      }
    });
  }

  public static void nighttimeSFX() {
    if (!isPlayingBackgroundSFX) {
      isPlayingBackgroundSFX = true;
      cricketsSFX();
      coyotesSFX();
    }
  }

  public static void daytimeSFX() {
    if (!isPlayingBackgroundSFX) {
      isPlayingBackgroundSFX = true;
      chirpSFX();
      eagleSFX();
    }
  }


  public static void musketSFX() {
    if (AZTrailController.sound) {
      sounds.get("musket").play(MAX_EFFECT_VOLUME);
    }
  }

  public static void oxenSFX() {
    if (AZTrailController.sound) {
      sounds.get("register").play(MAX_EFFECT_VOLUME);
    }
  }

  public static void movingSFX() {
    if (AZTrailController.sound && !sounds.get("moving").isPlaying()) {
      sounds.get("moving").play(MAX_EFFECT_VOLUME - 0.1);
    }
    // sfxPlayer1 = new MediaPlayer(moving);
    // sfxPlayer1.setVolume(MAX_EFFECT_VOLUME);
    // if (!AZTrailController.sound) {
    //   sfxPlayer1.setVolume(0);
    // }
    // if () {
    //
    // }
    // if (isPlayingMoving) {
    //   return;
    // }
    // sfxPlayer1.play();
    // sfxPlayer1.setOnEndOfMedia(new Runnable() {
    //   @Override
    //   public void run() {
    //     movingSFX();
    //   }
    // });
  }

  public static void stopMovingSFX() {
    if (sounds.get("moving").isPlaying()) {
      sounds.get("moving").stop();
    }
  }

  public static void breakdownSFX() {
    if (AZTrailController.sound) {
      sounds.get("breakdown").play(MAX_EFFECT_VOLUME);
    }
  }

  public static void sizzleSFX() {
    if (AZTrailController.sound) {
      sounds.get("sizzle").play(MAX_EFFECT_VOLUME);
    }
  }

  public static void ow1SFX() {
    if (AZTrailController.sound) {
      sounds.get("ow1").play(MAX_EFFECT_VOLUME);
    }
  }

  public static void ow2SFX() {
    if (AZTrailController.sound) {
      sounds.get("ow2").play(MAX_EFFECT_VOLUME);
    }
  }

  public static void owXLSFX() {
    if (AZTrailController.sound) {
      sounds.get("ow-xl").play(MAX_EFFECT_VOLUME);
    }
  }

  public static void wow1SFX() {
    if (AZTrailController.sound) {
      sounds.get("wow1").play(MAX_EFFECT_VOLUME);
    }
  }

  public static void wow2SFX() {
    if (AZTrailController.sound) {
      sounds.get("wow2").play(MAX_EFFECT_VOLUME);
    }
  }

  public static void wow3SFX() {
    if (AZTrailController.sound) {
      sounds.get("wow3").play(MAX_EFFECT_VOLUME);
    }
  }

  public static void mute() {
    if (AZTrailController.sound) {
      AZTrailController.sound = false;
      if (musicPlayer != null) {
        musicPlayer.setVolume(0);
      }
      if (sfxPlayer1 != null) {
        sfxPlayer1.setVolume(0);
      }
      if (sfxPlayer2 != null) {
        sfxPlayer2.setVolume(0);
      }
      sounds.entrySet().stream().forEach(entry -> {
        entry.getValue().setVolume(0);
      });
    } else {
      AZTrailController.sound = true;
      if (musicPlayer != null) {
        musicPlayer.setVolume(MAX_MUSIC_VOLUME);
      }
      if (sfxPlayer1 != null) {
        sfxPlayer1.setVolume(MAX_EFFECT_VOLUME);
      }
      if (sfxPlayer2 != null) {
        sfxPlayer2.setVolume(MAX_EFFECT_VOLUME);
      }
      sounds.entrySet().stream().forEach(entry -> {
        entry.getValue().setVolume(MAX_EFFECT_VOLUME);
      });
    }
  }

  public static void stop() {
    if (musicPlayer != null) {
      musicPlayer.stop();
    }
    if (sfxPlayer1 != null) {
      sfxPlayer1.stop();
    }
    if (sfxPlayer2 != null) {
      sfxPlayer2.stop();
    }
  }
}
