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

public class PartyLeaderMenu extends Scene {
  private String contents = "What is the first name of the\nwagon leader? ";
  private final int CHAR_LIMIT = 12;
  private String input = "_";
  private Text body;

  /**
   * [PartyLeaderMenu description]
   */
  public PartyLeaderMenu() {
    this(new BorderPane());
    getStylesheets().add(AZTrailView.styleSheet);
  }

  /**
   * [PartyLeaderMenu description]
   * @param root [description]
   */
  private PartyLeaderMenu(BorderPane root) {
    super(root, AZTrailView.WIDTH, AZTrailView.HEIGHT, Color.BLACK);

    // Create the tile image;
    FlowPane flow = new FlowPane(Orientation.VERTICAL);
    flow.setStyle("-fx-background-color: black;");
    flow.setColumnHalignment(HPos.CENTER);
    Image img = new Image("file:view/assets/graphics/partymenu.png");
    ImageView decor = new ImageView(img);
    decor.setPreserveRatio(true);
    decor.setFitWidth(600);

    // Create the text for the menu options
    body = new Text(contents + input);
    body.setId("text12");
    body.setFill(Color.WHITE);

    flow.getChildren().add(decor);
    flow.getChildren().add(body);

    flow.setMargin(decor, new Insets(0, 0, 10, 0));
    flow.setMargin(body, new Insets(0, 0, 0, 0));

    root.setStyle("-fx-background-color: black;");
    root.setCenter(flow);

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
          case BACK_SPACE:
            AZTrailController.escape = false;
            if (input.length() >= 2) {
              input = input.substring(0, input.length() - 2);
              input += "_";
            }
            body.setText(contents + input);
            break;

          case ENTER:
            AZTrailController.escape = false;
            if (input.length() > 1) {
              AZTrailView.controller.setName(0, input.substring(0,
                input.length() - 1));
              AZTrailView.stage.setScene(new PartyNamesMenu());
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
            updateInputText(event);
        }
      }
    });
  }

  /**
   * [updateInputText description]
   * @param num [description]
   */
  private void updateInputText(KeyEvent event) {
    String letter = event.getText();
    if (letter.length() != 1) {
      return;
    }
    if (!event.isShiftDown()) {
      letter = letter.toLowerCase();
    }
    if (input.length() <= CHAR_LIMIT && Character.isLetter(letter.charAt(0))) {
      input = input.substring(0, input.length() - 1);
      input += letter + "_";
      body.setText(contents + input);
    }
  }
}
