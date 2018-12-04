package view;

import javafx.scene.control.Alert.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
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
/**
 * @author Jordan Bridgewater, Jared Grady, Eric Najork, David Najork
 * @version     1.0
 * @since       1.0
 */

public class GameOver extends Scene {
  private BorderPane root;
  private boolean released = false;

  /**
   * No arg constructor for the game over screen
   */
  public GameOver() {
    this(new BorderPane());
    getStylesheets().add(AZTrailView.styleSheet);
  }

  /**
   * Constructs a new game over screen
   *
   * @param root   the root of the scene graph
   */
  private GameOver(BorderPane root) {
    super(root, AZTrailView.WIDTH, AZTrailView.HEIGHT, Color.BLACK);
    getStylesheets().add(AZTrailView.styleSheet);
    AZTrailView.sounds.stopMovingSFX();
    AZTrailView.sounds.startThemeLoop();
    this.root = root;
    root.setStyle("-fx-background-color: black;");

    Image img = new Image((AZTrailView.controller.getHunted())
      ? "file:view/assets/graphics/here-lies-hunted.png"
      : "file:view/assets/graphics/here-lies.png");
    ImageView splash = new ImageView(img);
    splash.setPreserveRatio(true);
    // splash.setFitWidth(AZTrailView.WIDTH * 0.985);
    root.setMargin(splash, new Insets(5, 5, 0, 5));
    root.setAlignment(splash, Pos.CENTER);
    root.setTop(splash);
    Text text = new Text("Game over!");
    text.setId("text12");
    text.setFill(Color.BLACK);
    StackPane stack = new StackPane();
    stack.getChildren().add(splash);
    stack.getChildren().add(text);
    root.setCenter(stack);
    root.setAlignment(stack, Pos.CENTER);
    root.setMargin(stack, new Insets(2));

    // Style the view
    Text footer = new Text("Press SPACE BAR to continue");
    footer.setId("text12");
    footer.setFill(Color.WHITE);
    root.setBottom(footer);
    root.setAlignment(footer, Pos.CENTER);
    root.setMargin(footer, new Insets(0, 0, 20, 0));

    addEventHandlers();
  }

  /**
   * Adds event handlers to the view to handle keyboard actions
   */
  private void addEventHandlers() {
    this.setOnKeyPressed(new EventHandler<KeyEvent>() {
      @Override
      public void handle(KeyEvent event) {
        switch (event.getCode()) {
          case SPACE:
            if (!released) {
              return;
            }
            AZTrailController.escape = false;
            AZTrailView.stage.setScene(new SplashMenu());
            break;

          case ENTER:
            AZTrailController.escape = false;
            AZTrailView.stage.setScene(new SplashMenu());
            break;

          case ESCAPE:
            AZTrailView.escapePressed(false);
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
    this.setOnKeyReleased(new EventHandler<KeyEvent>() {
      @Override
      public void handle(KeyEvent event) {
        switch (event.getCode()) {
          case SPACE:
            released = true;
            break;
        }
      }
    });
  }
}
