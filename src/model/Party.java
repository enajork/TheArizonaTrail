package model;

import java.util.*;

public class Party {
  protected static enum Profession {
    BANKER, CARPENTER, FARMER;
  }

  HashMap<String, Integer> members;
  Profession prof;
  Inventory inv;

  /**
   * [Party description]
   * @param prof [description]
   */
  public Party(Profession prof) {
    this.members = new HashMap<String, Integer>();
    this.prof = prof;
    this.inv = new Inventory(prof);
  }

  /**
   * [getProf description]
   * @return [description]
   */
  protected Profession getProf() {
    return this.prof;
  }

  /**
   * [getNames description]
   * @return [description]
   */
  protected String[] getNames() {
    return members.keySet().toArray(new String[members.keySet().size()]);
  }

  /**
   * [getMember description]
   * @param  name [description]
   * @return      [description]
   */
  protected int getMember(String name) {
    return members.get(name);
  }

  /**
   * [addMoney description]
   * @param  amount [description]
   * @return        [description]
   */
  protected int addMoney(int amount) {
    return inv.addMoney(amount);
  }

  /**
   * [removeMoney description]
   * @param  amount [description]
   * @return        [description]
   */
  protected boolean removeMoney(int amount) {
    return inv.removeMoney(amount);
  }

  /**
   * [getMoney description]
   * @return [description]
   */
  protected int getMoney() {
    return inv.getMoney();
  }

  /**
   * [addOxen description]
   * @param  amount [description]
   * @return        [description]
   */
  protected int addOxen(int amount) {
    return inv.addOxen(amount);
  }

  /**
   * [removeOxen description]
   * @param  amount [description]
   * @return        [description]
   */
  protected boolean removeOxen(int amount) {
    return inv.removeOxen(amount);
  }

  /**
   * [getOxen description]
   * @return [description]
   */
  protected int getOxen() {
    return inv.getOxen();
  }

  /**
   * [addBlankets description]
   * @param  amount [description]
   * @return        [description]
   */
  protected int addBlankets(int amount) {
    return inv.addBlankets(amount);
  }

  /**
   * [removeBlankets description]
   * @param  amount [description]
   * @return        [description]
   */
  protected boolean removeBlankets(int amount) {
    return inv.removeBlankets(amount);
  }

  /**
   * [getBlankets description]
   * @return [description]
   */
  protected int getBlankets() {
    return inv.getBlankets();
  }

  /**
   * [addBullets description]
   * @param  amount [description]
   * @return        [description]
   */
  protected int addBullets(int amount) {
    return inv.addBullets(amount);
  }

  /**
   * [removeBullets description]
   * @param  amount [description]
   * @return        [description]
   */
  protected boolean removeBullets(int amount) {
    return inv.removeBullets(amount);
  }

  /**
   * [getBullets description]
   * @return [description]
   */
  protected int getBullets() {
    return inv.getBullets();
  }

  /**
   * [addFood description]
   * @param  amount [description]
   * @return        [description]
   */
  protected int addFood(int amount) {
    return inv.addFood(amount);
  }

  /**
   * [removeFood description]
   * @param  amount [description]
   * @return        [description]
   */
  protected boolean removeFood(int amount) {
    return inv.removeFood(amount);
  }

  /**
   * [getFood description]
   * @return [description]
   */
  protected int getFood() {
    return inv.getFood();
  }

  /**
   * [addWater description]
   * @param  amount [description]
   * @return        [description]
   */
  protected int addWater(int amount) {
    return inv.addWater(amount);
  }

  /**
   * [removeWater description]
   * @param  amount [description]
   * @return        [description]
   */
  protected boolean removeWater(int amount) {
    return inv.removeWater(amount);
  }

  /**
   * [getWater description]
   * @return [description]
   */
  protected int getWater() {
    return inv.getWater();
  }
  // ----------- START OF OPTIONAL SPARE PARTS ------------------

  /**
   * [addWheels description]
   * @param  amount [description]
   * @return        [description]
   */
  protected int addWheels(int amount) {
    return inv.addWheels(amount);
  }

  /**
   * [removeWheels description]
   * @param  amount [description]
   * @return        [description]
   */
  protected boolean removeWheels(int amount) {
    return inv.removeWheels(amount);
  }

  /**
   * [getWheels description]
   * @return [description]
   */
  protected int getWheels() {
    return inv.getWheels();
  }

  /**
   * [addAxles description]
   * @param  amount [description]
   * @return        [description]
   */
  protected int addAxles(int amount) {
    return inv.addAxles(amount);
  }

  /**
   * [removeAxles description]
   * @param  amount [description]
   * @return        [description]
   */
  protected boolean removeAxles(int amount) {
    return inv.removeAxles(amount);
  }

  /**
   * [getAxles description]
   * @return [description]
   */
  protected int getAxles() {
    return inv.getAxles();
  }

  /**
   * [addTongues description]
   * @param  amount [description]
   * @return        [description]
   */
  protected int addTongues(int amount) {
    return inv.addTongues(amount);
  }

  /**
   * [removeTongues description]
   * @param  amount [description]
   * @return        [description]
   */
  protected boolean removeTongues(int amount) {
    return inv.removeTongues(amount);
  }

  /**
   * [getTongues description]
   * @return [description]
   */
  protected int getTongues() {
    return inv.getTongues();
  }

}
