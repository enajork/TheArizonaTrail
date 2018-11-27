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
  public void setName(int i, String name) {
    model.setName(i, name);
  }

  public int partySize() {
    return model.partySize();
  }

  /**
   * [setMonth description]
   * @param  month [description]
   * @return       [description]
   */
  public void setMonth(String month) {
    model.setMonth(month);
  }

  /**
   * [getMonth description]
   * @return [description]
   */
  public int getMonth() {
    return model.getMonth();
  }

  /**
   * [getDateStr description]
   * @return [description]
   */
  public String getDateStr() {
    return model.getDateStr();
  }

  /**
   * [getMoney description]
   * @return [description]
   */
  public void addMoney(double amount) {
    model.addMoney(amount);
  }

  /**
   * [removeMoney description]
   * @param  amount [description]
   * @return        [description]
   */
  public boolean removeMoney(double amount) {
    return model.removeMoney(amount);
  }

  /**
   * [getMoney description]
   * @return [description]
   */
  public double getMoney() {
    return model.getMoney();
  }

  /**
   * [addOxen description]
   * @param  amount [description]
   * @return        [description]
   */
  public int addOxen(int amount) {
    return model.addOxen(amount);
  }

  public double getCartClothes() {
    return model.getCartClothes();
  }

  public void setCartClothes(double amount) {
    model.setCartClothes(amount);
  }

  public double getCartParts() {
    return model.getCartParts();
  }

  public void setCartParts(double amount) {
    model.setCartParts(amount);
  }

  public double getCartTotal() {
    return model.getCartTotal();
  }

  public void setCartTotal(double amount) {
    model.setCartTotal(amount);
  }

  public double getCartOxen() {
    return model.getCartOxen();
  }

  public void setCartOxen(double amount) {
    model.setCartOxen(amount);
  }

  public double getCartFood() {
    return model.getCartFood();
  }

  public void setCartFood(double amount) {
    model.setCartFood(amount);
  }

  public double getCartAmmo() {
    return model.getCartAmmo();
  }

  public void setCartAmmo(double amount) {
    model.setCartAmmo(amount);
  }
}
