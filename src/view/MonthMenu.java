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
import model.*;

public class MonthMenu extends Scene {
  private final int NUM_OPTS = 6;
  private Text body;
  private String contents = "It is 1848. Your jumping off\nplace for Utah is"
    + " Blank,\nArizona. You must decide which\nmonth to leave Blank.\n\n  "
    + "1. March\n  "
    + "2. April\n  "
    + "3. May\n  "
    + "4. June\n  "
    + "5. July\n  "
    + "6. Ask for advice\n\n"
    + "What is your choice? ";
  private String input = "_";

  /**
   * [MonthMenu description]
   */
  public MonthMenu() {
    this(new BorderPane());
    getStylesheets().add(AZTrailView.styleSheet);
  }

  /**
   * [SplashMenu description]
   * @param root [description]
   */
  private MonthMenu(BorderPane root) {
    super(root, AZTrailView.WIDTH, AZTrailView.HEIGHT, Color.BLACK);

    // Create the text for the menu options
    body = new Text(contents + input);
    body.setId("text12");
    body.setFill(Color.WHITE);

    BorderPane tile = new BorderPane();
    tile.setStyle("-fx-background-color: black;");

    // Create the first accent
    ImageView accent1 = menuAccent();
    tile.setTop(accent1);
    tile.setCenter(body);

    // Create the second accent
    ImageView accent2 = menuAccent();
    tile.setBottom(accent2);
    tile.setMargin(accent2, new Insets(0, 0, 40, 0));

    // Style the view
    root.setAlignment(accent1, Pos.CENTER);
    root.setAlignment(accent2, Pos.CENTER);
    root.setStyle("-fx-background-color: black;");
    root.setCenter(tile);


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
    if (input.length() == 1 && num >= 1 && num <= NUM_OPTS) {
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
        AZTrailView.controller.setMonth("March");
        return shopView();
      case 2:
        AZTrailView.controller.setMonth("April");
        return shopView();
      case 3:
        AZTrailView.controller.setMonth("May");
        return shopView();
      case 4:
        AZTrailView.controller.setMonth("June");
        return shopView();
      case 5:
        AZTrailView.controller.setMonth("July");
        return shopView();
      case 6:
        return new GenericInfoMenu(new MonthMenu(), new String[]{
          "You attend a public meeting held\n"
          + "for \"folks with Arizona -\nUtah fever.\" You're told:\n\n"
          + "If you leave too early, there\nwon't be any grass for your\n"
          + "oxen to eat. If you leave too\nlate, you may not get to Utah\n"
          + "before winter comes. If you\nleave at just the right time,\n"
          + "there will be green grass and\nthe weather will still be cool."
        }, true);
    }
    return null;
  }

  private Scene shopView() {
    return new GenericInfoMenu(new ClerkInfoMenu(/*new StoreMenu()*/null,
      "Hello, I'm Matt. So you're going\nto Utah! "
      + "I can fix you up with\nwhat you need:",
      new String[]{"- a team of oxen to pull\nyour wagon\n\n- "
      + "clothing for both\nsummer and winter", "- plenty of food for the"
      + "\ntrip\n\n- ammunition for your\nrifles\n\n- spare parts for your"
      + "\nwagon", "- lots of blankets to\nstay warm\n\n- enough water for"
      + " you\nand your oxen"}),
      new String[]{ "Before leaving Blank you\nshould buy equipment and"
      + "\nsupplies. You have $"
      + (new DecimalFormat("#.00#").format(AZTrailView.controller.getMoney()))
      + " in\ncash, but you don't have to\nspend it all now.",
      "You can buy whatever you need at\nMatt's General Store."
    }, true);
  }
}