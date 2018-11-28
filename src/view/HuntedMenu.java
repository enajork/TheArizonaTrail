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

public class HuntedMenu extends Scene {
  private Scene nextScene;
  private BorderPane root;
  private String input = '_';

  /**
   * [HuntedMenu description]
   */
  public HuntedMenu() {
    this(new BorderPane());
    AZTrailView.sounds.startThemeLoop();
    AZTrailView.sounds.cashOutSFX();
  }

  /**
   * [HuntedMenu description]
   * @param root [description]
   */
  private HuntedMenu(BorderPane root) {
    super(root, AZTrailView.WIDTH, AZTrailView.HEIGHT, Color.BLACK);
    getStylesheets().add(AZTrailView.styleSheet);
    this.nextScene = new SizeUpView();
    this.root = root;
    root.setStyle("-fx-background-color: black;");

    Image img = new Image("file:view/assets/graphics/nogales_splash.png");
    ImageView splash = new ImageView(img);
    splash.setPreserveRatio(true);
    splash.setFitWidth(AZTrailView.WIDTH * 0.985);
    root.setAlignment(splash, Pos.CENTER);
    root.setTop(splash);
    Rectangle rect = new Rectangle(400, 40, Color.WHITE);
    BorderPane innerText = new BorderPane();
    Text title = new Text("WILL YOU BE HUNTED?");
    title.setId("text20");
    String prompt = "Would you like to enable hunted mode? ";
    Text info = new Text(prompt + input);
    info.setId("text16");
    innerText.setTop(title);
    innerText.setBottom(info);
    innerText.setAlignment(title, Pos.CENTER);
    innerText.setAlignment(info, Pos.CENTER);
    innerText.setMargin(title, new Insets(10, 0, 0, 0));
    innerText.setMargin(info, new Insets(0, 0, 10, 0));
    innerText.setMaxHeight(100);
    StackPane stack = new StackPane();
    stack.getChildren().add(rect);
    stack.getChildren().add(innerText);
    root.setCenter(stack);
    root.setMargin(stack, new Insets(2));
    root.setMargin(splash, new Insets(5, 5, 0, 5));
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
            }
            if (select) {
              footer.setText(PROMPT2 + input);
              return;
            }
            if (!confirmation) {
              names[curr].setText((curr + 1) + ". " + input);
            } else {
              footer.setText(PROMPT1 + input);
            }
            break;

          case ENTER:
            AZTrailController.escape = false;
            if (select) {
              if (input.length() == 2) {
                select = false;
                edit = true;
                input = input.substring(0, input.length() - 1);
                footer.setText(PROMPT2 + input);
                curr = Integer.parseInt(input) - 1;
                input = "_";
                editTemp = names[curr].getText();
                names[curr].setText((curr + 1) + ". " + input);
              }
              return;
            }
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
                confirmation = false;
                select = true;
                input = "_";
                footer.setText(PROMPT2 + input);
                return;
              }
              if (yesTrue) {
                AZTrailView.stage.setScene(new MonthMenu());
              }
              input = "_";
              footer.setText(PROMPT1 + input);
            }
            if (edit) {
              if (input.length() == 1) {
                names[curr].setText(editTemp);
              } else {
                input = input.substring(0, input.length() - 1);
                names[curr].setText((curr + 1) + ". " + input);
              }
              AZTrailView.controller.setName(curr,
                names[curr].getText().substring(3,
                names[curr].getText().length()));
              input = "_";
              footer.setText(PROMPT1 + input);
              edit = false;
              curr = 4;
              confirm();
              return;
            }
            if (!confirmation && input.length() == 1) {
              String[] presets = {"Henry", "Sara", "Zeke", "Beth"};
              do {
                AZTrailView.controller.setName(curr, presets[curr - 1]);
                names[curr].setText((curr + 1) + ". " + presets[curr - 1]);
                curr++;
              } while (curr <= AZTrailView.controller.partySize() - 1);
              confirm();
            }
            if (curr == AZTrailView.controller.partySize() - 1 && !confirmation) {
              AZTrailView.controller.setName(curr, input.substring(0,
                input.length() - 1));
              names[curr].setText((curr + 1) + ". " + input.substring(0,
                input.length() - 1));
              input = "_";
              confirm();
            }
            if (curr < AZTrailView.controller.partySize() - 1) {
              AZTrailView.controller.setName(curr, input.substring(0,
                input.length() - 1));
                names[curr].setText((curr + 1) + ". " + input.substring(0,
                  input.length() - 1));
              input = "_";
              curr++;
              names[curr].setText(names[curr].getText() + input);
            }
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
    if (select && input.length() < 2
        && Character.isDigit(letter.charAt(0))
        && Integer.parseInt(letter.substring(0, 1)) >= 1
        && Integer.parseInt(letter.substring(0, 1))
        <= AZTrailView.controller.partySize()) {
      input = input.substring(0, input.length() - 1);
      input += letter + "_";
      footer.setText(PROMPT2 + input);
    }
    if (select) {
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
      footer.setText(PROMPT1 + input);
    }
  }

  private void confirm() {
    footer.setText(PROMPT1 + input);
    flow.setMargin(footer, new Insets(0, 0, 0, 0));
    confirmation = true;
  }
}
