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

public class TrailTravelView extends Scene {

  /**
   * [TrailTravelView description]
   */
  public TrailTravelView() {
    this(new BorderPane());
    getStylesheets().add(AZTrailView.styleSheet);
  }

  /**
   * [TrailTravelView description]
   * @param root [description]
   */
  private TrailTravelView(BorderPane root) {
    super(root, AZTrailView.WIDTH, AZTrailView.HEIGHT);

    Text cont = new Text("Press SPACEBAR to continue...");
    cont.setId("text12");
    cont.setFill(Color.WHITE);

    BorderPane info = infoPane();
    root.setStyle("-fx-background-color: black;");
    root.setAlignment(cont, Pos.CENTER);
    //root.setTop();
    root.setCenter(info);
    root.setBottom(cont);

  }

  private BorderPane infoPane() {
    BorderPane info = new BorderPane();
    // TODO get partyStats from controller
    HBox statsArea = new HBox();
    Text stats = new Text("Weather: cold\nHealth: good\nFood: 0 Pounds\nNext landmark: 44 miles\nMiles Traveled: 0 miles");

    statsArea.getChildren().add(stats);
    statsArea.setStyle("-fx-background-color: white;");

    info.setAlignment(statsArea, Pos.CENTER);
    info.setBottom(statsArea);
    return info;
  }

  /**
   * [addEventHandlers description]
   */
  private void addEventHandlers() {
    this.setOnKeyPressed(new EventHandler<KeyEvent>() {
      @Override
      public void handle(KeyEvent event) {
        switch (event.getCode()) {
          case ESCAPE:
            if (AZTrailView.escape) {
              System.exit(0);
              // AZTrailView.stage.setScene(new SplashMenu());
            } else {
              AZTrailView.escape = true;
            }
            break;

          default:
            AZTrailView.escape = false;
            return;
        }
      }
    });
  }
}
