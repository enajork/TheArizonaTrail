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
  private Runnable call;
  private BorderPane root;
  private int curPage = 0;
  private String[] text;
  private int top;
  private boolean stop;
  private boolean released = false;

  /**
   * [GenericInfoMenu description]
   */
  public GenericInfoMenu(Runnable call, String[] text) {
    this(new BorderPane(), call, text, false, false, 0, false);
  }

  /**
   * [GenericInfoMenu description]
   */
  public GenericInfoMenu(Runnable call, String[] text, boolean accentsOn) {
    this(new BorderPane(), call, text, accentsOn, false, 0, false);
  }

  /**
   * [GenericInfoMenu description]
   */
  public GenericInfoMenu(Runnable call, String[] text, boolean accentsOn,
      int top) {
    this(new BorderPane(), call, text, accentsOn, false, top, false);
  }

  /**
   * [GenericInfoMenu description]
   */
  public GenericInfoMenu(Runnable call, String[] text, boolean accentsOn,
      boolean titleOn) {
    this(new BorderPane(), call, text, accentsOn, titleOn, 0, false);
  }


  /**
   * [GenericInfoMenu description]
   */
  public GenericInfoMenu(Runnable call, String[] text, boolean accentsOn,
      boolean titleOn, boolean stop) {
    this(new BorderPane(), call, text, accentsOn, titleOn, 0, stop);
  }

  /**
   * [GenericInfoMenu description]
   * @param root [description]
   */
  private GenericInfoMenu(BorderPane root, Runnable call, String[] text,
      boolean accentsOn, boolean titleOn, int top, boolean stop) {
    super(root, AZTrailView.WIDTH, AZTrailView.HEIGHT, Color.BLACK);
    getStylesheets().add(AZTrailView.styleSheet);
    this.call = call;
    this.accentsOn = accentsOn;
    this.titleOn = titleOn;
    this.root = root;
    this.stop = stop;
    this.text = text;
    this.top = top;

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
    body.setId("text12");
    body.setFill(Color.WHITE);
    tile.setCenter(body);
    tile.setAlignment(body, Pos.CENTER);

    if (accentsOn) {
      // Create the first accent
      ImageView accent1 = menuAccent();
      tile.setTop(accent1);
      tile.setAlignment(accent1, Pos.CENTER);
      tile.setMargin(accent1, new Insets(top, 0, 0, 0));
      // Create the second accent
      ImageView accent2 = menuAccent();
      tile.setBottom(accent2);
      tile.setMargin(accent2, new Insets(0, 0, 10, 0));
      tile.setAlignment(accent2, Pos.CENTER);
    }

    Text footer = new Text("Press SPACE BAR to continue...");
    footer.setFill(Color.WHITE);
    footer.setId("text12");

    root.setBottom(footer);
    root.setAlignment(footer, Pos.CENTER);
    root.setMargin(footer, new Insets(10, 0, 10, 0));
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
            if (stop && !released) {
              return;
            }
            AZTrailController.escape = false;
            if (curPage < text.length - 1) {
              curPage++;
              learnTextBlock();
            } else if (curPage == text.length - 1) {
              call.run();
            }
            break;

          case ENTER:
            AZTrailController.escape = false;
            if (curPage < text.length - 1) {
              curPage++;
              learnTextBlock();
            } else if (curPage == text.length - 1) {
              call.run();
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
            return;
        }
      }
    });
    this.setOnKeyReleased(new EventHandler<KeyEvent>() {
      @Override
      public void handle(KeyEvent event) {
        switch (event.getCode()) {
          case SPACE:
            released = true;
            break;
        }
      }
    });
  }
}
