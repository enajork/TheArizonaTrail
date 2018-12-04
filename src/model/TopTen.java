package model;

import javafx.util.*;
import java.util.*;
/**
 * @author Jordan Bridgewater, Jared Grady, Eric Najork, David Najork
 * @version     1.0
 * @since       1.0
 */

public class TopTen {
  private static final int SIZE = 10;
  private ArrayList<Pair<String, Integer>> scores;

  /**
   * Constructor for top ten, initializes an empty arraylist to
   * store the top ten players
   */
  public TopTen() {
    this.scores = new ArrayList<Pair<String, Integer>>();
    for (int i = 0; i < SIZE; i++) {
      scores.add(new Pair<String, Integer>("", 0));
    }
  }

  /**
   * gets the name of the ith player
   *
   * @param  i the index of the player to get
   *
   * @return   the player's name
   */
  public String getName(int i) {
    return scores.get(i).getKey();
  }

  /**
   * gets the score of the ith player
   *
   * @param  i the index of the player to get
   *
   * @return   the player's score
   */
  public int getScore(int i) {
    return scores.get(i).getValue();
  }

  /**
   * sets the score for a player based on the result of
   * their gameplay
   *
   * @param name  the name of the player
   * @param score the player's score
   */
  public void setScore(String name, int score) {
    scores.add(new Pair<String, Integer>(name, score));
    Collections.reverse(scores);
    if (scores.size() > SIZE) {
      scores.remove(SIZE);
    }
  }

  /**
   * getter for the size constant
   *
   * @return returns the current size constant
   */
  public int size() {
    return SIZE;
  }
}
