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
import java.text.*;
import java.util.*;
import java.io.*;

import controller.*;
/**
 * @author Jordan Bridgewater, Jared Grady, Eric Najork, David Najork
 * @version     1.0
 * @since       1.0
 */

public class StoreWheelMenu extends Scene {
  private final Rectangle rect1 = new Rectangle(AZTrailView.WIDTH * 0.77 ,
    AZTrailView.HEIGHT * 0.016, Color.RED);
  private final Rectangle rect2 = new Rectangle(AZTrailView.WIDTH * 0.77 ,
    AZTrailView.HEIGHT * 0.016, Color.RED);
  private final int MAX_PARTS = 3;
  private final int INPUT_SIZE = 1;
  private final int NUM_OPTS = 9;
  private String cost = new DecimalFormat("'$'###,##0.00")
    .format(AZTrailView.controller.getCartOxen());
  private boolean warn = false;
  private BorderPane tile;
  private Text footer;
  private Text body;
  private String prompt = "\n\nHow many wagon wheels? ";
  private String input = "_";
  private int parts = 0;
  private String name;
  private boolean start;

  /**
   * Constructs a new store wheel menu view
   *
   * @param name  the store name
   * @param start boolean
   */
  public StoreWheelMenu(String name, boolean start) {
    this(new BorderPane(), name, start);
    getStylesheets().add(AZTrailView.styleSheet);
    AZTrailController.escape = false;
  }

  /**
   * Constructs a new store wheel menu view
   *
   * @param root  root of the scene graph
   *
   * @param name  the store name
   * @param start boolean
   */
  private StoreWheelMenu(BorderPane root, String name, boolean start) {
    super(root, AZTrailView.WIDTH, AZTrailView.HEIGHT, Color.BLACK);
    StoreMenu.wheels = 0;
    this.name = name;
    this.start = start;
    tile = new BorderPane();
    tile.setStyle("-fx-background-color: black;");
    BorderPane banner = new BorderPane();
    banner.setStyle("-fx-background-color: black;");
    BorderPane bodyTile = new BorderPane();
    bodyTile.setStyle("-fx-background-color: black;");
    bodyTile.setMaxHeight(100);
    BorderPane prices = new BorderPane();
    prices.setStyle("-fx-background-color: black;");
    root.setCenter(tile);
    tile.setTop(banner);
    tile.setCenter(bodyTile);
    tile.setAlignment(bodyTile, Pos.CENTER);
    tile.setMargin(bodyTile, new Insets(5, 5, 5, 40));
    bodyTile.setCenter(prices);
    bodyTile.setAlignment(prices, Pos.CENTER);
    banner.setTop(rect1);
    banner.setAlignment(rect1, Pos.CENTER);
    banner.setMargin(rect1, new Insets(5));
    banner.setBottom(rect2);
    banner.setAlignment(rect2, Pos.CENTER);
    banner.setMargin(rect2, new Insets(5));

    // Create the image;
    Image img = new Image("file:view/assets/graphics/menuclerk.png");
    ImageView decor = new ImageView(img);
    decor.setPreserveRatio(true);
    decor.setFitWidth(100);
    root.setLeft(decor);
    root.setMargin(decor, new Insets(100, 10, 10, 10));

    // Create the text for the menu options
    body = new Text(prompt + input);
    body.setId("text12");
    body.setFill(Color.WHITE);
    bodyTile.setBottom(body);

    // Create the image;
    Image img2 = new Image("file:view/assets/graphics/menuparts.png");
    ImageView decor2 = new ImageView(img2);
    decor2.setPreserveRatio(true);
    decor2.setFitWidth(100);
    tile.setBottom(decor2);
    tile.setAlignment(decor2, Pos.CENTER);

    // Create the text for the menu options
    Text header = new Text(name + " General Store\n"
      + AZTrailView.controller.getCurrentCity()
      + ", Arizona");
    header.setId("text12");
    header.setFill(Color.WHITE);
    banner.setCenter(header);

    // Create the text for the menu options
    Text top = new Text("It's a good idea to have a\nfew spare parts for your"
      + "\nwagon. Here are the prices:\n\n");
    top.setId("text12");
    top.setFill(Color.WHITE);
    bodyTile.setTop(top);

    // Create the text for the menu options
    Text left = new Text("wagon wheel\nwagon axle\nwagon tongue");
    left.setId("text12");
    left.setFill(Color.WHITE);
    prices.setLeft(left);

    // Create the text for the menu options
    Text right = new Text("- $10 each\n- $10 each\n- $10 each");
    right.setId("text12");
    right.setFill(Color.WHITE);
    prices.setRight(right);
    prices.setMaxWidth(AZTrailView.WIDTH / 1.75);

    // Create the text for the menu options
    footer = new Text("Bill so far: " + new DecimalFormat("'$'###,##0.00")
      .format(AZTrailView.controller.getCartTotal()));
    footer.setId("text12");
    footer.setFill(Color.WHITE);

    root.setStyle("-fx-background-color: black;");
    root.setCenter(tile);
    root.setBottom(footer);
    root.setAlignment(footer, Pos.CENTER);
    root.setMargin(footer, new Insets(5));

    addEventHandlers();
  }

  /**
   * Adds event handlers to handle keyboard actions from the user
   */
  private void addEventHandlers() {
    this.setOnKeyPressed(new EventHandler<KeyEvent>() {
      @Override
      public void handle(KeyEvent event) {
        switch (event.getCode()) {
          case SPACE:
            AZTrailController.escape = false;
            if (warn) {
              AZTrailView.stage.setScene(new StoreWheelMenu(name, start));
            }
            break;
          case BACK_SPACE:
            AZTrailController.escape = false;
            if (warn) {
              return;
            }
            if (input.length() >= 2) {
              input = input.substring(0, input.length() - 2);
              input += "_";
            }
            body.setText(prompt + input);
            break;

          case ENTER:
            AZTrailController.escape = false;
            if (input.length() == 1) {
              return;
            }
            if (warn) {
              AZTrailView.stage.setScene(new StoreWheelMenu(name, start));
            } else {
              int i = Integer.parseInt(input.replace("_", ""));
              if (i >= 0 && i <= MAX_PARTS) {
                AZTrailView.stage.setScene(getNextView(i));
              }
              warn = true;
              body.setText("\n\nYour wagon may only carry 3\nwagon wheels.");
              footer.setText("Press SPACE BAR to continue");
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
            if (event.getText().length() > 0
                && Character.isDigit(event.getText().charAt(0))) {
              updateInputText(Integer.parseInt(event.getText()));
            }
        }
      }
    });
  }

  /**
   * updates the display when the user types in a selection the keyboard
   * @param num the value inserted by the user
   */
  private void updateInputText(int num) {
    if (warn) {
      return;
    }
    if (input.length() <= INPUT_SIZE && num >= 0 && num <= NUM_OPTS) {
      input = input.substring(0, input.length() - 1);
      input += num + "_";
      body.setText(prompt + input);
    }
  }

  /**
   * determines the next view to be scene by the player based on their choice
   * @param  choice integer choice number
   * @return        the next view to be staged
   */
  private Scene getNextView(int choice) {
    StoreMenu.wheels = choice;
    parts += choice;
    return new StoreAxlesMenu(parts, name, start);
  }
}
