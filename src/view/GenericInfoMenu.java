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

public class GenericInfoMenu extends Scene {
  private boolean accentsOn;
  private boolean titleOn;
  private Scene nextScene;
  private BorderPane root;
  private int curPage = 0;
  private String[] text;
  private int size;

  /**
   * [GenericInfoMenu description]
   */
  public GenericInfoMenu(Scene nextScene, String[] text) {
    this(new BorderPane(), nextScene, text, 12, false, false);
  }

  /**
   * [GenericInfoMenu description]
   */
  public GenericInfoMenu(Scene nextScene, String[] text, int size) {
    this(new BorderPane(), nextScene, text, size, false, false);
  }

  /**
   * [GenericInfoMenu description]
   */
  public GenericInfoMenu(Scene nextScene, String[] text, boolean accentsOn) {
    this(new BorderPane(), nextScene, text, 12, accentsOn, false);
  }

  /**
   * [GenericInfoMenu description]
   */
  public GenericInfoMenu(Scene nextScene, String[] text, int size, boolean accentsOn) {
    this(new BorderPane(), nextScene, text, size, accentsOn, false);
  }

  /**
   * [GenericInfoMenu description]
   */
  public GenericInfoMenu(Scene nextScene, String[] text,
      boolean accentsOn, boolean titleOn) {
    this(new BorderPane(), nextScene, text, 12, accentsOn, titleOn);
  }

  /**
   * [GenericInfoMenu description]
   */
  public GenericInfoMenu(Scene nextScene, int size, String[] text,
      boolean accentsOn, boolean titleOn) {
    this(new BorderPane(), nextScene, text, size, accentsOn, titleOn);
  }

  /**
   * [GenericInfoMenu description]
   * @param root [description]
   */
  private GenericInfoMenu(BorderPane root, Scene nextScene, String[] text,
      int size, boolean accentsOn, boolean titleOn) {
    super(root, AZTrailView.WIDTH, AZTrailView.HEIGHT, Color.BLACK);
    getStylesheets().add(AZTrailView.styleSheet);
    this.nextScene = nextScene;
    this.accentsOn = accentsOn;
    this.titleOn = titleOn;
    this.root = root;
    this.text = text;
    this.size = size;

    // Create the title image;
    if (titleOn) {
      Image img = new Image("file:view/assets/graphics/aztrail_splashtext.png");
      ImageView title = new ImageView(img);
      root.setAlignment(title, Pos.CENTER);
      root.setTop(title);
      root.setMargin(title, new Insets(5));
    }

    // Style the view
    root.setStyle("-fx-background-color: black;");

    learnTextBlock();
    addEventHandlers();
  }

  /**
   * [learnTextBlock description]
   */
  private void learnTextBlock() {
    BorderPane tile = new BorderPane();
    tile.setStyle("-fx-background-color: black;");

    Text body = new Text(text[curPage]);
    setTextSize(body);
    body.setFill(Color.WHITE);
    tile.setAlignment(body, Pos.CENTER);

    if (accentsOn) {
      // Create the first accent
      ImageView accent1 = menuAccent();
      tile.setTop(accent1);
      tile.setAlignment(accent1, Pos.CENTER);
      // Create the second accent
      ImageView accent2 = menuAccent();
      tile.setBottom(accent2);
      tile.setMargin(accent2, new Insets(0, 0, 10, 0));
      tile.setAlignment(accent2, Pos.CENTER);
    }

    Text footer = new Text("Press SPACEBAR to continue...");
    footer.setFill(Color.WHITE);
    setTextSize(footer);
    tile.setCenter(body);
    tile.setAlignment(body, Pos.CENTER);
    root.setBottom(footer);
    root.setAlignment(footer, Pos.CENTER);
    root.setMargin(footer, new Insets(0, 0, 40, 0));

    root.setCenter(tile);
    root.setAlignment(tile, Pos.CENTER);
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
          case SPACE:
            AZTrailController.escape = false;
            if (curPage < text.length - 1) {
              curPage++;
              learnTextBlock();
            } else if (curPage == text.length - 1) {
              AZTrailView.stage.setScene(nextScene);
            }
            break;

          case ENTER:
            AZTrailController.escape = false;
            if (curPage < text.length - 1) {
              curPage++;
              learnTextBlock();
            } else if (curPage == text.length - 1) {
              AZTrailView.stage.setScene(nextScene);
            }
            break;

          case ESCAPE:
            AZTrailView.escapePressed(true);
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
