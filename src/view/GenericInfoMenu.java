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
  private boolean allowSaving;

  /**
   * [GenericInfoMenu description]
   *
   * @param call the thread of the next view
   * @param text the text for the info view
   */
  public GenericInfoMenu(Runnable call, String[] text) {
    this(new BorderPane(), call, text, false, false, 0, false, false);
  }

  /**
   * [GenericInfoMenu description]
   *
   * @param call the thread of the next view
   * @param text the text for the info view
   * @param accentsOn whether or not to show accents
   */
  public GenericInfoMenu(Runnable call, String[] text, boolean accentsOn) {
    this(new BorderPane(), call, text, accentsOn, false, 0, false, false);
  }

  /**
   * [GenericInfoMenu description]
   *
   * @param call the thread of the next view
   * @param text the text for the info view
   * @param accentsOn whether or not to show accents
   * @param top       the integer at the top of the field
   */
  public GenericInfoMenu(Runnable call, String[] text, boolean accentsOn,
      int top) {
    this(new BorderPane(), call, text, accentsOn, false, top, false, false);
  }

  /**
   * [GenericInfoMenu description]
   *
   * @param call the thread of the next view
   * @param text the text for the info view
   * @param accentsOn whether or not to show accents
   * @param titleOn   whether or not to show the title
   */
  public GenericInfoMenu(Runnable call, String[] text, boolean accentsOn,
      boolean titleOn) {
    this(new BorderPane(), call, text, accentsOn, titleOn, 0, false, false);
  }

  /**
   * [GenericInfoMenu description]
   *
   * @param call the thread of the next view
   * @param text the text for the info view
   * @param accentsOn whether or not to show accents
   * @param titleOn   whether or not to show the title
   * @param stop      boolean
   */
  public GenericInfoMenu(Runnable call, String[] text, boolean accentsOn,
      boolean titleOn, boolean stop) {
    this(new BorderPane(), call, text, accentsOn, titleOn, 0, stop, true);
  }

  /**
   * [GenericInfoMenu description]
   *
   * @param call the thread of the next view
   * @param text the text for the info view
   * @param accentsOn whether or not to show accents
   * @param titleOn   whether or not to show the title
   * @param stop      boolean
   * @param allowSaving if the player can save from this view
   */
  public GenericInfoMenu(Runnable call, String[] text, boolean accentsOn,
      boolean titleOn, boolean stop, boolean allowSaving) {
    this(new BorderPane(), call, text, accentsOn, titleOn, 0, stop,
      allowSaving);
  }

  /**
   * [GenericInfoMenu description]
   *
   * @param root        root of the scene graph
   * @param call        the thread of the next view
   * @param text        the text for the info view
   * @param accentsOn   whether or not to show accents
   * @param titleOn     whether or not to show the title
   * @param stop        boolean
   * @param allowSaving if the player can save from this view
   */
  private GenericInfoMenu(BorderPane root, Runnable call, String[] text,
      boolean accentsOn, boolean titleOn, int top, boolean stop,
      boolean allowSaving) {
    super(root, AZTrailView.WIDTH, AZTrailView.HEIGHT, Color.BLACK);
    getStylesheets().add(AZTrailView.styleSheet);
    this.call = call;
    this.allowSaving = allowSaving;
    this.accentsOn = accentsOn;
    this.titleOn = titleOn;
    this.root = root;
    this.stop = stop;
    this.text = text;
    this.top = top;

    AZTrailView.sounds.stopMovingSFX();

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
   * Creates the learn text block and adds it to the scene graph
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
            AZTrailView.escapePressed(allowSaving);
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
