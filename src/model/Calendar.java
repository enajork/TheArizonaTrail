package model;

public class Calendar {
  private int year;
  private int monthNum;
  private int day;
  private String[] months;
  private Map<String, Integer> daysInMonth;

  public Calendar(int startMonth) {
    createMonthsArray();
    createDaysInMonthMap();

    year = 1848;
    monthNum = startMonth;
    day = 1;
  }

  public getDateStr() {
    return months[monthNum] + " " + day + ", " + year;
  }

  public void advanceCalendar() {
    // advance day
    if (day + 1 <= daysInMonth.get(months[monthNum])) {
      day++;
    } else {
      // advance month
      monthNum = (monthNum + 1) % 12;
      day = 1;
      // advance year
      if (monthNum == 1) {
        year++;
      }
    }
  }

  public String getSeason() {
    int season = (monthNum - 1) / 4;
    if (season == 0) {
      return "winter";
    } else if (season == 1) {
      return "spring";
    } else if (season == 2) {
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
    daysInMonth.put("Decemeber", 31);
  }

  private void createMonthsArray() {
    months = new Array[12];
    months[0]  = "January";
    months[1]  = "February";
    months[2]  = "March";
    months[3]  = "April";
    months[4]  = "May";
    months[5]  = "June";
    months[6]  = "July";
    months[7]  = "August";
    months[8]  = "September";
    months[9]  = "October";
    months[10] = "November";
    months[11] = "December";
  }

}
