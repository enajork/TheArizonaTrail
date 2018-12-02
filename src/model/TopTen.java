package model;

import java.util.*;

public class TopTen {
  private final int SIZE = 10;
  private String[] names;
  private int[] score;

  public TopTen() {
    this.names = new String[SIZE];
    this.score = new int[SIZE];
  }

  public String getName(int i) {
    return names[i];
  }

  public int getScore(int i) {
    return score[i];
  }

  public void setScore(int i, String name, int score) {
    this.score[i] = score;
    this.names[i] = name;
  }

  public int size() {
    return SIZE;
  }
}
