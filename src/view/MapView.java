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

public class MapView extends Scene {
  private final int SCENE_WIDTH = AZTrailView.WIDTH;
  private final int SCENE_HEIGHT = AZTrailView.HEIGHT;
  private BorderPane root;

  /**
   * [MapView description]
   */
  public MapView() {
    this(new BorderPane());
    getStylesheets().add(AZTrailView.styleSheet);
  }

  /**
   * [MapView description]
   * @param root [description]
   */
  private MapView(BorderPane root) {
    super(root, AZTrailView.WIDTH, AZTrailView.HEIGHT);
    AZTrailView.sounds.startBackgroundSFX();
    AZTrailView.sounds.startThemeLoop();
    this.root = root;
    root.setStyle("-fx-background-color: " + ((AZTrailView.controller
      .getHunted()) ? "black" : "white") + ";");
    Image map = new Image((AZTrailView.controller.getHunted())
      ? "file:view/assets/graphics/map-hunted.png"
      : "file:view/assets/graphics/map.png",
        SCENE_WIDTH, SCENE_HEIGHT, true, true);
    Image route = new Image((AZTrailView.controller.getHunted())
      ? "file:view/assets/graphics/route-hunted.png"
      : "file:view/assets/graphics/route.png",
      SCENE_WIDTH, SCENE_HEIGHT, true, true);
    ImageView mapView = new ImageView(map);
    ImageView routeView = new ImageView(route);
    Rectangle eclipse = new Rectangle(0, 0, SCENE_WIDTH, getProgress());
    eclipse.setFill((AZTrailView.controller.getHunted()) ? Color.BLACK
      : Color.WHITE);
    AnchorPane eclipseAnchor = new AnchorPane(eclipse);
    eclipseAnchor.setTopAnchor(eclipse, 0.0);
    StackPane topography = new StackPane(routeView, eclipseAnchor, mapView);
    AnchorPane anchor = new AnchorPane(topography);
    BorderPane view = new BorderPane(anchor);
    Rectangle2D port = new Rectangle2D(0, 0, SCENE_WIDTH, SCENE_HEIGHT);
    routeView.setViewport(port);
    mapView.setViewport(port);
    view.setCenter(topography);
    view.setAlignment(topography, Pos.CENTER);
    view.setMargin(topography, new Insets(10));
    // anchor.setLeftAnchor(topography, 110.0);

    // Create the pane containing the current stats
    root.setCenter(view);
    root.setAlignment(view, Pos.CENTER);
    addEventHandlers();
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
            AZTrailView.stage.setScene(new SizeUpView());
            break;

          case ESCAPE:
            AZTrailView.escapePressed(true);
            break;

          case S:
            AZTrailController.escape = false;
            if (event.isControlDown()) {
              AZTrailView.sounds.mute();
            }
            break;

          case ENTER:
            AZTrailController.escape = false;
            AZTrailView.stage.setScene(new SizeUpView());
            break;
          default:
            AZTrailController.escape = false;
            break;
        }
      }
    });
  }

  private int getProgress() {
    int[] cities = {338, 318, 300, 240, 174, 158};
    int[] distances = {20, 18, 60, 66, 16, 87};
    double ratio = AZTrailView.controller.getDistRatio();
    switch (AZTrailView.controller.getCurrentCity()) {
      case "Nogales":
        return (cities[0] - (int)((double)distances[0] * ratio));
      case "Tombstone":
        return (cities[1] - (int)((double)distances[1] * ratio));
      case "Tucson":
        return (cities[2] - (int)((double)distances[2] * ratio));
      case "Phoenix":
        return (cities[3] - (int)((double)distances[3] * ratio));
      case "Sedona":
        return (cities[4] - (int)((double)distances[4] * ratio));
      case "Flagstaff":
        return (cities[5] - (int)((double)distances[5] * ratio));
    }
    return 0;
  }
}
