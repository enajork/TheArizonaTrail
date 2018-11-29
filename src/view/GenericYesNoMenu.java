package view;

import javafx.animation.*;
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
import javafx.util.*;
import java.util.*;
import java.io.*;

import controller.*;

public class GenericYesNoMenu extends Scene {
  public static final int CHAR_LIMIT = 4;
  public static ImageView decor;
  public static String input = "_";
  public static BorderPane root;
  public static BorderPane tile;
  public static String prompt;
  public static Text body;
  public static int size;
  private boolean accentsOn;
  private Runnable yesCall;
  private Runnable noCall;
  private String img;

  /**
  * [GenericYesNoMenu description]
  */
  public GenericYesNoMenu(Runnable yesCall, Runnable noCall, String prompt) {
    this(new BorderPane(), yesCall, noCall, prompt, 12, "", false);
  }

  /**
  * [GenericYesNoMenu description]
  */
  public GenericYesNoMenu(Runnable yesCall, Runnable noCall, String prompt,
      int size) {
    this(new BorderPane(), yesCall, noCall, prompt, size, "", false);
  }

  /**
   * [GenericYesNoMenu description]
   */
  public GenericYesNoMenu(Runnable yesCall, Runnable noCall, String prompt,
      String img) {
    this(new BorderPane(), yesCall, noCall, prompt, 12, img, false);
  }

  /**
   * [GenericYesNoMenu description]
   */
  public GenericYesNoMenu(Runnable yesCall, Runnable noCall, String prompt,
      String img, boolean accentsOn) {
    this(new BorderPane(), yesCall, noCall, prompt, 12, img, accentsOn);
  }

  /**
   * [GenericYesNoMenu description]
   */
  public GenericYesNoMenu(Runnable yesCall, Runnable noCall, String prompt,
      int size, String img) {
    this(new BorderPane(), yesCall, noCall, prompt, size, img, false);
  }

  /**
   * [GenericYesNoMenu description]
   */
  public GenericYesNoMenu(Runnable yesCall, Runnable noCall, String prompt,
      int size, String img, boolean accentsOn) {
    this(new BorderPane(), yesCall, noCall, prompt, size, img, accentsOn);
  }

  /**
   * [GenericYesNoMenu description]
   * @param root [description]
   */
  private GenericYesNoMenu(BorderPane root, Runnable yesCall, Runnable noCall,
      String prompt, int size, String img, boolean accentsOn) {
    super(root, AZTrailView.WIDTH, AZTrailView.HEIGHT, Color.BLACK);
    getStylesheets().add(AZTrailView.styleSheet);
    this.yesCall = yesCall;
    this.noCall = noCall;
    this.accentsOn = accentsOn;
    this.prompt = prompt;
    this.root = root;
    this.size = size;
    this.img = img;

    tile = new BorderPane();
    tile.setMaxHeight(0);
    tile.setMaxWidth(0);
    tile.setStyle("-fx-background-color: black;");

    // Create the title image;
    if (img.length() != 0) {
      decor = new ImageView(new Image("file:view/assests/graphics/"
        + img));
      tile.setAlignment(decor, Pos.CENTER);
      tile.setTop(decor);
      root.setMargin(decor, new Insets(5));
    }

    if (accentsOn) {
      // Create the first accent
      ImageView accent1 = menuAccent();
      root.setTop(accent1);
      root.setAlignment(accent1, Pos.CENTER);
      // Create the second accent
      ImageView accent2 = menuAccent();
      root.setBottom(accent2);
      root.setMargin(accent2, new Insets(0, 0, 10, 0));
      root.setAlignment(accent2, Pos.CENTER);
    }
    body = new Text(prompt + " " + input);
    setTextSize(size);
    tile.setBottom(body);
    body.setFill(Color.WHITE);
    tile.setAlignment(body, Pos.CENTER);
    tile.setMargin(body, new Insets(20));

    root.setCenter(tile);
    root.setAlignment(tile, Pos.CENTER);

    // Style the view
    root.setStyle("-fx-background-color: black;");

    addEventHandlers();
  }

  /**
   * [menuAccent description]
   * @return [description]
   */
  private ImageView menuAccent() {
    return new ImageView(new Image("file:view/assets/graphics/menuaccent.png",
      620, 40, false, false));
  }

  /**
   * [addEventHandlers description]
   */
  private void addEventHandlers() {
    this.setOnKeyPressed(new EventHandler<KeyEvent>() {
      @Override
      public void handle(KeyEvent event) {
        switch (event.getCode()) {
          case BACK_SPACE:
            AZTrailController.escape = false;
            if (input.length() >= 2) {
              input = input.substring(0, input.length() - 2);
              input += "_";
              body.setText(prompt + " " + input);
            }
            break;

          case ENTER:
            boolean yesTrue = true;
            boolean noTrue = true;
            String yes = "yes";
            String no = "no";
            for (int i = 0; i < input.length() - 1; i++) {
              if (input.charAt(i) != yes.charAt(i)) {
                yesTrue = false;
              }
              if (i < no.length() && input.charAt(i) != no.charAt(i)) {
                noTrue = false;
              }
              if (i > no.length()) {
                noTrue = false;
              }
            }
            if (noTrue) {
              body.setText(prompt + " " + input);
              input = "_";
              noCall.run();
              return;
            }
            if (yesTrue) {
              yesCall.run();
              return;
            }
            AZTrailController.escape = false;
            input = "_";
            body.setText(prompt + " " + input);
            break;

          case ESCAPE:
            AZTrailView.escapePressed(true);
            break;

          case S:
            AZTrailController.escape = false;
            if (event.isControlDown()) {
              AZTrailView.sounds.mute();
            }
            updateInputText(event);
            break;

          default:
            updateInputText(event);
            AZTrailController.escape = false;
            return;
        }
      }
    });
  }

  private void updateInputText(KeyEvent event) {
    String letter = event.getText();
    if (letter.length() != 1) {
      return;
    }
    if (((letter.toLowerCase().charAt(0) != 'y'
      && letter.toLowerCase().charAt(0) != 'e'
      && letter.toLowerCase().charAt(0) != 's'
      && letter.toLowerCase().charAt(0) != 'n'
      && letter.toLowerCase().charAt(0) != 'o')
        || input.length() >= CHAR_LIMIT)) {
      return;
    }
    if (!event.isShiftDown()) {
      letter = letter.toLowerCase();
    }
    if (Character.isLetter(letter.charAt(0))) {
      input = input.substring(0, input.length() - 1);
      input += letter + "_";
      body.setText(prompt + " " + input);
    }
  }

  public static void fadeOut() {
    FadeTransition trans = new FadeTransition();
    trans.setDuration(Duration.millis(1000.0));
    trans.setNode(root);
    trans.setFromValue(1);
    trans.setToValue(0);
    trans.play();
  }

  private void setTextSize(int size) {
    switch (size) {
      case 8:
        body.setId("text8");
        break;
      case 10:
        body.setId("text10");
        break;
      case 12:
        body.setId("text12");
        break;
      case 14:
        body.setId("text14");
        break;
      case 16:
        body.setId("text16");
        break;
      case 18:
        body.setId("text18");
        break;
      case 20:
        body.setId("text20");
        break;
      default:
        body.setId("text12");
        break;
    }
  }


}
