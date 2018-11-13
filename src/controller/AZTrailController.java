package controller;
import model.*;
import java.util.Random;

public class AZTrailController {
  private AZTrailModel model;
  private static Random rand;

  public AZTrailController() {
    this.model = new AZTrailModel();
    this.rand = new Random(System.currentTimeMillis());
  }

  public AZTrailController(AZTrailModel model) {
    this.model = model;
    this.rand = new Random(System.currentTimeMillis());
  }

  public AZTrailControllerMessage splashMenu(int choice) {
    if (choice < 1 || choice > 6) {
      System.err.println("Invalid Menu Choice!");
      System.exit(1);
    }
    switch (choice) {
      case 1:
        // Travel the trail
        return new AZTrailControllerMessage(0, null);
      case 2:
        // Learn about the trail
        return new AZTrailControllerMessage(1, "LEARN STUFF\nthere will need to be multiple panes for this\n and each pane's text will be separated by a newline or some other indicative char"); // call info or learn method
      case 3:
        // See the Arizona top 10
        return new AZTrailControllerMessage(2, "TOP TEN\n1. me\n2.you"); // call top ten method
      case 4:
        // turn sound off
        return new AZTrailControllerMessage(3, null); // STUBBED
      case 5:
        // choose management option
        return new AZTrailControllerMessage(4, null); // STUBBED
      case 6:
        // end
        System.exit(0);
        // break;
    }
    // return;
  }

  // may not be void return
  public void profMenu(int choice) {
    if (choice < 1 || choice > 4) {
      System.err.println("Invalid Profession Choice!");
      System.exit(1);
    }
    switch (choice) {
      case 1:
        // banker from ***Boston
        break;
      case 2:
        // carpenter from ***Ohio
        break;
      case 3:
        // farmer from ***Illinois
        break;
      case 4:
        // find out the differences between these choices
        break;
    }
    // return;
  }

  public void leaderNameMenu(String name) {
    //
  }

  public void partyNamesMenu(String[] names) {
    //
  }

  public void monthMenu(int choice) {
    if (choice < 1 || choice > 6) {
      System.err.println("Invalid Month Choice!");
      System.exit(1);
    }
    switch (choice) {
      case 1:
        // March
        break;
      case 2:
        // April
        break;
      case 3:
        // May
        break;
      case 4:
        // June
        break;
      case 5:
        // July
        break;
      case 6:
        // Ask for advice
        break;
    }
  }

  // public void storeMenu(int choice) {
  //   if (choice < 1 || choice > 5) {
  //     System.err.println("Invalid Store Choice!");
  //     System.exit(1);
  //   }
  //   switch (choice) {
  //     case 1:
  //       // Oxen
  //       break;
  //     case 2:
  //       // Food
  //       break;
  //     case 3:
  //       // Clothing
  //       break;
  //     case 4:
  //       // Ammunition
  //       break;
  //     case 5:
  //       // Spare parts
  //       break;
  //   }
  // }

  // public void storeSubMenu(int quantity) {
  //   if (quantity < 1 || quantity > 10) {
  //     //
  //   }
  //   // add quantity to order
  // }

  // public void addToCart(Item item) {
  //   //
  // }

  // public void

  public void learn() {
    //
  }

  public void topTen() {
    //
  }

  public void sound() {
    // turn on or turn off
  }

  public void manage() {
    //
  }

  public void end() {
    // may not need
  }


}
