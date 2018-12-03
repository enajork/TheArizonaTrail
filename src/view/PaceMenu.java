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

public class PaceMenu extends Scene {
  private final int NUM_OPTS = 3;
  private String contents =
    "Set the pace at which\nyour party travels.\nYour choices are:\n\n"
    + "  1. A steady pace\n"
    + "  2. A strenuous pace\n"
    + "  3. A grueling pace\n\n"
    + "What is your choice? ";
  private String input = "_";
  private Text body;

  /**
   * [OptionsMenu description]
   */
  public PaceMenu() {
    this(new BorderPane());
    getStylesheets().add(AZTrailView.styleSheet);
  }

  /**
   * [OptionsMenu description]
   * @param root [description]
   */
  private PaceMenu(BorderPane root) {
    super(root, AZTrailView.WIDTH, AZTrailView.HEIGHT, Color.BLACK);

    // Create the title image;
    Image img = new Image("file:view/assets/graphics/aztrail_splashtext.png");
    ImageView title = new ImageView(img);

    // Create the text for the menu options
    body = new Text(contents + input);
    body.setId("text12");
    body.setFill(Color.WHITE);

    AnchorPane anchor = new AnchorPane(body);
    anchor.setLeftAnchor(body, 160.0);
    anchor.setTopAnchor(body, 80.0);

    // Create the first accent
    ImageView accent1 = menuAccent();

    // Create the second accent
    ImageView accent2 = menuAccent();

    // Style the view
    root.setStyle("-fx-background-color: black;");
    root.setTop(accent1);
    root.setCenter(anchor);
    root.setBottom(accent2);
    root.setAlignment(anchor, Pos.CENTER);
    root.setAlignment(accent1, Pos.CENTER);
    root.setAlignment(accent2, Pos.CENTER);

    addEventHandlers();
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
          case BACK_SPACE:
            AZTrailController.escape = false;
            if (input.length() >= 2) {
              input = input.substring(0, input.length() - 2);
              input += "_";
            }
            body.setText(contents + input);
            break;

          case ENTER:
            // call pace setting controller method here
            AZTrailView.controller.changePace(Integer.parseInt(
              input.substring(0, 1)));
            AZTrailView.stage.setScene(new SizeUpView());
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
            if (event.getText().length() > 0
                && Character.isDigit(event.getText().charAt(0))) {
              updateInputText(Integer.parseInt(event.getText()));
            }
        }
      }
    });
  }

  /**
   * [updateInputText description]
   * @param num [description]
   */
  private void updateInputText(int num) {
    if (input.length() == 1 && num >= 1 && num <= NUM_OPTS) {
      input = input.substring(0, input.length() - 1);
      input += num + "_";
      body.setText(contents + input);
    }
  }
}
