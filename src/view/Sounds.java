package view;

import javafx.scene.media.*;
import javafx.collections.*;
import javafx.util.*;
import java.util.*;
import java.io.*;
import controller.*;
/**
 * @author Jordan Bridgewater, Jared Grady, Eric Najork, David Najork
 * @version     1.0
 * @since       1.0
 */

public class Sounds {
  private static final ObservableList<Media> themes = FXCollections.observableArrayList();
  private static final HashMap<String, ObservableList<Media>> music = new HashMap<String, ObservableList<Media>>();
  private static final HashMap<String, AudioClip> sounds = new HashMap<String, AudioClip>();
  private static boolean isPlayingTheme;
  private static boolean isPlayingHomeStretchTheme;
  private static boolean isPlayingBackgroundSFX;
  private static final double MAX_EFFECT_VOLUME = 0.4;
  private static final double MAX_MUSIC_VOLUME = 0.2;
  private static final int NUM_SONGS = 5;
  private static MediaPlayer musicPlayer;
  private static MediaPlayer sfxPlayer1;
  private static MediaPlayer sfxPlayer2;
  private static Media gameOver;
  private static Media gameOverHunted;
  private static Media huntedMenu;
  private static Media huntedTheme;
  private static Media huntedFinal;
  private static Media huntedCredits;
  private static Media homeStretch;
  private static Media huntingSong;
  private static Media crickets;
  private static Media coyote;
  private static Media chirp;
  private static Media eagle;

  /**
   * loads all of the games sounds and stores them into the data structure for usage
   */
  public Sounds() {
    gameOver = new Media(getClass().getResource("assets/sounds/music/gameover.wav").toExternalForm());
    gameOverHunted = new Media(getClass().getResource("assets/sounds/music/gameover-hunted.wav").toExternalForm());
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
    sounds.put("ow-xl", new AudioClip(getClass().getResource("assets/sounds/fx/ow-xl.wav").toExternalForm()));
    sounds.put("ow1", new AudioClip(getClass().getResource("assets/sounds/fx/ow1.wav").toExternalForm()));
    sounds.put("ow2", new AudioClip(getClass().getResource("assets/sounds/fx/ow2.wav").toExternalForm()));
    sounds.put("wow1", new AudioClip(getClass().getResource("assets/sounds/fx/wow1.wav").toExternalForm()));
    sounds.put("wow2", new AudioClip(getClass().getResource("assets/sounds/fx/wow2.wav").toExternalForm()));
    sounds.put("wow3", new AudioClip(getClass().getResource("assets/sounds/fx/wow3.wav").toExternalForm()));
  }

  /**
   * loads the main theme song and plays it
   */
  public static void startThemeLoop() {
    if (!AZTrailView.controller.getNextCity().equals("Page")) {
      AZTrailView.sounds.mainThemes();
    } else {
      if (isPlayingHomeStretchTheme) {
        return;
      }
      isPlayingHomeStretchTheme = true;
      AZTrailView.sounds.stopMusic();
      AZTrailView.sounds.homeStretchTheme();
    }
  }

  /**
   * returns an ObservableList of all the main theme songs
   */
  public static void mainThemes() {
    if (!isPlayingTheme) {
      isPlayingTheme = true;
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
          isPlayingTheme = false;
          mainThemes();
        }
      });
    }
  }

  /**
   * plays the hunted menu theme song
   */
  public static void huntedMenuTheme() {
    if (!isPlayingTheme) {
      isPlayingTheme = true;
      musicPlayer = new MediaPlayer(huntedMenu);
      musicPlayer.setVolume(MAX_MUSIC_VOLUME);
      if (!AZTrailController.sound) {
        musicPlayer.setVolume(0);
      }
      musicPlayer.play();
      musicPlayer.setOnEndOfMedia(new Runnable() {
        @Override
        public void run() {
          isPlayingTheme = false;
          huntedMenuTheme();
        }
      });
    }
  }

  /**
   * plays the game over music
   */
  public static void gameOverTheme() {
    stop();
    if (!isPlayingTheme) {
      isPlayingTheme = true;
      musicPlayer = new MediaPlayer((AZTrailView.controller.getHunted()) ?
          gameOverHunted : gameOver);
      musicPlayer.setVolume(MAX_MUSIC_VOLUME);
      if (!AZTrailController.sound) {
        musicPlayer.setVolume(0);
      }
      musicPlayer.play();
      musicPlayer.setOnEndOfMedia(new Runnable() {
        @Override
        public void run() {
          isPlayingTheme = false;
          gameOverTheme();
        }
      });
    }
  }

  /**
   * plays the hunted initial theme
   */
  public static void huntedInitialTheme() {
    if (!isPlayingTheme) {
      isPlayingTheme = true;
      musicPlayer = new MediaPlayer(huntedTheme);
      musicPlayer.setVolume(MAX_MUSIC_VOLUME);
      if (!AZTrailController.sound) {
        musicPlayer.setVolume(0);
      }
      musicPlayer.play();
      musicPlayer.setOnEndOfMedia(new Runnable() {
        @Override
        public void run() {
          isPlayingTheme = false;
          huntedInitialTheme();
        }
      });
    }
  }

  /**
   * plays the final hunted theme
   */
  public static void huntedFinalTheme() {
    if (!isPlayingTheme) {
      isPlayingTheme = true;
      musicPlayer = new MediaPlayer(huntedFinal);
      musicPlayer.setVolume(MAX_MUSIC_VOLUME);
      if (!AZTrailController.sound) {
        musicPlayer.setVolume(0);
      }
      musicPlayer.setStartTime(Duration.millis(10000));
      musicPlayer.play();
      musicPlayer.setOnEndOfMedia(new Runnable() {
        @Override
        public void run() {
          isPlayingTheme = false;
          huntedFinalTheme();
        }
      });
    }
  }

  /**
   * plays the credit music
   */
  public static void creditsTheme() {
    stop();
    if (!isPlayingTheme) {
      isPlayingTheme = true;
      musicPlayer = new MediaPlayer((AZTrailView.controller.getHunted()) ?
          huntedCredits : gameOver);
      musicPlayer.setVolume(MAX_MUSIC_VOLUME);
      if (!AZTrailController.sound) {
        musicPlayer.setVolume(0);
      }
      musicPlayer.play();
      musicPlayer.setOnEndOfMedia(new Runnable() {
        @Override
        public void run() {
          isPlayingTheme = false;
          creditsTheme();
        }
      });
    }
  }

  /**
   * plays the music when on the home stretch
   */
  public static void homeStretchTheme() {
    if (!isPlayingTheme && !isPlayingHomeStretchTheme) {
      isPlayingHomeStretchTheme = true;
      isPlayingTheme = true;
      musicPlayer = new MediaPlayer(homeStretch);
      musicPlayer.setVolume(MAX_MUSIC_VOLUME);
      if (!AZTrailController.sound) {
        musicPlayer.setVolume(0);
      }
      musicPlayer.play();
      musicPlayer.setOnEndOfMedia(new Runnable() {
        @Override
        public void run() {
          isPlayingHomeStretchTheme = false;
          isPlayingTheme = false;
          homeStretchTheme();
        }
      });
    }
  }

  /**
   * plays the hunting minigame music
   */
  public static void huntingSong() {
    if (!isPlayingTheme) {
      isPlayingTheme = true;
      musicPlayer = new MediaPlayer(huntingSong);
      musicPlayer.setVolume(MAX_MUSIC_VOLUME);
      if (!AZTrailController.sound) {
        musicPlayer.setVolume(0);
      }
      musicPlayer.play();
      musicPlayer.setOnEndOfMedia(new Runnable() {
        @Override
        public void run() {
          isPlayingTheme = false;
          huntingSong();
        }
      });
    }
  }

  /**
   * runs the hunted mode sfx sounds
   */
  public static void huntedModeSFX() {
    if (AZTrailController.sound) {
      sounds.get("hunted").play(MAX_EFFECT_VOLUME);
    }
  }

  /**
   * plays the cash register sfx
   */
  public static void cashOutSFX() {
    if (AZTrailController.sound) {
      sounds.get("register").play(MAX_EFFECT_VOLUME);
    }
  }

  /**
   * plays the cricket sounds
   */
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

  /**
   * plays the coyote howl effect
   */
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

  /**
   * plays the chirps sfx
   */
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

  /**
   * plays the eagle sound fx
   */
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

  /**
   * plays the start background music
   */
  public static void startBackgroundSFX() {
    if (AZTrailView.controller.getHunted()) {
      AZTrailView.sounds.nighttimeSFX();
    } else {
      AZTrailView.sounds.daytimeSFX();
    }
  }

  /**
   * plays the nighttime sfx
   */
  public static void nighttimeSFX() {
    if (!isPlayingBackgroundSFX) {
      isPlayingBackgroundSFX = true;
      cricketsSFX();
      coyotesSFX();
    }
  }

  /**
   * plays the daytime sfx
   */
  public static void daytimeSFX() {
    if (!isPlayingBackgroundSFX) {
      isPlayingBackgroundSFX = true;
      chirpSFX();
      eagleSFX();
    }
  }

  /**
   * fires the musket special effect
   */
  public static void musketSFX() {
    if (AZTrailController.sound) {
      sounds.get("musket").play(MAX_EFFECT_VOLUME);
    }
  }

  /**
   * plays the oxen sfx
   */
  public static void oxenSFX() {
    if (AZTrailController.sound) {
      sounds.get("register").play(MAX_EFFECT_VOLUME);
    }
  }

  /**
   * plays the moving sound effect
   */
  public static void movingSFX() {
    if (AZTrailController.sound && !sounds.get("moving").isPlaying()) {
      sounds.get("moving").play(MAX_EFFECT_VOLUME - 0.1);
    }
  }

  /**
   * plays the stop moving sound effect
   */
  public static void stopMovingSFX() {
    if (sounds.get("moving").isPlaying()) {
      sounds.get("moving").stop();
    }
  }

  /**
   * plays the wagon breaking effect
   */
  public static void breakdownSFX() {
    if (AZTrailController.sound) {
      sounds.get("breakdown").play(MAX_EFFECT_VOLUME);
    }
  }

  /**
   * plays the sizzle sound effect
   */
  public static void sizzleSFX() {
    if (AZTrailController.sound) {
      sounds.get("sizzle").play(MAX_EFFECT_VOLUME);
    }
  }

  /**
   * plays the owl sound effect
   */
  public static void ow1SFX() {
    if (AZTrailController.sound) {
      sounds.get("ow1").play(MAX_EFFECT_VOLUME);
    }
  }

  /**
   * plays the second owl sfx
   */
  public static void ow2SFX() {
    if (AZTrailController.sound) {
      sounds.get("ow2").play(MAX_EFFECT_VOLUME);
    }
  }

  /**
   * plays the owXLSFX
   */
  public static void owXLSFX() {
    if (AZTrailController.sound) {
      sounds.get("ow-xl").play(MAX_EFFECT_VOLUME);
    }
  }

  /**
   * plays the owen wilson 1 wow
   */
  public static void wow1SFX() {
    if (AZTrailController.sound) {
      sounds.get("wow1").play(MAX_EFFECT_VOLUME);
    }
  }

  /**
   * plays the owen wilson 2 wow
   */
  public static void wow2SFX() {
    if (AZTrailController.sound) {
      sounds.get("wow2").play(MAX_EFFECT_VOLUME);
    }
  }

  /**
   * plays the owen wilson 3 wow
   */
  public static void wow3SFX() {
    if (AZTrailController.sound) {
      sounds.get("wow3").play(MAX_EFFECT_VOLUME);
    }
  }

  /**
   * mutes all sounds
   */
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

  /**
   * stops all music from both player threads
   */
  public static void stop() {
    stopMusic();
    if (sfxPlayer1 != null) {
      sfxPlayer1.stop();
      isPlayingBackgroundSFX = false;
    }
    if (sfxPlayer2 != null) {
      sfxPlayer2.stop();
      isPlayingBackgroundSFX = false;
    }
  }

  /**
   * stops a single music player
   */
  public static void stopMusic() {
    if (musicPlayer != null) {
      musicPlayer.stop();
      isPlayingTheme = false;
      isPlayingHomeStretchTheme = false;
    }
  }
}
