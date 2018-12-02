package model;

import java.util.*;
import java.io.*;

public class MapModel implements Serializable {
  private boolean atDestination;
  private int milesFromLastCity;
  private String currentCity;
  private String nextCity;
  private int totalMiles;
  private Map<String, Integer> milesToCityMap;
  private String[] citiesInOrder = {"Nogales", "Tombstone", "Tucson","Phoenix",
    "Sedona", "Flagstaff", "Page"};

  public MapModel() {
    currentCity = "Nogales";
    nextCity = "Tombstone";
    milesFromLastCity = 0;
    atDestination = false;

    createMilesToCityMap();
  }

  public void advancePosition(int distanceTraveled) {
    if (!atDestination) {
      this.totalMiles += distanceTraveled;
      int totalTraveled = milesFromLastCity + distanceTraveled;
      if (totalTraveled < milesToCityMap.get(nextCity)) {
        milesFromLastCity = totalTraveled;
      } else {
        milesFromLastCity = totalTraveled - milesToCityMap.get(nextCity);
        currentCity = nextCity;
        int i = getNextCityIndex(currentCity);
        nextCity = citiesInOrder[i];
        if (currentCity.equals("Page")) {
          atDestination = true;
        }
      }
    }
  }

  private int getNextCityIndex(String city) {
    for (int i = 0; i < citiesInOrder.length; i++) {
      if (citiesInOrder[i].equals(city)) {
        return Math.min(i + 1, citiesInOrder.length - 1);
      }
    }
    return citiesInOrder.length - 1;
  }

  public String getNextCity() {
    return nextCity;
  }

  public boolean getAtDestination() {
    return atDestination;
  }

  public double getDistRatio() {
    return (double) milesFromLastCity / (double) milesToCityMap.get(nextCity);
  }

  public int milesToLandmark() {
    if (atDestination) {
      return 0;
    }
    return milesToCityMap.get(nextCity) - milesFromLastCity;
  }

  public int getMilesFromLastCity() {
    return milesFromLastCity;
  }

  public String getCurrentCity() {
    return currentCity;
  }

  public void setCurrentCity(String city) {
    currentCity = city;
  }

  public int getTotalMiles() {
    return this.totalMiles;
  }

  private void createMilesToCityMap() {
    milesToCityMap = new HashMap<String, Integer>();
    milesToCityMap.put("Tombstone", 70);
    milesToCityMap.put("Tucson", 72);
    milesToCityMap.put("Phoenix", 114);
    milesToCityMap.put("Sedona", 116);
    milesToCityMap.put("Flagstaff", 30);
    milesToCityMap.put("Page", 129);
  }
}
