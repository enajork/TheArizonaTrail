package model;

import java.util.*;

public class Calendar {
  private int START_YEAR = 1848;
  private int TOTAL_MONTHS = 12;
  private int year;
  private int month;
  private int day;
  private Map<String, Integer> daysInMonth;
  private Map<String, Integer> monthNum;

  private String[] months = {"January", "February", "March", "April", "May",
		  "June", "July", "August", "September", "October", "November", "December"};

  public Calendar() {
    createDaysInMonthMap();
    createMonthNumMap();
    this.year = START_YEAR;
    this.month = 0;
    this.day = 1;
  }

  public int getYear() {
    return year;
  }

  public int getDay() {
    return day;
  }

  public void setMonth(String month) {
    int i = monthNum.get(month);
    if (i >= 0 && i <= TOTAL_MONTHS - 1) {
      this.month = i;
    } else {
      throw new IllegalStateException();
    }
  }

  public int getMonth() {
    return month;
  }

  public String getDateStr() {
    return months[month] + " " + day + ", " + year;
  }

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

  private void createMonthNumMap() {
    monthNum = new HashMap<String, Integer>();
    for (int i = 0; i < TOTAL_MONTHS; i++) {
      monthNum.put(months[i], i);
    }
  }
}
