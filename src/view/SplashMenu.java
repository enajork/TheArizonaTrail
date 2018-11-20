package view;

import javafx.scene.control.Alert.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.application.*;
import javafx.scene.input.*;
import javafx.scene.image.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.beans.value.*;
import javafx.geometry.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.text.Font;
import javafx.event.*;
import java.util.*;
import java.io.*;
import javafx.scene.text.Text;
import javafx.scene.input.KeyCode;

import controller.*;

public class SplashMenu extends Scene {
  private Text body;
  private String contents = "You may:\n\n\t"
    + "1. Travel the Trail\n\t"
    + "2. Learn about the trail\n\t"
    + "3. See the Arizona Top Ten\n\t"
    + "4. Turn sound off\n\t"
    + "5. Choose Management Options\n\t"
    + "6. End\n\n"
    + "What is your choice? ";
  private String input = "_";

  /**
   * [SplashMenu description]
   */
  public SplashMenu() {
    this(new BorderPane());
    getStylesheets().add(AZTrailView.styleSheet);
  }

  /**
   * [SplashMenu description]
   * @param root [description]
   */
  private SplashMenu(BorderPane root) {
    super(root, AZTrailView.WIDTH, AZTrailView.HEIGHT);

    // Create the tile image;
    Image img = new Image("file:view/assets/aztrail_splashtext.png");
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

    // Style the view
    root.setAlignment(title, Pos.CENTER);
    root.setAlignment(accent1, Pos.CENTER);
    root.setAlignment(accent2, Pos.CENTER);
    root.setStyle("-fx-background-color: black;");
    root.setTop(title);
    root.setCenter(tile);

    addEventHandlers();
  }

  /**
   * [menuAccent description]
   * @return [description]
   */
  private ImageView menuAccent() {
    return new ImageView(new Image("file:view/assets/menuaccent.png",
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
          case DIGIT1:
            updateInputText(1);
            break;

          case DIGIT2:
            updateInputText(2);
            break;

          case DIGIT3:
            updateInputText(3);
            break;

          case DIGIT4:
            updateInputText(4);
            break;

          case DIGIT5:
            updateInputText(5);
            break;

          case DIGIT6:
            updateInputText(6);
            break;

          case BACK_SPACE:
            if (input.length() >= 2) {
              input = input.substring(0, input.length() - 2);
              input += "_";
            }
            body.setText(contents + input);
            break;

          case ENTER:
            AZTrailView.stage.setScene(getSplashView(Integer.parseInt(input
              .substring(0, 1))));
            break;

          default:
            return;
        }
      }
    });
  }

  /**
   * [updateInputText description]
   * @param num [description]
   */
  private void updateInputText(int num) {
    if (input.length() == 1) {
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
        return new ProfMenu();
        // return new ProfMenuView();
      case 2:
        // Learn about the trail
        return new GenericInfoMenu(new SplashMenu(), 10, new String[]{
          "Try taking a journey by covered wagon across\n200 miles of desert, "
          + "mountains, and canyons.\nTry! In the desert, will you slosh your oxen"
          + "\nthrough dirt and ruts or will you plod through\ndust six inches deep?",
          "How will you cross the canyons?\nIf you have money, you might hire"
          + "\na guide (if there is a guide) or\nyou can try and cross it yourself"
          + "\nand hope you and your wagon aren't\nlost forever!",
          "What about supplies? Well, if\nyou're low on food you can\nhunt. You might"
          + " get an Elk...\nyou might. And there are bears\nin the mountains.",
          "If for some reason you don't\nsurvive -- your wagon burns,\nor bandits "
          + "steal your oxen, or\nyou run out of provisions, or\nyou die of "
          + "dehydration -- don't\ngive up!\tTry again... and again...\n"
          + "until your name is up with the\nothers on The Arizona Top Ten.",
          "You may turn the sound on or\noff during the program by\npressing "
          + "Control-S.", "You may want to quit in the\nmiddle of the program. If so,"
          + "\npress the Escape (Esc) key\ntwice whenever the computer\nis waiting"
          + " for a response.", "The software team responsible for the\ncreation of "
          + "this product includes:\n\nJordan Bridgewater\nJared Grady\nDavid Najork"
          + "\nEric Najork"
        });
      case 3:
        // See the Arizona top 10
        return new GenericInfoMenu(new SplashMenu(), new String[]{
          "Not yet implemented..."
        });
      case 4:
        // turn sound off
        return new GenericInfoMenu(new SplashMenu(), new String[]{
          "The sound is now turned off. \n"
            + "You may turn sound on or off\n"
            + "during the program by pressing\nControl-S."
        });
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
