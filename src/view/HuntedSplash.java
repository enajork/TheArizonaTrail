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

public class HuntedSplash extends Scene {
  private BorderPane root;

  /**
   * No arg constructor for the hunting splash
   */
  public HuntedSplash() {
    this(new BorderPane());
    AZTrailView.sounds.stopMusic();
    AZTrailView.sounds.huntedFinalTheme();
  }

  /**
   * Constructs the hunting splash
   *
   * @param root   the root of the scene graph
   */
  private HuntedSplash(BorderPane root) {
    super(root, AZTrailView.WIDTH, AZTrailView.HEIGHT, Color.BLACK);
    getStylesheets().add(AZTrailView.styleSheet);
    this.root = root;
    root.setStyle("-fx-background-color: black;");

    BorderPane innerText = new BorderPane();
    Text title = new Text("Owen Wilson has found you!\n    Prepare to fight!\n     Prepare to die!");
    title.setId("text16");
    title.setFill(Color.WHITE);
    innerText.setTop(title);
    innerText.setAlignment(title, Pos.CENTER);
    innerText.setMargin(title, new Insets(10, 0, 0, 0));
    innerText.setMaxHeight(100);
    BorderPane pane = new BorderPane();
    pane.getChildren().add(innerText);
    root.setCenter(innerText);
    root.setMargin(pane, new Insets(2));

    // Style the view
    Text footer = new Text("Press SPACE BAR to continue");
    footer.setId("text12");
    footer.setFill(Color.WHITE);
    root.setBottom(footer);
    root.setAlignment(footer, Pos.CENTER);
    root.setMargin(footer, new Insets(0, 0, 20, 10));

    addEventHandlers();
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
            AZTrailView.stage.setScene(new HuntedView());
            break;

          case ENTER:
            AZTrailController.escape = false;
            AZTrailView.stage.setScene(new HuntedView());
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
}