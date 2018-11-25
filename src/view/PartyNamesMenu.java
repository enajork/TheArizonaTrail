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

public class PartyNamesMenu extends Scene {
  private final String PROMPT = "\nAre these names correct? ";
  private final int CHAR_LIMIT = 12;
  private Text header = new Text("What are the first names of the\nfour other"
    + " members in your party?\n");
  private boolean confirmation = false;
  private String input = "_";
  private FlowPane flow;
  private Text footer;
  private Text[] names;
  private int curr;

  /**
   * [PartyNamesMenu description]
   */
  public PartyNamesMenu() {
    this(new BorderPane());
    getStylesheets().add(AZTrailView.styleSheet);
  }

  /**
   * [PartyNamesMenu description]
   * @param root [description]
   */
  private PartyNamesMenu(BorderPane root) {
    super(root, AZTrailView.WIDTH, AZTrailView.HEIGHT, Color.BLACK);
    names = new Text[AZTrailView.controller.partySize()];

    AZTrailView.controller.resetPartyNames();

    // Create the tile image;
    flow = new FlowPane(Orientation.VERTICAL);
    flow.setStyle("-fx-background-color: black;");
    flow.setColumnHalignment(HPos.LEFT);
    Image img = new Image("file:view/assets/partymenu.png");
    ImageView decor = new ImageView(img);
    decor.setPreserveRatio(true);
    decor.setFitWidth(600);

    // Create the text for the menu options
    header.setId("text12");
    header.setFill(Color.WHITE);

    footer = new Text("\n(Enter names or press Enter)");
    footer.setId("text12");
    footer.setFill(Color.WHITE);

    flow.getChildren().add(decor);
    flow.getChildren().add(header);

    for (int i = 0; i < names.length; i++) {
      if (i == 0) {
        names[i] = new Text((i + 1) + ". " + AZTrailView.controller.getName(0));
      } else {
        names[i] = new Text((i + 1) + ". ");
      }
      names[i].setId("text12");
      names[i].setFill(Color.WHITE);
      flow.getChildren().add(names[i]);
    }

    flow.getChildren().add(footer);
    flow.setMargin(footer, new Insets(0, 100, 0, 100));

    root.setMargin(flow, new Insets(5));
    root.setStyle("-fx-background-color: black;");
    root.setCenter(flow);

    addEventHandlers();

    curr = 1;
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
            if (input.length() >= 2) {
              input = input.substring(0, input.length() - 2);
              input += "_";
            }
            if (!confirmation) {
              names[curr].setText((curr + 1) + ". " + input);
            } else {
              footer.setText(PROMPT + input);
            }
            break;

          case ENTER:
            if (confirmation && input.length() > 1) {
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
                AZTrailView.stage.setScene(new PartyNamesMenu());
              }
              if (yesTrue) {
                System.out.println("next scene!");
                // AZTrailView.stage.setScene();
              }
              input = "_";
              footer.setText(PROMPT + input);
            }
            if (curr == 1 && input.length() == 1) {
              names[1].setText(names[1].getText() + "Henry");
              names[2].setText(names[2].getText() + "Sara");
              names[3].setText(names[3].getText() + "Zeke");
              names[4].setText(names[4].getText() + "Beth");
              curr = 4;
              confirm();
            }
            if (curr == AZTrailView.controller.partySize() - 1 && !confirmation) {
              AZTrailView.controller.setName(input.substring(0,
                input.length() - 1));
              names[curr].setText((curr + 1) + ". " + input.substring(0,
                input.length() - 1));
              input = "_";
              confirm();
            }
            if (curr < AZTrailView.controller.partySize() - 1) {
              AZTrailView.controller.setName(input.substring(0,
                input.length() - 1));
                names[curr].setText((curr + 1) + ". " + input.substring(0,
                  input.length() - 1));
              input = "_";
              curr++;
              names[curr].setText(names[curr].getText() + input);;
            }
            break;

          default:
            updateInputText(event, curr);
        }
      }
    });
  }

  /**
   * [updateInputText description]
   * @param num [description]
   */
  private void updateInputText(KeyEvent event, int i) {
    String letter = event.getText();
    if (letter.length() != 1) {
      return;
    }
    if (confirmation && ((letter.toLowerCase().charAt(0) != 'y'
      && letter.toLowerCase().charAt(0) != 'e'
      && letter.toLowerCase().charAt(0) != 's'
      && letter.toLowerCase().charAt(0) != 'n'
      && letter.toLowerCase().charAt(0) != 'o')
        || input.length() >= 4)) {
      return;
    }
    if (!event.isShiftDown()) {
      letter = letter.toLowerCase();
    }
    if (input.length() <= CHAR_LIMIT && Character.isLetter(letter.charAt(0))) {
      input = input.substring(0, input.length() - 1);
      input += letter + "_";
    }
    if (!confirmation) {
      names[i].setText((i + 1) + ". " + input);
    } else {
      footer.setText(PROMPT + input);
    }
  }

  private void confirm() {
    footer.setText(PROMPT + input);
    flow.setMargin(footer, new Insets(0, 0, 0, 0));
    confirmation = true;
  }
}
