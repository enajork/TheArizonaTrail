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
import javafx.geometry.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.text.Font;
import javafx.event.*;
import java.util.*;
import java.io.*;
import javafx.scene.text.Text;
import javafx.scene.input.KeyCode;

import controller.*;

public class ProfMenu extends Scene {
  private Text body;
  private String contents = "You may:\n\n\t"
    + "1. Be a banker from Boston\n\t"
    + "2. Be a carpenter from Ohio\n\t"
    + "3. Be a farmer from Illinois\n\t"
    + "4. Find out the differences\n\t"
    + "   between these choices\n\n"
    + "What is your choice? ";
  private String input = "_";

  /**
   * [ProfMenu description]
   */
  public ProfMenu() {
    this(new BorderPane());
    getStylesheets().add(getClass()
      .getResource("assets/style.css").toExternalForm());
  }

  /**
   * [ProfMenu description]
   * @param root [description]
   */
  private ProfMenu(BorderPane root) {
    super(root, AZTrailView.WIDTH, AZTrailView.HEIGHT);

    // Create the tile image;
    Image img = new Image("file:view/assets/aztrail_splashtext.png");
    ImageView title = new ImageView(img);

    // Create the text for the menu options
    body = new Text(contents + input);
    body.setId("text12");
    body.setFill(Color.WHITE);

    BorderPane tile = new BorderPane();
    tile.setStyle("-fx-background-color: black;");

    // Create the first accent
    ImageView accent1 = menuAccent();
    tile.setTop(accent1);
    tile.setCenter(body);

    // Create the second accent
    ImageView accent2 = menuAccent();
    tile.setBottom(accent2);

    // Style the view
    root.setAlignment(title, Pos.CENTER);
    root.setAlignment(accent1, Pos.CENTER);
    root.setAlignment(accent2, Pos.CENTER);
    root.setStyle("-fx-background-color: black;");
    root.setTop(title);
    root.setCenter(tile);

    addEventHandlers();
  }

  /**
   * [menuAccent description]
   * @return [description]
   */
  private ImageView menuAccent() {
    return new ImageView(new Image("file:view/assets/menuaccent.png",
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
          case DIGIT1:
            updateInputText(1);
            break;

          case DIGIT2:
            updateInputText(2);
            break;

          case DIGIT3:
            updateInputText(3);
            break;

          case DIGIT4:
            updateInputText(4);
            break;

          case BACK_SPACE:
            if (input.length() >= 2) {
              input = input.substring(0, input.length() - 2);
              input += "_";
            }
            body.setText(contents + input);
            break;

          case ENTER:
            AZTrailView.stage.setScene(getSplashView(Integer.parseInt(input
              .substring(0, 1))));
            break;

          default:
            return;
        }
      }
    });
  }

  /**
   * [updateInputText description]
   * @param num [description]
   */
  private void updateInputText(int num) {
    if (input.length() == 1) {
      input = input.substring(0, input.length() - 1);
      input += num + "_";
      body.setText(contents + input);
    }
  }

  /**
   * [getSplashView description]
   * @param  choice [description]
   * @return        [description]
   */
  private Scene getSplashView(int choice) {
    if (choice < 1 || choice > 6) {
      throw new IllegalStateException();
    }
    switch (choice) {
      case 1:
        System.out.println(AZTrailView.model.getProf());
        AZTrailView.controller.profMenu(1);
        // party creation screen goes here
        return new GenericInfoMenu(new ProfMenu(), new String[]{
          "Banker selected"
        });
      case 2:
        System.out.println(AZTrailView.model.getProf());
        AZTrailView.controller.profMenu(2);
        return new GenericInfoMenu(new ProfMenu(), new String[]{
          "Carpenter selected"
        });
      case 3:
        System.out.println(AZTrailView.model.getProf());
        AZTrailView.controller.profMenu(3);
        return new GenericInfoMenu(new ProfMenu(), new String[]{
          "Farmer selected"
        });
      case 4:
        return new GenericInfoMenu(new ProfMenu(), new String[]{
          "Not yet implemented..."
        });
    }
    // return;
    return null;
  }
}
