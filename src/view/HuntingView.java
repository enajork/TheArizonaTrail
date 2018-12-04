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
  private static Canvas canvas = new Canvas(AZTrailView.WIDTH,
    AZTrailView.HEIGHT);
  private boolean hunted = AZTrailView.controller.getHunted();
  private Image up = new Image("file:view/assets/graphics/hunter/up"
    + ((hunted) ? "-hunted" : "") + ".png");
  private Image down = new Image("file:view/assets/graphics/hunter/down"
    + ((hunted) ? "-hunted" : "") + ".png");
  private Image left = new Image("file:view/assets/graphics/hunter/left"
    + ((hunted) ? "-hunted" : "") + ".png");
  private Image right = new Image("file:view/assets/graphics/hunter/right"
    + ((hunted) ? "-hunted" : "") + ".png");
  private Image downleft = new Image("file:view/assets/graphics/hunter/downleft"
    + ((hunted) ? "-hunted" : "") + ".png");
  private Image downright = new Image("file:view/assets/graphics/hunter/downright"
    + ((hunted) ? "-hunted" : "") + ".png");
  private Image upleft = new Image("file:view/assets/graphics/hunter/upleft"
    + ((hunted) ? "-hunted" : "") + ".png");
  private Image upright = new Image("file:view/assets/graphics/hunter/upright"
    + ((hunted) ? "-hunted" : "") + ".png");
  private Image img = up;
  private final int CANVAS_WIDTH = (int)canvas.getWidth();
  private final int CANVAS_HEIGHT = (int)canvas.getHeight();
  private int x = CANVAS_WIDTH / 2;
  private int y = CANVAS_HEIGHT / 2;
  private int width = 38;
  private int height = 46;
  private int mouseX = CANVAS_WIDTH / 2;
  private int mouseY = 0;
  private static GraphicsContext gc = canvas.getGraphicsContext2D();
  private StackPane root;

  /**
   * [HuntingView description]
   */
  public HuntingView() {
    this(new StackPane());
    getStylesheets().add(AZTrailView.styleSheet);
  }

  /**
   * [HuntingView description]
   * @param root [description]
   */
  private HuntingView(StackPane root) {
    super(root, AZTrailView.WIDTH, AZTrailView.HEIGHT, Color.BLACK);
    this.root = root;
    root.setStyle("-fx-background-color: black;");
    root.getChildren().add(canvas);
    gc.drawImage(img, 0, 0, width, height, CANVAS_WIDTH / 2, CANVAS_HEIGHT / 2,
      width, height);

    addEventHandlers();

    AnimationTimer timer = new AnimationTimer() {
      @Override
      public void handle(long now) {
        tick();
      }
    };
    timer.start();
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
            } else {
              y = 0;
            }
            break;

          case A:
            AZTrailController.escape = false;
            if (x - MOVESPEED >= 0) {
              x -= MOVESPEED;
            } else {
              x = 0;
            }
            break;

          case S:
            AZTrailController.escape = false;
            if (event.isControlDown()) {
              AZTrailView.sounds.mute();
              break;
            }
            if (y + MOVESPEED + height <= CANVAS_HEIGHT) {
              y += MOVESPEED;
            } else {
              y = CANVAS_HEIGHT - height;
            }
            break;

          case D:
            AZTrailController.escape = false;
            if (x + MOVESPEED + width <= CANVAS_WIDTH) {
              x += MOVESPEED;
            } else {
              x = CANVAS_WIDTH - width;
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
        mouseX = (int) event.getX();
        mouseY = (int) event.getY();
      }
    });
    this.setOnMousePressed(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent event) {
        Circle bullet = new Circle(2, Color.WHITE);
        TranslateTransition fire = new TranslateTransition();
        fire.setDuration(Duration.seconds(1));
        fire.setNode(bullet);

        fire.setToX(100);
        fire.setToY(100);
        fire.setCycleCount(1);
        fire.play();
      }
    });
  }

  private void tick() {
    redraw();
  }

  private void redraw() {
    turn();
    gc.clearRect(0, 0, CANVAS_WIDTH, CANVAS_HEIGHT);
    gc.drawImage(img, 0, 0, width, height, x, y, width, height);
  }

  private void turn() {
    int baseX = x + width / 2;
    int baseY = y + height / 2;
    int angle = (int) Math.toDegrees(Math.atan2(mouseY - baseY,
      mouseX - baseX));
    if (angle < 0) {
      angle += 360;
    }
    if (angle >= 0 && angle <= 22) {
      img = right;
    } else if (angle >= 23 && angle <= 68) {
      img = downright;
    } else if (angle >= 69 && angle <= 114) {
      img = down;
    } else if (angle >= 115 && angle <= 160) {
      img = downleft;
    } else if (angle >= 161 && angle <= 206) {
      img = left;
    } else if (angle >= 207 && angle <= 252) {
      img = upleft;
    } else if (angle >= 253 && angle <= 298) {
      img = up;
    } else if (angle >= 299 && angle <= 344) {
      img = upright;
    } else if (angle >= 345 && angle <= 360) {
      img = right;
    }
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
