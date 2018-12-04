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

public class ProfMenu extends Scene {
  private final int NUM_OPTS = 4;
  private Text body;
  private String contents = "You may:\n\n  "
    + "1. Be a banker from Boston\n  "
    + "2. Be a carpenter from Ohio\n  "
    + "3. Be a farmer from Illinois\n  "
    + "4. Find out the differences\n  "
    + "   between these choices\n\n"
    + "What is your choice? ";
  private String input = "_";

  /**
   * No arg constructor for the prof menu
   */
  public ProfMenu() {
    this(new BorderPane());
    getStylesheets().add(AZTrailView.styleSheet);
  }

  /**
   * Constructs a prof menu view
   *
   * @param root   the root of the scene graph
   */
  private ProfMenu(BorderPane root) {
    super(root, AZTrailView.WIDTH, AZTrailView.HEIGHT, Color.BLACK);

    // Create the text for the menu options
    body = new Text(contents + input);
    body.setId("text12");
    body.setFill(Color.WHITE);

    BorderPane tile = new BorderPane();
    tile.setStyle("-fx-background-color: black;");

    // Create the first accent
    ImageView accent1 = menuAccent();
    tile.setTop(accent1);
    tile.setCenter(body);

    // Create the second accent
    ImageView accent2 = menuAccent();
    tile.setBottom(accent2);
    tile.setMargin(accent2, new Insets(0, 0, 40, 0));

    // Style the view
    root.setAlignment(accent1, Pos.CENTER);
    root.setAlignment(accent2, Pos.CENTER);
    root.setStyle("-fx-background-color: black;");
    root.setCenter(tile);

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
              AZTrailView.stage.setScene(getNextView(Integer.parseInt(input
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
   * determines the next view to be scene by the player based on their choice
   * @param  choice integer choice number
   * @return        the next view to be staged
   */
  private Scene getNextView(int choice) {
    if (choice < 1 || choice > NUM_OPTS) {
      throw new IllegalStateException();
    }
    switch (choice) {
      case 1:
        AZTrailView.controller.profMenu(1);
        // party creation screen goes here
        return new PartyLeaderMenu();
      case 2:
        AZTrailView.controller.profMenu(2);
        return new PartyLeaderMenu();
      case 3:
        AZTrailView.controller.profMenu(3);
        return new PartyLeaderMenu();
      case 4:
        return new GenericInfoMenu(
          new Runnable() {
            @Override
            public void run() {
              AZTrailView.stage.setScene(new ProfMenu());
            }
          }, new String[]{
          "Traveling to Utah isn't easy!\nBut if you're a banker, you'll\n"
          + "have more money for supplies\nand services than a carpenter\n"
          + "or a farmer.\n\nHowever, the harder you have\nto try, the "
          + "more points you\ndeserve! Therefore, the\nfarmer earns the "
          + "greatest\nnumber of points and the\nbanker earns the least."
        }, true);
    }
    // return;
    return null;
  }
}
