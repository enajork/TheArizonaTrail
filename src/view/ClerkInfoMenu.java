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

public class ClerkInfoMenu extends Scene {
  private Runnable call;
  private BorderPane root;
  private int curPage = 0;
  private String[] text;
  private Text header;

  /**
   * No arg constructor for the clerk info menu view
   */
  public ClerkInfoMenu(Runnable call, String header, String[] text) {
    this(new BorderPane(), call, header, text);
  }

  /**
   * Constructs a new clerk info menu view with the given data
   *
   * @param root   the root of the scene graph
   * @param call   metadata for the next thread
   * @param header the header text for the clerk
   * @param text   the description data
   */
  private ClerkInfoMenu(BorderPane root, Runnable call, String header,
      String[] text) {
    super(root, AZTrailView.WIDTH, AZTrailView.HEIGHT, Color.BLACK);
    getStylesheets().add(AZTrailView.styleSheet);
    this.call = call;
    this.root = root;
    this.text = text;

    this.header = new Text(header);
    this.header.setFill(Color.WHITE);
    this.header.setId("text12");
    root.setTop(this.header);
    root.setAlignment(this.header, Pos.CENTER);
    root.setMargin(this.header, new Insets(40, 0, 0, 0));

    // Style the view
    root.setStyle("-fx-background-color: black;");

    learnTextBlock();
    addEventHandlers();
  }

  /**
   * Builds the learn text node for the scene
   */
  private void learnTextBlock() {
    BorderPane tile = new BorderPane();
    tile.setStyle("-fx-background-color: black;");

    // Create the image;
    Image img = new Image("file:view/assets/graphics/menuclerk.png");
    ImageView decor = new ImageView(img);
    decor.setPreserveRatio(true);
    decor.setFitWidth(100);
    tile.setLeft(decor);
    tile.setMargin(decor, new Insets(10));

    Text body = new Text(text[curPage]);
    body.setId("text12");
    body.setFill(Color.WHITE);
    tile.setAlignment(body, Pos.CENTER);

    Text footer = new Text("Press SPACE BAR to continue...");
    footer.setFill(Color.WHITE);
    footer.setId("text12");
    tile.setCenter(body);
    tile.setAlignment(body, Pos.CENTER);
    root.setBottom(footer);
    root.setAlignment(footer, Pos.CENTER);
    root.setMargin(footer, new Insets(0, 0, 20, 0));

    root.setCenter(tile);
    root.setAlignment(tile, Pos.CENTER);
  }

  /**
   * creates a menu accent for the view
   * @return an imageview of the accent
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
  }
}
