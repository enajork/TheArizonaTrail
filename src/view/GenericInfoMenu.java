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

public class GenericInfoMenu extends Scene {
  private Scene prevScene;
  private BorderPane root;
  private int curPage = 0;
  private String[] text;
  private int size;


  /**
   * [GenericInfoMenu description]
   */
  public GenericInfoMenu(Scene prevScene, String[] text) {
    this(new BorderPane(), text, 12);
    getStylesheets().add(AZTrailView.styleSheet);
    this.prevScene = prevScene;
  }

  /**
   * [GenericInfoMenu description]
   */
  public GenericInfoMenu(Scene prevScene, int size, String[] text) {
    this(new BorderPane(), text, size);
    getStylesheets().add(getClass().getResource("assets/style.css")
      .toExternalForm());
    this.prevScene = prevScene;
  }

  /**
   * [GenericInfoMenu description]
   * @param root [description]
   */
  private GenericInfoMenu(BorderPane root, String[] text, int size) {
    super(root, AZTrailView.WIDTH, AZTrailView.HEIGHT);
    this.root = root;
    this.text = text;
    this.size = size;

    // Create the tile image;
    Image img = new Image("file:view/assets/aztrail_splashtext.png");
    ImageView title = new ImageView(img);

    // Create the text for the menu options
    Text body = new Text(text[0] + "\n\nPress SPACEBAR to continue...");
    setTextSize(body);
    body.setFill(Color.WHITE);

    // Style the view
    root.setAlignment(title, Pos.CENTER);
    root.setStyle("-fx-background-color: black;");
    root.setTop(title);

    learnTextBlock();
    addEventHandlers();
  }

  /**
   * [learnTextBlock description]
   */
  private void learnTextBlock() {
    Text body = new Text(text[curPage] + "\n\nPress SPACEBAR to continue...");
    setTextSize(body);
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
        if (event.getCode().equals(KeyCode.SPACE) && curPage < text.length - 1) {
          curPage++;
          learnTextBlock();
        } else if (event.getCode().equals(KeyCode.SPACE) && curPage == text.length - 1) {
          AZTrailView.stage.setScene(prevScene);
        }
      }
    });
  }

  private void setTextSize(Text body) {
    switch (size) {
      case 8:
        body.setId("text8");
        break;
      case 10:
        body.setId("text10");
        break;
      case 12:
        body.setId("text12");
        break;
      default:
        body.setId("text12");
        break;
    }
  }
}
