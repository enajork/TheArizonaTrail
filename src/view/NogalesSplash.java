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

public class NogalesSplash extends Scene {
  private Scene nextScene;
  private BorderPane root;

  /**
   * [NogalesSplash description]
   */
  public NogalesSplash() {
    this(new BorderPane());
    AZTrailView.sounds.startThemeLoop();
  }

  /**
   * [NogalesSplash description]
   * @param root [description]
   */
  private NogalesSplash(BorderPane root) {
    super(root, AZTrailView.WIDTH, AZTrailView.HEIGHT, Color.BLACK);
    getStylesheets().add(AZTrailView.styleSheet);
    this.nextScene = new SizeUpView();
    this.root = root;
    root.setStyle("-fx-background-color: black;");

    Image img = (AZTrailView.controller.getHunted()) ?
      new Image("file:view/assets/graphics/nogales_splash-hunted.png") :
      new Image("file:view/assets/graphics/nogales_splash.png");
    ImageView splash = new ImageView(img);
    splash.setPreserveRatio(true);
    splash.setFitWidth(AZTrailView.WIDTH * 0.985);
    root.setAlignment(splash, Pos.CENTER);
    root.setTop(splash);
    Rectangle rect = new Rectangle(400, 40, Color.WHITE);
    BorderPane innerText = new BorderPane();
    Text title = new Text("Nogales");
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
    root.setMargin(splash, new Insets(5, 5, 0, 5));
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
}
