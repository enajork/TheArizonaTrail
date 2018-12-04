package model;

import java.util.*;
import java.io.*;
/**
 * @author Jordan Bridgewater, Jared Grady, Eric Najork, David Najork
 * @version     1.0
 * @since       1.0
 */

public class Party implements Serializable {
  public static enum Profession implements Serializable {
    BANKER, CARPENTER, FARMER;
  }

  private ArrayList<String> names;
  private final int SIZE = 5;
  private Profession prof;
  private Inventory inv;

  /**
   * Constructs a new party model
   */
  public Party() {
    this.names = new ArrayList<String>();
    for (int i = 0; i < SIZE; i++) {
      names.add("");
    }
    this.prof = Profession.BANKER;
    this.inv = new Inventory();
  }

  /**
   * getter for Profession
   * @return returns the players profession
   */
  public Profession getProf() {
    return this.prof;
  }

  /**
   * setter for prof
   * @param the profession for the player
   */
  public void setProf(String name) {
    if (name.toLowerCase().equals("banker")) {
      this.prof = Profession.BANKER;
      inv.removeMoney(inv.getMoney());
      inv.addMoney(400);
    } else if (name.toLowerCase().equals("carpenter")) {
      this.prof = Profession.CARPENTER;
      inv.removeMoney(inv.getMoney());
      inv.addMoney(200);
    } else if (name.toLowerCase().equals("farmer")) {
      this.prof = Profession.FARMER;
      inv.removeMoney(inv.getMoney());
      inv.addMoney(100);
    } else {
      throw new IllegalStateException();
    }
  }

  /**
   * get the ith party member's name
   * @param i index of the party member's name
   * @return the name of the party member
   */
  public String getName(int i) {
    return names.get(i);
  }

  /**
   * set the ith party member's name
   * @param i the index of the party member
   * @param name the name of the party member
   */
  public void setName(int i, String name) {
    if (i >= 0 && i < SIZE) {
      names.set(i, name);
    }
  }

  /**
   * removes a party members name
   * @return the removed name
   */
  public String removeName() {
    if (names.size() > 0) {
      String temp = names.get(names.size() - 1);
      names.remove(names.size() - 1);
      return temp;
    }
    return "";
  }

  /**
   * returns the number of surviving party members
   *
   * @return the number of party members
   */
  public int size() {
    return names.size();
  }

  /**
   * returns the total possible party size
   *
   * @return the max size of the party
   */
  public int totalSize() {
    return SIZE;
  }

  /**
   * adds money to the party
   * @param  amount amount of money to add
   */
  public void addMoney(double amount) {
    inv.addMoney(amount);
  }

  /**
   * removes money from the party
   * @param  amount amount of money to add
   * @return        true if all money is gone
   */
  public boolean removeMoney(double amount) {
    return inv.removeMoney(amount);
  }

  /**
   * getter for the party's money
   * @return amount of money the party has
   */
  public double getMoney() {
    return inv.getMoney();
  }

  /**
   * adds oxen to the party
   * @param  amount amount of oxen to add
   */
  public int addOxen(int amount) {
    return inv.addOxen(amount);
  }

  /**
   * [removeOxen description]
   * @param  amount [description]
   * @return        [description]
   */
  public boolean removeOxen(int amount) {
    return inv.removeOxen(amount);
  }

  /**
   * [getOxen description]
   * @return [description]
   */
  public int getOxen() {
    return inv.getOxen();
  }

  /**
   * adds clothes to the party
   * @param  amount amount of clothes to add
   */
  public int addClothes(int amount) {
    return inv.addClothes(amount);
  }

  /**
   * [removeBlankets description]
   * @param  amount [description]
   * @return        [description]
   */
  public boolean removeClothes(int amount) {
    return inv.removeClothes(amount);
  }

  /**
   * [getBlankets description]
   * @return [description]
   */
  public int getClothes() {
    return inv.getClothes();
  }

  /**
   * adds blankets to the party
   * @param  amount amount of blankets to add
   */
  public int addBlankets(int amount) {
    return inv.addBlankets(amount);
  }

  /**
   * [removeBlankets description]
   * @param  amount [description]
   * @return        [description]
   */
  public boolean removeBlankets(int amount) {
    return inv.removeBlankets(amount);
  }

  /**
   * [getBlankets description]
   * @return [description]
   */
  public int getBlankets() {
    return inv.getBlankets();
  }

  /**
   * adds bullets to the party
   * @param  amount amount of bullets to add
   */
  public int addBullets(int amount) {
    return inv.addBullets(amount);
  }

  /**
   * [removeBullets description]
   * @param  amount [description]
   * @return        [description]
   */
  public boolean removeBullets(int amount) {
    return inv.removeBullets(amount);
  }

  /**
   * [getBullets description]
   * @return [description]
   */
  public int getBullets() {
    return inv.getBullets();
  }

  /**
   * adds food to the party
   * @param  amount amount of food to add
   */
  public int addFood(int amount) {
    return inv.addFood(amount);
  }

  /**
   * [removeFood description]
   * @param  amount [description]
   * @return        [description]
   */
  public boolean removeFood(int amount) {
    return inv.removeFood(amount);
  }

  /**
   * [getFood description]
   * @return [description]
   */
  public int getFood() {
    return inv.getFood();
  }

  /**
   * adds water to the party
   * @param  amount amount of water to add
   */
  public int addWater(int amount) {
    return inv.addWater(amount);
  }

  /**
   * [removeWater description]
   * @param  amount [description]
   * @return        [description]
   */
  public boolean removeWater(int amount) {
    return inv.removeWater(amount);
  }

  /**
   * [getWater description]
   * @return [description]
   */
  public int getWater() {
    return inv.getWater();
  }
  // ----------- START OF OPTIONAL SPARE PARTS ------------------

  /**
   * [addWheels description]
   * @param  amount [description]
   * @return        [description]
   */
  public int addWheels(int amount) {
    return inv.addWheels(amount);
  }

  /**
   * [removeWheels description]
   * @param  amount [description]
   * @return        [description]
   */
  public boolean removeWheels(int amount) {
    return inv.removeWheels(amount);
  }

  /**
   * [getWheels description]
   * @return [description]
   */
  public int getWheels() {
    return inv.getWheels();
  }

  /**
   * [addAxles description]
   * @param  amount [description]
   * @return        [description]
   */
  public int addAxles(int amount) {
    return inv.addAxles(amount);
  }

  /**
   * [removeAxles description]
   * @param  amount [description]
   * @return        [description]
   */
  public boolean removeAxles(int amount) {
    return inv.removeAxles(amount);
  }

  /**
   * [getAxles description]
   * @return [description]
   */
  public int getAxles() {
    return inv.getAxles();
  }

  /**
   * [addTongues description]
   * @param  amount [description]
   * @return        [description]
   */
  public int addTongues(int amount) {
    return inv.addTongues(amount);
  }

  /**
   * [removeTongues description]
   * @param  amount [description]
   * @return        [description]
   */
  public boolean removeTongues(int amount) {
    return inv.removeTongues(amount);
  }

  /**
   * [getTongues description]
   * @return [description]
   */
  public int getTongues() {
    return inv.getTongues();
  }
}
