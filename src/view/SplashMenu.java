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

public class SplashMenu extends Scene {
  private final int NUM_OPTS = 6;
  private Text body;
  private String contents = "You may:\n\n  "
    + "1. Travel the Trail\n  "
    + "2. Learn about the trail\n  "
    + "3. See the Arizona Top Ten\n  "
    + "4. Turn sound off\n  "
    + "5. Choose Management Options\n  "
    + "6. End\n\n"
    + "What is your choice? ";
  private String input = "_";

  /**
   * No arg constructor for the splash menu
   */
  public SplashMenu() {
    this(new BorderPane());
    getStylesheets().add(AZTrailView.styleSheet);
    AZTrailController.escape = false;
  }

  /**
   * Constructs a splash menu view
   *
   * @param root   the root of the scene graph
   */
  private SplashMenu(BorderPane root) {
    super(root, AZTrailView.WIDTH, AZTrailView.HEIGHT, Color.BLACK);

    // Create the title image;
    Image img = new Image("file:view/assets/graphics/aztrail_splashtext.png");
    ImageView title = new ImageView(img);

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
    root.setAlignment(title, Pos.CENTER);
    root.setAlignment(accent1, Pos.CENTER);
    root.setAlignment(accent2, Pos.CENTER);
    root.setMargin(accent2, new Insets(0, 0, 46, 0));
    root.setStyle("-fx-background-color: black;");
    root.setTop(title);
    root.setMargin(title, new Insets(5));
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
            if (AZTrailController.escape) {
              System.exit(0);
              // AZTrailView.stage.setScene(new SplashMenu());
            } else {
              AZTrailController.escape = true;
            }
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
        // Travel the trail
        if (AZTrailController.hasSave) {
          return new GenericYesNoMenu(
            new Runnable() {
              @Override
              public void run() {
                AZTrailView.controller.loadGame();
                AZTrailView.stage.setScene(new CitySplash(AZTrailView.controller
                  .getCurrentCity(), false));
              }
            },
            new Runnable() {
              @Override
              public void run() {
                AZTrailView.controller.resetGame();
                AZTrailView.stage.setScene(new ProfMenu());
              }
            },
            "Would you like to continue\na saved game?",
            "",
            true,
            true
          );
        } else {
          AZTrailView.controller.resetGame();
          return new ProfMenu();
        }
        // return new ProfMenuView();
      case 2:
        // Learn about the trail
        return new GenericInfoMenu(
          new Runnable() {
            @Override
            public void run() {
              AZTrailView.stage.setScene(new SplashMenu());
            }
          }, new String[]{
          "Try taking a journey by\ncovered wagon across 200\nmiles of desert, "
            + "canyons,\nand mountains.Try! In the\ndesert, will you slosh "
            + "your\noxen through dirt and\ntreacherous ruts or will\n"
            + "you plod through dust six\ninches deep?",

          "How will you cross the\ncanyons? If you have\nmoney, you might hire "
            + "\na guide (if there is a\nguide). Or, you can\ntry and cross it"
            + "\nyourself and hope you\nand your wagon aren't\nlost forever!",

          "What about supplies? Well, if\nyou're low on food you can\nhunt. You"
            + "might get an Elk...\nyou might. And there are bears\nin the "
            + "mountains.",

          "If for some reason you don't\nsurvive -- your wagon burns,\nor "
            + "bandits steal your oxen, or\nyou run out of provisions, or\nyou"
            + " die of dehydration -- don't\ngive up!  Try again... and "
            + "again...\nuntil your name is up with the\nothers on The Arizona "
            + "Top Ten.",

          "You may turn the sound on or\noff during the program by\npressing "
            + "Control-S.",

          "You may want to quit in the\nmiddle of the program. If so,"
            + "\npress the Escape (Esc) key\ntwice whenever the computer\nis "
            + "waiting for a response.",

          "In Hunted Mode, the premise is\nthe same but you were somehow\n"
            + "sucked into an alternate reality.\nIn this universe, time "
            + "travel is\npossible. And, an evil Owen Wilson\nfrom the future "
            + "is hunting you\ndown. Evade his capture at all\ncosts!",

          "The software team responsible for the\ncreation of "
            + "this product includes:\n\n Jordan Bridgewater\n Jared Grady\n "
            + "David Najork\n Eric Najork"
        }, true, true);
      case 3:
        // See the Arizona top 10
        return new TopTenMenu();
      case 4:
        // turn sound off
        AZTrailController.sound = !AZTrailController.sound;
        return new GenericInfoMenu(
          new Runnable() {
            @Override
            public void run() {
              AZTrailView.stage.setScene(new SplashMenu());
            }
          },
          new String[]{
            "The sound is now turned " + (AZTrailController.sound ? "on"
              : "off") + ". \n"
              + "You may turn sound on or off\n"
              + "during the program by pressing\nControl-S."
          },
          true,
          true
        );
      case 5:
        // choose management option
        return new OptionsMenu();
      case 6:
        // end
        System.exit(0);
    }
    // return;
    return null;
  }
}
