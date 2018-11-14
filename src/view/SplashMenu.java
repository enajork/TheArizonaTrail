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

public class SplashMenu {
  public static Scene create(Stage stage) {
    BorderPane root = new BorderPane();

    // Create the tile image;
    Image img = new Image("file:view/assets/aztrail_splashtext.png");
    ImageView title = new ImageView(img);

    // Create the text for the menu options
    Text body = new Text("You can:\n\n\t1. Travel the Trail\n\t2. Learn about the trail\n\t3. See the Arizona Top Ten\n\t4. Turn sound off\n\t5. Choose Management Options\n\t6. End\n\nWhat is your choice?");
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

    Scene scene = new Scene(root, AZTrailView.WIDTH, AZTrailView.HEIGHT);
    addEventHandlers(scene);
    return scene;
  }

  private static ImageView menuAccent() {
    return new ImageView(new Image("file:view/assets/menuaccent.png",
      620, 40, false, false));
  }

  private static void addEventHandlers(Scene scene) {
    scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
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