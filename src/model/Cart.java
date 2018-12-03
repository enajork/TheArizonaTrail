package model;

import java.io.*;
/**
 * @author Jordan Bridgewater, Jared Grady, Eric Najork, David Najork
 * @version     1.0
 * @since       1.0
 */

public class Cart implements Serializable {
  private double clothes;
  private double parts;
  private double oxen;
  private double food;
  private double ammo;

  public Cart() {
    this.clothes = 0.0;
    this.parts = 0.0;
    this.oxen = 0.0;
    this.food = 0.0;
    this.ammo = 0.0;
  }

  public double getClothes() {
    return clothes;
  }

  public void setClothes(double amount) {
    if (amount >= 0) {
      clothes = amount;
    }
  }

  public double getParts() {
    return parts;
  }

  public void setParts(double amount) {
    if (amount >= 0) {
      parts = amount;
    }
  }

  public double getTotal() {
    return oxen + food + clothes + ammo + parts;
  }

  public double getOxen() {
    return oxen;
  }

  public void setOxen(double amount) {
    if (amount >= 0) {
      oxen = amount;
    }
  }

  public double getFood() {
    return food;
  }

  public void setFood(double amount) {
    if (amount >= 0) {
      food = amount;
    }
  }

  public double getAmmo() {
    return ammo;
  }

  public void setAmmo(double amount) {
    if (amount >= 0) {
      ammo = amount;
    }
  }

  public void resetCart() {
    clothes = 0.0;
    parts = 0.0;
    oxen = 0.0;
    food = 0.0;
    ammo = 0.0;
  }
}
