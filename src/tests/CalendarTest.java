package tests;

import model.Calendar;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;
/**
 * @author Jordan Bridgewater, Jared Grady, Eric Najork, David Najork
 * @version     1.0
 * @since       1.0
 */

public class CalendarTest {
  @Test
  public void testAdvance() {
    // TEST: test one advance
    Calendar cal1 = new Calendar();
    cal1.setMonth(3);
    cal1.advance();
    assertEquals(2, cal1.getDay());
    assertEquals(3, cal1.getMonth());
    assertEquals(1848, cal1.getYear());

    // TEST: advance a month
    Calendar cal2 = new Calendar();
    cal2.setMonth(1);
    for (int i = 0; i < 28; i++) {
      cal2.advance();
    }
    assertEquals(1, cal2.getDay());
    assertEquals(2, cal2.getMonth());
    assertEquals(1848, cal2.getYear());

    // TEST: advance year
    Calendar cal3 = new Calendar();
    cal3.setMonth(11);
    for (int i = 0; i < 31; i++) {
      cal3.advance();
    }
    assertEquals(1, cal3.getDay());
    assertEquals(0, cal3.getMonth());
    assertEquals(1849, cal3.getYear());
    }

  @Test
  public void testGetDateStr() {
    Calendar cal = new Calendar();
    cal.setMonth(4);
    for (int i = 0; i < 15; i++) {
      cal.advance();
    }
    assertEquals("May 16, 1848", cal.getDateStr());
    }

  @Test
  public void testSetMonth() {
	  // valid month (int)
	  Calendar cal1 = new Calendar();
	  cal1.setMonth(1);
	  assertEquals(1, cal1.getMonth());

	  // invalid month (int)
	  Calendar cal2 = new Calendar();
	  cal2.setMonth(12);
	  assertEquals(12, cal2.getMonth());

	  // valid month (String)
	  Calendar cal3 = new Calendar();
	  cal3.setMonth("October");
	  assertEquals(9, cal3.getMonth());

	  // invalid month(String)
	  Calendar cal4 = new Calendar();
	  assertThrows(NullPointerException.class, () -> {cal4.setMonth("Invalid");});
  }

  @Test
  public void testSetDay() {
	  Calendar cal = new Calendar();
	  cal.setDay(4);
	  assertEquals(4, cal.getDay());
  }

  @Test
  public void testSetYear() {
	  Calendar cal = new Calendar();
	  cal.setYear(1995);
	  assertEquals(1995, cal.getYear());
  }

  @Test
  public void testGetSeason() {
    // TEST: winter
    Calendar dec = new Calendar();
    dec.setMonth(11);
    assertEquals("winter", dec.getSeason());
    Calendar jan = new Calendar();
    jan.setMonth(0);
    assertEquals("winter", jan.getSeason());
    Calendar feb = new Calendar();
    feb.setMonth(1);
    assertEquals("winter", feb.getSeason());

    // TEST: spring
    Calendar mar = new Calendar();
    mar.setMonth(2);
    assertEquals("spring", mar.getSeason());
    Calendar apr = new Calendar();
    apr.setMonth(3);
    assertEquals("spring", apr.getSeason());
    Calendar may = new Calendar();
    may.setMonth(4);
    assertEquals("spring", may.getSeason());

    // TEST: summer
    Calendar jun = new Calendar();
    jun.setMonth(5);
    assertEquals("summer", jun.getSeason());
    Calendar jul = new Calendar();
    jul.setMonth(6);
    assertEquals("summer", jul.getSeason());
    Calendar aug = new Calendar();
    aug.setMonth(7);
    assertEquals("summer", aug.getSeason());

    // TEST: fall
    Calendar sep = new Calendar();
    sep.setMonth(8);
    assertEquals("fall", sep.getSeason());
    Calendar oct = new Calendar();
    oct.setMonth(9);
    assertEquals("fall", oct.getSeason());
    Calendar nov = new Calendar();
    nov.setMonth(10);
    assertEquals("fall", nov.getSeason());
    }
}
