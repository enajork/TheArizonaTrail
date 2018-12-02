package model;

import java.util.*;
import java.io.*;

public class Party implements Serializable {
  public static enum Profession implements Serializable {
    BANKER, CARPENTER, FARMER;
  }

  private ArrayList<String> names;
  private final int SIZE = 5;
  private Profession prof;
  private Inventory inv;

  /**
   * [Party description]
   * @param prof [description]
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
   * [getProf description]
   * @return [description]
   */
  public Profession getProf() {
    return this.prof;
  }

  /**
   * [getProf description]
   * @return [description]
   */
  public void setProf(String name) {
    if (name.toLowerCase().equals("banker")) {
      this.prof = Profession.BANKER;
      inv.removeMoney(inv.getMoney());
      inv.addMoney(1600);
    } else if (name.toLowerCase().equals("carpenter")) {
      this.prof = Profession.CARPENTER;
      inv.removeMoney(inv.getMoney());
      inv.addMoney(800);
    } else if (name.toLowerCase().equals("farmer")) {
      this.prof = Profession.FARMER;
      inv.removeMoney(inv.getMoney());
      inv.addMoney(400);
    } else {
      throw new IllegalStateException();
    }
  }

  /**
   * [getNames description]
   * @return [description]
   */
  public String getName(int i) {
    return names.get(i);
  }

  public void setName(int i, String name) {
    if (i >= 0 && i < SIZE) {
      names.set(i, name);
    }
  }

  public int size() {
    return names.size();
  }

  public int totalSize() {
    return SIZE;
  }

  /**
   * [addMoney description]
   * @param  amount [description]
   * @return        [description]
   */
  public void addMoney(double amount) {
    inv.addMoney(amount);
  }

  /**
   * [removeMoney description]
   * @param  amount [description]
   * @return        [description]
   */
  public boolean removeMoney(double amount) {
    return inv.removeMoney(amount);
  }

  /**
   * [getMoney description]
   * @return [description]
   */
  public double getMoney() {
    return inv.getMoney();
  }

  /**
   * [addOxen description]
   * @param  amount [description]
   * @return        [description]
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
   * [addBlankets description]
   * @param  amount [description]
   * @return        [description]
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
   * [addBlankets description]
   * @param  amount [description]
   * @return        [description]
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
   * [addBullets description]
   * @param  amount [description]
   * @return        [description]
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
   * [addFood description]
   * @param  amount [description]
   * @return        [description]
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
   * [addWater description]
   * @param  amount [description]
   * @return        [description]
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
