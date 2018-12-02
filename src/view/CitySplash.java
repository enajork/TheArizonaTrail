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

public class CitySplash extends Scene {
  private boolean released = false;
  private BorderPane root;
  private String city;

  /**
   * [CitySplash description]
   */
  public CitySplash(String city) {
    this(new BorderPane(), city);
    getStylesheets().add(AZTrailView.styleSheet);
  }

  /**
   * [CitySplash description]
   * @param root [description]
   */
  private CitySplash(BorderPane root, String city) {
    super(root, AZTrailView.WIDTH, AZTrailView.HEIGHT, Color.BLACK);
    getStylesheets().add(AZTrailView.styleSheet);
    AZTrailView.controller.setCheckpoint();
    AZTrailView.sounds.stop();
    AZTrailView.sounds.startThemeLoop();
    this.root = root;
    this.city = city;
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
   * [addEventHandlers description]
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
          ? "file:view/assets/graphics/locations/nogales_splash-hunted.png"
          : "file:view/assets/graphics/locations/nogales_splash.png");
        splash = new ImageView(img);
        splash.setPreserveRatio(true);
        splash.setFitWidth(AZTrailView.WIDTH * 0.985);
        root.setMargin(splash, new Insets(5, 5, 0, 5));
        root.setAlignment(splash, Pos.CENTER);
        root.setTop(splash);
        return;
    }
  }

  private Scene getNextScene() {
    switch (city) {
      case "Nogales":
        return new SizeUpView();
      default:
        return new StoreMenu(city, false);
    }
  }
}
