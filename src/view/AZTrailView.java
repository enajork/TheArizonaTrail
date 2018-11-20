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
  protected static AZTrailController controller;
  protected static Stage stage;
  protected static final int HEIGHT = 408;
  protected static final int WIDTH = 650;

  @Override
  public void start(Stage stage) throws Exception {
    this.stage = stage;
    controller = new AZTrailController();
    this.stage.setTitle("Arizona Trail");
    this.stage.setResizable(false);
    //stage.initStyle(StageStyle.UNDECORATED);

    // Show the splash menu
    Scene splashMenuView = new SplashMenuView();
    splashMenuView.getStylesheets().add(AZTrailView.class
      .getResource("assets/style.css").toExternalForm());

    this.stage.setScene(splashMenuView);
    this.stage.show();
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
