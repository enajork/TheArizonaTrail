package view;

import javafx.scene.control.Alert.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.input.*;
import javafx.application.*;
import javafx.scene.canvas.*;
import javafx.scene.input.*;
import javafx.scene.image.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.scene.*;
import javafx.beans.value.*;
import javafx.animation.*;
import javafx.geometry.*;
import javafx.stage.*;
import javafx.event.*;
import java.util.*;
import java.io.*;
import javafx.util.Duration;

import controller.*;

public class HuntingView extends Scene {
  private final int MOVESPEED = 20;
  private int TERRAIN_DENSITY = 64;
  private static Canvas canvas = new Canvas(AZTrailView.WIDTH,
    AZTrailView.HEIGHT);
  private Image img = new Image("file:view/assets/graphics/hunter/up.png");
  private Image up = new Image("file:view/assets/graphics/hunter/up.png");
  private Image down = new Image("file:view/assets/graphics/hunter/down.png");
  private Image left = new Image("file:view/assets/graphics/hunter/left.png");
  private Image right = new Image("file:view/assets/graphics/hunter/right.png");
  private Image downleft = new Image("file:view/assets/graphics/hunter/downleft.png");
  private Image downright = new Image("file:view/assets/graphics/hunter/downright.png");
  private Image upleft = new Image("file:view/assets/graphics/hunter/upleft.png");
  private Image upright = new Image("file:view/assets/graphics/hunter/upright.png");
  private Image upHunted = new Image("file:view/assets/graphics/hunter/up-hunted.png");
  private Image downHunted = new Image("file:view/assets/graphics/hunter/down-hunted.png");
  private Image leftHunted = new Image("file:view/assets/graphics/hunter/left-hunted.png");
  private Image rightHunted = new Image("file:view/assets/graphics/hunter/right-hunted.png");
  private Image downleftHunted = new Image("file:view/assets/graphics/hunter/downleft-hunted.png");
  private Image downrightHunted = new Image("file:view/assets/graphics/hunter/downright-hunted.png");
  private Image upleftHunted = new Image("file:view/assets/graphics/hunter/upleft-hunted.png");
  private Image uprightHunted = new Image("file:view/assets/graphics/hunter/upright-hunted.png");
  private final int CANVAS_WIDTH = (int)canvas.getWidth();
  private final int CANVAS_HEIGHT = (int)canvas.getHeight();
  private int x = CANVAS_WIDTH / 2;
  private int y = CANVAS_HEIGHT / 2;
  private int width = 38;
  private int height = 46;
  private static GraphicsContext gc = canvas.getGraphicsContext2D();
  private BorderPane root;
  private String city;

  /**
   * [HuntingView description]
   */
  public HuntingView() {
    this(new BorderPane());
    getStylesheets().add(AZTrailView.styleSheet);
  }

  /**
   * [HuntingView description]
   * @param root [description]
   */
  private HuntingView(BorderPane root) {
    super(root, AZTrailView.WIDTH, AZTrailView.HEIGHT, Color.BLACK);
    this.root = root;
    root.setStyle("-fx-background-color: black;");
    Image rock1 = new Image((AZTrailView.controller.getHunted())
        ? "file:view/assets/graphics/rock1-hunted.png"
        : "file:view/assets/graphics/rock1.png",
        60, 35, false, true);
    Image rock2 = new Image((AZTrailView.controller.getHunted())
        ? "file:view/assets/graphics/rock2-hunted.png"
        : "file:view/assets/graphics/rock2.png",
        50, 50, false, true);
    Image rock3 = new Image((AZTrailView.controller.getHunted())
        ? "file:view/assets/graphics/rock1-hunted.png"
        : "file:view/assets/graphics/rock1.png",
        50, 30, false, true);
    Image rock4 = new Image((AZTrailView.controller.getHunted())
        ? "file:view/assets/graphics/rock2-hunted.png"
        : "file:view/assets/graphics/rock2.png",
        70, 70, false, true);
    Image cactus1 = new Image((AZTrailView.controller.getHunted())
        ? "file:view/assets/graphics/cactus1-hunted.png"
        : "file:view/assets/graphics/cactus1.png",
        50, 70, false, true);
    Image cactus2 = new Image((AZTrailView.controller.getHunted())
        ? "file:view/assets/graphics/cactus2-hunted.png"
        : "file:view/assets/graphics/cactus2.png",
        40, 68, false, true);
    Image cactus3 = new Image((AZTrailView.controller.getHunted())
        ? "file:view/assets/graphics/cactus1-hunted.png"
        : "file:view/assets/graphics/cactus1.png",
        40, 56, false, true);
    Image cactus4 = new Image((AZTrailView.controller.getHunted())
        ? "file:view/assets/graphics/cactus2-hunted.png"
        : "file:view/assets/graphics/cactus2.png",
        50, 85, false, true);
    ArrayList<Image> propList = new ArrayList<Image>();
    propList.add(rock1);
    propList.add(rock2);
    propList.add(rock3);
    propList.add(rock4);
    propList.add(cactus1);
    propList.add(cactus2);
    propList.add(cactus3);
    propList.add(cactus4);
    ImageView[] props = new ImageView[64];
    for (int i = 0; i < props.length; ++i) {
      Random rand = new Random();
      props[i] = new ImageView(propList.get(rand.nextInt(propList.size())));
    }
    AnchorPane positions[] = new AnchorPane[64];
    for (int i = 0; i < positions.length; ++i) {
      positions[i] = new AnchorPane(props[i]);
    }
    for (int i = 0; i < positions.length; ++i) {
      double[] pos = getRandomPosition();
      positions[i].setLeftAnchor(props[i], pos[0]);
      positions[i].setTopAnchor(props[i], pos[1]);
    }
    StackPane layers = new StackPane();
    for (int i = 0; i < positions.length; ++i) {
      layers.getChildren().add(positions[i]);
    }
    AnchorPane anchor = new AnchorPane(layers);
    BorderPane view = new BorderPane(anchor);
    layers.getChildren().add(canvas);
    root.setCenter(layers);
    gc.drawImage(img, 0, 0, width, height, CANVAS_WIDTH / 2, CANVAS_HEIGHT / 2,
      width, height);

    addEventHandlers();
  }

  private double[] getRandomPosition() {
    double pos[] = new double[2];
    pos[0] = 0;
    pos[1] = 0;
    while (pos[0] < 20 || pos[0] > AZTrailView.WIDTH - 80||
           pos[1] < 5 || pos[1] > AZTrailView.HEIGHT - 90) {
      pos[0] = Math.random() * AZTrailView.WIDTH;
      pos[1] = Math.random() * AZTrailView.HEIGHT;
    }
    return pos;
  }

  private void doneHunting() {
    AZTrailView.sounds.stopMusic();
    AZTrailView.sounds.startThemeLoop();
  }

  /**
   * [addEventHandlers description]
   */
  private void addEventHandlers() {
    this.setOnKeyPressed(new EventHandler<KeyEvent>() {
      @Override
      public void handle(KeyEvent event) {
        switch (event.getCode()) {
          case ENTER:
            AZTrailController.escape = false;
            AZTrailView.stage.setScene(new SizeUpView());
            doneHunting();
            break;

          case ESCAPE:
            AZTrailView.stage.setScene(new SizeUpView());
            doneHunting();
            break;

          case W:
            AZTrailController.escape = false;
            if (y - MOVESPEED >= 0) {
              y -= MOVESPEED;
              redraw();
            } else {
              y = 0;
              redraw();
            }
            break;

          case A:
            AZTrailController.escape = false;
            if (x - MOVESPEED >= 0) {
              x -= MOVESPEED;
              redraw();
            } else {
              x = 0;
              redraw();
            }
            break;

          case S:
            AZTrailController.escape = false;
            if (y + MOVESPEED + height <= CANVAS_HEIGHT) {
              y += MOVESPEED;
              redraw();
            } else {
              y = CANVAS_HEIGHT - height;
              redraw();
            }
            if (event.isControlDown()) {
              AZTrailView.sounds.mute();
            }
            break;

          case D:
            AZTrailController.escape = false;
            if (x + MOVESPEED + width <= CANVAS_WIDTH) {
              x += MOVESPEED;
              redraw();
            } else {
              x = CANVAS_WIDTH - width;
              redraw();
            }
            break;

          default:
            AZTrailController.escape = false;
            return;
        }
      }
    });
    this.setOnMouseMoved(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent event) {
        System.out.println("x=" + event.getX() + ", y=" + event.getY());
      }
    });
    this.setOnMousePressed(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent event) {

      }
    });
  }

  private void redraw() {
    gc.clearRect(0, 0, CANVAS_WIDTH, CANVAS_HEIGHT);
    gc.drawImage(img, 0, 0, width, height, x, y, width, height);
  }

  private class HuntingSprite {
    private final Image IMAGE = new Image((AZTrailView.controller.getHunted())
      ? "file:view/assets/graphics/hunter-spritesheet-hunted.png"
      : "file:view/assets/graphics/hunter-spritesheet.png",
      600, 600, true, false);
    private static final int COLUMNS  =   5;
    private static final int COUNT    =   5;
    private static final int OFFSET_X =   0;
    private static final int OFFSET_Y =   0;
    private static final int WIDTH    =  120;
    private static final int HEIGHT   =  46;
    private final Animation animation;
    private ImageView imageView;

    public HuntingSprite() {
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
