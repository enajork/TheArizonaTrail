package view;

import javafx.scene.control.Alert.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.input.*;
import javafx.application.*;
import javafx.scene.input.*;
import javafx.scene.image.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.scene.*;
import javafx.beans.value.*;
import javafx.animation.*;
import javafx.geometry.*;
import javafx.stage.*;
import javafx.event.*;
import java.util.*;
import java.io.*;
import javafx.util.Duration;

import controller.*;

public class HuntingView extends Scene {
  private Scene nextScene;
  private BorderPane root;
  private String city;

  /**
   * [HuntingView description]
   */
  public HuntingView() {
    this(new BorderPane());
    getStylesheets().add(AZTrailView.styleSheet);
  }

  /**
   * [HuntingView description]
   * @param root [description]
   */
  private HuntingView(BorderPane root) {
    super(root, AZTrailView.WIDTH, AZTrailView.HEIGHT, Color.BLACK);
    this.nextScene = new SizeUpView();
    this.root = root;
    root.setStyle("-fx-background-color: black;");
    // Text footer = new Text("Press SPACE BAR to continue");
    // footer.setId("text12");
    // footer.setFill(Color.WHITE);
    // root.setBottom(footer);
    // root.setAlignment(footer, Pos.CENTER);
    // root.setMargin(footer, new Insets(0, 0, 20, 0));

    addEventHandlers();
  }

  private void doneHunting() {
    AZTrailView.sounds.stopMusic();
    AZTrailView.sounds.startThemeLoop();
  }

  /**
   * [addEventHandlers description]
   */
  private void addEventHandlers() {
    this.setOnKeyPressed(new EventHandler<KeyEvent>() {
      @Override
      public void handle(KeyEvent event) {
        switch (event.getCode()) {
          case SPACE:
            AZTrailController.escape = false;
            AZTrailView.stage.setScene(nextScene);
            // VERY IMPORTANT! NEEDED TO STOP THEME AND START THE NEXT! DO NOT LEAVE THIS VIEW WITHOUT DOING THIS!!!
            doneHunting();
            break;

          case ENTER:
            AZTrailController.escape = false;
            AZTrailView.stage.setScene(nextScene);
            // VERY IMPORTANT! NEEDED TO STOP THEME AND START THE NEXT! DO NOT LEAVE THIS VIEW WITHOUT DOING THIS!!!
            doneHunting();
            break;

          case ESCAPE:
            AZTrailView.escapePressed(true);
            break;
          case S:
            AZTrailController.escape = false;
            if (event.isControlDown()) {
              AZTrailView.sounds.mute();
            }
            break;

          default:
            AZTrailController.escape = false;
            return;
        }
      }
    });
  }

  private class HuntingSprite {
    private final Image IMAGE = new Image((AZTrailView.controller.getHunted())
      ? "file:view/assets/graphics/hunter-spritesheet-hunted.png"
      : "file:view/assets/graphics/hunter-spritesheet.png",
      600, 600, true, false);
    private static final int COLUMNS  =   5;
    private static final int COUNT    =   5;
    private static final int OFFSET_X =   0;
    private static final int OFFSET_Y =   0;
    private static final int WIDTH    =  120;
    private static final int HEIGHT   =  46;
    private final Animation animation;
    private ImageView imageView;

    public HuntingSprite() {
      this.imageView = new ImageView(IMAGE);
      this.imageView.setViewport(new Rectangle2D(OFFSET_X, OFFSET_Y, WIDTH,
        HEIGHT));

      this.animation = new SpriteAnimation(
        this.imageView,
        Duration.millis(500),
        COUNT, COLUMNS,
        OFFSET_X, OFFSET_Y,
        WIDTH, HEIGHT
      );
      this.animation.setCycleCount(4);
    }

    public void play() {
      this.animation.play();
    }

    public void pause() {
      this.animation.pause();
    }

    public ImageView getSprite() {
      return this.imageView;
    }
  }
}
