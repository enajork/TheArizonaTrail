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
/**
 * @author Jordan Bridgewater, Jared Grady, Eric Najork, David Najork
 * @version     1.0
 * @since       1.0
 */

public class StoreMenu extends Scene {
  public static int wheels = 0;
  public static int axles = 0;
  public static int tongues = 0;
  public static int oxen = 0;
  public static int food = 0;
  public static int clothes = 0;
  public static int ammo = 0;
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
  private BorderPane tile;
  private boolean warn = false;
  private Text footer;
  private Text body;
  private String prompt = "  Which item would you\n  like to buy? ";
  private String input = "_";
  private boolean start;
  private String name;

  /**
   * Constructs a new store menu
   *
   * @param name  the store name
   */
  public StoreMenu(String name) {
    this(new BorderPane(), name, false);
    getStylesheets().add(AZTrailView.styleSheet);
    AZTrailController.escape = false;
  }

  /**
   * Constructs a new store wheel menu view
   *
   * @param name  the store name
   * @param start boolean
   */
  public StoreMenu(String name, boolean start) {
    this(new BorderPane(), name, start);
    getStylesheets().add(AZTrailView.styleSheet);
    AZTrailController.escape = false;
  }

  /**
   * Constructs a new store menu
   *
   * @param root  root of the scene graph
   *
   * @param name  the store name
   * @param start boolean
   */
  private StoreMenu(BorderPane root, String name, boolean start) {
    super(root, AZTrailView.WIDTH, AZTrailView.HEIGHT, Color.BLACK);
    this.name = name;
    this.start = start;
    tile = new BorderPane();
    tile.setStyle("-fx-background-color: black;");
    BorderPane receiptBody = new BorderPane();
    receiptBody.setStyle("-fx-background-color: black;");
    BorderPane billBody = new BorderPane();
    billBody.setStyle("-fx-background-color: black;");
    billBody.setMaxWidth(AZTrailView.WIDTH / 2.2);
    BorderPane subTotals = new BorderPane();
    subTotals.setStyle("-fx-background-color: black;");
    subTotals.setMaxWidth(AZTrailView.WIDTH / 1.65);
    BorderPane banner = new BorderPane();
    banner.setStyle("-fx-background-color: black;");
    BorderPane top = new BorderPane();
    top.setStyle("-fx-background-color: black;");
    BorderPane lower = new BorderPane();
    lower.setStyle("-fx-background-color: black;");
    top.setTop(banner);
    top.setBottom(receiptBody);
    root.setCenter(tile);
    tile.setTop(top);
    tile.setBottom(lower);
    tile.setMargin(lower, new Insets(40));
    banner.setTop(rect1);
    banner.setAlignment(rect1, Pos.CENTER);
    banner.setMargin(rect1, new Insets(5));
    banner.setBottom(rect2);
    banner.setAlignment(rect2, Pos.CENTER);
    banner.setMargin(rect2, new Insets(5));
    receiptBody.setTop(subTotals);
    receiptBody.setAlignment(subTotals, Pos.CENTER_RIGHT);
    receiptBody.setMargin(subTotals, new Insets(0, 70, 0, 0));
    receiptBody.setCenter(rect3);
    receiptBody.setAlignment(rect3, Pos.CENTER);
    receiptBody.setMargin(rect3, new Insets(5));
    receiptBody.setMaxHeight(0);

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
    Text header = new Text(name + " General Store\n"
      + AZTrailView.controller.getCurrentCity()
      + ", Arizona\n\n     "
      + AZTrailView.controller.getDateStr());
    header.setId("text12");
    header.setFill(Color.WHITE);
    banner.setCenter(header);

    // Create the text for the menu options
    footer = new Text("Press SPACE BAR to\nleave store");
    footer.setId("text12");
    footer.setFill(Color.WHITE);

    // Create the text for the menu options
    Text bill = new Text(new DecimalFormat("'$'###,##0.00")
      .format(AZTrailView.controller.getCartTotal()));
    bill.setId("text12");
    bill.setFill(Color.WHITE);

    // Create the text for the menu options
    Text total = new Text("Amount you have: "
      + new DecimalFormat("'$'###,##0.00")
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

    billBody.setLeft(billText);
    billBody.setRight(bill);

    receiptBody.setBottom(billBody);
    receiptBody.setAlignment(billBody, Pos.CENTER_RIGHT);
    receiptBody.setMargin(billBody, new Insets(0, 70, 0, 0));

    lower.setTop(total);
    lower.setAlignment(total, Pos.CENTER_RIGHT);
    lower.setMargin(total, new Insets(0, 30, 30, 0));
    lower.setBottom(body);
    lower.setAlignment(body, Pos.CENTER_LEFT);

    root.setStyle("-fx-background-color: black;");
    root.setCenter(tile);
    root.setBottom(footer);
    root.setAlignment(footer, Pos.CENTER);
    root.setMargin(footer, new Insets(5));

    addEventHandlers();
  }

  /**
   * Adds event handlers to handle keyboard actions from the user
   */
  private void addEventHandlers() {
    this.setOnKeyPressed(new EventHandler<KeyEvent>() {
      @Override
      public void handle(KeyEvent event) {
        switch (event.getCode()) {
          case SPACE:
            AZTrailController.escape = false;
            if (warn) {
              AZTrailView.stage.setScene(new StoreMenu(name, start));
            } else if (start && oxen == 0 && !warn) {
              Text warning = new Text("Don't forget, you'll need\noxen to pull "
                + "your wagon.");
              warning.setId("text12");
              warning.setFill(Color.WHITE);
              tile.setBottom(warning);
              tile.setMargin(warning, new Insets(40));
              footer.setText("Press SPACE BAR to continue");
              warn = true;
            } else if (AZTrailView.controller.getCartTotal()
                > AZTrailView.controller.getMoney()) {
              Text warning = new Text("Okay, that comes to a total\nof "
                + new DecimalFormat("'$'###,##0.00")
                .format(AZTrailView.controller.getCartTotal())
                + " But I see that\nyou only have "
                + new DecimalFormat("'$'###,##0.00")
                .format(AZTrailView.controller.getMoney())
                + ".\nWe'd better go over the\nlist again.");
              warning.setId("text12");
              warning.setFill(Color.WHITE);
              tile.setBottom(warning);
              tile.setMargin(warning, new Insets(40));
              footer.setText("Press SPACE BAR to continue");
              warn = true;
            } else if (start) {
              AZTrailView.controller.addOxen(oxen);
              AZTrailView.controller.addFood(food);
              AZTrailView.controller.addClothes(clothes);
              AZTrailView.controller.addBullets(ammo);
              AZTrailView.controller.addWheels(wheels);
              AZTrailView.controller.addAxles(axles);
              AZTrailView.controller.addTongues(tongues);
              wheels = 0;
              axles = 0;
              tongues = 0;
              oxen = 0;
              food = 0;
              clothes = 0;
              ammo = 0;
              AZTrailView.controller.addWater(300);
              AZTrailView.controller.removeMoney(AZTrailView.controller
                .getCartTotal());
              if (AZTrailView.controller.getCartTotal() > 0) {
                AZTrailView.sounds.cashOutSFX();
              }
              AZTrailView.sounds.huntedMenuTheme();
              AZTrailView.controller.resetCart();
              AZTrailView.stage.setScene(new ClerkInfoMenu(
                new Runnable() {
                  @Override
                  public void run() {
                    AZTrailView.stage.setScene(getNextView());
                  }
                },
                "", new String[]{"Here, take these blankets\nand water. On the house!",
                "Well then, you're ready\nto start. Good luck!\nYou have a long"
                 + " and\ndifficult journey ahead\nof you."
              }));
            } else if (!start) {
              AZTrailView.controller.addOxen(oxen);
              AZTrailView.controller.addFood(food);
              AZTrailView.controller.addClothes(clothes);
              AZTrailView.controller.addBullets(ammo);
              AZTrailView.controller.addWheels(wheels);
              AZTrailView.controller.addAxles(axles);
              AZTrailView.controller.addTongues(tongues);
              wheels = 0;
              axles = 0;
              tongues = 0;
              oxen = 0;
              food = 0;
              clothes = 0;
              ammo = 0;
              AZTrailView.controller.addWater(200);
              AZTrailView.controller.removeMoney(AZTrailView.controller
                .getCartTotal());
              AZTrailView.controller.resetCart();
              if (AZTrailView.controller.getCartTotal() > 0) {
                AZTrailView.sounds.cashOutSFX();
              }
              AZTrailView.stage.setScene(new ClerkInfoMenu(
                new Runnable() {
                  @Override
                  public void run() {
                    AZTrailView.stage.setScene(getNextView());
                  }
                },
                "", new String[]{"Here take some water.\nGood luck, stranger!"
              }));
            }
            break;

          case BACK_SPACE:
            AZTrailController.escape = false;
            if (input.length() >= 2) {
              input = input.substring(0, input.length() - 2);
              input += "_";
            }
            body.setText(prompt + input);
            break;

          case ENTER:
            AZTrailController.escape = false;
            if (warn) {
              AZTrailView.stage.setScene(new StoreMenu(name, start));
            }
            if (input.length() == 2) {
              AZTrailView.stage.setScene(getNextMenu(Integer.parseInt(input
                .substring(0, 1))));
            }
            break;

          case ESCAPE:
            if (AZTrailController.escape) {
              wheels = 0;
              axles = 0;
              tongues = 0;
              oxen = 0;
              food = 0;
              clothes = 0;
              ammo = 0;
            }
            AZTrailView.escapePressed((start) ? false : true);
            break;

          case S:
            AZTrailController.escape = false;
            if (event.isControlDown()) {
              AZTrailView.sounds.mute();
            }
            break;

          default:
            AZTrailController.escape = false;
            if (event.getText().length() > 0
                && Character.isDigit(event.getText().charAt(0))) {
              updateInputText(Integer.parseInt(event.getText()));
            }
        }
      }
    });
  }

  /**
   * updates the display when the user types in a selection the keyboard
   * @param num the value inserted by the user
   */
  private void updateInputText(int num) {
    if (input.length() == 1 && num >= 1 && num <= NUM_OPTS) {
      input = input.substring(0, input.length() - 1);
      input += num + "_";
      body.setText(prompt + input);
    }
  }

  /**
   * determines the next view to be scene by the player based on their choice
   * @param  choice integer choice number
   * @return        the next view to be staged
   */
  private Scene getNextMenu(int choice) {
    if (choice < 1 || choice > NUM_OPTS) {
      throw new IllegalStateException();
    }
    switch (choice) {
      case 1:
        return new StoreOxenMenu(name, start);
      case 2:
        return new StoreFoodMenu(name, start);
      case 3:
        return new StoreClothesMenu(name, start);
      case 4:
        return new StoreAmmoMenu(name, start);
      case 5:
        return new StoreWheelMenu(name, start);
    }
    // return;
    return null;
  }

  private Scene getNextView() {
    switch (AZTrailView.controller.getCurrentCity()) {
      case "Nogales":
        AZTrailView.controller.addBlankets(5);
        return new HuntedMenu();
      case "Flagstaff":
        return (AZTrailView.controller.getHunted())
          ? new HuntedSplash() : new SizeUpView();
      default:
        return new SizeUpView();
    }
  }
}
