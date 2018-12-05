package controller;

import java.util.*;
import java.io.*;
import model.*;
/**
 * @author Jordan Bridgewater, Jared Grady, Eric Najork, David Najork
 * @version     1.0
 * @since       1.0
 */

public class AZTrailController {
  private final int BREAK_TONGUE = 1;
  private final int BREAK_WHEEL = 1;
  private final int BREAK_AXLE = 1;
  private final int OXEN_DEATH = 1;
  private final int SICK = 1;
  private final int LUCK = 300;
  private final String savePath = "model/save_game.dat";
  private final String scoresPath = "model/topten.dat";
  public static boolean hasSave = false;
  public static boolean sound = true;
  public static boolean escape = false;
  private AZTrailModel checkpoint;
  private AZTrailModel model;
  private static Random rand;
  private TopTen topTen;

  // debug flags
  private static final boolean SAVE_DEBUG = false;
  private static final boolean SCORE_DEBUG = false;
  private static final boolean DAMAGE = true;

  /**
   * Constructs a new controller and loads a new or saved model
   */
  public AZTrailController() {
    this.rand = new Random(System.currentTimeMillis());
    this.checkpoint = new AZTrailModel();
    this.model = new AZTrailModel();
    this.topTen = new TopTen();
  }

  /**
   * Loads a saved game if a saved game file exists
   */
  public void loadGame() {
    try {
      File file = new File(savePath);
      file.createNewFile();
      file.setWritable(true);
      FileInputStream load = new FileInputStream(savePath);
      ObjectInputStream save = new ObjectInputStream(load);
      model = (AZTrailModel) save.readObject();
      hasSave = true;
      if (SAVE_DEBUG) {
        System.out.println("save loaded");
        System.out.println(toString());
      }
      save.close();
      load.close();
    } catch (IOException e) {
      if (SAVE_DEBUG) {
        System.err.println("failed to load save");
        System.out.println(toString());
      }
      model = new AZTrailModel();
    } catch (ClassNotFoundException e) {
      System.err.println("Fatal error: " + e.getMessage());
      System.exit(1);
    } catch (ClassCastException e) {
      System.err.println("Fatal error: " + e.getMessage());
      System.exit(1);
    }
  }

  /**
   * Serializes the gameplay model and writes it to
   * a saved game file in the model directory
   */
  public void saveGame() {
    try {
      FileOutputStream save = new FileOutputStream(savePath);
      ObjectOutputStream load = new ObjectOutputStream(save);
      load.writeObject(checkpoint);
      hasSave = true;
      if (SAVE_DEBUG) {
        System.out.println("game saved");
        System.out.println(toString());
      }
      load.close();
      save.close();
    } catch (IOException e) {
      if (SAVE_DEBUG) {
        System.err.println("unable to save");
      }
    }
  }

  /**
   * Deletes the saved game file stored in the
   * model directory if it exists
   */
  public void deleteSave() {
    File save = new File(savePath);
    if (save.exists()) {
      save.delete();
      hasSave = false;
      if (SAVE_DEBUG) {
        System.out.println("save deleted");
      }
    }
    model = new AZTrailModel();
    if (SAVE_DEBUG) {
      System.out.println(toString());
    }
  }

  /**
   * Starts a new game by initializing a new model and
   * writes to standard out if save debug is enabled
   */
  public void resetGame() {
    model = new AZTrailModel();
    if (SAVE_DEBUG) {
      System.out.println(toString());
    }
  }

  /**
   * Creates a new trail model checkpoint with
   * the data from the current model
   */
  public void setCheckpoint() {
    checkpoint = new AZTrailModel();
    checkpoint.setMap(model.getMap());
    checkpoint.setParty(model.getParty());
    checkpoint.setHunted(model.getHunted());
    checkpoint.setCalendar(model.getCalendar());
    checkpoint.setTravelRate(model.getTravelRate());
  }

  /**
   * Retrieves the serialized leaderboard from disk
   */
  public void loadTopTen() {
    try {
      File file = new File(scoresPath);
      file.createNewFile();
      file.setWritable(true);
      FileInputStream load = new FileInputStream(scoresPath);
      ObjectInputStream save = new ObjectInputStream(load);
      topTen = (TopTen) save.readObject();
      if (SCORE_DEBUG) {
        System.out.println("scores loaded");
      }
      save.close();
      load.close();
    } catch (IOException e) {
      if (SCORE_DEBUG) {
        System.err.println("failed to load scores");
      }
      topTen = new TopTen();
    } catch (ClassNotFoundException e) {
      System.err.println("Fatal error: " + e.getMessage());
      System.exit(1);
    } catch (ClassCastException e) {
      System.err.println("Fatal error: " + e.getMessage());
      System.exit(1);
    }
  }

  /**
   * Serializes the top 10 players names and scores to disk
   * for later retrievals
   */
  public void saveTopTen() {
    try {
      FileOutputStream save = new FileOutputStream(scoresPath);
      ObjectOutputStream load = new ObjectOutputStream(save);
      load.writeObject(topTen);
      if (SCORE_DEBUG) {
        System.out.println("scores saved");
      }
      load.close();
      save.close();
    } catch (IOException e) {
      if (SCORE_DEBUG) {
        System.err.println("unable to save scores");
      }
    }
  }

  /**
   * Clears the leaderboard from disk
   */
  public void resetTopTen() {
    File save = new File(scoresPath);
    if (save.exists()) {
      save.delete();
      if (SCORE_DEBUG) {
        System.out.println("scores reset");
      }
    }
    topTen = new TopTen();
  }

  /**
   * Moves the model's calendar forward one day
   * and advances the position of the player in the AZTrailModel
   */
  public boolean advance() {
    model.advanceCalendar();
    return model.advancePosition();
  }

  /**
   * Changes the pace the party is traveling at
   */
  public void changePace(int pace) {
    int rate;

    switch (pace) {
      case 1:
        rate = 2;
        break;

      case 2:
        rate = 4;
        break;

      case 3:
        rate = 6;
        break;

      default:
        rate = 2;
    }

    model.setTravelRate(rate);
  }

  /**
   * Sets the chosen profession for the player based
   * on the input parameter
   * @param choice integer option for profession
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
   * Serializes the model as a string and returns it
   *
   * @return the stringified model
   */
  public String toString() {
    String result = "\n----------start-----------\n" + model.toString();
    result += "\nhasSave=" + hasSave;
    result += "\nsound=" + sound;
    result += "\nescape=" + escape;
    return result + "\n----------end------------\n";
  }

  /**
   * getter for the name field in the model
   * @return the model's name field
   */
  public String getName(int i) {
    return model.getName(i);
  }

  /**
   * setter for the name field in the model
   * @param name sets the models name field
   */
  public void setName(int i, String name) {
    model.setName(i, name);
  }

  /**
   * returns the current party size in the model
   *
   * @return party size
   */
  public int partySize() {
    return model.currPartySize();
  }

  /**
   * Sets the current month in the model
   * @param  month the month to set
   */
  public void setMonth(String month) {
    model.setMonth(month);
  }

  /**
   * gets the current month from the model
   * @return the model's month field
   */
  public int getMonth() {
    return model.getMonth();
  }

  /**
   * gets the current date's date string
   * @return the date string
   */
  public String getDateStr() {
    return model.getDateStr();
  }

  /**
   * add's money to the player's wallet
   * @param  amount an integer amount of money
   */
  public void addMoney(double amount) {
    model.addMoney(amount);
  }

  /**
   * removes's money to the player's wallet
   * @param  amount an integer amount of money
   * @return        true or false if successful
   */
  public boolean removeMoney(double amount) {
    return model.removeMoney(amount);
  }

  /**
   * getter for the player's money amount
   * @return player's remaining money
   */
  public double getMoney() {
    return model.getMoney();
  }

  /**
   * add's an oxen yoke to the party
   * @param  amount the amount of oxen to add
   * @return        the amount of oxen total
   */
  public int addOxen(int amount) {
    return model.addOxen(amount);
  }

  /**
   * remove's a yoke of oxen from the party
   * @param  amount the number of oxen to remove
   * @return        true if there are no more oxen
   */
  public boolean removeOxen(int amount) {
    return model.removeOxen(amount);
  }

  /**
   * returns the number of oxen in the party
   * @return the number of oxen
   */
  public int getOxen() {
    return model.getOxen();
  }

  /**
   * adds clothing to the players party
   * @param  amount the amount of clothes to add
   * @return        the total amount of clothes
   */
  public int addClothes(int amount) {
    return model.addClothes(amount);
  }

  /**
   * removes clothing to the players party
   * @param  amount the amount of clothes to remove
   * @return        true if there are no clothes
   */
  public boolean removeClothes(int amount) {
    return model.removeClothes(amount);
  }

  /**
   * getter for clothes
   * @return returns the number of clothes left
   */
  public int getClothes() {
    return model.getClothes();
  }

  /**
   * adds blankets to the party's store
   * @param  amount amount of blankets to add
   * @return        total amount of blankets
   */
  public int addBlankets(int amount) {
    return model.addBlankets(amount);
  }

  /**
   * removes blankets to the party's store
   * @param  amount amount of blankets to removes
   * @return        true if no blankets remain
   */
  public boolean removeBlankets(int amount) {
    return model.removeBlankets(amount);
  }

  /**
   * getter for blanket
   * @return returns the number of blankets in the party
   */
  public int getBlankets() {
    return model.getBlankets();
  }

  /**
   * adds bullets to the party's store
   * @param  amount number of bullets to add
   * @return        returns the total number of bullets
   */
  public int addBullets(int amount) {
    return model.addBullets(amount);
  }

  /**
   * removes bullets to the party's store
   * @param  amount number of bullets to remove
   * @return        true if there are no more bullets
   */
  public boolean removeBullets(int amount) {
    return model.removeBullets(amount);
  }

  /**
   * getter for the amount of bullets
   * @return number of remaining bullets
   */
  public int getBullets() {
    return model.getBullets();
  }

  /**
   * adds food to the party's store
   * @param  amount amount of food to add
   * @return        returns the amount of food
   */
  public int addFood(int amount) {
    return model.addFood(amount);
  }

  /**
   * removes food to the party's store
   * @param  amount amount of food to removes
   * @return        true if no food remains
   */
  public boolean removeFood(int amount) {
    return model.removeFood(amount);
  }

  /**
   * getter for the amount of food
   * @return amount of food remaining
   */
  public int getFood() {
    return model.getFood();
  }

  /**
   * adds water to the party's store
   * @param  amount amount of water to add
   * @return        total amount of water
   */
  public int addWater(int amount) {
    return model.addWater(amount);
  }

  /**
   * removes water to the party's store
   * @param  amount amount of water to removes
   * @return        true if no water remains
   */
  public boolean removeWater(int amount) {
    return model.removeWater(amount);
  }

  /**
   * getter for the amount of water
   * @return amount of water remaining
   */
  public int getWater() {
    return model.getWater();
  }

  /**
   * adds spare wheels to the party's store
   * @param  amount amount of wheels to add
   * @return        total number of wheels left
   */
  public int addWheels(int amount) {
    return model.addWheels(amount);
  }

  /**
   * removes spare wheels to the party's store
   * @param  amount amount of wheels to removes
   * @return        true if no wheels remain
   */
  public boolean removeWheels(int amount) {
    return model.removeWheels(amount);
  }

  /**
   * getter for the number of wheels
   * @return the number of remaining wheels
   */
  public int getWheels() {
    return model.getWheels();
  }

  /**
   * adds spare axles to the party's store
   * @param  amount amount of axles to add
   * @return        total number of axles
   */
  public int addAxles(int amount) {
    return model.addAxles(amount);
  }

  /**
   * removes spare axles to the party's store
   * @param  amount amount of axles to remove
   * @return        true if no axles remain
   */
  public boolean removeAxles(int amount) {
    return model.removeAxles(amount);
  }

  /**
   * getter for axles
   * @return number of axles in the party's store
   */
  public int getAxles() {
    return model.getAxles();
  }

  /**
   * adds spare tongues to the party's store
   * @param  amount amount of tongues to add
   * @return        total number of tongues
   */
  public int addTongues(int amount) {
    return model.addTongues(amount);
  }

  /**
   * remove spare tongues to the party's store
   * @param  amount amount of tongues to remove
   * @return        total number of tongues
   */
  public boolean removeTongues(int amount) {
    return model.removeTongues(amount);
  }

  /**
   * getter for tongues
   * @return number of tongues in the party's store
   */
  public int getTongues() {
    return model.getTongues();
  }

  /**
   * getter for cart clothes
   * @return number of cart clothes in the party's store
   */
  public double getCartClothes() {
    return model.getCartClothes();
  }

  /**
   * setter for cart clothes
   *
   * @param amount amount of cart clothes to add
   */
  public void setCartClothes(double amount) {
    model.setCartClothes(amount);
  }

  /**
   * getter for cart parts
   * @return number of cart parts in the party's store
   */
  public double getCartParts() {
    return model.getCartParts();
  }

  /**
   * setter for cart parts
   *
   * @param amount amount of cart parts to add
   */
  public void setCartParts(double amount) {
    model.setCartParts(amount);
  }

  /**
   * gets the total resources of the cart
   *
   * @return total cart resources as a double
   */
  public double getCartTotal() {
    return model.getCartTotal();
  }

  /**
   * gets the amount of oxen on the cart
   *
   * @return amount of oxen on the cart
   */
  public double getCartOxen() {
    return model.getCartOxen();
  }

  /**
   * sets the amount of oxen on the cart
   *
   * @param amount amount of oxen to set
   */
  public void setCartOxen(double amount) {
    model.setCartOxen(amount);
  }

  /**
   * gets the amount of food on the cart
   *
   * @return amount of food on the cart
   */
  public double getCartFood() {
    return model.getCartFood();
  }

  /**
   * sets the amount of food on the cart
   *
   * @param amount amount of food to set
   */
  public void setCartFood(double amount) {
    model.setCartFood(amount);
  }

  /**
   * gets the amount of ammo on the cart
   *
   * @return amount of ammo on the cart
   */
  public double getCartAmmo() {
    return model.getCartAmmo();
  }

  /**
   * sets the amount of ammo on the cart
   *
   * @param amount amount of amo to set
   */
  public void setCartAmmo(double amount) {
    model.setCartAmmo(amount);
  }

  /**
   * resets the cart to the base stats
   */
  public void resetCart() {
    model.resetCart();
  }

  /**
   * gets the current closest city to the player
   *
   * @return the string name of the city
   */
  public String getCurrentCity() {
    return model.getCurrentCity();
  }

  /**
   * gets the distance ratio between the previous and next landmark
   *
   * @return the double distance ratio
   */
  public double getDistRatio() {
    return model.getDistRatio();
  }

  /**
   * setter for the hunted field
   *
   * @param value true if the player should be hunted, false otherwise
   */
  public void setHunted(boolean value) {
    model.setHunted(value);
  }

  /**
   * getter for the hunted field
   *
   * @return returns true if the player is being hunted
   */
  public boolean getHunted() {
    return model.getHunted();
  }

  /**
   * getter for the total miles traveled by the wagon
   *
   * @return integer count of miles
   */
  public int getTotalMiles() {
    return model.getTotalMiles();
  }

  /**
   * getter for the current distance to the next landmark
   *
   * @return the remaining distance till the next landmark
   */
  public double milesToLandmark() {
    return model.milesToLandmark();
  }

  /**
   * calculates the current weather in the area
   *
   * @return a string representation of the current weather
   */
  public String getWeather() {
    int p = rand.nextInt(9);

    String season = model.getSeason();

    if (season.equals("winter")) {
      if (p < 2) {
        return "cold";
      } else {
        return "cool";
      }
    }
    else if (season.equals("spring")) {
      if (p == 0) {
        return "cool";
      } else {
        return "warm";
      }
    }
    else if (season.equals("summer")) {
      if (p < 5) {
        return "hot";
      } else {
        return "scorching";
      }
    }
    else {
      if (p < 2) {
        return "hot";
      } else if (p >= 2 && p < 7){
        return "warm";
      } else {
        return "cool";
      }
    }
  }

  /**
   * returns the next city on the map
   *
   * @return the name of the next city
   */
  public String getNextCity() {
    return model.getNextCity();
  }

  /**
   * sets a player's final score after the game
   *
   * @param name  the player's name
   * @param score the player's score
   */
  public void setScore(String name, int score) {
    topTen.setScore(name, score);
  }

  /**
   * get's the names of the top ten players from the model
   *
   * @return a string representation of the players
   */
  public String getTopTenNames() {
    String result = "";
    for (int i = 0; i < topTen.size(); i++) {
      result += (i + 1)
        + ". "
        + ((topTen.getName(i) == null || topTen.getName(i).length() == 0)
          ? "" : topTen.getName(i))
        + "\n";
    }
    return result;
  }

  /**
   * get's the top ten scores from the model
   *
   * @return the top ten scores as a string
   */
  public String getTopTenScores() {
    String result = "";
    for (int i = 0; i < topTen.size(); i++) {
      result += ((topTen.getScore(i) == 0) ? "" : topTen.getScore(i)) + "\n";
    }
    return result;
  }

  /**
   * calculates the party's current health based on its size
   *
   * @return a string representation of the party's health
   */
  public String getHealth() {
    switch(model.currPartySize()) {
      case 1:
        return "bad";
      case 2:
        return "poor";
      case 3:
        return "okay";
      case 4:
        return "good";
      case 5:
        return "great";
      default:
        return "";
    }
  }

  /**
   * getter for the current travel rate
   *
   * @return the integer current travel rate
   */
  public int getTravelRate() {
    return model.getTravelRate();
  }

  /**
   * sets the current travel rate of the player
   *
   * @param rate the rate at which to travel
   */
  public void setTravelRate(int rate) {
    model.setTravelRate(rate);
  }

  /**
   * getter for the getAtDestination field
   *
   * @return true if the player is at the end of the map
   */
  public boolean getAtDestination() {
    return model.getAtDestination();
  }

  /**
   * calculates the player's score based on remaining items,
   * party members and distance reached
   *
   * @return the player's score as an integer
   */
  public int getScore() {
    setCartAmmo(getBullets());
    setCartFood(getFood());
    setCartOxen(getOxen());
    setCartParts(getWheels() + getAxles() + getTongues());
    setCartClothes(getClothes());
    int result = (int)getMoney() + (int)getCartTotal();
    resetCart();
    result += getWater() + getBlankets();
    result *= model.currPartySize();
    result *= ((getHunted()) ? 2 : 1);
    switch (model.getProf()) {
      case "Banker":
        result *= 1;
        break;
      case "Carpenter":
        result *= 2;
        break;
      case "Farmer":
        result *= 4;
        break;
    }
    return result;
  }

  /**
   * depletes the player's resources as they travel the trail.
   * resources deplete faster as the player's travel rate increases and
   * the weather worsens
   *
   * @return a non empty string if the party dies
   */
  public String deplete() {
    if (!DAMAGE) {
      return "";
    }
    int consumption = 0;
    switch (getWeather()) {
      case "cold":
        consumption = 1;
        break;
      case "cool":
        consumption = 2;
        break;
      case "warm":
        consumption = 3;
        break;
      case "hot":
        consumption = 4;
        break;
      case "scorching":
        consumption = 5;
        break;
    }
    int eat = (getTravelRate() / 2) * (getOxen() + model.currPartySize()) / 2;
    int drink = (getTravelRate() / 2) * (getOxen() + model.currPartySize()
      + consumption) / 2;
    if (getHunted() && ((getFood() <= eat && getWater() <= drink)
        || (getFood() <= eat))) {
      return "Your party died from cannibalism.";
    } else if (getFood() <= eat && getWater() <= drink) {
      return "Your party died from starvation\nand dehydration.";
    } else if (getFood() <= eat) {
      return "Your party died from starvation.";
    } else if (getWater() <= drink) {
      return "Your party died from dehydration.";
    }
    removeFood(eat);
    removeWater(drink);
    return "";
  }

  /**
   * Creates a new random event based on the player's luck stat
   * If the player is not damaged, they will not be susceptible to
   * these negative effects
   *
   * @return a string with the resulting event
   */
  public String randomEvent() {
    if (!DAMAGE) {
      return "";
    }
    int p = rand.nextInt(LUCK);
    if (p >= 0 && p <= (SICK * (model.currPartySize() - getClothes() + 1)
        * ((model.getSeason().equals("winter")) ? 2 : 1 ))) {
      removeBlankets(1);
      if (getBlankets() == 0) {
        return "0" + model.removeName() + " died.";
      }
      if (model.currPartySize() == 0) {
        return "1All of your party members\nhave died.";
      }
      p = rand.nextInt(model.partySize());
      return "0" + getName(p) + " has " + randomIllness() + ".";
    }
    p = rand.nextInt(LUCK);
    if (p >= 0 && p <= (BREAK_WHEEL * (getTravelRate() / 2))) {
      removeWheels(1);
      if (getWheels() == 0) {
        return "1Your wagon wheel broke\nand you don't have\nany spares.";
      }
      return "0One of your wheels broke.";
    }
    p = rand.nextInt(LUCK);
    if (p >= 0 && p <= BREAK_AXLE * (getTravelRate() / 2)) {
      removeAxles(1);
      if (getAxles() == 0) {
        return "1Your wagon axle broke\nand you don't have\nany spares.";
      }
      return "0One of your axles broke.";
    }
    p = rand.nextInt(LUCK);
    if (p >= 0 && p <= BREAK_TONGUE * (getTravelRate() / 2)) {
      removeTongues(1);
      if (getTongues() == 0) {
        return "1Your wagon tongue broke\nand you don't have\nany spares.";
      }
      return "0One of your tongues broke.";
    }
    p = rand.nextInt(LUCK);
    if (p >= 0 && p <= OXEN_DEATH * ((getWeather().equals("summer")) ? 2 : 1)) {
      removeOxen(1);
      if (getOxen() == 0) {
        return "1All of your oxen have died.";
      }
      return "0One of your oxen has died.";
    }
    return "";
  }

  /**
   * randomly selects an illness from the given options
   *
   * @return a string containing the illness name
   */
  private String randomIllness() {
    int p = rand.nextInt(2);
    if (p == 0) {
      return "pneumonia";
    } else if (p == 1) {
      return "dysentery";
    } else {
      return "";
    }
  }
}
