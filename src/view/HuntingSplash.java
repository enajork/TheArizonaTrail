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

public class HuntingSplash extends Scene {
  private Scene nextScene;
  private BorderPane root;
  private String city;

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
    innerText.setTop(title);
    innerText.setAlignment(title, Pos.CENTER);
    innerText.setMargin(title, new Insets(10, 0, 0, 0));
    innerText.setMaxHeight(100);
    TilePane pane = new TilePane();
    Image keys = new Image("file:view/assets/graphics/hunt-instructions.png");
    ImageView instructions = new ImageView(keys);
    instructions.setPreserveRatio(true);
    instructions.setFitWidth(104);
    root.setMargin(instructions, new Insets(5, 5, 0, 5));
    root.setAlignment(instructions, Pos.CENTER);
    root.setTop(instructions);
    pane.getChildren().add(innerText);
    root.setCenter(pane);
    root.setMargin(pane, new Insets(2));

    // Style the view
    Text footer = new Text("Press SPACE BAR to continue");
    footer.setId("text12");
    footer.setFill(Color.WHITE);
    root.setBottom(footer);
    root.setAlignment(footer, Pos.CENTER);
    root.setMargin(footer, new Insets(0, 0, 20, 0));

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
