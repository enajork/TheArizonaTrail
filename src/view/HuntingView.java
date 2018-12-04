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
import javafx.scene.text.*;
import javafx.scene.*;
import javafx.beans.value.*;
import javafx.animation.*;
import javafx.geometry.*;
import javafx.stage.*;
import javafx.event.*;
import javafx.util.*;
import java.util.*;
import java.text.*;
import java.io.*;

import controller.*;

public class HuntingView extends Scene {
  private boolean wDown = false;
  private boolean aDown = false;
  private boolean sDown = false;
  private boolean dDown = false;

  private final int COOLDOWN_TIME = 300;
  private final int MAX_SHOTS = 10;
  private double score = 0.0;
  private int shots = 0;
  private int i = 0;
  private final int ACCEL_RATE = 50;
  private boolean cooldown = false;
  private final int MINSPEED = 1;
  private final int MAXSPEED = 8;
  private int movespeed = MINSPEED;
  private final int TERRAIN_DENSITY = 16 + (int) (Math.random() * 16);
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
  private AnchorPane bullets = new AnchorPane();
  private Circle bullet;
  private GraphicsContext gc = canvas.getGraphicsContext2D();
  private BorderPane root;
  private AnchorPane info;
  private Text ammo = new Text(AZTrailView.controller.getBullets() + " bullets");

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
    root.setStyle("-fx-background-color: " + ((AZTrailView.controller.getHunted())
        ? "rgb(142, 141, 117)" : "rgb(245, 237, 149)") + ";");
    Image rock1 = new Image((AZTrailView.controller.getHunted())
        ? "file:view/assets/graphics/terrain/rock1-hunted.png"
        : "file:view/assets/graphics/terrain/rock1.png",
        60, 35, false, true);
    Image rock2 = new Image((AZTrailView.controller.getHunted())
        ? "file:view/assets/graphics/terrain/rock2-hunted.png"
        : "file:view/assets/graphics/terrain/rock2.png",
        50, 50, false, true);
    Image rock3 = new Image((AZTrailView.controller.getHunted())
        ? "file:view/assets/graphics/terrain/rock1-hunted.png"
        : "file:view/assets/graphics/terrain/rock1.png",
        50, 30, false, true);
    Image rock4 = new Image((AZTrailView.controller.getHunted())
        ? "file:view/assets/graphics/terrain/rock2-hunted.png"
        : "file:view/assets/graphics/terrain/rock2.png",
        70, 70, false, true);
    Image rock5 = new Image((AZTrailView.controller.getHunted())
        ? "file:view/assets/graphics/terrain/rock1-mirrored-hunted.png"
        : "file:view/assets/graphics/terrain/rock1-mirrored.png",
        60, 35, false, true);
    Image rock6 = new Image((AZTrailView.controller.getHunted())
        ? "file:view/assets/graphics/terrain/rock2-mirrored-hunted.png"
        : "file:view/assets/graphics/terrain/rock2-mirrored.png",
        50, 50, false, true);
    Image rock7 = new Image((AZTrailView.controller.getHunted())
        ? "file:view/assets/graphics/terrain/rock1-mirrored-hunted.png"
        : "file:view/assets/graphics/terrain/rock1-mirrored.png",
        50, 30, false, true);
    Image rock8 = new Image((AZTrailView.controller.getHunted())
        ? "file:view/assets/graphics/terrain/rock2-mirrored-hunted.png"
        : "file:view/assets/graphics/terrain/rock2-mirrored.png",
        70, 70, false, true);
    Image cactus1 = new Image((AZTrailView.controller.getHunted())
        ? "file:view/assets/graphics/terrain/cactus1-hunted.png"
        : "file:view/assets/graphics/terrain/cactus1.png",
        50, 70, false, true);
    Image cactus2 = new Image((AZTrailView.controller.getHunted())
        ? "file:view/assets/graphics/terrain/cactus2-hunted.png"
        : "file:view/assets/graphics/terrain/cactus2.png",
        40, 68, false, true);
    Image cactus3 = new Image((AZTrailView.controller.getHunted())
        ? "file:view/assets/graphics/terrain/cactus1-hunted.png"
        : "file:view/assets/graphics/terrain/cactus1.png",
        40, 56, false, true);
    Image cactus4 = new Image((AZTrailView.controller.getHunted())
        ? "file:view/assets/graphics/terrain/cactus2-hunted.png"
        : "file:view/assets/graphics/terrain/cactus2.png",
        50, 85, false, true);
    Image cactus5 = new Image((AZTrailView.controller.getHunted())
        ? "file:view/assets/graphics/terrain/cactus1-mirrored-hunted.png"
        : "file:view/assets/graphics/terrain/cactus1-mirrored.png",
        50, 70, false, true);
    Image cactus6 = new Image((AZTrailView.controller.getHunted())
        ? "file:view/assets/graphics/terrain/cactus2-mirrored-hunted.png"
        : "file:view/assets/graphics/terrain/cactus2-mirrored.png",
        40, 68, false, true);
    Image cactus7 = new Image((AZTrailView.controller.getHunted())
        ? "file:view/assets/graphics/terrain/cactus1-mirrored-hunted.png"
        : "file:view/assets/graphics/terrain/cactus1-mirrored.png",
        40, 56, false, true);
    Image cactus8 = new Image((AZTrailView.controller.getHunted())
        ? "file:view/assets/graphics/terrain/cactus2-mirrored-hunted.png"
        : "file:view/assets/graphics/terrain/cactus2-mirrored.png",
        50, 85, false, true);
    ArrayList<Image> propList = new ArrayList<Image>();
    propList.add(rock1);
    propList.add(rock2);
    propList.add(rock3);
    propList.add(rock4);
    propList.add(rock5);
    propList.add(rock6);
    propList.add(rock7);
    propList.add(rock8);
    propList.add(cactus1);
    propList.add(cactus2);
    propList.add(cactus3);
    propList.add(cactus4);
    propList.add(cactus5);
    propList.add(cactus6);
    propList.add(cactus7);
    propList.add(cactus8);
    ImageView[] props = new ImageView[TERRAIN_DENSITY];
    for (int i = 0; i < props.length; ++i) {
      Random rand = new Random();
      props[i] = new ImageView(propList.get(rand.nextInt(propList.size())));
    }
    AnchorPane positions[] = new AnchorPane[TERRAIN_DENSITY];
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
    layers.getChildren().add(bullets);
    layers.getChildren().add(canvas);
    ammo.setId("text10");
    ammo.setFill(Color.BLACK);
    info = new AnchorPane(ammo);
    info.setTopAnchor(ammo, 380.0);
    info.setLeftAnchor(ammo, 500.0);
    layers.getChildren().add(info);
    root.setCenter(layers);
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
            gameOver();
            break;

          case ESCAPE:
            gameOver();
            break;

          case W:
            AZTrailController.escape = false;
            wDown = true;
            break;

          case A:
            AZTrailController.escape = false;
            aDown = true;
            break;

          case S:
            AZTrailController.escape = false;
            sDown = true;
            break;

          case D:
            AZTrailController.escape = false;
            dDown = true;
            break;

          default:
            AZTrailController.escape = false;
            return;
        }
      }
    });
    this.setOnKeyReleased(new EventHandler<KeyEvent>() {
      @Override
      public void handle(KeyEvent event) {
        switch (event.getCode()) {
          case W:
            wDown = false;
            resetSpeed();
            break;

          case A:
            aDown = false;
            resetSpeed();
            break;

          case S:
            sDown = false;
            resetSpeed();
            break;

          case D:
            dDown = false;
            resetSpeed();
            break;
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
        if (!cooldown && AZTrailView.controller.getBullets() != 0) {
          fire();
        }
      }
    });
  }

  private void fire() {
    cooldown = true;
    bullet = new Circle(2, Color.BLACK);
    bullets.getChildren().add(bullet);
    bullets.setTopAnchor(bullet, (double) (y + height / 2));
    bullets.setLeftAnchor(bullet,(double) (x + width / 2));
    TranslateTransition path = new TranslateTransition();
    path.setDuration(Duration.millis((int) ((double) COOLDOWN_TIME
      * ((double) (Math.hypot(mouseX - x - width / 2,
      mouseY - y - height / 2))
      / (double) Math.hypot(CANVAS_WIDTH, CANVAS_HEIGHT)))));
    path.setNode(bullet);

    path.setToX(mouseX - x - width / 2);
    path.setToY(mouseY - y - height / 2);
    path.setCycleCount(1);
    path.onFinishedProperty().set(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent actionEvent) {
        bullets.getChildren().remove(bullet);
        cooldown = false;
        if (shots == MAX_SHOTS) {
          gameOver();
        }
      }
    });
    path.play();
    shots++;
    AZTrailView.controller.removeBullets(1);
    ammo.setText(AZTrailView.controller.getBullets() + " bullets");
  }

  private void gameOver() {
    AZTrailView.sounds.stopMusic();
    AZTrailView.sounds.startThemeLoop();
    AZTrailView.controller.addMoney(score);
    AZTrailView.stage.setScene(
      new GenericInfoMenu(new Runnable() {
        @Override
        public void run() {
          AZTrailView.stage.setScene(new SizeUpView());
        }
      },
      new String[]{
        "You shot " + new DecimalFormat("'$'###,##0.00").format(score)
        + " worth\nof tumbleweeds!\n"
      },
      true
    ));
  }

  private void tick() {
    boolean accelerate = false;
    if (wDown) {
      wPress();
      accelerate = true;
    }
    if (aDown) {
      aPress();
      accelerate = true;
    }
    if (sDown) {
      sPress();
      accelerate = true;
    }
    if (dDown) {
      dPress();
      accelerate = true;
    }
    if (accelerate) {
      accel();
    }
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

  private void wPress() {
    if (y - movespeed >= 0) {
      y -= movespeed;
    } else {
      y = 0;
    }
  }

  private void aPress() {
    if (x - movespeed >= 0) {
      x -= movespeed;
    } else {
      x = 0;
    }
  }

  private void sPress() {
    if (y + movespeed + height <= CANVAS_HEIGHT) {
      y += movespeed;
    } else {
      y = CANVAS_HEIGHT - height;
    }
  }

  private void dPress() {
    if (x + movespeed + width <= CANVAS_WIDTH) {
      x += movespeed;
    } else {
      x = CANVAS_WIDTH - width;
    }
  }

  private void accel() {
    if (i % ACCEL_RATE == 0 && movespeed < MAXSPEED) {
      movespeed++;
    }
  }

  private void resetSpeed() {
    if (!wDown && !aDown && !sDown && !dDown) {
      movespeed = MINSPEED;
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
