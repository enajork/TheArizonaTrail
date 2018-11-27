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
import java.text.*;
import java.util.*;
import java.io.*;

import controller.*;

public class StoreMenu extends Scene {
  private final Rectangle rect1 = new Rectangle(AZTrailView.WIDTH * 0.77 ,
    AZTrailView.HEIGHT * 0.016, Color.RED);
  private final Rectangle rect2 = new Rectangle(AZTrailView.WIDTH * 0.77 ,
    AZTrailView.HEIGHT * 0.016, Color.RED);
  private final Rectangle rect3 = new Rectangle(AZTrailView.WIDTH * 0.77 ,
    AZTrailView.HEIGHT * 0.016, Color.RED);
  private final int NUM_OPTS = 5;
  private String item1 = new DecimalFormat("'$'###,##0.00")
    .format(AZTrailView.controller.getCartOxen());
  private String item2 = new DecimalFormat("'$'###,##0.00")
    .format(AZTrailView.controller.getCartFood());
  private String item3 = new DecimalFormat("'$'###,##0.00")
    .format(AZTrailView.controller.getCartClothes());
  private String item4 = new DecimalFormat("'$'###,##0.00")
    .format(AZTrailView.controller.getCartAmmo());
  private String item5 = new DecimalFormat("'$'###,##0.00")
    .format(AZTrailView.controller.getCartParts());
  private Text body;
  private String prompt = "  Which item would you\n  like to buy? ";
  private String input = "_";

  /**
   * [StoreMenu description]
   */
  public StoreMenu() {
    this(new BorderPane());
    getStylesheets().add(AZTrailView.styleSheet);
    AZTrailView.escape = false;
  }

  /**
   * [StoreMenu description]
   * @param root [description]
   */
  private StoreMenu(BorderPane root) {
    super(root, AZTrailView.WIDTH, AZTrailView.HEIGHT, Color.BLACK);
    BorderPane tile = new BorderPane();
    tile.setStyle("-fx-background-color: black;");
    BorderPane receiptBody = new BorderPane();
    receiptBody.setStyle("-fx-background-color: black;");
    BorderPane subTotals = new BorderPane();
    subTotals.setStyle("-fx-background-color: black;");
    subTotals.setMaxWidth(AZTrailView.WIDTH / 1.65);
    BorderPane banner = new BorderPane();
    banner.setStyle("-fx-background-color: black;");
    BorderPane lower = new BorderPane();
    lower.setStyle("-fx-background-color: black;");
    BorderPane billBody = new BorderPane();
    billBody.setStyle("-fx-background-color: black;");
    billBody.setMaxWidth(AZTrailView.WIDTH / 2.3);
    BorderPane totalBody = new BorderPane();
    totalBody.setStyle("-fx-background-color: black;");
    root.setCenter(tile);
    tile.setTop(banner);
    tile.setCenter(receiptBody);
    tile.setBottom(lower);
    tile.setMargin(lower, new Insets(40));
    banner.setTop(rect1);
    banner.setAlignment(rect1, Pos.CENTER);
    banner.setMargin(rect1, new Insets(5));
    banner.setBottom(rect2);
    banner.setAlignment(rect2, Pos.CENTER);
    banner.setMargin(rect2, new Insets(5));
    banner.setMargin(rect3, new Insets(5));
    receiptBody.setTop(subTotals);
    receiptBody.setAlignment(subTotals, Pos.CENTER_RIGHT);
    receiptBody.setMargin(subTotals, new Insets(0, 70, 0, 0));
    receiptBody.setCenter(rect3);
    receiptBody.setAlignment(rect3, Pos.CENTER);
    receiptBody.setMaxHeight(100);
    receiptBody.setAlignment(billBody, Pos.CENTER_RIGHT);
    receiptBody.setMargin(billBody, new Insets(0, 70, 0, 0));
    receiptBody.setBottom(billBody);

    // Create the image;
    Image img = new Image("file:view/assets/graphics/menuclerk.png");
    ImageView decor = new ImageView(img);
    decor.setPreserveRatio(true);
    decor.setFitWidth(100);
    root.setLeft(decor);
    root.setMargin(decor, new Insets(100, 10, 10, 10));

    // Create the text for the menu options
    body = new Text(prompt + input);
    body.setId("text12");
    body.setFill(Color.WHITE);

    // Create the text for the menu options
    Text header = new Text("Matt's General Store\nNogales, Arizona\n\n     "
      + AZTrailView.controller.getDateStr());
    header.setId("text12");
    header.setFill(Color.WHITE);
    banner.setCenter(header);

    // Create the text for the menu options
    Text footer = new Text("Press SPACE BAR to\nleave store");
    footer.setId("text12");
    footer.setFill(Color.WHITE);

    // Create the text for the menu options
    Text bill = new Text(new DecimalFormat("'$'###,##0.00")
      .format(AZTrailView.controller.getCartTotal()));
    bill.setId("text12");
    bill.setFill(Color.WHITE);

    // Create the text for the menu options
    Text total = new Text(new DecimalFormat("'$'###,##0.00")
      .format(AZTrailView.controller.getMoney()));
    total.setId("text12");
    total.setFill(Color.WHITE);

    // Create the text for the menu options
    Text items = new Text("1. Oxen\n2. Food\n3. Clothing\n4. Ammunition\n5. "
      + "Spare parts");
    items.setId("text12");
    items.setFill(Color.WHITE);
    subTotals.setLeft(items);

    // Create the text for the menu options
    Text receipt = new Text(item1 + "\n" + item2 + "\n" + item3 + "\n" + item4
      + "\n" + item5);
    receipt.setId("text12");
    receipt.setFill(Color.WHITE);
    subTotals.setRight(receipt);

    // Create the text for the menu options
    Text billText = new Text("Total bill:");
    billText.setId("text12");
    billText.setFill(Color.WHITE);

    // Create the text for the menu options
    Text totalText = new Text("Amount you have: ");
    totalText.setId("text12");
    totalText.setFill(Color.WHITE);

    billBody.setLeft(billText);
    billBody.setRight(bill);

    lower.setTop(totalBody);
    lower.setAlignment(totalBody, Pos.CENTER_RIGHT);
    lower.setMargin(totalBody, new Insets(0, 70, 30, 0));
    lower.setBottom(body);
    lower.setAlignment(body, Pos.CENTER_LEFT);
    totalBody.setLeft(totalText);
    totalBody.setRight(total);

    root.setStyle("-fx-background-color: black;");
    root.setCenter(tile);
    root.setBottom(footer);
    root.setAlignment(footer, Pos.CENTER);
    root.setMargin(footer, new Insets(5));

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
          case BACK_SPACE:
            AZTrailView.escape = false;
            if (input.length() >= 2) {
              input = input.substring(0, input.length() - 2);
              input += "_";
            }
            body.setText(prompt + input);
            break;

          case ENTER:
            AZTrailView.escape = false;
            if (input.length() == 2) {
              AZTrailView.stage.setScene(getNextView(Integer.parseInt(input
                .substring(0, 1))));
            }
            break;

          case ESCAPE:
            if (AZTrailView.escape) {
              System.exit(0);
              // AZTrailView.stage.setScene(new StoreMenu());
            } else {
              AZTrailView.escape = true;
            }
            break;

          default:
            AZTrailView.escape = false;
            if (event.getText().length() > 0
                && Character.isDigit(event.getText().charAt(0))) {
              updateInputText(Integer.parseInt(event.getText()));
            }
        }
      }
    });
  }

  /**
   * [updateInputText description]
   * @param num [description]
   */
  private void updateInputText(int num) {
    if (input.length() == 1 && num >= 1 && num <= NUM_OPTS) {
      input = input.substring(0, input.length() - 1);
      input += num + "_";
      body.setText(prompt + input);
    }
  }

  /**
   * [getNextView description]
   * @param  choice [description]
   * @return        [description]
   */
  private Scene getNextView(int choice) {
    if (choice < 1 || choice > NUM_OPTS) {
      throw new IllegalStateException();
    }
    switch (choice) {
      case 1:
        // Travel the trail
        return new ProfMenu();
        // return new ProfMenuView();
      case 2:
        // Learn about the trail
        return new GenericInfoMenu(new StoreMenu(), 10, new String[]{
          "Try taking a journey by covered wagon across\n200 miles of desert, "
          + "mountains, and canyons.\nTry! In the desert, will you slosh your oxen"
          + "\nthrough dirt and ruts or will you plod through\ndust six inches deep?",
          "How will you cross the canyons?\nIf you have money, you might hire"
          + "\na guide (if there is a guide) or\nyou can try and cross it yourself"
          + "\nand hope you and your wagon aren't\nlost forever!",
          "What about supplies? Well, if\nyou're low on food you can\nhunt. You might"
          + " get an Elk...\nyou might. And there are bears\nin the mountains.",
          "If for some reason you don't\nsurvive -- your wagon burns,\nor bandits "
          + "steal your oxen, or\nyou run out of provisions, or\nyou die of "
          + "dehydration -- don't\ngive up!  Try again... and again...\n"
          + "until your name is up with the\nothers on The Arizona Top Ten.",
          "You may turn the sound on or\noff during the program by\npressing "
          + "Control-S.", "You may want to quit in the\nmiddle of the program. If so,"
          + "\npress the Escape (Esc) key\ntwice whenever the computer\nis waiting"
          + " for a response.", "The software team responsible for the\ncreation of "
          + "this product includes:\n\nJordan Bridgewater\nJared Grady\nDavid Najork"
          + "\nEric Najork"
        }, true, true);
      case 3:
        // See the Arizona top 10
        return new GenericInfoMenu(new StoreMenu(), new String[]{
          "Not yet implemented..."
        });
      case 4:
        // turn sound off
        return new GenericInfoMenu(new StoreMenu(), new String[]{
          "The sound is now turned off. \n"
            + "You may turn sound on or off\n"
            + "during the program by pressing\nControl-S."
        }, true, true);
      case 5:
        // choose management option
        return new OptionsMenu();
    }
    // return;
    return null;
  }
}
