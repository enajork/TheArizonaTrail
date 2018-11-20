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

  protected int addMoney(int amount) {
    if (amount >= 0) {
      return this.money += amount;
    }
    return this.money;
  }

  protected boolean removeMoney(int amount) {
    if (amount >= 0 && this.money - amount >= 0) {
      this.money -= amount;
      return true;
    }
    return false;
  }

  protected int getMoney() {
    return this.money;
  }

  protected int addOxen(int amount) {
    if (amount >= 0) {
      return this.oxen += amount;
    }
    return this.oxen;
  }

  protected boolean removeOxen(int amount) {
    if (amount >= 0 && this.oxen - amount >= 0) {
      this.oxen -= amount;
      return true;
    }
    return false;
  }

  protected int getOxen() {
    return this.oxen;
  }

  protected int addBlankets(int amount) {
    if (amount >= 0) {
      return this.blankets += amount;
    }
    return this.blankets;
  }

  protected boolean removeBlankets(int amount) {
    if (amount >= 0 && this.blankets - amount >= 0) {
      this.blankets -= amount;
      return true;
    }
    return false;
  }

  protected int getBlankets() {
    return this.blankets;
  }

  protected int addFood(int amount) {
    if (amount >= 0) {
      return this.food += amount;
    }
    return this.food;
  }

  protected boolean removeFood(int amount) {
    if (amount >= 0 && this.food - amount >= 0) {
      this.food -= amount;
      return true;
    }
    return false;
  }

  protected int getFood() {
    return this.food;
  }

  protected int addWater(int amount) {
    if (amount >= 0) {
      return this.water += amount;
    }
    return this.water;
  }

  protected boolean removeWater(int amount) {
    if (amount >= 0 && this.water - amount >= 0) {
      this.water -= amount;
      return true;
    }
    return false;
  }

  protected int getWater() {
    return this.water;
  }

  protected int addBullets(int amount) {
    if (amount >= 0) {
      return this.bullets += amount;
    }
    return this.bullets;
  }

  protected boolean removeBullets(int amount) {
    if (amount >= 0 && this.bullets - amount >= 0) {
      this.bullets -= amount;
      return true;
    }
    return false;
  }

  protected int getBullets() {
    return this.bullets;
  }
  // ------------------- start of optional parts ----------------//
  protected int addWheels(int amount) {
    if (amount >= 0) {
      return this.wheels += amount;
    }
    return this.wheels;
  }

  protected boolean removeWheels(int amount) {
    if (amount >= 0 && this.wheels - amount >= 0) {
      this.wheels -= amount;
      return true;
    }
    return false;
  }

  protected int getWheels() {
    return this.wheels;
  }

  protected int addAxles(int amount) {
    if (amount >= 0) {
      return this.axles += amount;
    }
    return this.axles;
  }

  protected boolean removeAxles(int amount) {
    if (amount >= 0 && this.axles - amount >= 0) {
      this.axles -= amount;
      return true;
    }
    return false;
  }

  protected int getAxles() {
    return this.axles;
  }

  protected int addTongues(int amount) {
    if (amount >= 0) {
      return this.tongues += amount;
    }
    return this.tongues;
  }

  protected boolean removeTongues(int amount) {
    if (amount >= 0 && this.tongues - amount >= 0) {
      this.tongues -= amount;
      return true;
    }
    return false;
  }

  protected int getTongues() {
    return this.tongues;
  }


}
