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
import javafx.event.*;
import java.util.*;
import java.io.*;

import controller.*;

public class AZTrailView extends Application {
  private final int HEIGHT = 408;
  private final int WIDTH = 650;

  @Override
  public void start(Stage stage) throws Exception {
    TilePane root = new TilePane();
    root.setTileAlignment(Pos.CENTER);
    root.setStyle("-fx-background-color: black;");
    root.setPrefRows(6);

    Scene splash = new Scene(root, WIDTH, HEIGHT);
    stage.setTitle("Arizona Trail");
    stage.setResizable(false);
    stage.initStyle(StageStyle.UNDECORATED);
    stage.setScene(splash);
    stage.show();
  }

  public void splashMenu(int choice) {
    // Travel the trail
    // Learn about the trail
    // See the Arizona top 10
    // turn sound off
    // choose management option
    // end
  }

  public void profMenu(int choice) {
    // banker from ***Boston
    // carpenter from ***Ohio
    // farmer from ***Illinois
    // find out the differences between these choices
  }

  public void leaderNameMenu(String name) {
    //
  }

  public void partyNamesMenu(String[] names) {
    //
  }

  public void monthMenu(int choice) {
    // March
    // April
    // May
    // June
    // July
    // Ask for advice
  }

  public void storeMenu(int choice) {
    //
  }

  public void storeSubMenu(int quantity) {
    //
  }

}
