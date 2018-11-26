package view;

import javafx.scene.control.Alert.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.application.*;
import javafx.scene.input.*;
import javafx.scene.image.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.beans.value.*;
import javafx.geometry.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.text.Font;
import javafx.event.*;
import java.util.*;
import java.io.*;
import javafx.scene.text.Text;
import javafx.scene.input.KeyCode;

import controller.*;

public class SizeUpView extends Scene {
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
    Text locDate = new Text("Independence\nMarch 1, 1848\n");
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
          case DIGIT1:
            updateInputText(1);
            break;

          case DIGIT2:
            updateInputText(2);
            break;

          case DIGIT3:
            updateInputText(3);
            break;

          case DIGIT4:
            updateInputText(4);
            break;

          case DIGIT5:
            updateInputText(5);
            break;

          case DIGIT6:
            updateInputText(6);
            break;

          case DIGIT7:
            updateInputText(7);
            break;

          case DIGIT8:
            updateInputText(8);
            break;

          case DIGIT9:
            updateInputText(9);
            break;

          case BACK_SPACE:
            if (input.length() >= 2) {
              input = input.substring(0, input.length() - 2);
              input += "_";
            }
            body.setText(contents + input);
            break;

          case ENTER:
            break;

          default:
            return;
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
}