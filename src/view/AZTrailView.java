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
    this.stage.setTitle("Arizona Trail");
    this.stage.setResizable(false);
    // stage.initStyle(StageStyle.UNDECORATED);
    this.stage.setScene(new GenericYesNoMenu(new Runnable() {
      @Override
      public void run() {
        AZTrailView.stage.setScene(new NogalesSplash());
      }
    },
    new Runnable() {
      @Override
      public void run() {
        GenericYesNoMenu.decor = new ImageView(new Image("file:view/assets/graphics/menuclerk.png"));
        GenericYesNoMenu.decor.setPreserveRatio(true);
        GenericYesNoMenu.decor.setFitWidth(100);
        GenericYesNoMenu.tile.setAlignment(GenericYesNoMenu.decor, Pos.CENTER);
        GenericYesNoMenu.tile.setTop(GenericYesNoMenu.decor);
        GenericYesNoMenu.root.setMargin(GenericYesNoMenu.decor, new Insets(5));
        GenericYesNoMenu.fadeOut();
        AZTrailView.stage.setScene(new NogalesSplash());
      }
    }, "body text goes here"));
    this.stage.show();
  }

  public static void escapePressed(boolean menu) {
    if (AZTrailController.escape) {
      if (!menu) {
        stage.setScene(new SplashMenu());
        AZTrailView.sounds.stop();
      } else {
        stage.setScene(new SplashMenu());
      }
    } else {
      AZTrailController.escape = true;
    }
  }
}
