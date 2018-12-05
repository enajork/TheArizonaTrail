package model;

import java.io.*;
/**
 * @author Jordan Bridgewater, Jared Grady, Eric Najork, David Najork
 * @version     1.0
 * @since       1.0
 */

public class Cart implements Serializable {
  private final double OXEN_PRICE = 40.0;
  private final double FOOD_PRICE = 0.2;
  private final double CLOTHES_PRICE = 10.0;
  private final double AMMO_PRICE = 0.1;
  private final double PARTS_PRICE = 10.0;
  private double clothes;
  private double parts;
  private double oxen;
  private double food;
  private double ammo;

  /**
   * Constructor for the cart. Sets all initial score values to 0
   */
  public Cart() {
    this.clothes = 0.0;
    this.parts = 0.0;
    this.oxen = 0.0;
    this.food = 0.0;
    this.ammo = 0.0;
  }

  /**
   * getter for the clothes field
   *
   * @return number of clothes
   */
  public double getClothes() {
    return clothes;
  }

  /**
   * setter for the clothes field
   *
   * @param amount amount of clothes to add
   */
  public void setClothes(double amount) {
    if (amount >= 0) {
      clothes = amount;
    }
  }

  /**
   * getter for parts
   *
   * @return number of parts in the cart
   */
  public double getParts() {
    return parts;
  }

  /**
   * setter for parts
   *
   * @param amount amount of parts to set
   */
  public void setParts(double amount) {
    if (amount >= 0) {
      parts = amount;
    }
  }

  /**
   * calculates the total number of supplies in the cart
   *
   * @return total score based on number of parts
   */
  public double getTotal() {
    return oxen + food + clothes + ammo + parts;
  }

  /**
   * getter for oxen
   *
   * @return the number of oxen in the cart
   */
  public double getOxen() {
    return oxen;
  }

  /**
   * setter for oxen
   *
   * @param amount the amount of oxen to add
   */
  public void setOxen(double amount) {
    if (amount >= 0) {
      oxen = amount;
    }
  }

  /**
   * getter for food
   *
   * @return the amount of food on the cart
   */
  public double getFood() {
    return food;
  }

  /**
   * setter for food
   *
   * @param amount amount of food to add
   */
  public void setFood(double amount) {
    if (amount >= 0) {
      food = amount;
    }
  }

  /**
   * getter for ammo
   *
   * @return the amount of ammo on the cart
   */
  public double getAmmo() {
    return ammo;
  }

  /**
   * setter for ammo
   *
   * @param amount amount of ammo to add
   */
  public void setAmmo(double amount) {
    if (amount >= 0) {
      ammo = amount;
    }
  }

  /**
   * resets the cart to its base supply counts
   */
  public void resetCart() {
    clothes = 0.0;
    parts = 0.0;
    oxen = 0.0;
    food = 0.0;
    ammo = 0.0;
  }
}
