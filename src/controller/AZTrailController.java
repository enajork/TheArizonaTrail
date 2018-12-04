package controller;

import java.util.*;
import java.io.*;
import model.*;

public class AZTrailController {
  private final int BREAK_TONGUE = 1;
  private final int BREAK_WHEEL = 1;
  private final int BREAK_AXLE = 1;
  private final int OXEN_DEATH = 1;
  private final int SICK = 1;
  private final int LUCK = 100;
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
   * [AZTrailController description]
   */
  public AZTrailController() {
    this.rand = new Random(System.currentTimeMillis());
    this.checkpoint = new AZTrailModel();
    this.model = new AZTrailModel();
    this.topTen = new TopTen();
  }

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

  public void resetGame() {
    model = new AZTrailModel();
    if (SAVE_DEBUG) {
      System.out.println(toString());
    }
  }

  public void setCheckpoint() {
    checkpoint = new AZTrailModel();
    checkpoint.setMap(model.getMap());
    checkpoint.setParty(model.getParty());
    checkpoint.setHunted(model.getHunted());
    checkpoint.setCalendar(model.getCalendar());
    checkpoint.setTravelRate(model.getTravelRate());
  }

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
   * [advance description]
   */
  public boolean advance() {
    model.advanceCalendar();
    return model.advancePosition();
  }

  /**
   * [changePace description]
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

  public String toString() {
    String result = "\n----------start-----------\n" + model.toString();
    result += "\nhasSave=" + hasSave;
    result += "\nsound=" + sound;
    result += "\nescape=" + escape;
    return result + "\n----------end------------\n";
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
    return model.currPartySize();
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

  public void resetCart() {
    model.resetCart();
  }

  public String getCurrentCity() {
    return model.getCurrentCity();
  }

  public double getDistRatio() {
    return model.getDistRatio();
  }

  public void setHunted(boolean value) {
    model.setHunted(value);
  }

  public boolean getHunted() {
    return model.getHunted();
  }

  public int getTotalMiles() {
    return model.getTotalMiles();
  }

  public double milesToLandmark() {
    return model.milesToLandmark();
  }

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

  public String getNextCity() {
    return model.getNextCity();
  }

  public void setScore(String name, int score) {
    topTen.setScore(name, score);
  }

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

  public String getTopTenScores() {
    String result = "";
    for (int i = 0; i < topTen.size(); i++) {
      result += ((topTen.getScore(i) == 0) ? "" : topTen.getScore(i)) + "\n";
    }
    return result;
  }

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

  public int getTravelRate() {
    return model.getTravelRate();
  }

  public void setTravelRate(int rate) {
    model.setTravelRate(rate);
  }

  public boolean getAtDestination() {
    return model.getAtDestination();
  }

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
    if (p >= 0 && p <= BREAK_WHEEL) {
      removeWheels(1);
      if (getWheels() == 0) {
        return "1Your wagon wheel broke\nand you don't have\nany spares.";
      }
      return "0One of your wheels broke.";
    }
    p = rand.nextInt(LUCK);
    if (p >= 0 && p <= BREAK_AXLE) {
      removeAxles(1);
      if (getAxles() == 0) {
        return "1Your wagon axle broke\nand you don't have\nany spares.";
      }
      return "0One of your axles broke.";
    }
    p = rand.nextInt(LUCK);
    if (p >= 0 && p <= BREAK_TONGUE) {
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
