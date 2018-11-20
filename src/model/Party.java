package model;

import java.util.*;

public class Party {
  static enum Profession {
    BANKER, CARPENTER, FARMER;
  }

  HashMap<String, Integer> members;
  Profession prof;
  Inventory inv;

  public Party(Profession prof) {
    this.members = new HashMap<String, Integer>();
    this.prof = prof;
    this.inv = new Inventory(prof);
  }

  protected Profession getProf() {
    return this.prof;
  }

  protected String[] getNames() {
    return members.keySet().toArray(new String[members.keySet().size()]);
  }

  protected int getMember(String name) {
    return members.get(name);
  }

  protected int addMoney(int amount) {
    return inv.addMoney(amount);
  }

  protected boolean removeMoney(int amount) {
    return inv.removeMoney(amount);
  }

  protected int getMoney() {
    return inv.getMoney();
  }

  protected int addOxen(int amount) {
    return inv.addOxen(amount);
  }

  protected boolean removeOxen(int amount) {
    return inv.removeOxen(amount);
  }

  protected int getOxen() {
    return inv.getOxen();
  }

  protected int addBlankets(int amount) {
    return inv.addBlankets(amount);
  }

  protected boolean removeBlankets(int amount) {
    return inv.removeBlankets(amount);
  }

  protected int getBlankets() {
    return inv.getBlankets();
  }

  protected int addBullets(int amount) {
    return inv.addBullets(amount);
  }

  protected boolean removeBullets(int amount) {
    return inv.removeBullets(amount);
  }

  protected int getBullets() {
    return inv.getBullets();
  }

  protected int addFood(int amount) {
    return inv.addFood(amount);
  }

  protected boolean removeFood(int amount) {
    return inv.removeFood(amount);
  }

  protected int getFood() {
    return inv.getFood();
  }

  protected int addWater(int amount) {
    return inv.addWater(amount);
  }

  protected int removeWater(int amount) {
    return inv.addWater(amount);
  }

  protected int getWater() {
    return inv.getWater();
  }
  // ----------- START OF OPTIONAL SPARE PARTS ------------------
  protected int addWheels(int amount) {
    return inv.addWheels(amount);
  }

  protected boolean removeWheels(int amount) {
    return inv.removeWheels(amount);
  }

  protected int getWheels() {
    return inv.getWheels();
  }

  protected int addAxles(int amount) {
    return inv.addAxles(amount);
  }

  protected boolean removeAxles(int amount) {
    return inv.removeAxles(amount);
  }

  protected int getAxles() {
    return inv.getAxles();
  }

  protected int addTongues(int amount) {
    return inv.addTongues(amount);
  }

  protected boolean removeTongues(int amount) {
    return inv.removeTongues(amount);
  }

  protected int getTongues() {
    return inv.getTongues();
  }

}
