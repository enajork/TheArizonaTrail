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

public class TopTenMenu extends Scene {
  private BorderPane root;

  /**
   * [TopTenMenu description]
   */
  public TopTenMenu() {
    this(new BorderPane());
  }

  /**
   * [TopTenMenu description]
   * @param root [description]
   */
  private TopTenMenu(BorderPane root) {
    super(root, AZTrailView.WIDTH, AZTrailView.HEIGHT, Color.BLACK);
    getStylesheets().add(AZTrailView.styleSheet);
    root.setStyle("-fx-background-color: black;");
    this.root = root;

    Text top = new Text("The Arizona Top Ten");
    top.setId("text12");
    top.setFill(Color.WHITE);

    Text left = new Text(AZTrailView.controller.getTopTenNames());
    left.setId("text12");
    left.setFill(Color.WHITE);

    Text right = new Text(AZTrailView.controller.getTopTenScores());
    right.setId("text12");
    right.setFill(Color.WHITE);

    // Style the view
    Text footer = new Text("Press SPACE BAR to continue");
    footer.setId("text12");
    footer.setFill(Color.WHITE);

    root.setTop(top);
    root.setAlignment(top, Pos.CENTER);
    root.setMargin(top, new Insets(20));
    root.setLeft(left);
    root.setAlignment(left, Pos.CENTER_LEFT);
    root.setMargin(left, new Insets(20, 120, 20, 120));
    root.setRight(right);
    root.setAlignment(right, Pos.CENTER_LEFT);
    root.setMargin(right, new Insets(20, 120, 20, 120));
    root.setBottom(footer);
    root.setAlignment(footer, Pos.CENTER);
    root.setMargin(footer, new Insets(20));

    addEventHandlers();
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
            AZTrailView.stage.setScene(new SplashMenu());
            break;

          case ENTER:
            AZTrailController.escape = false;
            AZTrailView.stage.setScene(new SplashMenu());
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
