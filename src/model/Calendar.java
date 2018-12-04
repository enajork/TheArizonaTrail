package model;

import java.util.*;
import java.io.*;
/**
 * @author Jordan Bridgewater, Jared Grady, Eric Najork, David Najork
 * @version     1.0
 * @since       1.0
 */

public class Calendar implements Serializable {
  private int START_YEAR = 1848;
  private int TOTAL_MONTHS = 12;
  private int year;
  private int month;
  private int day;
  private Map<String, Integer> daysInMonth;
  private Map<String, Integer> monthNum;

  private String[] months = {"January", "February", "March", "April", "May",
    "June", "July", "August", "September", "October", "November", "December"};

  /**
   * Constructor for the calendar model. Sets the calendar to
   * the start date of the game
   */
  public Calendar() {
    createDaysInMonthMap();
    createMonthNumMap();
    this.year = START_YEAR;
    this.month = 0;
    this.day = 1;
  }

  /**
   * getter for year
   *
   * @return returns the int year
   */
  public int getYear() {
    return year;
  }

  /**
   * getter for day
   *
   * @return returns the int day
   */
  public int getDay() {
    return day;
  }

  /**
   * setter for year
   *
   * @param year the year to set
   */
  public void setYear(int year) {
    this.year = year;
  }

  /**
   * setter for day
   *
   * @param day the int day
   */
  public void setDay(int day) {
    this.day = day;
  }

  /**
   * sets a valid month to the month field
   *
   * @param month the month to add
   */
  public void setMonth(String month) {
    int i = monthNum.get(month);
    if (i >= 0 && i <= TOTAL_MONTHS - 1) {
      this.month = i;
    } else {
      throw new IllegalStateException();
    }
  }

  /**
   * getter for month
   *
   * @return the current month
   */
  public int getMonth() {
    return month;
  }

  /**
   * setter for month
   *
   * @param month the month int to add
   */
  public void setMonth(int month) {
    this.month = month;
  }


  /**
   * returns the current date as a date string in the format
   * month day year
   *
   * @return the constructed month string
   */
  public String getDateStr() {
    return months[month] + " " + day + ", " + year;
  }

  /**
   * advances the date on the calendar by one day
   */
  public void advance() {
    // advance day
    if (day + 1 <= daysInMonth.get(months[month])) {
      day++;
    } else {
      // advance month
      month = (month + 1) % TOTAL_MONTHS;
      day = 1;
      // advance year
      if (month == 0) {
        year++;
      }
    }
  }

  /**
   * returns the current season based on the month
   *
   * @return the current season
   */
  public String getSeason() {
    if ((0 <= month && month <= 1) || month == (TOTAL_MONTHS - 1)) {
      return "winter";
    } else if (2 <= month && month <= 4) {
      return "spring";
    } else if (5 <= month && month <= 7) {
      return "summer";
    } else {
      return "fall";
    }
  }

  /**
   * generates the initial hash map of months to number of days
   * without using leap years
   */
  private void createDaysInMonthMap() {
    daysInMonth = new HashMap<String, Integer>();
    // disregarding leapyears and other aspects of the Gregorian calendar for
    // the sake of simplicity...
    daysInMonth.put("January", 31);
    daysInMonth.put("February", 28);
    daysInMonth.put("March", 31);
    daysInMonth.put("April", 30);
    daysInMonth.put("May", 31);
    daysInMonth.put("June", 31);
    daysInMonth.put("July", 31);
    daysInMonth.put("August", 31);
    daysInMonth.put("September", 30);
    daysInMonth.put("October", 31);
    daysInMonth.put("November", 30);
    daysInMonth.put("December", 31);
  }

  /**
   * creates a mapping of month numbers to month names
   */
  private void createMonthNumMap() {
    monthNum = new HashMap<String, Integer>();
    for (int i = 0; i < TOTAL_MONTHS; i++) {
      monthNum.put(months[i], i);
    }
  }
}
