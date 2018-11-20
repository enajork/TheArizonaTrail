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

public class SplashMenu extends Scene {
  public SplashMenu() {
    this(new BorderPane());
  }

  public SplashMenu(BorderPane root) {
    super(root, AZTrailView.WIDTH, AZTrailView.HEIGHT);

    // Create the tile image;
    Image img = new Image("file:view/assets/aztrail_splashtext.png");
    ImageView title = new ImageView(img);

    // Create the text for the menu options
    Text body = new Text("You can:\n\n\t"
      + "1. Travel the Trail\n\t"
      + "2. Learn about the trail\n\t"
      + "3. See the Arizona Top Ten\n\t"
      + "4. Turn sound off\n\t"
      + "5. Choose Management Options\n\t"
      + "6. End\n\n"
      + "What is your choice? _");
    body.setFont(Font.font("file:view/assets/here-lies-mecc.tff", 20));
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
            System.out.println(1);
            break;

          case DIGIT2:
            System.out.println(2);
            break;

          case DIGIT3:
            System.out.println(3);
            break;

          case DIGIT4:
            System.out.println(4);
            break;

          case DIGIT5:
            System.out.println(5);
            break;

          case DIGIT6:
            System.out.println(6);
            break;

          default:
            return;
        }
      }
    });
  }
}
