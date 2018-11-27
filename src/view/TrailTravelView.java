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
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.animation.Timeline;
import javafx.util.Duration;
import javafx.animation.KeyFrame;
import java.lang.Thread;

import controller.*;

public class TrailTravelView extends Scene {
  private BorderPane root;
  private GraphicsContext gc;
  Timeline timeline;

  private Image imgs[] = {
    new Image("file:view/assets/graphics/ox1.png"),
    new Image("file:view/assets/graphics/ox2.png"),
    new Image("file:view/assets/graphics/ox3.png"),
    new Image("file:view/assets/graphics/ox4.png"),
    new Image("file:view/assets/graphics/ox5.png"),
  };

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
    this.root = root;

    Text cont = new Text("Press SPACEBAR to continue...\n");
    cont.setId("text12");
    cont.setFill(Color.WHITE);

    BorderPane info = infoPane();
    root.setStyle("-fx-background-color: black;");
    root.setAlignment(cont, Pos.CENTER);

    root.setTop(travelGraphics());
    root.setCenter(info);
    root.setBottom(cont);
    addEventHandlers();
  }

  private BorderPane infoPane() {
    BorderPane info = new BorderPane();
    // TODO get partyStats from controller
    HBox statsArea = new HBox();
    Text stats = new Text("Weather: cold\nHealth: good\nFood: 0 Pounds\nNext landmark: 44 miles\nMiles Traveled: 0 miles");
    stats.setId("text12");
    statsArea.getChildren().add(stats);
    statsArea.setStyle("-fx-background-color: white;");

    info.setAlignment(statsArea, Pos.CENTER);
    info.setCenter(statsArea);
    return info;
  }

  private Canvas travelGraphics() {
    final Canvas canvas = new Canvas(650, 150);
    this.gc = canvas.getGraphicsContext2D();

    gc.drawImage(new Image("file:view/assets/graphics/mountain.png"), 0, 0, 1000, 50);
    gc.drawImage(new Image("file:view/assets/graphics/grass.png"), 0, 100, 1000, 50);
    //gc.drawImage(new Image("file:view/assets/graphics/ox1.png"), 400, 50, 160, 70);

    return canvas;
  }

  /**
   * [addEventHandlers description]
   */
  private void addEventHandlers() {
    this.setOnKeyPressed(new EventHandler<KeyEvent>() {
      @Override
      public void handle(KeyEvent event) {
        switch (event.getCode()) {
          case SPACE:
            walkTrail();
            AZTrailView.escape = false;
            break;

          case ENTER:
            break;
        }
      }
    });
  }

  private void walkTrail() {
      this.timeline = new Timeline(
        new KeyFrame(Duration.millis(200),
        new AnimationHandler()));

      this.timeline.setCycleCount(12);
      this.timeline.play();
  }

  private class AnimationHandler implements EventHandler<ActionEvent> {
    int tick = 0;
    int curFrame = 0;

    @Override
    public void handle(ActionEvent event) {
      tick++;
      gc.fillRect(400, 50, 160, 70);
      gc.drawImage(new Image("file:view/assets/graphics/grass.png"), 0, 100, 1000, 50);
      gc.drawImage(imgs[curFrame], 400, 50, 160, 70);

      if (tick > 200) {
        timeline.stop();
      }
      curFrame++;

      if (curFrame == imgs.length) {
        curFrame = 0;
      }
    }
  }
}