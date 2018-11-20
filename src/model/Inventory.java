package model;

public class Inventory {
  int money;
  int oxen;
  int water;
  int food;
  int blankets;
  int bullets;
  // optional
  int wheels;
  int axles;
  int tongues;

  /**
   * [Inventory description]
   * @param prof [description]
   */
  public Inventory(Party.Profession prof) {
    switch (prof) {
      case BANKER:
        this.money = 1600;
        break;
      case CARPENTER:
        this.money = 800;
        break;
      case FARMER:
        this.money = 400;
        break;
      default:
        throw new IllegalStateException();
    }
    this.money = 0;
    this.oxen = 0;
    this.water = 0;
    this.blankets = 0;
    this.food = 0;
    // optional
    this.bullets = 0;
    this.wheels = 0;
    this.axles = 0;
    this.tongues = 0;
  }

  /**
   * [addMoney description]
   * @param  amount [description]
   * @return        [description]
   */
  protected int addMoney(int amount) {
    if (amount >= 0) {
      return this.money += amount;
    }
    return this.money;
  }

  /**
   * [removeMoney description]
   * @param  amount [description]
   * @return        [description]
   */
  protected boolean removeMoney(int amount) {
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
  protected int getMoney() {
    return this.money;
  }

  /**
   * [addOxen description]
   * @param  amount [description]
   * @return        [description]
   */
  protected int addOxen(int amount) {
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
  protected boolean removeOxen(int amount) {
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
  protected int getOxen() {
    return this.oxen;
  }

  /**
   * [addBlankets description]
   * @param  amount [description]
   * @return        [description]
   */
  protected int addBlankets(int amount) {
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
  protected boolean removeBlankets(int amount) {
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
  protected int getBlankets() {
    return this.blankets;
  }

  /**
   * [addFood description]
   * @param  amount [description]
   * @return        [description]
   */
  protected int addFood(int amount) {
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
  protected boolean removeFood(int amount) {
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
  protected int getFood() {
    return this.food;
  }

  /**
   * [addWater description]
   * @param  amount [description]
   * @return        [description]
   */
  protected int addWater(int amount) {
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
  protected boolean removeWater(int amount) {
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
  protected int getWater() {
    return this.water;
  }

  /**
   * [addBullets description]
   * @param  amount [description]
   * @return        [description]
   */
  protected int addBullets(int amount) {
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
  protected boolean removeBullets(int amount) {
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
  protected int getBullets() {
    return this.bullets;
  }
  // ------------------- start of optional parts ----------------//

  /**
   * [addWheels description]
   * @param  amount [description]
   * @return        [description]
   */
  protected int addWheels(int amount) {
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
  protected boolean removeWheels(int amount) {
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
  protected int getWheels() {
    return this.wheels;
  }

  /**
   * [addAxles description]
   * @param  amount [description]
   * @return        [description]
   */
  protected int addAxles(int amount) {
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
  protected boolean removeAxles(int amount) {
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
  protected int getAxles() {
    return this.axles;
  }

  /**
   * [addTongues description]
   * @param  amount [description]
   * @return        [description]
   */
  protected int addTongues(int amount) {
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
  protected boolean removeTongues(int amount) {
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
  protected int getTongues() {
    return this.tongues;
  }
}
