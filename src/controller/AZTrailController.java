package controller;

import java.util.*;
import java.io.*;
import model.*;

public class AZTrailController {
  private final String savePath = "model/save_game.dat";
  private final String scoresPath = "model/topten.dat";
  public static boolean hasSave = false;
  public static boolean sound = true;
  public static boolean escape = false;
  private AZTrailModel checkpoint;
  private AZTrailModel model;
  private TopTen topTen;
  private static Random rand;

  // debug flag
  private static final boolean SAVE_DEBUG = false;
  private static final boolean SCORE_DEBUG = true;

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
    checkpoint.addMoney(model.getMoney());
    checkpoint.addOxen(model.getOxen());
    checkpoint.addFood(model.getFood());
    checkpoint.addBlankets(model.getBlankets());
    checkpoint.addClothes(model.getClothes());
    checkpoint.addBullets(model.getBullets());
    checkpoint.addWheels(model.getWheels());
    checkpoint.addAxles(model.getAxles());
    checkpoint.addTongues(model.getTongues());
    checkpoint.addWater(model.getWater());
    checkpoint.setDay(model.getDay());
    checkpoint.setMonth(model.getMonth());
    checkpoint.setYear(model.getYear());
    checkpoint.setCurrentCity(model.getCurrentCity());
    checkpoint.setHunted(model.getHunted());
    checkpoint.setProf(model.getProf());
    checkpoint.setTravelRate(model.getTravelRate());
    for (int i = 0; i < model.currPartySize(); i++) {
      checkpoint.setName(i, model.getName(i));
    }
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
        System.out.println(toString());
      }
      save.close();
      load.close();
    } catch (IOException e) {
      if (SCORE_DEBUG) {
        System.err.println("failed to load scores");
        System.out.println(toString());
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
        System.out.println(toString());
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
  public void advance() {
    this.model.advanceCalendar();
    this.model.advancePosition();
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

  public void setScore(int i, String name, int score) {
    topTen.setScore(i, name, score);
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
}
