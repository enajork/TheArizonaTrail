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

public class CitySplash extends Scene {
  private BorderPane root;
  private String city;
  private boolean released = false;
  private boolean stop;

  /**
   * No arg constructor for the city splash view
   * @param city the city the player arrived in
   * @param stop the stop field
   */
  public CitySplash(String city, boolean stop) {
    this(new BorderPane(), city, stop);
    getStylesheets().add(AZTrailView.styleSheet);
  }

  /**
   * Constructs the city splash view which is shown
   * when a player arrives in a new city
   * @param root the root of the scene graph
   * @param city the city the player arrived in
   * @param stop the stop field
   */
  private CitySplash(BorderPane root, String city, boolean stop) {
    super(root, AZTrailView.WIDTH, AZTrailView.HEIGHT, Color.BLACK);
    getStylesheets().add(AZTrailView.styleSheet);
    AZTrailView.controller.setCheckpoint();
    if (city.equals("Page")) {
      AZTrailView.sounds.stopMovingSFX();
      AZTrailView.sounds.creditsTheme();
    } else {
      AZTrailView.sounds.stopMovingSFX();
      AZTrailView.sounds.startThemeLoop();
    }
    this.root = root;
    this.city = city;
    this.stop = stop;
    root.setStyle("-fx-background-color: black;");

    setSplash();
    Rectangle rect = new Rectangle(400, 40, Color.WHITE);
    BorderPane innerText = new BorderPane();
    Text title = new Text(city);
    title.setId("text12");
    Text date = new Text(AZTrailView.controller.getDateStr());
    date.setId("text12");
    innerText.setTop(title);
    innerText.setBottom(date);
    innerText.setAlignment(title, Pos.CENTER);
    innerText.setAlignment(date, Pos.CENTER);
    innerText.setMargin(title, new Insets(10, 0, 0, 0));
    innerText.setMargin(date, new Insets(0, 0, 10, 0));
    innerText.setMaxHeight(100);
    StackPane stack = new StackPane();
    stack.getChildren().add(rect);
    stack.getChildren().add(innerText);
    root.setCenter(stack);
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
   * Creates event handlers to handle actions that
   * the player can take from this view
   */
  private void addEventHandlers() {
    this.setOnKeyPressed(new EventHandler<KeyEvent>() {
      @Override
      public void handle(KeyEvent event) {
        switch (event.getCode()) {
          case SPACE:
            if (stop && !released) {
              return;
            }
            AZTrailController.escape = false;
            AZTrailView.stage.setScene(getNextScene());
            break;

          case ENTER:
            AZTrailController.escape = false;
            AZTrailView.stage.setScene(getNextScene());
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

  /**
   * Creates the imageview for the city splash
   */
  private void setSplash() {
    Image img;
    ImageView splash;
    switch (city) {
      case "Nogales":
        img = new Image((AZTrailView.controller.getHunted())
          ? "file:view/assets/graphics/locations/nogales_splash-hunted.png"
          : "file:view/assets/graphics/locations/nogales_splash.png");
        splash = new ImageView(img);
        splash.setPreserveRatio(true);
        splash.setFitWidth(AZTrailView.WIDTH * 0.985);
        root.setMargin(splash, new Insets(5, 5, 0, 5));
        root.setAlignment(splash, Pos.CENTER);
        root.setTop(splash);
        return;
      default:
        img = new Image((AZTrailView.controller.getHunted())
          ? "file:view/assets/graphics/locations/" + city.toLowerCase() + "_splash-hunted.png"
          : "file:view/assets/graphics/locations/" + city.toLowerCase() + "_splash.png");
        splash = new ImageView(img);
        splash.setPreserveRatio(true);
        splash.setFitWidth(AZTrailView.WIDTH * 0.5);
        root.setMargin(splash, new Insets(5, 5, 0, 5));
        root.setAlignment(splash, Pos.CENTER);
        root.setTop(splash);
        return;
    }
  }

  /**
   * determines the next scene that should be shown to the player
   *
   * @return the scene to be displayed
   */
  private Scene getNextScene() {
    switch (city) {
      case "Nogales":
        return new SizeUpView();
      case "Page":
        return new GenericInfoMenu(
          new Runnable() {
            @Override
            public void run() {
              AZTrailView.controller.setScore(
                AZTrailView.controller.getName(0),
                AZTrailView.controller.getScore()
              );
              AZTrailView.controller.saveTopTen();
              AZTrailView.stage.setScene(new SplashMenu());
            }
          },
          new String[]{
            "Congratulations, you won!",
            "Check the Top Ten to see\nif your name has been\nadded!",
            "The software team responsible for the\ncreation of "
              + "this product includes:\n\n Jordan Bridgewater\n Jared Grady\n "
              + "David Najork\n Eric Najork"
          },
          true,
          true
        );
      default:
        return new StoreMenu(city, false);
    }
  }
}
