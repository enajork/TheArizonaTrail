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
    Calendar cal1 = new Calendar (3);
    cal1.advance();
    assertEquals(2, cal1.getDay());
    assertEquals(3, cal1.getMonth());
    assertEquals(1848, cal1.getYear());

    // TEST: advance a month
    Calendar cal2 = new Calendar(1);
    for (int i = 0; i < 28; i++) {
      cal2.advance();
    }
    assertEquals(1, cal2.getDay());
    assertEquals(2, cal2.getMonth());
    assertEquals(1848, cal2.getYear());

    // TEST: advance year
    Calendar cal3 = new Calendar(11);
    for (int i = 0; i < 31; i++) {
      cal3.advance();
    }
    assertEquals(1, cal3.getDay());
    assertEquals(0, cal3.getMonth());
    assertEquals(1849, cal3.getYear());
    }

  @Test
  public void testGetDateStr() {
    Calendar cal = new Calendar(4);
    for (int i = 0; i < 15; i++) {
      cal.advance();
    }
    assertEquals("May 16, 1848", cal.getDateStr());
    }

  @Test
  public void testGetSeason() {
    // TEST: winter
    Calendar dec = new Calendar(11);
    assertEquals("winter", dec.getSeason());
    Calendar jan = new Calendar(0);
    assertEquals("winter", jan.getSeason());
    Calendar feb = new Calendar(1);
    assertEquals("winter", feb.getSeason());

    // TEST: spring
    Calendar mar = new Calendar(2);
    assertEquals("spring", mar.getSeason());
    Calendar apr = new Calendar(3);
    assertEquals("spring", apr.getSeason());
    Calendar may = new Calendar(4);
    assertEquals("spring", may.getSeason());

    // TEST: summer
    Calendar jun = new Calendar(5);
    assertEquals("summer", jun.getSeason());
    Calendar jul = new Calendar(6);
    assertEquals("summer", jul.getSeason());
    Calendar aug = new Calendar(7);
    assertEquals("summer", aug.getSeason());

    // TEST: fall
    Calendar sep = new Calendar(8);
    assertEquals("fall", sep.getSeason());
    Calendar oct = new Calendar(9);
    assertEquals("fall", oct.getSeason());
    Calendar nov = new Calendar(10);
    assertEquals("fall", nov.getSeason());
    }
}
