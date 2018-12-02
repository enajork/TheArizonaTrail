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
import javafx.collections.*;
import javafx.scene.*;
import javafx.beans.value.*;
import javafx.geometry.*;
import javafx.stage.*;
import javafx.event.*;
import java.util.*;
import java.io.*;

import controller.*;
import model.*;

public class AZTrailView extends Application {
  protected static AZTrailController controller;
  protected static Sounds sounds;

  protected static final int HEIGHT = 408;
  protected static final int WIDTH = 650;
  protected static String styleSheet;
  protected static Stage stage;
  protected static int time = 0;

  /**
   * Main entry point for the view
   * @param  stage     [description]
   * @throws Exception [description]
   */
  @Override
  public void start(Stage stage) throws Exception {
    styleSheet = getClass().getResource("assets/font/style.css").toExternalForm();
    this.stage = stage;
    controller = new AZTrailController();
    sounds = new Sounds();
    controller.loadGame();
    controller.loadTopTen();
    this.stage.setTitle("Arizona Trail");
    this.stage.setResizable(false);
    // stage.initStyle(StageStyle.UNDECORATED);

    this.stage.setScene(new TrailTravelView());
    this.stage.show();
  }

  public static void escapePressed(boolean saveAllowed) {
    if (AZTrailController.escape) {
      if (saveAllowed) {
        stage.setScene(new GenericYesNoMenu(
          new Runnable() {
            @Override
            public void run() {
              AZTrailView.stage.setScene(new GenericInfoMenu(
                new Runnable() {
                  @Override
                  public void run() {
                    AZTrailView.stage.setScene(new SplashMenu());
                  }
                },
                new String[]{
                  "Your game has been saved."
                },
                true,
                46
              ));
              AZTrailView.controller.saveGame();
            }
          },
          new Runnable() {
            @Override
            public void run() {
              AZTrailView.stage.setScene(new SplashMenu());
            }
          },
          "Do you want to save your game?",
          "Leaving the Arizona Trail",
          "",
          true
        ));
        AZTrailView.sounds.stop();
      } else {
        stage.setScene(new SplashMenu());
      }
    } else {
      AZTrailController.escape = true;
    }
  }
}
