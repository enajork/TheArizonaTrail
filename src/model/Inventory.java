package model;

public class Inventory {
  int money;
  int oxen;
  int clothes;
  int bullets;
  int wheels;
  int axles;
  int tongues;
  int food;

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
    this.clothes = 0;
    this.bullets = 0;
    this.wheels = 0;
    this.axles = 0;
    this.tongues = 0;
    this.food = 0;
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

  protected int addClothes(int amount) {
    if (amount >= 0) {
      return this.clothes += amount;
    }
    return this.clothes;
  }

  protected boolean removeClothes(int amount) {
    if (amount >= 0 && this.clothes - amount >= 0) {
      this.clothes -= amount;
      return true;
    }
    return false;
  }

  protected int getClothes() {
    return this.clothes;
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
}
