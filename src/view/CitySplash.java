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
  private Scene nextScene;
  private BorderPane root;
  private String city;

  /**
   * [CitySplash description]
   */
  public CitySplash(String city) {
    this(new BorderPane(), city);
    AZTrailView.sounds.startThemeLoop();
  }

  /**
   * [CitySplash description]
   * @param root [description]
   */
  private CitySplash(BorderPane root, String city) {
    super(root, AZTrailView.WIDTH, AZTrailView.HEIGHT, Color.BLACK);
    getStylesheets().add(AZTrailView.styleSheet);
    this.nextScene = new SizeUpView();
    this.root = root;
    this.city = city;
    root.setStyle("-fx-background-color: black;");

    setSplash(city);
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
            AZTrailController.escape = false;
            AZTrailView.stage.setScene(nextScene);
            break;

          case ENTER:
            AZTrailController.escape = false;
            AZTrailView.stage.setScene(nextScene);
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
  }

  private void setSplash(String city) {
    ImageView splash;
    Image img;
    switch (city) {
      case "Nogales":
        img = new Image("file:view/assets/graphics/nogales_splash.png");
        splash = new ImageView(img);
        splash.setPreserveRatio(true);
        splash.setFitWidth(AZTrailView.WIDTH * 0.985);
        root.setMargin(splash, new Insets(5, 5, 0, 5));
        root.setAlignment(splash, Pos.CENTER);
        root.setTop(splash);
        AZTrailView.controller.setGameStarted(true);
        return;

      default:
        img = new Image("file:view/assets/graphics/nogales_splash.png");
        splash = new ImageView(img);
        splash.setPreserveRatio(true);
        splash.setFitWidth(AZTrailView.WIDTH * 0.985);
        root.setMargin(splash, new Insets(5, 5, 0, 5));
        root.setAlignment(splash, Pos.CENTER);
        root.setTop(splash);
        return;
    }
  }
}
