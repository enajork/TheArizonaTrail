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

public class OptionsMenu extends Scene {
  private final int NUM_OPTS = 1;
  private Text body;
  private String contents = "You may:\n\n  "
    + "1. Travel the Trail\n\n"
    + "What is your choice? ";
  private String input = "_";

  /**
   * [OptionsMenu description]
   */
  public OptionsMenu() {
    this(new BorderPane());
    getStylesheets().add(AZTrailView.styleSheet);
  }

  /**
   * [OptionsMenu description]
   * @param root [description]
   */
  private OptionsMenu(BorderPane root) {
    super(root, AZTrailView.WIDTH, AZTrailView.HEIGHT, Color.BLACK);

    // Create the text for the menu options
    body = new Text(contents + input);
    body.setId("text12");
    body.setFill(Color.WHITE);

    BorderPane tile = new BorderPane();
    tile.setStyle("-fx-background-color: black;");
    tile.setCenter(body);
    root.setStyle("-fx-background-color: black;");
    root.setCenter(tile);

    addEventHandlers();
  }

  /**
   * [menuAccent description]
   * @return [description]
   */
  private ImageView menuAccent() {
    return new ImageView(new Image("file:view/assets/graphics/menuaccent.png",
      620, 40, false, false));
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
            AZTrailView.escape = false;
            if (input.length() >= 2) {
              input = input.substring(0, input.length() - 2);
              input += "_";
            }
            body.setText(contents + input);
            break;

          case ENTER:
            AZTrailView.escape = false;
            if (input.length() == 2) {
              AZTrailView.stage.setScene(getSplashView(Integer.parseInt(input
                .substring(0, 1))));
            }
            break;

          case ESCAPE:
            if (AZTrailView.escape) {
              AZTrailView.stage.setScene(new SplashMenu());
            } else {
              AZTrailView.escape = true;
            }
            break;

          case S:
            AZTrailView.escape = false;
            if (event.isControlDown()) {
              AZTrailView.sounds.mute();
            }
            break;

          default:
            AZTrailView.escape = false;
            if (event.getText().length() > 0
                && Character.isDigit(event.getText().charAt(0))) {
              updateInputText(Integer.parseInt(event.getText()));
            }
        }
      }
    });
  }

  /**
   * [updateInputText description]
   * @param num [description]
   */
  private void updateInputText(int num) {
    if (input.length() == 1 && num >= 1 && num <= NUM_OPTS) {
      input = input.substring(0, input.length() - 1);
      input += num + "_";
      body.setText(contents + input);
    }
  }

  /**
   * [getSplashView description]
   * @param  choice [description]
   * @return        [description]
   */
  private Scene getSplashView(int choice) {
    if (choice < 1 || choice > 6) {
      throw new IllegalStateException();
    }
    switch (choice) {
      case 1:
        // Travel the trail
        return new SplashMenu();
    }
    // return;
    return null;
  }
}
