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
  protected static AZTrailModel model;
  protected static Sounds sounds;

  protected static final int HEIGHT = 408;
  protected static final int WIDTH = 650;
  protected static String styleSheet;
  protected static Stage stage;

  protected static boolean huntedMode = false;
  protected static boolean sound = true;
  protected static boolean escape = false;

  /**
   * Main entry point for the view
   * @param  stage     [description]
   * @throws Exception [description]
   */
  @Override
  public void start(Stage stage) throws Exception {
    styleSheet = getClass().getResource("assets/font/style.css").toExternalForm();
    this.stage = stage;
    model = new AZTrailModel();
    controller = new AZTrailController(model);
    sounds = new Sounds();
    this.stage.setTitle("Arizona Trail");
    this.stage.setResizable(false);
    //stage.initStyle(StageStyle.UNDECORATED);
    this.stage.setScene(new SplashMenu());
    this.stage.show();
  }
}
