package model;

public class AZTrailModel {
  Party party;
  // byte day;
  byte month;
  // int year;

  public GameModel(Party party, byte month) {
    this.party = party;
    this.month = month;
    // this.day = 1;
  }

  public Party.Profession getProf() {
    return party.getProf();
  }

  public String[] getNames() {
    return party.getNames();
  }

  public int getMember(String name) {
    return party.getMember(name);
  }

  public int addMoney(int amount) {
    return party.addMoney(amount);
  }

  public boolean removeMoney(int amount) {
    return party.removeMoney(amount);
  }

  public int getMoney() {
    return party.getMoney();
  }

  public int addOxen(int amount) {
    return party.addOxen(amount);
  }

  public boolean removeOxen(int amount) {
    return party.removeOxen(amount);
  }

  public int getOxen() {
    return party.getOxen();
  }

  public int addClothes(int amount) {
    return party.addClothes(amount);
  }

  public boolean removeClothes(int amount) {
    return party.removeClothes(amount);
  }

  public int getClothes() {
    return party.getClothes();
  }

  public int addBullets(int amount) {
    return party.addBullets(amount);
  }

  public boolean removeBullets(int amount) {
    return party.removeBullets(amount);
  }

  public int getBullets() {
    return party.getBullets();
  }

  public int addWheels(int amount) {
    return party.addWheels(amount);
  }

  public boolean removeWheels(int amount) {
    return party.removeWheels(amount);
  }

  public int getWheels() {
    return party.getWheels();
  }

  public int addAxles(int amount) {
    return party.addAxles(amount);
  }

  public boolean removeAxles(int amount) {
    return party.removeAxles(amount);
  }

  public int getAxles() {
    return party.getAxles();
  }

  public int addTongues(int amount) {
    return party.addTongues(amount);
  }

  public boolean removeTongues(int amount) {
    return party.removeTongues(amount);
  }

  public int getTongues() {
    return party.getTongues();
  }

  public int addFood(int amount) {
    return party.addFood(amount);
  }

  public boolean removeFood(int amount) {
    return party.removeFood(amount);
  }

  public int getFood() {
    return party.getFood();
  }

  public byte setMonth(byte month) {
    if (month > 0 && month <= 12) {
      this.month = month;
    }
    return this.month;
  }

  // public void advanceDay() {
  //
  // }

  // public void advanceMonth() {
  //
  // }

  public byte getMonth() {
    return this.month;
  }
}
