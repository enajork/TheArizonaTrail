package model;

import java.io.*;

public class Inventory implements Serializable {
  double money;
  int oxen;
  int water;
  int food;
  int blankets;
  int clothes;
  int bullets;
  int wheels;
  int axles;
  int tongues;

  /**
   * [Inventory description]
   * @param prof [description]
   */
  public Inventory() {
    this.money = 400;
    this.oxen = 0;
    this.water = 0;
    this.blankets = 0;
    this.clothes = 0;
    this.food = 0;
    this.bullets = 0;
    this.wheels = 0;
    this.axles = 0;
    this.tongues = 0;
  }

  /**
   * [addmoney description]
   * @param  amount [description]
   * @return        [description]
   */
  public void addMoney(double amount) {
    if (amount >= 0) {
      this.money += amount;
    }
  }

  /**
   * [removemoney description]
   * @param  amount [description]
   * @return        [description]
   */
  public boolean removeMoney(double amount) {
    if (amount >= 0 && this.money - amount >= 0) {
      this.money -= amount;
      return true;
    }
    return false;
  }

  /**
   * [getMoney description]
   * @return [description]
   */
  public double getMoney() {
    return this.money;
  }

  /**
   * [addOxen description]
   * @param  amount [description]
   * @return        [description]
   */
  public int addOxen(int amount) {
    if (amount >= 0) {
      return this.oxen += amount;
    }
    return this.oxen;
  }

  /**
   * [removeOxen description]
   * @param  amount [description]
   * @return        [description]
   */
  public boolean removeOxen(int amount) {
    if (amount >= 0 && this.oxen - amount >= 0) {
      this.oxen -= amount;
      return true;
    }
    return false;
  }

  /**
   * [getOxen description]
   * @return [description]
   */
  public int getOxen() {
    return this.oxen;
  }

  /**
   * [addBlankets description]
   * @param  amount [description]
   * @return        [description]
   */
  public int addClothes(int amount) {
    if (amount >= 0) {
      return this.clothes += amount;
    }
    return this.clothes;
  }

  /**
   * [removeBlankets description]
   * @param  amount [description]
   * @return        [description]
   */
  public boolean removeClothes(int amount) {
    if (amount >= 0 && this.clothes - amount >= 0) {
      this.clothes -= amount;
      return true;
    }
    return false;
  }

  /**
   * [getBlankets description]
   * @return [description]
   */
  public int getClothes() {
    return this.clothes;
  }

  /**
   * [addBlankets description]
   * @param  amount [description]
   * @return        [description]
   */
  public int addBlankets(int amount) {
    if (amount >= 0) {
      return this.blankets += amount;
    }
    return this.blankets;
  }

  /**
   * [removeBlankets description]
   * @param  amount [description]
   * @return        [description]
   */
  public boolean removeBlankets(int amount) {
    if (amount >= 0 && this.blankets - amount >= 0) {
      this.blankets -= amount;
      return true;
    }
    return false;
  }

  /**
   * [getBlankets description]
   * @return [description]
   */
  public int getBlankets() {
    return this.blankets;
  }

  /**
   * [addFood description]
   * @param  amount [description]
   * @return        [description]
   */
  public int addFood(int amount) {
    if (amount >= 0) {
      return this.food += amount;
    }
    return this.food;
  }

  /**
   * [removeFood description]
   * @param  amount [description]
   * @return        [description]
   */
  public boolean removeFood(int amount) {
    if (amount >= 0 && this.food - amount >= 0) {
      this.food -= amount;
      return true;
    }
    return false;
  }

  /**
   * [getFood description]
   * @return [description]
   */
  public int getFood() {
    return this.food;
  }

  /**
   * [addWater description]
   * @param  amount [description]
   * @return        [description]
   */
  public int addWater(int amount) {
    if (amount >= 0) {
      return this.water += amount;
    }
    return this.water;
  }

  /**
   * [removeWater description]
   * @param  amount [description]
   * @return        [description]
   */
  public boolean removeWater(int amount) {
    if (amount >= 0 && this.water - amount >= 0) {
      this.water -= amount;
      return true;
    }
    return false;
  }

  /**
   * [getWater description]
   * @return [description]
   */
  public int getWater() {
    return this.water;
  }

  /**
   * [addBullets description]
   * @param  amount [description]
   * @return        [description]
   */
  public int addBullets(int amount) {
    if (amount >= 0) {
      return this.bullets += amount;
    }
    return this.bullets;
  }

  /**
   * [removeBullets description]
   * @param  amount [description]
   * @return        [description]
   */
  public boolean removeBullets(int amount) {
    if (amount >= 0 && this.bullets - amount >= 0) {
      this.bullets -= amount;
      return true;
    }
    return false;
  }

  /**
   * [getBullets description]
   * @return [description]
   */
  public int getBullets() {
    return this.bullets;
  }
  // ------------------- start of optional parts ----------------//

  /**
   * [addWheels description]
   * @param  amount [description]
   * @return        [description]
   */
  public int addWheels(int amount) {
    if (amount >= 0) {
      return this.wheels += amount;
    }
    return this.wheels;
  }

  /**
   * [removeWheels description]
   * @param  amount [description]
   * @return        [description]
   */
  public boolean removeWheels(int amount) {
    if (amount >= 0 && this.wheels - amount >= 0) {
      this.wheels -= amount;
      return true;
    }
    return false;
  }

  /**
   * [getWheels description]
   * @return [description]
   */
  public int getWheels() {
    return this.wheels;
  }

  /**
   * [addAxles description]
   * @param  amount [description]
   * @return        [description]
   */
  public int addAxles(int amount) {
    if (amount >= 0) {
      return this.axles += amount;
    }
    return this.axles;
  }

  /**
   * [removeAxles description]
   * @param  amount [description]
   * @return        [description]
   */
  public boolean removeAxles(int amount) {
    if (amount >= 0 && this.axles - amount >= 0) {
      this.axles -= amount;
      return true;
    }
    return false;
  }

  /**
   * [getAxles description]
   * @return [description]
   */
  public int getAxles() {
    return this.axles;
  }

  /**
   * [addTongues description]
   * @param  amount [description]
   * @return        [description]
   */
  public int addTongues(int amount) {
    if (amount >= 0) {
      return this.tongues += amount;
    }
    return this.tongues;
  }

  /**
   * [removeTongues description]
   * @param  amount [description]
   * @return        [description]
   */
  public boolean removeTongues(int amount) {
    if (amount >= 0 && this.tongues - amount >= 0) {
      this.tongues -= amount;
      return true;
    }
    return false;
  }

  /**
   * [getTongues description]
   * @return [description]
   */
  public int getTongues() {
    return this.tongues;
  }
}
