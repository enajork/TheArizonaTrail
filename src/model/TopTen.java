package model;

import javafx.util.*;
import java.util.*;

public class TopTen {
  private static final int SIZE = 10;
  private ArrayList<Pair<String, Integer>> scores;

  public TopTen() {
    this.scores = new ArrayList<Pair<String, Integer>>();
    for (int i = 0; i < SIZE; i++) {
      scores.add(new Pair<String, Integer>("", 0));
    }
  }

  public String getName(int i) {
    return scores.get(i).getKey();
  }

  public int getScore(int i) {
    return scores.get(i).getValue();
  }

  public void setScore(String name, int score) {
    scores.add(new Pair<String, Integer>(name, score));
    Collections.reverse(scores);
    if (scores.size() > SIZE) {
      scores.remove(SIZE);
    }
  }

  public int size() {
    return SIZE;
  }
}
