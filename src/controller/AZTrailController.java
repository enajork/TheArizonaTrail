package controller;
import javafx.scene.*;
import model.*;
import view.*;
// import java.util.Random;

public class AZTrailController {
  private AZTrailModel model;
  // private static Random rand;

  /**
   * [AZTrailController description]
   */
  public AZTrailController() {
    this.model = new AZTrailModel();
    // this.rand = new Random(System.currentTimeMillis());
  }

  /**
   * [AZTrailController description]
   * @param model [description]
   */
  public AZTrailController(AZTrailModel model) {
    this.model = model;
    // this.rand = new Random(System.currentTimeMillis());
  }

  /**
   * [profMenu description]
   * @param choice [description]
   */
  public void profMenu(int choice) {
    if (choice < 1 || choice > 4) {
      System.err.println("Invalid Profession Choice!");
      System.exit(1);
    }
    switch (choice) {
      case 1:
        model.setProf("banker");
        break;
      case 2:
        model.setProf("carpenter");
        break;
      case 3:
        model.setProf("farmer");
        break;
      default:
        throw new IllegalStateException();
    }
  }

  /**
   * [getNames description]
   * @return [description]
   */
  public String getName(int i) {
    return model.getName(i);
  }

  /**
   * [leaderNameMenu description]
   * @param name [description]
   */
  public void setName(String name) {
    model.setName(name);
  }

  public int partySize() {
    return model.partySize();
  }

  public void resetPartyNames() {
    model.resetPartyNames();
  }

  /**
   * [partyNamesMenu description]
   * @param names [description]
   */
  public void partyNamesMenu(String[] names) {
    //
  }

  /**
   * [monthMenu description]
   * @param choice [description]
   */
  public void monthMenu(int choice) {
    if (choice < 1 || choice > 6) {
      System.err.println("Invalid Month Choice!");
      System.exit(1);
    }
    switch (choice) {
      case 1:
        // March
        model.setCalendar(new Calendar(2));
        break;
      case 2:
        // April
        model.setCalendar(new Calendar(3));
        break;
      case 3:
        // May
        model.setCalendar(new Calendar(4));
        break;
      case 4:
        // June
        model.setCalendar(new Calendar(5));
        break;
      case 5:
        // July
        model.setCalendar(new Calendar(6));
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
}
