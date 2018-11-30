package view;

import javafx.animation.Animation;
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
import javafx.util.Duration;

import controller.*;

public class TrailTravelView extends Scene {
  private BorderPane root;
  private GraphicsContext gc;
  private OxenSprite ox;
  private Text stats;

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

    // Create the text for spacebar continue message
    Text cont = new Text("Press SPACEBAR to continue...\n");
    cont.setId("text12");
    cont.setFill(Color.WHITE);

    // Create the pane containing the current stats
    BorderPane info = infoPane();
    root.setStyle("-fx-background-color: black;");
    root.setAlignment(cont, Pos.CENTER);

    // Add them to the scene
    root.setTop(travelGraphics());
    root.setCenter(info);
    root.setBottom(cont);
    addEventHandlers();
  }

  private BorderPane infoPane() {
    BorderPane info = new BorderPane();

    // Create the sizeup box
    HBox sizeUpBox = new HBox();
    Text sizeUp = new Text("Press ENTER to size up the situation");
    sizeUp.setId("text12");
    sizeUp.setFill(Color.WHITE);
    sizeUpBox.getChildren().add(sizeUp);
    sizeUpBox.setStyle("-fx-background-color: black;");

    // create the stats area
    HBox statsArea = new HBox();
    this.stats = new Text(buildStatsString());
    this.stats.setId("text12");
    statsArea.getChildren().add(stats);
    statsArea.setStyle("-fx-background-color: white;");
    statsArea.setMargin(stats, new Insets(0.5));

    // add them to the scene
    info.setAlignment(sizeUpBox, Pos.CENTER);
    info.setTop(sizeUpBox);
    info.setAlignment(statsArea, Pos.CENTER);
    info.setCenter(statsArea);
    return info;
  }

  private StackPane travelGraphics() {
    StackPane pane = new StackPane();
    final Canvas canvas = new Canvas(650, 150);
    this.gc = canvas.getGraphicsContext2D();

    gc.drawImage(new Image("file:view/assets/graphics/mountain.png"), 0, 0, 1000, 50);
    gc.drawImage(new Image("file:view/assets/graphics/sand.png"), 0, 100, 1000, 50);

    this.ox = new OxenSprite();
    this.ox.getSprite().setTranslateX(150);
    this.ox.getSprite().setTranslateY(20);

    pane.getChildren().add(canvas);
    pane.getChildren().add(this.ox.getSprite());
    return pane;
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
            ox.play();
            updateStats();
            break;

          case ENTER:
            AZTrailView.stage.setScene(new SizeUpView());
            break;
        }
      }
    });
  }

  /**
   * [buildStatsString description]
   */
  private String buildStatsString() {

    String res = "Date: %s\nWeather: %s\nHealth: %s\nFood: %d Pounds\nNext landmark: %.0f miles\nMiles Traveled: %d miles";
    String date = AZTrailView.controller.getDateStr();

    int food = AZTrailView.controller.getFood();
    double remaining = AZTrailView.controller.milesToLandmark();
    int totalMiles = AZTrailView.controller.getTotalMiles();
    return String.format(res, date, "cold", "good", food, remaining, totalMiles);
  }

  /**
   * [updateStats description]
   */
  private void updateStats() {
    AZTrailView.controller.advance();
    this.stats.setText(buildStatsString());
  }

  private class OxenSprite {
    private final Image IMAGE = new Image("file:view/assets/graphics/oxenwalk.png",
      600, 600, true, false);
    private static final int COLUMNS  =   5;
    private static final int COUNT    =   5;
    private static final int OFFSET_X =   0;
    private static final int OFFSET_Y =   0;
    private static final int WIDTH    =  120;
    private static final int HEIGHT   =  46;
    private final Animation animation;
    private ImageView imageView;

    public OxenSprite() {
      this.imageView = new ImageView(IMAGE);
      this.imageView.setViewport(new Rectangle2D(OFFSET_X, OFFSET_Y, WIDTH, HEIGHT));

      this.animation = new SpriteAnimation(
              this.imageView,
              Duration.millis(500),
              COUNT, COLUMNS,
              OFFSET_X, OFFSET_Y,
              WIDTH, HEIGHT
      );
      this.animation.setCycleCount(4);
    }

    public void play() {
      this.animation.play();
    }

    public ImageView getSprite() {
      return this.imageView;
    }
  }
}
