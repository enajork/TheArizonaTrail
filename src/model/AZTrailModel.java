package model;

import java.io.*;
/**
 * @author Jordan Bridgewater, Jared Grady, Eric Najork, David Najork
 * @version     1.0
 * @since       1.0
 */

public class AZTrailModel implements Serializable {
  private MapModel mapModel;
  private Calendar calendar;
  private boolean hunted;
  private int travelRate;
  private Party party;
  private Cart cart;

  /**
   * Constructs a new AZTrailModel with the inital settings then builds
   * the neccessary sub models
   */
  public AZTrailModel() {
    this.hunted = true;
    this.calendar = new Calendar();
    this.mapModel = new MapModel();
    this.party = new Party();
    this.cart = new Cart();
    this.travelRate = 4;
  }

  /**
   * getter for map
   *
   * @return returns the map model
   */
  public MapModel getMap() {
    return mapModel;
  }

  /**
   * sets the map model to a new one
   *
   * @param map the new map model to set
   */
  public void setMap(MapModel map) {
    this.mapModel = map;
  }

  /**
   * getter for the calendar model
   *
   * @return the calendar model
   */
  public Calendar getCalendar() {
    return calendar;
  }

  /**
   * sets the calendar to a new calendar model
   *
   * @param cal calendar model to add
   */
  public void setCalendar(Calendar cal) {
    this.calendar = cal;
  }

  /**
   * getter for the party model
   *
   * @return the party model
   */
  public Party getParty() {
    return party;
  }

  /**
   * sets the party to a new party model
   *
   * @param cal party model to add
   */
  public void setParty(Party party) {
    this.party = party;
  }

  /**
   * returns the player's profession
   * @return string representation of the player's profession
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
   * sets the player's profession
   * @return the profession name to set for the players
   */
  public void setProf(String name) {
    party.setProf(name);
  }

  /**
   * returns the name of a particular party member
   *
   * @param  i the index of the party member to get
   *
   * @return   the name of party member i
   */
  public String getName(int i) {
    return party.getName(i);
  }

  /**
   * sets the name of a party member
   *
   * @param i    index of the party member
   * @param name name of the party member
   */
  public void setName(int i, String name) {
    party.setName(i, name);
  }

  /**
   * removes a party member's name
   *
   * @return returns the name removed
   */
  public String removeName() {
    return party.removeName();
  }

  /**
   * returns the total size of the party
   *
   * @return size of the party
   */
  public int partySize() {
    return party.totalSize();
  }

  /**
   * returns the current party size
   *
   * @return current party size
   */
  public int currPartySize() {
    return party.size();
  }

  /**
   * add's money to the party
   * @param  amount amount of money to add
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

  public void resetCart() {
    cart.resetCart();
  }

  public String getCurrentCity() {
    return mapModel.getCurrentCity();
  }


  public void setCurrentCity(String city) {
    mapModel.setCurrentCity(city);
  }

  public String getNextCity() {
    return mapModel.getNextCity();
  }

  public double getDistRatio() {
    return mapModel.getDistRatio();
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

  public boolean advancePosition() {
    return mapModel.advancePosition(travelRate);
  }

  public int getTotalMiles() {
    return mapModel.getTotalMiles();
  }

  public int milesToLandmark() {
    return mapModel.milesToLandmark();
  }

  public int getTravelRate() {
    return travelRate;
  }

  public void setTravelRate(int rate) {
    travelRate = rate;
  }

  public boolean getAtDestination() {
    return mapModel.getAtDestination();
  }
}
