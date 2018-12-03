package view;

import javafx.scene.control.Alert.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.input.*;
import javafx.scene.text.*;
import javafx.application.*;
import javafx.scene.input.*;
import javafx.scene.image.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.scene.*;
import javafx.beans.value.*;
import javafx.geometry.*;
import javafx.stage.*;
import javafx.event.*;
import java.util.*;
import java.io.*;

import controller.*;
/**
 * @author Jordan Bridgewater, Jared Grady, Eric Najork, David Najork
 * @version     1.0
 * @since       1.0
 */

public class HuntingSplash extends Scene {
  private Scene nextScene;
  private BorderPane root;

  /**
   * [HuntingSplash description]
   */
  public HuntingSplash() {
    this(new BorderPane());
    AZTrailView.sounds.stopMusic();
    AZTrailView.sounds.huntingSong();
  }

  /**
   * [HuntingSplash description]
   * @param root [description]
   */
  private HuntingSplash(BorderPane root) {
    super(root, AZTrailView.WIDTH, AZTrailView.HEIGHT, Color.BLACK);
    getStylesheets().add(AZTrailView.styleSheet);
    this.nextScene = new HuntingView();
    this.root = root;
    root.setStyle("-fx-background-color: black;");

    BorderPane innerText = new BorderPane();
    Text title = new Text("Hunting Instructions");
    title.setId("text12");
    title.setFill(Color.WHITE);
    innerText.setTop(title);
    innerText.setAlignment(title, Pos.CENTER);
    innerText.setMargin(title, new Insets(10, 0, 0, 0));
    innerText.setMaxHeight(100);
    BorderPane pane = new BorderPane();

    TilePane instructions = new TilePane();
    instructions.setHgap(50);
    instructions.setPrefColumns(2);
    Image keys = new Image("file:view/assets/graphics/hunt-instructions.png");
    ImageView diagram = new ImageView(keys);
    diagram.setPreserveRatio(true);
    diagram.setFitWidth(104);
    Text directions = new Text("Move UP, LEFT, DOWN, and RIGHT\n\nusing the W,"
        + " A, S, and D keys\n\n\n"
        + "Aim by pointing and moving\n\nthe CURSOR\n\n\n"
        + "Shoot by pressing the\n\nLEFT MOUSE BUTTON\n\n\n"
        + "There is no prey in sight...\n\nJust a few tumbleweeds...");
    directions.setId("text10");
    directions.setFill(Color.WHITE);
    instructions.getChildren().addAll(diagram, directions);
    AnchorPane controls = new AnchorPane(diagram, directions);
    root.setCenter(controls);
    controls.setTopAnchor(diagram, 40.0);
    controls.setLeftAnchor(diagram, 25.0);
    controls.setTopAnchor(directions, 65.0);
    controls.setLeftAnchor(directions, 150.0);
    pane.getChildren().add(innerText);
    root.setTop(innerText);
    root.setMargin(pane, new Insets(2));

    // Style the view
    Text footer = new Text("Press SPACE BAR to continue");
    footer.setId("text12");
    footer.setFill(Color.WHITE);
    root.setBottom(footer);
    root.setAlignment(footer, Pos.CENTER);
    root.setMargin(footer, new Insets(0, 0, 20, 10));

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
            AZTrailView.stage.setScene(nextScene);
            break;

          case ENTER:
            AZTrailController.escape = false;
            AZTrailView.stage.setScene(nextScene);
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

          default:
            AZTrailController.escape = false;
            return;
        }
      }
    });
  }
}
