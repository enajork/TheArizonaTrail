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
import java.net.*;
import java.io.*;
import javafx.scene.text.Text;

import controller.*;
import model.*;

public class AZTrailView extends Application {
  protected static AZTrailController controller;
  protected static AZTrailModel model;
  protected static Stage stage;
  protected static final int HEIGHT = 408;
  protected static final int WIDTH = 650;
  protected static String styleSheet;

  /**
   * Main entry point for the view
   * @param  stage     [description]
   * @throws Exception [description]
   */
  @Override
  public void start(Stage stage) throws Exception {
    try {
      URL url = new File("view/assets/style.css").toURI().toURL();
      styleSheet = url.toExternalForm();
    } catch (MalformedURLException e) {
      System.err.println("Fatal error! Style sheet not loaded.");
      System.exit(1);
    }
    this.stage = stage;
    model = new AZTrailModel();
    controller = new AZTrailController(model);
    this.stage.setTitle("Arizona Trail");
    this.stage.setResizable(false);
    //stage.initStyle(StageStyle.UNDECORATED);

    // Show the splash menu
    Scene SplashMenu = new SplashMenu();

    this.stage.setScene(SplashMenu);
    this.stage.show();
  }

  /**
   * [profMenu description]
   * @param choice [description]
   */
  private void profMenu(int choice) {
    // banker from ***Boston
    // carpenter from ***Ohio
    // farmer from ***Illinois
    // find out the differences between these choices
  }

  /**
   * [leaderNameMenu description]
   * @param name [description]
   */
  private void leaderNameMenu(String name) {
    //
  }

  /**
   * [partyNamesMenu description]
   * @param names [description]
   */
  private void partyNamesMenu(String[] names) {
    //
  }

  /**
   * [monthMenu description]
   * @param choice [description]
   */
  private void monthMenu(int choice) {
    // March
    // April
    // May
    // June
    // July
    // Ask for advice
  }

  /**
   * [storeMenu description]
   * @param choice [description]
   */
  private void storeMenu(int choice) {
    //
  }

  /**
   * [storeSubMenu description]
   * @param quantity [description]
   */
  private void storeSubMenu(int quantity) {
    //
  }

}
