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

public class SoundMenuView extends Scene {
  private BorderPane root;
  private int curPage = 0;

  private static final String text = "\tThe sound is now turned off. \n" +
     "\tYou may turn sound on or off\n" +
     "\tduring the program by pressing Control-S." +
     "\n\n\tPress SPACEBAR to continue...";

  /**
   * [SoundMenuView description]
   */
  public SoundMenuView() {
    this(new BorderPane());
  }

  /**
   * [SoundMenuView description]
   * @param root [description]
   */
  private SoundMenuView(BorderPane root) {
    super(root, AZTrailView.WIDTH, AZTrailView.HEIGHT);
    this.root = root;

    // Create the tile image;
    Image img = new Image("file:view/assets/aztrail_splashtext.png");
    ImageView title = new ImageView(img);

    // Style the view
    root.setAlignment(title, Pos.CENTER);
    root.setStyle("-fx-background-color: black;");
    root.setTop(title);
    body();
    addEventHandlers();
  }

  /**
   * [body description]
   */
  private void body() {
    Text body = new Text(text);
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
        if (event.getCode().equals(KeyCode.SPACE)) {
          AZTrailView.stage.setScene(new SplashMenuView());
        }
      }
    });
  }
}