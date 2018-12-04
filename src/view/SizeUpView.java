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
import java.text.*;
import java.util.*;
import java.io.*;

import controller.*;

public class SizeUpView extends Scene {
  private final int MIN_INPUT = 1;
  private final int MAX_INPUT = 6;
  private Text body;
  private String contents = "You may:\n\n"
    + "  1. Continue on the trail\n"
    + "  2. Check supplies\n"
    + "  3. Look at map\n"
    + "  4. Change pace\n"
    + "  5. Hunt for food\n"
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
    AZTrailView.sounds.startBackgroundSFX();
    AZTrailView.sounds.startThemeLoop();
    // Create the text for the menu options
    body = new Text(contents + input);
    body.setId("text12");
    body.setFill(Color.WHITE);

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
    partyStats.setMargin(stats, new Insets(10, 10, 10, 10));

    BorderPane tile = new BorderPane();
    tile.setTop(partyStats);
    tile.setCenter(body);
    tile.setAlignment(body, Pos.CENTER_LEFT);
    tile.setMargin(body, new Insets(20));

    root.setStyle("-fx-background-color: black;");
    root.setAlignment(locDate, Pos.CENTER);
    root.setTop(locDate);
    root.setMargin(locDate, new Insets(10, 0, 0, 0));
    root.setCenter(tile);
    root.setAlignment(locDate, Pos.CENTER);
    root.setMargin(tile, new Insets(20));

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
            if (input.length() == 2) {
              AZTrailView.stage.setScene(getNextView(Integer.parseInt(input
                .substring(0, 1))));
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
    if (input.length() == 1 && (num >= MIN_INPUT && num <= MAX_INPUT)) {
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
    if (choice < MIN_INPUT || choice > MAX_INPUT) {
      throw new IllegalStateException();
    }
    switch (choice) {
      case 1:
        // Travel the trail
        AZTrailView.travel.updatePace();
        return AZTrailView.travel;
      case 2:
        return new GenericInfoMenu(
        new Runnable() {
          @Override
          public void run() {
            AZTrailView.stage.setScene(new SizeUpView());
          }
        },
        new String[]{
          "Your Supplies\n\n"
          + " 1. Money - " + new DecimalFormat("'$'###,##0.00")
            .format(AZTrailView.controller.getMoney())
          + "\n 2. Oxen - " + AZTrailView.controller.getOxen()
          + "\n 3. Clothes - " + AZTrailView.controller.getClothes()
          + "\n 4. Blankets - " + AZTrailView.controller.getClothes()
          + "\n 5. Bullets - " + AZTrailView.controller.getBullets()
          + "\n 6. Wheels - " + AZTrailView.controller.getWheels()
          + "\n 7. Axles - " + AZTrailView.controller.getAxles()
          + "\n 8. Tongues - " + AZTrailView.controller.getTongues()
          + "\n 9. Food - " + AZTrailView.controller.getFood() + " lbs"
          + "\n 10. Water - " + AZTrailView.controller.getWater() + " gallons"
        }
      );

      case 3:
        // Look at map
        return new MapView();
      case 4:
        // Change pace
        return new PaceMenu();
      case 5:
        // Talk to people
        return new HuntingSplash();
    }
    // return;
    return null;
  }
}
