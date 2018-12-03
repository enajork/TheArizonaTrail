package view;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.util.Duration;
import javafx.application.*;
import javafx.scene.input.*;
import javafx.scene.image.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.beans.value.*;
import javafx.animation.*;
import javafx.geometry.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.event.*;
import java.util.*;
import java.io.*;

import controller.*;

public class TrailTravelView extends Scene {
  // private static final int TIME_DILATION = 30 - ((AZTrailView.controller.getTravelRate() / 3) * 10); // 30 seems good
  private static final int TIME_DILATION = 1;
  private static final int BACK_SPEED = 10000 * TIME_DILATION;
  private static final int MID_SPEED = 5000 * TIME_DILATION;
  private static final int FORE_SPEED = 3000 * TIME_DILATION;
  private static final int OXEN_SPEED = 50 * TIME_DILATION;
  private final int SCENE_WIDTH = AZTrailView.WIDTH;
  private int ICON_WIDTH = 50;
  private int ICON_HEIGHT = 50;
  private ParallelTransition movementBack;
  private ParallelTransition movementMid;
  private ParallelTransition movementFore;
  private StackPane city[] = {null, null};
  private ImageView view[] = {null, null};
  private BorderPane root;
  private OxenSprite ox;
  private Text stats;
  private static int time = 0;

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
    AZTrailView.sounds.startBackgroundSFX();
    AZTrailView.sounds.startThemeLoop();
    this.root = root;
    root.setStyle("-fx-background-color: black;");

    Text footer = new Text("Hold SPACE BAR to continue...\n");
    footer.setId("text12");
    footer.setFill(Color.WHITE);
    BorderPane tooltip = new BorderPane();
    tooltip.setCenter(footer);
    tooltip.setAlignment(footer, Pos.CENTER);
    tooltip.setMargin(footer, new Insets(10));
    AnchorPane anchor = new AnchorPane(tooltip);
    anchor.setLeftAnchor(tooltip, 110.0);

    // Create the pane containing the current stats
    BorderPane info = infoPane();
    root.setTop(travelGraphics());
    root.setCenter(info);
    root.setBottom(anchor);
    root.setAlignment(anchor, Pos.CENTER);
    addEventHandlers();
  }

  private BorderPane infoPane() {
    BorderPane info = new BorderPane();

    // Create the sizeup box
    HBox sizeUpBox = new HBox();
    BorderPane sizeUpPane = new BorderPane();
    Text sizeUp = new Text("  Press ENTER to size up the situation");
    sizeUp.setId("text12");
    sizeUp.setFill(Color.WHITE);
    sizeUpPane.setCenter(sizeUp);
    sizeUpPane.setAlignment(sizeUp, Pos.CENTER);
    sizeUpPane.setMargin(sizeUp, new Insets(5));
    sizeUpBox.getChildren().add(sizeUpPane);
    sizeUpBox.setStyle("-fx-background-color: black;");

    // create the stats area
    HBox statsArea = new HBox();
    this.stats = new Text(buildStatsString());
    this.stats.setId("text12");

    stats.setFill((AZTrailView.controller.getHunted()) ?
          (Color.WHITE) : (Color.BLACK));
    statsArea.getChildren().add(stats);

    statsArea.setStyle("-fx-background-color: " +
        ((AZTrailView.controller.getHunted()) ? "black" : "white") + ";");

    // add them to the scene
    info.setAlignment(sizeUpBox, Pos.CENTER);
    info.setTop(sizeUpBox);
    info.setCenter(statsArea);
    info.setAlignment(statsArea, Pos.CENTER);
    statsArea.setMargin(stats, new Insets(30, 20, 20, 40));
    return info;
  }

  private StackPane travelGraphics() {
    StackPane pane = new StackPane();
    TilePane tile = new TilePane(Orientation.VERTICAL);
    tile.setPrefRows(3);
    tile.setAlignment(Pos.TOP_CENTER);

    ImageView mountains[] = {null, null};
    TranslateTransition transBack[] = {null, null};
    for (int i = 0; i < 2; ++i) {
      Rectangle2D back = new Rectangle2D(0, 0, SCENE_WIDTH, 50);
      mountains[i] = new ImageView((AZTrailView.controller.getHunted())
      ? new Image("file:view/assets/graphics/mountain-hunted.png", 1000, 50,
        false, true)
      : new Image("file:view/assets/graphics/mountain.png", 1000, 50, false,
        true));
      mountains[i].setViewport(back);
      transBack[i] = new TranslateTransition(Duration.millis(BACK_SPEED),
        mountains[i]);
      transBack[i].setFromX(0);
      transBack[i].setToX(-1 * SCENE_WIDTH);
      transBack[i].setInterpolator(Interpolator.LINEAR);
    }
    movementBack = new ParallelTransition(transBack[1], transBack[0]);
    movementBack.setCycleCount(1);

    ImageView scenery[] = {null, null};
    TranslateTransition transMid[] = {null, null};
    for (int i = 0; i < 2; ++i) {
      Rectangle2D mid = new Rectangle2D(0, 0, SCENE_WIDTH, 50);
      scenery[i] = new ImageView((AZTrailView.controller.getHunted())
        ? new Image("file:view/assets/graphics/scenery-hunted.png", 700, 50,
          false, true)
        : new Image("file:view/assets/graphics/scenery.png", 700, 50, false,
          true));
      scenery[i].setViewport(mid);
      transMid[i] = new TranslateTransition(Duration.millis(MID_SPEED), scenery[i]);
      transMid[i].setFromX(0);
      transMid[i].setToX(-1 * SCENE_WIDTH);
      transMid[i].setInterpolator(Interpolator.LINEAR);
    }
    movementMid = new ParallelTransition(transMid[1], transMid[0]);
    movementMid.setCycleCount(1);

    if (AZTrailView.controller.getNextCity().equals("Flagstaff") ||
        AZTrailView.controller.getNextCity().equals("Tucson")) {
      if (AZTrailView.controller.getHunted()) {
        ICON_WIDTH = 50;
        ICON_HEIGHT = 50;
      } else {
        ICON_WIDTH = 50;
        ICON_HEIGHT = 50;
      }
    } else if (AZTrailView.controller.getNextCity().equals("Sedona") ||
               AZTrailView.controller.getNextCity().equals("Tombstone")) {
      if (AZTrailView.controller.getHunted()) {
        ICON_WIDTH = 90;
        ICON_HEIGHT = 50;
      } else {
        ICON_WIDTH = 90;
        ICON_HEIGHT = 50;
      }
    } else if (AZTrailView.controller.getNextCity().equals("Page") ||
               AZTrailView.controller.getNextCity().equals("Phoenix")) {
      if (AZTrailView.controller.getHunted()) {
        ICON_WIDTH = 110;
        ICON_HEIGHT = 45;
      } else {
        ICON_WIDTH = 110;
        ICON_HEIGHT = 45;
      }
    }

    ImageView sand[] = {null, null};
    TranslateTransition transFore[] = {null, null};
    for (int i = 0; i < 2; ++i) {
      Rectangle2D fore = new Rectangle2D(0, 0, SCENE_WIDTH, 50);
      sand[i] = new ImageView((AZTrailView.controller.getHunted())
        ? new Image("file:view/assets/graphics/sand-hunted.png", 1000, 50,
          false, true)
        : new Image("file:view/assets/graphics/sand.png", 1000, 50,
          false, true));
      city[i] = new StackPane(sand[i]);
      sand[i].setViewport(fore);
      transFore[i] = new TranslateTransition(Duration.millis(FORE_SPEED), city[i]);
      transFore[i].setFromX(0);
      transFore[i].setToX(-1 * SCENE_WIDTH);
      transFore[i].setInterpolator(Interpolator.LINEAR);
      if (i == 0) {
        view[i] = new ImageView((AZTrailView.controller.getHunted())
        ? new Image("file:view/assets/graphics/locations/" +
          AZTrailView.controller.getNextCity() + "-hunted.png", ICON_WIDTH, ICON_HEIGHT,
          false, true)
        : new Image("file:view/assets/graphics/locations/" +
          AZTrailView.controller.getNextCity() + ".png", ICON_WIDTH, ICON_HEIGHT,
          false, true));
        city[i].getChildren().add(view[i]);

        if (!AZTrailView.controller.getNextCity().equals("Flagstaff") &&
            !AZTrailView.controller.getNextCity().equals("Tombstone")) {
          view[i].setViewport(fore);
        }
      }
    }

    movementFore = new ParallelTransition(transFore[1], transFore[0]);
    movementFore.setCycleCount(1);

    tile.getChildren().addAll(mountains[1], scenery[1], city[1], mountains[0],
      scenery[0], city[0]);

    this.ox = new OxenSprite();
    this.ox.getSprite().setTranslateX(-200);
    this.ox.getSprite().setTranslateY(54);

    pane.getChildren().add(tile);
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
            AZTrailController.escape = false;
            ox.play();
            AZTrailView.sounds.movingSFX();
            movementBack.setRate(-1.0);
            movementMid.setRate(-1.0);
            movementFore.setRate(-1.0);
            movementBack.play();
            movementMid.play();
            movementFore.play();
            tick();
            break;

          case ESCAPE:
            AZTrailView.escapePressed(true);
            ox.pause();
            movementBack.pause();
            movementMid.pause();
            movementFore.pause();
            break;

          case S:
            AZTrailController.escape = false;
            if (event.isControlDown()) {
              AZTrailView.sounds.mute();
            }
            ox.pause();
            AZTrailView.sounds.stopMovingSFX();
            movementBack.pause();
            movementMid.pause();
            movementFore.pause();
            break;

          case ENTER:
            AZTrailController.escape = false;
            ox.pause();
            AZTrailView.sounds.stopMovingSFX();
            movementBack.pause();
            movementMid.pause();
            movementFore.pause();
            AZTrailView.stage.setScene(new SizeUpView());
            break;
          default:
            AZTrailController.escape = false;
            ox.pause();
            AZTrailView.sounds.stopMovingSFX();
            movementBack.pause();
            movementMid.pause();
            movementFore.pause();
            break;
        }
      }
    });
    this.setOnKeyReleased(new EventHandler<KeyEvent>() {
      @Override
      public void handle(KeyEvent event) {
        switch (event.getCode()) {
          default:
            ox.pause();
            AZTrailView.sounds.stopMovingSFX();
            movementBack.pause();
            movementMid.pause();
            movementFore.pause();
            break;
        }
      }
    });
  }

  /**
   * [buildStatsString description]
   */
  private String buildStatsString() {
    String res = "Date: %s\nWeather: %s\nHealth: %s\nFood: %d pounds\nWater: "
      + "%d Gallons\nNext landmark: %.0f miles\nMiles Traveled: %d miles";
    double remaining = AZTrailView.controller.milesToLandmark();
    String weather = AZTrailView.controller.getWeather();
    String health = AZTrailView.controller.getHealth();
    String date = AZTrailView.controller.getDateStr();
    int totalMiles = AZTrailView.controller.getTotalMiles();
    int water = AZTrailView.controller.getWater();
    int food = AZTrailView.controller.getFood();
    return String.format(res, date, weather, health, food, water, remaining,
      totalMiles);
  }

  /**
   * [updateStats description]
   */
  private void tick() {
    if (time == Integer.MAX_VALUE) {
      time = 0;
    } else {
      time++;
    }
    if (time % TIME_DILATION == 0) {
      String result = AZTrailView.controller.deplete();
      if (result.length() != 0) {
        AZTrailView.sounds.gameOverTheme();
        AZTrailView.stage.setScene(new GenericInfoMenu(
          new Runnable() {
            @Override
            public void run() {
              AZTrailView.stage.setScene(new GameOver());
            }
          },
          new String[]{ result },
          true,
          false,
          true
        ));
      }
      result = AZTrailView.controller.randomEvent();
      if (result.length() != 0) {
        if (result.charAt(0) == '0') {
          result = result.replace("0", "");
          AZTrailView.stage.setScene(new GenericInfoMenu(
            new Runnable() {
              @Override
              public void run() {
                AZTrailView.stage.setScene(new TrailTravelView());
              }
            },
            new String[]{ result },
            true,
            false,
            true,
            true
          ));
        } else if (result.charAt(0) == '1') {
          AZTrailView.sounds.gameOverTheme();
          result = result.replace("1", "");
          AZTrailView.stage.setScene(new GenericInfoMenu(
            new Runnable() {
              @Override
              public void run() {
                AZTrailView.stage.setScene(new GameOver());
              }
            },
            new String[]{ result },
            true,
            false,
            true
          ));
        }
      }
      if (AZTrailView.controller.advance()) {
        AZTrailView.stage.setScene(new CitySplash(AZTrailView.controller
          .getCurrentCity(), true));
      }
    }
    this.stats.setText(buildStatsString());
  }

  private class OxenSprite {
    private final Image IMAGE = new Image((AZTrailView.controller.getHunted())
      ? "file:view/assets/graphics/oxenwalk-hunted.png"
      : "file:view/assets/graphics/oxenwalk.png",
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
      this.imageView.setViewport(new Rectangle2D(OFFSET_X, OFFSET_Y, WIDTH,
        HEIGHT));

      this.animation = new SpriteAnimation(
        this.imageView,
        Duration.millis(OXEN_SPEED),
        COUNT, COLUMNS,
        OFFSET_X, OFFSET_Y,
        WIDTH, HEIGHT
      );
      this.animation.setCycleCount(4);
    }

    public void play() {
      this.animation.play();
    }

    public void pause() {
      this.animation.pause();
    }

    public ImageView getSprite() {
      return this.imageView;
    }
  }
}
