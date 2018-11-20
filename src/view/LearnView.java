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

public class LearnView extends Scene {
  private BorderPane root;
  private int curPage = 0;

  private static final String[] text = {
    "Try taking a journey by covered wagon \nacross 200 miles of desert, "
    + "mountains, and canyons.\nTry! In the desert, will you slosh your oxen "
    + "through dirt and ruts\nor will you plod through dust six inches deep?",
    "How will you cross the canyons?\nIf you have money, you might hire a "
    + "guide (if there is a guide).\nor you can try and cross it yourself\nand "
    + "hope you and your wagon aren't lost forever!",
    "What about supplies? Well, if\nyou're low on food you can hunt.\nYou might"
    + " get an Elk...\nyou might.\nAnd there are bear in the mountains.",
    "If for some reason you don't\nsurvive -- your wagon burns,\nor bandits "
    + "steal your oxen, or\nyou run out of provisions, or\nyou die of "
    + "dehydration -- don't\ngive up!\tTry again...and\nagain...until your name"
    + " is up\nwith the others on The Arizona\nTop Ten.",
    "You may turn the sound on or\noff during the program by\npressing "
    + "Control-S.", "You may want to quit in the\nmiddle of the program. If so,"
    + "\npress the Escape (Esc) key\ntwice whenever the computer is waiting for"
    + " a response.", "\tThe software team responsible\nfor the creation of "
    + "this product includes:\n\nJordan Bridgewater\nJared Grady\nDavid Najork"
    + "\nEric Najork"
  };

  public LearnView() {
    this(new BorderPane());
  }

  public LearnView(BorderPane root) {
    super(root, AZTrailView.WIDTH, AZTrailView.HEIGHT);
    this.root = root;

    // Create the tile image;
    Image img = new Image("file:view/assets/aztrail_splashtext.png");
    ImageView title = new ImageView(img);

    // Create the text for the menu options
    Text body = new Text(text[0] + "\n\nPress SPACEBAR to continue...");
    body.setFont(Font.font("file:view/assets/here-lies-mecc.tff", 20));
    body.setFill(Color.WHITE);

    // Style the view
    root.setAlignment(title, Pos.CENTER);
    root.setStyle("-fx-background-color: black;");
    root.setTop(title);

    learnTextBlock();
    addEventHandlers();
  }

  private void learnTextBlock() {
    Text body = new Text(text[curPage] + "\n\nPress SPACEBAR to continue...");
    body.setFont(Font.font("file:view/assets/here-lies-mecc.tff", 20));
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

    this.root.setAlignment(accent1, Pos.CENTER);
    this.root.setAlignment(accent2, Pos.CENTER);
    this.root.setCenter(tile);
  }

  private ImageView menuAccent() {
    return new ImageView(new Image("file:view/assets/menuaccent.png",
      620, 40, false, false));
  }

  private void addEventHandlers() {
    this.setOnKeyPressed(new EventHandler<KeyEvent>() {
      @Override
      public void handle(KeyEvent event) {
        if (event.getCode().equals(KeyCode.SPACE) && curPage < text.length - 1) {
          curPage++;
          learnTextBlock();
        }
      }
    });
  }
}
