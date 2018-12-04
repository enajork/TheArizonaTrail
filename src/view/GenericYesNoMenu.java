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
/**
 * @author Jordan Bridgewater, Jared Grady, Eric Najork, David Najork
 * @version     1.0
 * @since       1.0
 */

public class GenericYesNoMenu extends Scene {
  public static final int CHAR_LIMIT = 4;
  public static ImageView decor;
  public static String input = "_";
  public static BorderPane root;
  public static BorderPane tile;
  public static String prompt;
  public static String header;
  public static Text top;
  public static Text body;
  private boolean accentsOn;
  private boolean titleOn;
  private Runnable yesCall;
  private Runnable noCall;
  private String img;

  /**
   * Constructor for the generic yes no menu
   *
   * @param yesCall thread for the user choosing yes
   * @param noCall  thread for the user choosing no
   * @param prompt  the question for the user
   * @param header  the description of the prompt
   */
  public GenericYesNoMenu(Runnable yesCall, Runnable noCall, String prompt,
      String header) {
    this(new BorderPane(), yesCall, noCall, prompt, header, "", false, false);
  }

  /**
   * constructor for the yes no menu with imgs
   *
   * @param yesCall thread for the user choosing yes
   * @param noCall  thread for the user choosing no
   * @param prompt  the question for the user
   * @param img     the image to show the user
   * @param header  the description of the prompt
   */
  public GenericYesNoMenu(Runnable yesCall, Runnable noCall, String prompt,
      String img, String header) {
    this(new BorderPane(), yesCall, noCall, prompt, header, img, false, false);
  }

  /**
   * constructor for the yes no menu with accents on
   *
   * @param yesCall thread for the user choosing yes
   * @param noCall  thread for the user choosing no
   * @param prompt  the question for the user
   * @param img     the image to show the user
   * @param header  the description of the prompt
   * @param accentsOn whether or not to show accents
   */
  public GenericYesNoMenu(Runnable yesCall, Runnable noCall, String prompt,
      String header, String img, boolean accentsOn) {
    this(new BorderPane(), yesCall, noCall, prompt, header, img, accentsOn,
      false);
  }

  /**
   * Primary constructor for the yes no menu
   *
   * @param root   the root of the scene graph
   * @param yesCall thread for the user choosing yes
   * @param noCall  thread for the user choosing no
   * @param prompt  the question for the user
   * @param img     the image to show the user
   * @param header  the description of the prompt
   * @param accentsOn whether or not to show accents
   * @param titleOn true if the title should be shown
   */
  public GenericYesNoMenu(Runnable yesCall, Runnable noCall, String prompt,
      String img, boolean accentsOn, boolean titleOn) {
    this(new BorderPane(), yesCall, noCall, prompt, "", img, accentsOn,
      titleOn);
  }

  /**
   * [GenericYesNoMenu description]
   *
   * @param yesCall thread for the user choosing yes
   * @param noCall  thread for the user choosing no
   * @param prompt  the question for the user
   * @param img     the image to show the user
   * @param header  the description of the prompt
   * @param accentsOn whether or not to show accents
   * @param titleOn true if the title should be shown
   */
  private GenericYesNoMenu(BorderPane root, Runnable yesCall, Runnable noCall,
      String prompt, String header, String img, boolean accentsOn,
      boolean titleOn) {
    super(root, AZTrailView.WIDTH, AZTrailView.HEIGHT, Color.BLACK);
    getStylesheets().add(AZTrailView.styleSheet);
    this.yesCall = yesCall;
    this.noCall = noCall;
    this.accentsOn = accentsOn;
    this.titleOn = titleOn;
    this.header = header;
    this.prompt = prompt;
    this.root = root;
    this.img = img;

    tile = new BorderPane();
    tile.setMaxHeight(0);
    tile.setMaxWidth(0);
    tile.setMinWidth(600);
    tile.setStyle("-fx-background-color: black;");

    if (img.length() != 0) {
      decor = new ImageView(new Image("file:view/assests/graphics/"
        + img));
      decor.setPreserveRatio(true);
      decor.setFitWidth(100);
      tile.setAlignment(decor, Pos.CENTER);
      tile.setTop(decor);
      root.setMargin(decor, new Insets(5));
    }

    BorderPane banner = new BorderPane();
    top = new Text(header);
    top.setId("text12");
    top.setFill(Color.WHITE);

    banner.setTop(top);
    banner.setAlignment(top, Pos.CENTER);
    banner.setMargin(top, new Insets(10));

    // Create the title image;
    if (titleOn) {
      ImageView title = new ImageView(new Image("file:view/assets/graphics/"
        + "aztrail_splashtext.png"));
      banner.setAlignment(title, Pos.CENTER);
      banner.setTop(title);
      banner.setMargin(title, new Insets(5));
    }

    if (accentsOn) {
      // Create the first accent
      ImageView accent1 = menuAccent();
      // Create the second accent
      banner.setBottom(accent1);
      banner.setAlignment(accent1, Pos.CENTER);
      if (!titleOn) {
        banner.setMargin(accent1, new Insets(10, 0, 0, 0));
      }


      ImageView accent2 = menuAccent();
      root.setBottom(accent2);
      root.setMargin(accent2, new Insets(0, 0, 46, 0));
      root.setAlignment(accent2, Pos.CENTER);
    }
    root.setTop(banner);
    body = new Text(prompt + " " + input);
    body.setId("text12");
    tile.setBottom(body);
    body.setFill(Color.WHITE);
    tile.setAlignment(body, Pos.CENTER_LEFT);
    tile.setMargin(body, new Insets(20));

    root.setCenter(tile);
    root.setAlignment(tile, Pos.CENTER);

    // Style the view
    root.setStyle("-fx-background-color: black;");

    addEventHandlers();
  }

  /**
   * Creates a new menu accent node for the scene graph
   * @return an imageview into the accent
   */
  private ImageView menuAccent() {
    return new ImageView(new Image("file:view/assets/graphics/menuaccent.png",
      620, 40, false, false));
  }

  /**
   * Adds event handlers to the view to handle keyboard actions
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
            AZTrailController.escape = false;
            if (input.length() == 1) {
              return;
            }
            boolean yesTrue = true;
            boolean noTrue = true;
            String yes = "yes";
            String no = "no";
            for (int i = 0; i < input.length() - 1; i++) {
              if (input.toLowerCase().charAt(i) != yes.charAt(i)) {
                yesTrue = false;
              }
              if (i < no.length() && input.toLowerCase().charAt(i)
                  != no.charAt(i)) {
                noTrue = false;
              }
              if (i > no.length()) {
                noTrue = false;
              }
            }
            if (noTrue) {
              input = "_";
              body.setText(prompt + " " + input);
              noCall.run();
              return;
            }
            if (yesTrue) {
              input = "_";
              body.setText(prompt + " " + input);
              yesCall.run();
              return;
            }
            input = "_";
            body.setText(prompt + " " + input);
            break;

          case ESCAPE:
            AZTrailView.escapePressed(false);
            break;

          case S:
            AZTrailController.escape = false;
            if (event.isControlDown()) {
              AZTrailView.sounds.mute();
            }
            updateInputText(event);
            break;

          default:
            AZTrailController.escape = false;
            updateInputText(event);
            return;
        }
      }
    });
  }

  /**
   * updates the display when the user types in a selection the keyboard
   * @param num the value inserted by the user
   */
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
}
