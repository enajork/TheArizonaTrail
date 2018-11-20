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

public class SplashMenuView extends Scene {
  Text body;
  String contents = "You can:\n\n\t"
    + "1. Travel the Trail\n\t"
    + "2. Learn about the trail\n\t"
    + "3. See the Arizona Top Ten\n\t"
    + "4. Turn sound off\n\t"
    + "5. Choose Management Options\n\t"
    + "6. End\n\n"
    + "What is your choice? ";
  String input = "_";

  public SplashMenuView() {
    this(new BorderPane());
  }

  public SplashMenuView(BorderPane root) {
    super(root, AZTrailView.WIDTH, AZTrailView.HEIGHT);

    // Create the tile image;
    Image img = new Image("file:view/assets/aztrail_splashtext.png");
    ImageView title = new ImageView(img);

    // Create the text for the menu options
    body = new Text(contents + input);
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

    // Style the view
    root.setAlignment(title, Pos.CENTER);
    root.setAlignment(accent1, Pos.CENTER);
    root.setAlignment(accent2, Pos.CENTER);
    root.setStyle("-fx-background-color: black;");
    root.setTop(title);
    root.setCenter(tile);

    addEventHandlers();
  }

  private ImageView menuAccent() {
    return new ImageView(new Image("file:view/assets/menuaccent.png",
      620, 40, false, false));
  }

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

          case BACK_SPACE:
            if (input.length() >= 2) {
              input = input.substring(0, input.length() - 1);
              input = input.substring(0, input.length() - 1);
              input += "_";
            }
            body.setText(contents + input);
            break;

          case ENTER:
            System.out.println(input.substring(0, input.length() - 1));
            break;

          default:
            return;
        }
      }
    });
  }

  private void updateInputText(int num) {
    if (input.length() == 1) {
      input = input.substring(0, input.length() - 1);
      input += num + "_";
      body.setText(contents + input);
    }
  }
}
