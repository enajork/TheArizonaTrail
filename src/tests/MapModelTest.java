package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.Test;

public class MapModelTest {
	@Test
	public void testAdvancePosition() {
		// no changes to new map
		MapModel map = new MapModel();
		assertFalse(map.getAtDestination());
		assertEquals(0, map.getMilesFromLastCity());
		assertEquals("Nogales", map.getCurrentCity());
		assertEquals("Tombstone", map.getNextCity());

		// add mileage
		map.advancePosition(5);
		assertFalse(map.getAtDestination());
		assertEquals(5, map.getMilesFromLastCity());
		assertEquals("Nogales", map.getCurrentCity());
		assertEquals("Tombstone", map.getNextCity());

		// travel to next city exactly (no extra distance)
		map.advancePosition(65);
		assertFalse(map.getAtDestination());
		assertEquals(0, map.getMilesFromLastCity());
		assertEquals("Tombstone", map.getCurrentCity());
		assertEquals("Tucson", map.getNextCity());

		// travel to tucson, phoenix, sedona, flagstaff
		map.advancePosition(72);
		assertFalse(map.getAtDestination());
		assertEquals(0, map.getMilesFromLastCity());
		assertEquals("Tucson", map.getCurrentCity());
		assertEquals("Phoenix", map.getNextCity());

		map.advancePosition(115);
		assertFalse(map.getAtDestination());
		assertEquals(1, map.getMilesFromLastCity());
		assertEquals("Phoenix", map.getCurrentCity());
		assertEquals("Sedona", map.getNextCity());

		map.advancePosition(115);
		assertFalse(map.getAtDestination());
		assertEquals(0, map.getMilesFromLastCity());
		assertEquals("Sedona", map.getCurrentCity());
		assertEquals("Flagstaff", map.getNextCity());

		map.advancePosition(31);
		assertFalse(map.getAtDestination());
		assertEquals(1, map.getMilesFromLastCity());
		assertEquals("Flagstaff", map.getCurrentCity());
		assertEquals("Page", map.getNextCity());

		// arrive at Page (destination)
		map.advancePosition(128);
		assertTrue(map.getAtDestination());
		assertEquals(0, map.getMilesFromLastCity());
		assertEquals("Page", map.getCurrentCity());
		assertEquals("Page", map.getNextCity());

		// try to advance past Page
		map.advancePosition(5);
		assertTrue(map.getAtDestination());
		assertEquals(0, map.getMilesFromLastCity());
		assertEquals("Page", map.getCurrentCity());
		assertEquals("Page", map.getNextCity());
	}
}
