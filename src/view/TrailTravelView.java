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
  // private GraphicsContext gc;
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

    Text cont = new Text("Press SPACEBAR to continue...\n");
    cont.setId("text12");
    cont.setFill(Color.WHITE);

    BorderPane info = infoPane();
    root.setStyle("-fx-background-color: black;");
    root.setAlignment(cont, Pos.BASELINE_CENTER);

    root.setTop(travelGraphics());
    root.setCenter(info);
    root.setBottom(cont);
    addEventHandlers();
  }

  private BorderPane infoPane() {
    BorderPane info = new BorderPane();
    // TODO get partyStats from controller
    HBox statsArea = new HBox();
    // statsArea.setSpacing(0.5);
    Text stats = new Text("Date: March 1, 1848\nWeather: cold\nHealth: good\nFood:" +
                          " 0 Pounds\nNext landmark: 44 miles\nMiles Traveled: 0 miles");
    stats.setId("text12");
    stats.setFill((AZTrailView.controller.getHunted()) ?
                                         (Color.WHITE) : (Color.BLACK));
    statsArea.getChildren().add(stats);
    statsArea.setStyle("-fx-background-color: " +
        ((AZTrailView.controller.getHunted()) ? "black" : "white") + ";");

    statsArea.setMargin(stats, new Insets(0.5));
    info.setAlignment(statsArea, Pos.CENTER);
    info.setCenter(statsArea);
    return info;
  }

  private StackPane travelGraphics() {
    StackPane pane = new StackPane();
    // final Canvas canvas = new Canvas(650, 150);
    // this.gc = canvas.getGraphicsContext2D();
    TilePane scene = new TilePane(Orientation.VERTICAL);
    // HBox scene = new HBox();
    scene.setPrefRows(3);
    scene.setAlignment(Pos.TOP_CENTER);
    Rectangle2D back = new Rectangle2D(0, 0, SCENE_WIDTH, 50);
    ImageView mountain = new ImageView((AZTrailView.controller.getHunted()) ?
                         new Image("file:view/assets/graphics/mountain-hunted.png", 1000, 50, false, true) :
                         new Image("file:view/assets/graphics/mountain.png", 1000, 50, false, true));
    mountain.setViewport(back);
    scene.getChildren().add(mountain);
    Rectangle2D mid = new Rectangle2D(0, 0, SCENE_WIDTH, 50);
    ImageView scenery = new ImageView((AZTrailView.controller.getHunted()) ?
                        new Image("file:view/assets/graphics/scenery-hunted.png", 1000, 50, false, true) :
                        new Image("file:view/assets/graphics/scenery.png", 1000, 50, false, true));
    // scenery.setX(0);
    // scenery.setY(50);
    scenery.setViewport(mid);
    scene.getChildren().add(scenery);
    Rectangle2D fore = new Rectangle2D(0, 0, SCENE_WIDTH, 50);
    ImageView sand = new ImageView((AZTrailView.controller.getHunted()) ?
                     new Image("file:view/assets/graphics/sand-hunted.png", 1000, 50, false, true) :
                     new Image("file:view/assets/graphics/sand.png", 1000, 50, false, true));
    sand.setViewport(fore);
    scene.getChildren().add(sand);



    Rectangle2D back2 = new Rectangle2D(0, 0, SCENE_WIDTH, 50);
    ImageView mountain2 = new ImageView((AZTrailView.controller.getHunted()) ?
                         new Image("file:view/assets/graphics/mountain-hunted.png", 1000, 50, false, true) :
                         new Image("file:view/assets/graphics/mountain.png", 1000, 50, false, true));
    mountain2.setViewport(back2);
    scene.getChildren().add(mountain2);
    Rectangle2D mid2 = new Rectangle2D(0, 0, SCENE_WIDTH, 50);
    ImageView scenery2 = new ImageView((AZTrailView.controller.getHunted()) ?
                        new Image("file:view/assets/graphics/scenery-hunted.png", 1000, 50, false, true) :
                        new Image("file:view/assets/graphics/scenery.png", 1000, 50, false, true));
    // scenery.setX(0);
    // scenery.setY(50);
    scenery2.setViewport(mid2);
    scene.getChildren().add(scenery2);
    Rectangle2D fore2 = new Rectangle2D(0, 0, SCENE_WIDTH, 50);
    ImageView sand2 = new ImageView((AZTrailView.controller.getHunted()) ?
                     new Image("file:view/assets/graphics/sand-hunted.png", 1000, 50, false, true) :
                     new Image("file:view/assets/graphics/sand.png", 1000, 50, false, true));
    sand2.setViewport(fore2);
    scene.getChildren().add(sand2);
    // gc.getChildren.add();

    this.ox = new OxenSprite();
    this.ox.getSprite().setTranslateX(150);
    this.ox.getSprite().setTranslateY(20);

    TranslateTransition transBack =
        new TranslateTransition(Duration.millis(10000), mountain);
    transBack.setFromX(0);
    transBack.setToX(-1 * SCENE_WIDTH);
    transBack.setInterpolator(Interpolator.LINEAR);

    TranslateTransition transBackWrap =
        new TranslateTransition(Duration.millis(10000), mountain2);
    transBackWrap.setFromX(0);
    transBackWrap.setToX(-1 * SCENE_WIDTH);
    transBackWrap.setInterpolator(Interpolator.LINEAR);

    movementBack = new ParallelTransition(transBack, transBackWrap);
    movementBack.setCycleCount(Animation.INDEFINITE);

    TranslateTransition transMid =
        new TranslateTransition(Duration.millis(10000), scenery);
    transMid.setFromX(0);
    transMid.setToX(-1 * SCENE_WIDTH);
    transMid.setInterpolator(Interpolator.LINEAR);

    TranslateTransition transMidWrap =
        new TranslateTransition(Duration.millis(10000), scenery2);
    transMidWrap.setFromX(0);
    transMidWrap.setToX(-1 * SCENE_WIDTH);
    transMidWrap.setInterpolator(Interpolator.LINEAR);

    movementMid = new ParallelTransition(transMid, transMidWrap);
    movementMid.setCycleCount(Animation.INDEFINITE);

    TranslateTransition transFore =
        new TranslateTransition(Duration.millis(10000), sand);
    transFore.setFromX(0);
    transFore.setToX(-1 * SCENE_WIDTH);
    transFore.setInterpolator(Interpolator.LINEAR);

    TranslateTransition transForeWrap =
        new TranslateTransition(Duration.millis(10000), sand2);
    transForeWrap.setFromX(0);
    transForeWrap.setToX(-1 * SCENE_WIDTH);
    transForeWrap.setInterpolator(Interpolator.LINEAR);

    movementFore = new ParallelTransition(transFore, transForeWrap);
    movementFore.setCycleCount(Animation.INDEFINITE);

    //
    // Sets the label of the Button based on the animation state
    //
    movementBack.statusProperty().addListener((obs, old, val) -> {
      if (val == Animation.Status.RUNNING) {
        // btnControl.setText("||");
      } else {
        // btnControl.setText(">");
      }
    });
    this.movementBack.play();
    this.movementMid.play();
    this.movementFore.play();

    // pane.getChildren().add(canvas);
    pane.getChildren().add(scene);
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
            AZTrailController.escape = false;
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
        }
      }
    });
  }

  private class OxenSprite {
    private final Image IMAGE = new Image((AZTrailView.controller.getHunted()) ?
                               "file:view/assets/graphics/oxenwalk-hunted.png" :
                               "file:view/assets/graphics/oxenwalk.png",
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





  //
  // @FXML
  // public void initialize() {
  //
  // }
  //
  // // The remainder of the Controller are the actions registered to the Button.  Recall that startAnimation() is also called from the Stage's onShown event.
  //
  public void startAmination() {
    movementBack.play();
  }

  public void pauseAnimation() {
    movementBack.pause();
  }
  //
  // @FXML
  public void controlPressed() {
    if (movementBack.getStatus() == Animation.Status.RUNNING) {
      pauseAnimation();
    } else {
      startAmination();
    }
  }





}
