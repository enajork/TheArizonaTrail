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

import controller.*;

public class AZTrailView extends Application {
  private final int HEIGHT = 408;
  private final int WIDTH = 650;


  @Override
  public void start(Stage stage) throws Exception {
    stage.setTitle("Arizona Trail");
    stage.setResizable(false);
    stage.initStyle(StageStyle.UNDECORATED);

    // Show the splash menu
    stage.setScene(splashMenu(stage));
    stage.show();
  }

  private Scene splashMenu(Stage stage) {
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

    // Set styles and add images
    root.setAlignment(title, Pos.CENTER);
    root.setAlignment(accent1, Pos.CENTER);
    root.setAlignment(accent2, Pos.CENTER);
    root.setStyle("-fx-background-color: black;");
    root.setTop(title);
    root.setCenter(tile);

    return new Scene(root, WIDTH, HEIGHT);
  }

  private ImageView menuAccent() {
    return new ImageView(new Image("file:view/assets/menuaccent.png",
      620, 40, false, false));
  }

  private void profMenu(int choice) {
    // banker from ***Boston
    // carpenter from ***Ohio
    // farmer from ***Illinois
    // find out the differences between these choices
  }

  private void leaderNameMenu(String name) {
    //
  }

  private void partyNamesMenu(String[] names) {
    //
  }

  private void monthMenu(int choice) {
    // March
    // April
    // May
    // June
    // July
    // Ask for advice
  }

  private void storeMenu(int choice) {
    //
  }

  private void storeSubMenu(int quantity) {
    //
  }

}
