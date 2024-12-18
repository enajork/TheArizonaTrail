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

public class OptionsMenu extends Scene {
  private final int NUM_OPTS = 3;
  private String contents = "You may:\n\n"
    + "  1. Delete save file\n"
    + "  2. Reset the Top Ten\n"
    + "  3. Return to Main Menu\n\n"
    + "What is your choice? ";
  private String input = "_";
  private Text body;

  /**
   * No arg constructor for the options menu
   */
  public OptionsMenu() {
    this(new BorderPane());
    getStylesheets().add(AZTrailView.styleSheet);
  }

  /**
   * Constructs a options menu view
   *
   * @param root   the root of the scene graph
   */
  private OptionsMenu(BorderPane root) {
    super(root, AZTrailView.WIDTH, AZTrailView.HEIGHT, Color.BLACK);

    // Create the title image;
    Image img = new Image("file:view/assets/graphics/aztrail_splashtext.png");
    ImageView title = new ImageView(img);

    // Create the text for the menu options
    body = new Text(contents + input);
    body.setId("text12");
    body.setFill(Color.WHITE);

    AnchorPane anchor = new AnchorPane(body);
    anchor.setLeftAnchor(body, 120.0);
    anchor.setTopAnchor(body, 30.0);

    BorderPane tile = new BorderPane();
    tile.setStyle("-fx-background-color: black;");

    // Create the first accent
    ImageView accent1 = menuAccent();
    tile.setTop(accent1);
    tile.setCenter(anchor);
    tile.setMargin(anchor, new Insets(20));

    // Create the second accent
    ImageView accent2 = menuAccent();
    tile.setBottom(accent2);
    tile.setMargin(accent2, new Insets(0, 0, 40, 0));

    // Style the view
    root.setAlignment(title, Pos.CENTER);
    root.setAlignment(accent1, Pos.CENTER);
    root.setAlignment(accent2, Pos.CENTER);
    root.setMargin(accent2, new Insets(0, 0, 46, 0));
    root.setStyle("-fx-background-color: black;");
    root.setTop(title);
    root.setMargin(title, new Insets(5));
    root.setCenter(tile);
    root.setAlignment(tile, Pos.CENTER);

    addEventHandlers();
  }

  /**
   * Creates a new menu accent node for the scene graph
   * @return an imageview into the accent
   */
  private ImageView menuAccent() {
    return new ImageView(new Image("file:view/assets/graphics/menuaccent.png",
      620, 40, false, false));
  }

  /**
   * Adds event handlers to the view to handle keyboard actions
   */
  private void addEventHandlers() {
    this.setOnKeyPressed(new EventHandler<KeyEvent>() {
      @Override
      public void handle(KeyEvent event) {
        switch (event.getCode()) {
          case BACK_SPACE:
            AZTrailController.escape = false;
            if (input.length() >= 2) {
              input = input.substring(0, input.length() - 2);
              input += "_";
            }
            body.setText(contents + input);
            break;

          case ENTER:
            AZTrailController.escape = false;
            if (input.length() == 2) {
              AZTrailView.stage.setScene(getSplashView(Integer.parseInt(input
                .substring(0, 1))));
            }
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
            if (event.getText().length() > 0
                && Character.isDigit(event.getText().charAt(0))) {
              updateInputText(Integer.parseInt(event.getText()));
            }
        }
      }
    });
  }

  /**
   * updates the display when the user types in a selection the keyboard
   * @param num the value inserted by the user
   */
  private void updateInputText(int num) {
    if (input.length() == 1 && num >= 1 && num <= NUM_OPTS) {
      input = input.substring(0, input.length() - 1);
      input += num + "_";
      body.setText(contents + input);
    }
  }

  /**
   * returns the user to the splash view based on their current choice
   * @param  choice the users selection
   * @return        the scene to return to
   */
  private Scene getSplashView(int choice) {
    if (choice < 1 || choice > 6) {
      throw new IllegalStateException();
    }
    switch (choice) {
      case 1:
        if (AZTrailController.hasSave) {
          return new GenericYesNoMenu(
            new Runnable() {
              @Override
              public void run() {
                AZTrailView.stage.setScene(new OptionsMenu());
                AZTrailView.controller.deleteSave();
              }
            },
            new Runnable() {
              @Override
              public void run() {
                AZTrailView.stage.setScene(new OptionsMenu());
              }
            },
            "    Are you sure?",
            "",
            true,
            true
          );
        } else {
          return new GenericInfoMenu(
            new Runnable() {
              @Override
              public void run() {
                AZTrailView.stage.setScene(new OptionsMenu());
              }
            },
            new String[]{
              "No save to delete..."
            },
            true,
            true
          );
        }
      case 2:
        return new GenericYesNoMenu(
          new Runnable() {
            @Override
            public void run() {
              AZTrailView.stage.setScene(new OptionsMenu());
              AZTrailView.controller.resetTopTen();
            }
          },
          new Runnable() {
            @Override
            public void run() {
              AZTrailView.stage.setScene(new OptionsMenu());
            }
          },
          "    Are you sure?",
          "",
          true,
          true
        );
      case 3:
        return new SplashMenu();
    }
    // return;
    return null;
  }
}
