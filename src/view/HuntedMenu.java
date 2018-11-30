package view;

import javafx.animation.*;
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
import javafx.util.*;
import java.util.*;
import java.io.*;

import controller.*;

public class HuntedMenu extends Scene {
  private final int CHAR_LIMIT = 4;
  private boolean fadeStart;
  private ImageView decor;
  private String input = "_";
  private BorderPane root;
  private BorderPane tile;
  private StackPane stack;
  private String prompt = "";
  private Text body;

  /**
  * [HuntedMenu description]
  */
  public HuntedMenu() {
    this(new BorderPane());
    AZTrailView.sounds.huntedMenuTheme();
  }

  /**
   * [HuntedMenu description]
   * @param root [description]
   */
  private HuntedMenu(BorderPane root) {
    super(root, AZTrailView.WIDTH, AZTrailView.HEIGHT, Color.BLACK);
    getStylesheets().add(AZTrailView.styleSheet);
    this.root = root;

    tile = new BorderPane();
    stack = new StackPane();
    tile.setMaxHeight(0);
    tile.setMaxWidth(0);
    tile.setStyle("-fx-background-color: black;");

    // Create the title image;
    decor = new ImageView(new Image("file:view/assets/graphics/hunted-menu-normal.png"));
    decor.setPreserveRatio(true);
    decor.setFitWidth(400);
    stack.setAlignment(decor, Pos.CENTER);
    stack.getChildren().add(decor);
    tile.setTop(stack);
    stack.setMargin(decor, new Insets(5));

    body = new Text(prompt + " " + input);
    body.setId("text12");
    tile.setBottom(body);
    body.setFill(Color.WHITE);
    tile.setAlignment(body, Pos.CENTER);
    tile.setMargin(body, new Insets(20));

    root.setCenter(tile);
    root.setAlignment(tile, Pos.CENTER);

    // Style the view
    root.setStyle("-fx-background-color: black;");

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
          case BACK_SPACE:
            if (fadeStart) {
              return;
            }
            AZTrailController.escape = false;
            if (input.length() >= 2) {
              input = input.substring(0, input.length() - 2);
              input += "_";
              body.setText(prompt + " " + input);
            }
            break;

          case ENTER:
            if (fadeStart) {
              return;
            }
            boolean yesTrue = true;
            boolean noTrue = true;
            String yes = "yes";
            String no = "no";
            for (int i = 0; i < input.length() - 1; i++) {
              if (input.charAt(i) != yes.charAt(i)) {
                yesTrue = false;
              }
              if (i < no.length() && input.charAt(i) != no.charAt(i)) {
                noTrue = false;
              }
              if (i > no.length()) {
                noTrue = false;
              }
            }
            if (noTrue) {
              AZTrailView.sounds.stop();
              AZTrailView.controller.setHunted(false);
              fadeOut(3000);
              return;
            }
            if (yesTrue) {
              if (!fadeStart) {
                decor = new ImageView(new Image("file:view/assets/graphics/"
                  + "hunted-menu-inverted.png"));
                tile.setStyle("-fx-background-color: white;");
                root.setStyle("-fx-background-color: white;");
                body.setFill(Color.BLACK);
                decor.setPreserveRatio(true);
                decor.setFitWidth(400);
                fadeStart = true;
                AZTrailView.sounds.stop();
                AZTrailView.sounds.huntedModeSFX();
                AZTrailView.controller.setHunted(true);
                fadeOut(14000);
                return;
              }
            }
            AZTrailController.escape = false;
            input = "_";
            body.setText(prompt + " " + input);
            break;

          case ESCAPE:
            if (fadeStart) {
              return;
            }
            AZTrailView.escapePressed(true);
            break;

          case S:
            if (fadeStart) {
              return;
            }
            AZTrailController.escape = false;
            if (event.isControlDown()) {
              AZTrailView.sounds.mute();
            }
            updateInputText(event);
            break;

          default:
            if (fadeStart) {
              return;
            }
            updateInputText(event);
            AZTrailController.escape = false;
            return;
        }
      }
    });
  }

  private void fadeOut(double duration) {
    FadeTransition trans = new FadeTransition();
    trans.setDuration(Duration.millis(duration));
    trans.setNode(root);
    trans.setFromValue(1);
    trans.setToValue(0);
    trans.setOnFinished(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        AZTrailView.stage.setScene(new CitySplash(AZTrailView.controller
          .getCurrentCity()));
      }
    });
    trans.play();
  }

  private void updateInputText(KeyEvent event) {
    String letter = event.getText();
    if (letter.length() != 1) {
      return;
    }
    if (((letter.toLowerCase().charAt(0) != 'y'
      && letter.toLowerCase().charAt(0) != 'e'
      && letter.toLowerCase().charAt(0) != 's'
      && letter.toLowerCase().charAt(0) != 'n'
      && letter.toLowerCase().charAt(0) != 'o')
        || input.length() >= CHAR_LIMIT)) {
      return;
    }
    if (!event.isShiftDown()) {
      letter = letter.toLowerCase();
    }
    if (Character.isLetter(letter.charAt(0))) {
      input = input.substring(0, input.length() - 1);
      input += letter + "_";
      body.setText(prompt + " " + input);
    }
  }
}
