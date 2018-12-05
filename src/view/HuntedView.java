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

public class HuntedView extends Scene {
  private boolean wDown = false;
  private boolean aDown = false;
  private boolean sDown = false;
  private boolean dDown = false;

  private static final int SIZE = 43;
  private final int START_HEALTH = 5;
  private int health = START_HEALTH;

  private final boolean INFINITE_AMMO = false;
  private final int TIMEOUT = 15000;
  private final int COOLDOWN_TIME = 300;
  private int shots = 0;
  private int hits = 0;
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
  private Image up = new Image("file:view/assets/graphics/hunter/up.png");
  private Image down = new Image("file:view/assets/graphics/hunter/down.png");
  private Image left = new Image("file:view/assets/graphics/hunter/left.png");
  private Image right = new Image("file:view/assets/graphics/hunter/right.png");
  private Image downleft = new Image("file:view/assets/graphics/hunter/downleft.png");
  private Image downright = new Image("file:view/assets/graphics/hunter/downright.png");
  private Image upleft = new Image("file:view/assets/graphics/hunter/upleft.png");
  private Image upright = new Image("file:view/assets/graphics/hunter/upright.png");
  private Image owenUnhurt = new Image("file:view/assets/graphics/hunter/wilson/undamaged/downleft.png");
  private Image owenHurt = new Image("file:view/assets/graphics/hunter/wilson/damaged/downleft.png");
  private Image owen = owenUnhurt;
  private ImageView owenView = new ImageView(owen);
  private Image img = up;
  private final int CANVAS_WIDTH = (int)canvas.getWidth();
  private final int CANVAS_HEIGHT = (int)canvas.getHeight();
  private int width = 38;
  private int height = 46;
  private double owenX = 0.0;
  private double owenY = (double) CANVAS_HEIGHT - height;
  private int x = 0;
  private int y = CANVAS_HEIGHT - height;
  private int mouseX = CANVAS_WIDTH / 2;
  private int mouseY = 0;
  private AnchorPane bullets = new AnchorPane();
  private Circle bullet;
  private GraphicsContext gc = canvas.getGraphicsContext2D();
  private BorderPane root;
  private AnchorPane info;
  private AnchorPane owenAnchor;
  private Text ammo = new Text(AZTrailView.controller.getBullets() + " bullets");

  /**
   * [HuntedView description]
   */
  public HuntedView() {
    this(new BorderPane());
    getStylesheets().add(AZTrailView.styleSheet);
  }

  /**
   * [HuntedView description]
   * @param root [description]
   */
  private HuntedView(BorderPane root) {
    super(root, AZTrailView.WIDTH, AZTrailView.HEIGHT, Color.BLACK);
    this.root = root;
    root.setStyle("-fx-background-color: rgb(142, 141, 117);");
    Image rock1 = new Image("file:view/assets/graphics/terrain/rock1-hunted.png",
        60, 35, false, true);
    Image rock2 = new Image("file:view/assets/graphics/terrain/rock2-hunted.png",
        50, 50, false, true);
    Image rock3 = new Image("file:view/assets/graphics/terrain/rock1-hunted.png",
        50, 30, false, true);
    Image rock4 = new Image("file:view/assets/graphics/terrain/rock2-hunted.png",
        70, 70, false, true);
    Image rock5 = new Image("file:view/assets/graphics/terrain/rock1-mirrored-hunted.png",
        60, 35, false, true);
    Image rock6 = new Image("file:view/assets/graphics/terrain/rock2-mirrored-hunted.png",
        50, 50, false, true);
    Image rock7 = new Image("file:view/assets/graphics/terrain/rock1-mirrored-hunted.png",
        50, 30, false, true);
    Image rock8 = new Image("file:view/assets/graphics/terrain/rock2-mirrored-hunted.png",
        70, 70, false, true);
    Image cactus1 = new Image("file:view/assets/graphics/terrain/cactus1-hunted.png",
        50, 70, false, true);
    Image cactus2 = new Image("file:view/assets/graphics/terrain/cactus2-hunted.png",
        40, 68, false, true);
    Image cactus3 = new Image("file:view/assets/graphics/terrain/cactus1-hunted.png",
        40, 56, false, true);
    Image cactus4 = new Image("file:view/assets/graphics/terrain/cactus2-hunted.png",
        50, 85, false, true);
    Image cactus5 = new Image("file:view/assets/graphics/terrain/cactus1-mirrored-hunted.png",
        50, 70, false, true);
    Image cactus6 = new Image("file:view/assets/graphics/terrain/cactus2-mirrored-hunted.png",
        40, 68, false, true);
    Image cactus7 = new Image("file:view/assets/graphics/terrain/cactus1-mirrored-hunted.png",
        40, 56, false, true);
    Image cactus8 = new Image("file:view/assets/graphics/terrain/cactus2-mirrored-hunted.png",
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
    info.setLeftAnchor(ammo, 450.0);
    layers.getChildren().add(info);
    root.setCenter(layers);
    gc.drawImage(img, 0, 0, width, height, 0, CANVAS_HEIGHT - height,
      width, height);
    owenAnchor = new AnchorPane();
    owenAnchor.getChildren().add(owenView);
    owenAnchor.setTopAnchor(owenView, owenX);
    owenAnchor.setLeftAnchor(owenView, owenY);
    layers.getChildren().add(owenAnchor);

    addEventHandlers();

    AnimationTimer timer = new AnimationTimer() {
      @Override
      public void handle(long now) {
        tick();
      }
    };
    timer.start();

    SequentialTransition pause = new SequentialTransition (
      new PauseTransition(Duration.millis(TIMEOUT)));
    pause.onFinishedProperty().set(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent actionEvent) {
        gameOver(false);
      }
    });
    pause.play();
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
            if (event.isControlDown()) {
              AZTrailView.sounds.mute();
            }
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
    AZTrailView.sounds.musketSFX();
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
        if (bullet.getBoundsInParent().intersects(owenView.getBoundsInParent())) {
          owenView.setImage(owenHurt);
          AZTrailView.sounds.ow2SFX();
          owenX = (new Random().nextInt((400) + 1));
          owenY = (new Random().nextInt((400) + 1));
          owenAnchor.setTopAnchor(owenView, owenX);
          owenAnchor.setLeftAnchor(owenView, owenY);

          SequentialTransition pause = new SequentialTransition (
            new PauseTransition(Duration.millis(700)));
            pause.onFinishedProperty().set(new EventHandler<ActionEvent>() {
              @Override
              public void handle(ActionEvent actionEvent) {
                owenView.setImage(owenUnhurt);
              }
            });
            pause.play();
          health--;
        }
        if (shots == START_HEALTH) {
          gameOver(false);
        }
      }
    });
    path.play();
    if (INFINITE_AMMO) {
      return;
    }
    shots++;
    AZTrailView.controller.removeBullets(1);
    ammo.setText(AZTrailView.controller.getBullets() + " bullets");
  }

  private void gameOver(boolean won) {
    if (won) {
      AZTrailView.sounds.stopMusic();
      AZTrailView.sounds.startThemeLoop();
      AZTrailView.sounds.wow1SFX();
      AZTrailView.stage.setScene(
        new GenericInfoMenu(new Runnable() {
          @Override
          public void run() {
            AZTrailView.stage.setScene(new SizeUpView());
          }
        },
        new String[]{
          "You defeated Owen Wilson!\nTime to finish your\njourney..."
        },
        true
      ));
    } else {
      AZTrailView.sounds.stopMusic();
      AZTrailView.sounds.startThemeLoop();
      AZTrailView.sounds.wow1SFX();
      AZTrailView.stage.setScene(
        new GenericInfoMenu(new Runnable() {
          @Override
          public void run() {
            AZTrailView.stage.setScene(new GameOver());
          }
        },
        new String[]{
          "You took too long...\nOwen Wilson captured and\nkilled your party."
        },
        true
      ));
    }
  }

  private void tick() {
    if (health == 0) {
      gameOver(true);
    }
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
}
