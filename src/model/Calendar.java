package model;

import java.util.*;

public class Calendar {
  private int year;
  private int monthNum;
  private int day;
  private Map<String, Integer> daysInMonth;

  private String[] months = {"January", "February", "March", "April", "May",
		  "June", "July", "August", "September", "October", "November", "December"};

  public Calendar(int startMonth) {
    createDaysInMonthMap();

    year = 1848;
    monthNum = startMonth;
    day = 1;
  }

  public int getYear() {
    return year;
  }

  public int getDay() {
    return day;
  }

  public int getMonth() {
    return monthNum;
  }

  public String getDateStr() {
    return months[monthNum] + " " + day + ", " + year;
  }

  public void advance() {
    // advance day
    if (day + 1 <= daysInMonth.get(months[monthNum])) {
      day++;
    } else {
      // advance month
      monthNum = (monthNum + 1) % 12;
      day = 1;
      // advance year
      if (monthNum == 0) {
        year++;
      }
    }
  }

  public String getSeason() {
    if ((0 <= monthNum && monthNum <= 1) || monthNum == 11) {
      return "winter";
    } else if (2 <= monthNum && monthNum <= 4) {
      return "spring";
    } else if (5 <= monthNum && monthNum <= 7) {
      return "summer";
    } else {
      return "fall";
    }
  }

  private void createDaysInMonthMap() {
    daysInMonth = new HashMap<String, Integer>();
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
}
