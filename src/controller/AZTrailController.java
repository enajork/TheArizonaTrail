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

  public void startThemeLoop() {
    AZTrailView.startThemeLoop();
  }

  public void cashOutSFX() {
    AZTrailView.cashOutSFX();
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
   * [addMoney description]
   * @param  amount [description]
   * @return        [description]
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

  /**
   * [removeOxen description]
   * @param  amount [description]
   * @return        [description]
   */
  public boolean removeOxen(int amount) {
    return model.removeOxen(amount);
  }

  /**
   * [getOxen description]
   * @return [description]
   */
  public int getOxen() {
    return model.getOxen();
  }

  /**
   * [addBlankets description]
   * @param  amount [description]
   * @return        [description]
   */
  public int addClothes(int amount) {
    return model.addClothes(amount);
  }

  /**
   * [removeBlankets description]
   * @param  amount [description]
   * @return        [description]
   */
  public boolean removeClothes(int amount) {
    return model.removeClothes(amount);
  }

  /**
   * [getBlankets description]
   * @return [description]
   */
  public int getClothes() {
    return model.getClothes();
  }

  /**
   * [addBlankets description]
   * @param  amount [description]
   * @return        [description]
   */
  public int addBlankets(int amount) {
    return model.addBlankets(amount);
  }

  /**
   * [removeBlankets description]
   * @param  amount [description]
   * @return        [description]
   */
  public boolean removeBlankets(int amount) {
    return model.removeBlankets(amount);
  }

  /**
   * [getBlankets description]
   * @return [description]
   */
  public int getBlankets() {
    return model.getBlankets();
  }

  /**
   * [addBullets description]
   * @param  amount [description]
   * @return        [description]
   */
  public int addBullets(int amount) {
    return model.addBullets(amount);
  }

  /**
   * [removeBullets description]
   * @param  amount [description]
   * @return        [description]
   */
  public boolean removeBullets(int amount) {
    return model.removeBullets(amount);
  }

  /**
   * [getBullets description]
   * @return [description]
   */
  public int getBullets() {
    return model.getBullets();
  }

  /**
   * [addFood description]
   * @param  amount [description]
   * @return        [description]
   */
  public int addFood(int amount) {
    return model.addFood(amount);
  }

  /**
   * [removeFood description]
   * @param  amount [description]
   * @return        [description]
   */
  public boolean removeFood(int amount) {
    return model.removeFood(amount);
  }

  /**
   * [getFood description]
   * @return [description]
   */
  public int getFood() {
    return model.getFood();
  }

  /**
   * [addWater description]
   * @param  amount [description]
   * @return        [description]
   */
  public int addWater(int amount) {
    return model.addWater(amount);
  }

  /**
   * [removeWater description]
   * @param  amount [description]
   * @return        [description]
   */
  public boolean removeWater(int amount) {
    return model.removeWater(amount);
  }

  /**
   * [getWater description]
   * @return [description]
   */
  public int getWater() {
    return model.getWater();
  }

  /**
   * [addWheels description]
   * @param  amount [description]
   * @return        [description]
   */
  public int addWheels(int amount) {
    return model.addWheels(amount);
  }

  /**
   * [removeWheels description]
   * @param  amount [description]
   * @return        [description]
   */
  public boolean removeWheels(int amount) {
    return model.removeWheels(amount);
  }

  /**
   * [getWheels description]
   * @return [description]
   */
  public int getWheels() {
    return model.getWheels();
  }

  /**
   * [addAxles description]
   * @param  amount [description]
   * @return        [description]
   */
  public int addAxles(int amount) {
    return model.addAxles(amount);
  }

  /**
   * [removeAxles description]
   * @param  amount [description]
   * @return        [description]
   */
  public boolean removeAxles(int amount) {
    return model.removeAxles(amount);
  }

  /**
   * [getAxles description]
   * @return [description]
   */
  public int getAxles() {
    return model.getAxles();
  }

  /**
   * [addTongues description]
   * @param  amount [description]
   * @return        [description]
   */
  public int addTongues(int amount) {
    return model.addTongues(amount);
  }

  /**
   * [removeTongues description]
   * @param  amount [description]
   * @return        [description]
   */
  public boolean removeTongues(int amount) {
    return model.removeTongues(amount);
  }

  /**
   * [getTongues description]
   * @return [description]
   */
  public int getTongues() {
    return model.getTongues();
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

  public String getCurrentCity() {
    return model.getCurrentCity();
  }
}
