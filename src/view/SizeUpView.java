package view;

import javafx.scene.control.Alert.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.media.*;
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

public class SizeUpView extends Scene {
  private final int NUM_OPTS = 9;
  private Text body;
  private String contents = "You may:\n\n\t\t"
    + "1. Continue on the trail\n\t\t"
    + "2. Check Supplies\n\t\t"
    + "3. Look at map\n\t\t"
    + "4. Change pace\n\t\t"
    + "5. Change food rations\n\t\t"
    + "6. Stop to rest\n\t\t"
    + "7. Attempt to trade\n\t\t"
    + "8. Talk to people\n\t\t"
    + "9. Buy supplies\n\n"
    + "What is your choice? ";

  private String input = "_";

  /**
   * [SizeUpView description]
   */
  public SizeUpView() {
    this(new BorderPane());
    getStylesheets().add(AZTrailView.styleSheet);
  }

  /**
   * [SplashMenu description]
   * @param root [description]
   */
  private SizeUpView(BorderPane root) {
    super(root, AZTrailView.WIDTH, AZTrailView.HEIGHT);
    // Create the text for the menu options
    body = new Text(contents + input);
    body.setId("text12");
    body.setFill(Color.WHITE);

    // TODO get date and location from controller
    Text locDate = new Text(AZTrailView.controller.getCurrentCity()
      + "\n" + AZTrailView.controller.getDateStr() + "\n");
    locDate.setFill(Color.WHITE);
    locDate.setId("text12");

    HBox partyStats = new HBox();
    // TODO get partyStats from controller
    Text stats = new Text("Weather: cold\nHealth: good\nPace: steady\nRations: filling");
    stats.setId("text12");
    partyStats.setStyle("-fx-background-color: white;");
    partyStats.getChildren().add(stats);

    BorderPane tile = new BorderPane();
    tile.setTop(partyStats);
    tile.setCenter(body);
    tile.setAlignment(body, Pos.CENTER_LEFT);

    root.setStyle("-fx-background-color: black;");
    root.setAlignment(locDate, Pos.CENTER);
    root.setTop(locDate);
    root.setCenter(tile);

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
            AZTrailView.escape = false;
            if (input.length() >= 2) {
              input = input.substring(0, input.length() - 2);
              input += "_";
            }
            body.setText(contents + input);
            break;

          case ENTER:
            AZTrailView.escape = false;
            if (input.length() == 2) {
              AZTrailView.stage.setScene(getNextView(Integer.parseInt(input
                .substring(0, 1))));
            }
            break;

          case ESCAPE:
            if (AZTrailView.escape) {
              AZTrailView.stage.setScene(new SplashMenu());
            } else {
              AZTrailView.escape = true;
            }
            break;

          default:
            AZTrailView.escape = false;
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
    if (input.length() == 1) {
      input = input.substring(0, input.length() - 1);
      input += num + "_";
      body.setText(contents + input);
    }
  }

  /**
   * [getNextView description]
   * @param  choice [description]
   * @return        [description]
   */
  private Scene getNextView(int choice) {
    if (choice < 1 || choice > NUM_OPTS) {
      throw new IllegalStateException();
    }
    switch (choice) {
      case 1:
        // Travel the trail
        return new TrailTravelView();
    }
    // return;
    return null;
  }
}
