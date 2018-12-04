package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.Test;

import model.MapModel;

public class MapModelTest {
	@Test
	public void testGetDistRatio() {
		MapModel map = new MapModel();
		map.advancePosition(35);
		assertEquals(0.5, map.getDistRatio());

		map.advancePosition(35);
		assertEquals(0.0, map.getDistRatio());

		map.advancePosition(70);
		double expected = 70.0 / 72.0;
		assertEquals(expected, map.getDistRatio());
	}

	@Test
	public void testAdvancePosition() {
		// no changes to new map
		MapModel map = new MapModel();
		assertFalse(map.getAtDestination());
		assertEquals(0, map.getMilesFromLastCity());
		assertEquals(0, map.getTotalMiles());
		assertEquals(70, map.milesToLandmark());
		assertEquals("Nogales", map.getCurrentCity());
		assertEquals("Tombstone", map.getNextCity());

		// add mileage
		map.advancePosition(5);
		assertFalse(map.getAtDestination());
		assertEquals(5, map.getMilesFromLastCity());
		assertEquals(5, map.getTotalMiles());
		assertEquals(65, map.milesToLandmark());
		assertEquals("Nogales", map.getCurrentCity());
		assertEquals("Tombstone", map.getNextCity());

		// travel to next city exactly (no extra distance)
		map.advancePosition(65);
		assertFalse(map.getAtDestination());
		assertEquals(0, map.getMilesFromLastCity());
		assertEquals(70, map.getTotalMiles());
		assertEquals(72, map.milesToLandmark());
		assertEquals("Tombstone", map.getCurrentCity());
		assertEquals("Tucson", map.getNextCity());

		// travel to tucson, phoenix, sedona, flagstaff
		map.advancePosition(72);
		assertFalse(map.getAtDestination());
		assertEquals(0, map.getMilesFromLastCity());
		assertEquals(142, map.getTotalMiles());
		assertEquals(114, map.milesToLandmark());
		assertEquals("Tucson", map.getCurrentCity());
		assertEquals("Phoenix", map.getNextCity());

		map.advancePosition(115);
		assertFalse(map.getAtDestination());
		assertEquals(1, map.getMilesFromLastCity());
		assertEquals(257, map.getTotalMiles());
		assertEquals(115, map.milesToLandmark());
		assertEquals("Phoenix", map.getCurrentCity());
		assertEquals("Sedona", map.getNextCity());

		map.advancePosition(115);
		assertFalse(map.getAtDestination());
		assertEquals(0, map.getMilesFromLastCity());
		assertEquals(372, map.getTotalMiles());
		assertEquals(30, map.milesToLandmark());
		assertEquals("Sedona", map.getCurrentCity());
		assertEquals("Flagstaff", map.getNextCity());

		map.advancePosition(31);
		assertFalse(map.getAtDestination());
		assertEquals(1, map.getMilesFromLastCity());
		assertEquals(403, map.getTotalMiles());
		assertEquals(128, map.milesToLandmark());
		assertEquals("Flagstaff", map.getCurrentCity());
		assertEquals("Page", map.getNextCity());

		// arrive at Page (destination)
		map.advancePosition(128);
		assertTrue(map.getAtDestination());
		assertEquals(0, map.getMilesFromLastCity());
		assertEquals(531, map.getTotalMiles());
		assertEquals(0, map.milesToLandmark());
		assertEquals("Page", map.getCurrentCity());
		assertEquals("Page", map.getNextCity());

		// try to advance past Page
		map.advancePosition(5);
		assertTrue(map.getAtDestination());
		assertEquals(0, map.getMilesFromLastCity());
		assertEquals(531, map.getTotalMiles());
		assertEquals(0, map.milesToLandmark());
		assertEquals("Page", map.getCurrentCity());
		assertEquals("Page", map.getNextCity());
	}

	@Test
	public void testSetCity() {
		MapModel map = new MapModel();
		map.setCurrentCity("Flagstaff");
		assertEquals("Flagstaff", map.getCurrentCity());
		assertFalse(map.getAtDestination());

		MapModel map2 = new MapModel();
		map2.setCurrentCity("Page");
		assertEquals("Page", map2.getCurrentCity());
		assertTrue(map2.getAtDestination());
	}
}
