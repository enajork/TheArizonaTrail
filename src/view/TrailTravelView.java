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
import javafx.animation.*;
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
  private ParallelTransition movementBack;
  private ParallelTransition movementMid;
  private ParallelTransition movementFore;
  private final int SCENE_WIDTH = 650;
  private BorderPane root;
  private OxenSprite ox;

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
    root.setStyle("-fx-background-color: black;");

    Text footer = new Text("Hold SPACEBAR to continue...\n");
    footer.setId("text12");
    footer.setFill(Color.WHITE);
    AnchorPane anchor = new AnchorPane(footer);
    anchor.setLeftAnchor(footer, 110.0);

    BorderPane info = infoPane();
    root.setTop(travelGraphics());
    root.setCenter(info);
    root.setBottom(anchor);
    root.setAlignment(anchor, Pos.CENTER);
    addEventHandlers();
  }

  private BorderPane infoPane() {
    BorderPane info = new BorderPane();
    // TODO get partyStats from controller
    HBox statsArea = new HBox();
    // statsArea.setSpacing(0.5);
    Text stats = new Text(
      "Date: March 1, 1848\nWeather: cold\nHealth: good\nFood: "
        + "0 Pounds\nNext landmark: 44 miles\nMiles Traveled: 0 miles"
    );
    stats.setId("text12");
    stats.setFill((AZTrailView.controller.getHunted()) ?
                                         (Color.WHITE) : (Color.BLACK));
    statsArea.getChildren().add(stats);
    statsArea.setStyle("-fx-background-color: " +
        ((AZTrailView.controller.getHunted()) ? "black" : "white") + ";");

    info.setCenter(statsArea);
    info.setAlignment(statsArea, Pos.CENTER);
    statsArea.setMargin(stats, new Insets(10));
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
      transBack[i] = new TranslateTransition(Duration.millis(100000),
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
        ? new Image("file:view/assets/graphics/scenery-hunted.png", 1000, 50,
          false, true)
        : new Image("file:view/assets/graphics/scenery.png", 1000, 50, false,
          true));
      scenery[i].setViewport(mid);
      transMid[i] = new TranslateTransition(Duration.millis(50000), scenery[i]);
      transMid[i].setFromX(0);
      transMid[i].setToX(-1 * SCENE_WIDTH);
      transMid[i].setInterpolator(Interpolator.LINEAR);
    }
    movementMid = new ParallelTransition(transMid[1], transMid[0]);
    movementMid.setCycleCount(1);

    ImageView sand[] = {null, null};
    TranslateTransition transFore[] = {null, null};
    for (int i = 0; i < 2; ++i) {
      Rectangle2D fore = new Rectangle2D(0, 0, SCENE_WIDTH, 50);
      sand[i] = new ImageView((AZTrailView.controller.getHunted())
        ? new Image("file:view/assets/graphics/sand-hunted.png", 1000, 50,
          false, true)
        : new Image("file:view/assets/graphics/sand.png", 1000, 50, false,
          true));
      sand[i].setViewport(fore);
      transFore[i] = new TranslateTransition(Duration.millis(30000), sand[i]);
      transFore[i].setFromX(0);
      transFore[i].setToX(-1 * SCENE_WIDTH);
      transFore[i].setInterpolator(Interpolator.LINEAR);
    }
    movementFore = new ParallelTransition(transFore[1], transFore[0]);
    movementFore.setCycleCount(1);

    tile.getChildren().addAll(mountains[1], scenery[1], sand[1], mountains[0],
      scenery[0], sand[0]);

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
            movementBack.setRate(-1.0);
            movementMid.setRate(-1.0);
            movementFore.setRate(-1.0);
            movementBack.play();
            movementMid.play();
            movementFore.play();
            break;

            case ESCAPE:
              AZTrailView.escapePressed(false);
              break;

            case S:
              AZTrailController.escape = false;
              if (event.isControlDown()) {
                AZTrailView.sounds.mute();
              }
              break;

            case ENTER:
              AZTrailController.escape = false;
              break;

            default:
              AZTrailController.escape = false;
              break;
        }
      }
    });
    this.setOnKeyReleased(new EventHandler<KeyEvent>() {
      @Override
      public void handle(KeyEvent event) {
        switch (event.getCode()) {
          case SPACE:
            AZTrailController.escape = false;
            ox.pause();
            movementBack.pause();
            movementMid.pause();
            movementFore.pause();
            break;
        }
      }
    });
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

    public void pause() {
      this.animation.pause();
    }

    public ImageView getSprite() {
      return this.imageView;
    }
  }
}
