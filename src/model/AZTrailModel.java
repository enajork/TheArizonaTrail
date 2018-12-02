package model;

import java.io.*;

public class AZTrailModel implements Serializable {
  private MapModel mapModel;
  private Calendar calendar;
  private boolean hunted;
  private Party party;
  private Cart cart;
  private int travelRate;

  public AZTrailModel() {
    this.hunted = true;
    this.calendar = new Calendar();
    this.mapModel = new MapModel();
    this.party = new Party();
    this.cart = new Cart();
    this.travelRate = 6;
  }

  /**
   * [getProf description]
   * @return [description]
   */
  public String getProf() {
    switch(party.getProf()) {
      case BANKER:
        return "Banker";
      case CARPENTER:
        return "Carpenter";
      case FARMER:
        return "Farmer";
      default:
        throw new IllegalStateException();
    }
  }

  /**
   * [getProf description]
   * @return [description]
   */
  public void setProf(String name) {
    party.setProf(name);
  }

  /**
   * [getNames description]
   * @return [description]
   */
  public String getName(int i) {
    return party.getName(i);
  }

  public void setName(int i, String name) {
    party.setName(i, name);
  }

  public int partySize() {
    return party.totalSize();
  }

  public int currPartySize() {
    return party.size();
  }

  /**
   * [addMoney description]
   * @param  amount [description]
   * @return        [description]
   */
  public void addMoney(double amount) {
    party.addMoney(amount);
  }

  /**
   * [removeMoney description]
   * @param  amount [description]
   * @return        [description]
   */
  public boolean removeMoney(double amount) {
    return party.removeMoney(amount);
  }

  /**
   * [getMoney description]
   * @return [description]
   */
  public double getMoney() {
    return party.getMoney();
  }

  /**
   * [addOxen description]
   * @param  amount [description]
   * @return        [description]
   */
  public int addOxen(int amount) {
    return party.addOxen(amount);
  }

  /**
   * [removeOxen description]
   * @param  amount [description]
   * @return        [description]
   */
  public boolean removeOxen(int amount) {
    return party.removeOxen(amount);
  }

  /**
   * [getOxen description]
   * @return [description]
   */
  public int getOxen() {
    return party.getOxen();
  }

  /**
   * [addBlankets description]
   * @param  amount [description]
   * @return        [description]
   */
  public int addClothes(int amount) {
    return party.addClothes(amount);
  }

  /**
   * [removeBlankets description]
   * @param  amount [description]
   * @return        [description]
   */
  public boolean removeClothes(int amount) {
    return party.removeClothes(amount);
  }

  /**
   * [getBlankets description]
   * @return [description]
   */
  public int getClothes() {
    return party.getClothes();
  }

  /**
   * [addBlankets description]
   * @param  amount [description]
   * @return        [description]
   */
  public int addBlankets(int amount) {
    return party.addBlankets(amount);
  }

  /**
   * [removeBlankets description]
   * @param  amount [description]
   * @return        [description]
   */
  public boolean removeBlankets(int amount) {
    return party.removeBlankets(amount);
  }

  /**
   * [getBlankets description]
   * @return [description]
   */
  public int getBlankets() {
    return party.getBlankets();
  }

  /**
   * [addBullets description]
   * @param  amount [description]
   * @return        [description]
   */
  public int addBullets(int amount) {
    return party.addBullets(amount);
  }

  /**
   * [removeBullets description]
   * @param  amount [description]
   * @return        [description]
   */
  public boolean removeBullets(int amount) {
    return party.removeBullets(amount);
  }

  /**
   * [getBullets description]
   * @return [description]
   */
  public int getBullets() {
    return party.getBullets();
  }

  /**
   * [addFood description]
   * @param  amount [description]
   * @return        [description]
   */
  public int addFood(int amount) {
    return party.addFood(amount);
  }

  /**
   * [removeFood description]
   * @param  amount [description]
   * @return        [description]
   */
  public boolean removeFood(int amount) {
    return party.removeFood(amount);
  }

  /**
   * [getFood description]
   * @return [description]
   */
  public int getFood() {
    return party.getFood();
  }

  /**
   * [addWater description]
   * @param  amount [description]
   * @return        [description]
   */
  public int addWater(int amount) {
    return party.addWater(amount);
  }

  /**
   * [removeWater description]
   * @param  amount [description]
   * @return        [description]
   */
  public boolean removeWater(int amount) {
    return party.removeWater(amount);
  }

  /**
   * [getWater description]
   * @return [description]
   */
  public int getWater() {
    return party.getWater();
  }

  /**
   * [addWheels description]
   * @param  amount [description]
   * @return        [description]
   */
  public int addWheels(int amount) {
    return party.addWheels(amount);
  }

  /**
   * [removeWheels description]
   * @param  amount [description]
   * @return        [description]
   */
  public boolean removeWheels(int amount) {
    return party.removeWheels(amount);
  }

  /**
   * [getWheels description]
   * @return [description]
   */
  public int getWheels() {
    return party.getWheels();
  }

  /**
   * [addAxles description]
   * @param  amount [description]
   * @return        [description]
   */
  public int addAxles(int amount) {
    return party.addAxles(amount);
  }

  /**
   * [removeAxles description]
   * @param  amount [description]
   * @return        [description]
   */
  public boolean removeAxles(int amount) {
    return party.removeAxles(amount);
  }

  /**
   * [getAxles description]
   * @return [description]
   */
  public int getAxles() {
    return party.getAxles();
  }

  /**
   * [addTongues description]
   * @param  amount [description]
   * @return        [description]
   */
  public int addTongues(int amount) {
    return party.addTongues(amount);
  }

  /**
   * [removeTongues description]
   * @param  amount [description]
   * @return        [description]
   */
  public boolean removeTongues(int amount) {
    return party.removeTongues(amount);
  }

  /**
   * [getTongues description]
   * @return [description]
   */
  public int getTongues() {
    return party.getTongues();
  }

  /**
  * [advanceCalendar description]
  * @return [description]
  */
  public void advanceCalendar() {
    this.calendar.advance();
  }

  /**
   * [getDay description]
   * @return [description]
   */
  public int getDay() {
    return calendar.getDay();
  }

  /**
   * [getMonth description]
   * @return [description]
   */
  public int getMonth() {
    return calendar.getMonth();
  }

  public void setMonth(String month) {
    calendar.setMonth(month);
  }

  /**
   * [getYear description]
   * @return [description]
   */
  public int getYear() {
    return calendar.getYear();
  }

  /**
   * [getSeason description]
   * @return [description]
   */
  public String getSeason() {
    return calendar.getSeason();
  }

  /**
   * [getDateStr description]
   * @return [description]
   */
  public String getDateStr() {
    return calendar.getDateStr();
  }

  public double getCartClothes() {
    return cart.getClothes();
  }

  public void setCartClothes(double amount) {
    cart.setClothes(amount);
  }

  public double getCartParts() {
    return cart.getParts();
  }

  public void setCartParts(double amount) {
    cart.setParts(amount);
  }

  public double getCartTotal() {
    return cart.getTotal();
  }

  public double getCartOxen() {
    return cart.getOxen();
  }

  public void setCartOxen(double amount) {
    cart.setOxen(amount);
  }

  public double getCartFood() {
    return cart.getFood();
  }

  public void setCartFood(double amount) {
    cart.setFood(amount);
  }

  public double getCartAmmo() {
    return cart.getAmmo();
  }

  public void setCartAmmo(double amount) {
    cart.setAmmo(amount);
  }

  public String getCurrentCity() {
    return mapModel.getCurrentCity();
  }

  public void setCurrentCity(String city) {
    mapModel.setCurrentCity(city);
  }

  public void setHunted(boolean value) {
    this.hunted = value;
  }

  public boolean getHunted() {
    return this.hunted;
  }

  public String toString() {
    String result = "";
    result += "prof=" + getProf();
    for (int i = 0; i < partySize(); i++) {
      result += "\nname" + i + "=" + getName(i);
    }
    result += "\nmoney=" + getMoney();
    result += "\noxen=" + getOxen();
    result += "\nclothes=" + getClothes();
    result += "\nblankets=" + getBlankets();
    result += "\nbullets=" + getBullets();
    result += "\nfood=" + getFood();
    result += "\nwater=" + getWater();
    result += "\nwheels=" + getWheels();
    result += "\naxles=" + getAxles();
    result += "\ntongues=" + getTongues();
    result += "\ndate=" + getDateStr();
    result += "\ncity=" + getCurrentCity();
    result += "\nhunted=" + getHunted();
    result += "\ntravelRate=" + travelRate;
    return result;
  }

  public void setDay(int day) {
    calendar.setDay(day);
  }

  public void setMonth(int month) {
    calendar.setMonth(month);
  }

  public void setYear(int year) {
    calendar.setYear(year);
  }

  public void advancePosition() {
    mapModel.advancePosition(travelRate);
  }

  public int getTotalMiles() {
    return mapModel.getTotalMiles();
  }

  public double milesToLandmark() {
    return mapModel.milesToLandmark();
  }

  public int getTravelRate() {
    return travelRate;
  }

  public void setTravelRate(int rate) {
    travelRate = rate;
  }
}
