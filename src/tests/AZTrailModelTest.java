package tests;

import model.*;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AZTrailModelTest {
	@Test
	public void testToString() {
		AZTrailModel model = new AZTrailModel();
		model.toString();
	}

	@Test
	public void testTravelRate() {
		AZTrailModel model = new AZTrailModel();
		model.setTravelRate(10);
		assertEquals(10, model.getTravelRate());
	}

	@Test
	public void testHunted() {
		AZTrailModel model = new AZTrailModel();
		assertTrue(model.getHunted());
		model.setHunted(false);
		assertFalse(model.getHunted());
	}

	@Test
	public void testMapMethods() {
		AZTrailModel model = new AZTrailModel();
		MapModel map = new MapModel();
		model.setMap(map);

		assertEquals("Nogales", model.getCurrentCity());
		assertEquals("Tombstone", model.getNextCity());
		assertFalse(model.getAtDestination());

		model.setCurrentCity("Tucson");
		assertEquals("Tucson", model.getCurrentCity());
		assertEquals("Phoenix", model.getNextCity());
		assertFalse(model.getAtDestination());

		assertEquals(114, model.milesToLandmark());

		model.setCurrentCity("Page");
		assertEquals("Page", model.getCurrentCity());
		assertEquals("Page", model.getNextCity());
		assertTrue(model.getAtDestination());

		assertEquals(0, model.getTotalMiles());
	}

	@Test
	public void testResetCart() {
		AZTrailModel model = new AZTrailModel();
		Cart cart = model.getCart();
		model.setCartAmmo(5.0);
		model.setCartClothes(12.0);
		model.setCartFood(1.0);
		model.setCartOxen(3.0);
		model.setCartParts(1.0);

		model.resetCart();
		assertEquals(0.0, cart.getAmmo());
		assertEquals(0.0, cart.getClothes());
		assertEquals(0.0, cart.getFood());
		assertEquals(0.0, cart.getOxen());
		assertEquals(0.0, cart.getParts());
	}

	@Test
	public void testCartAmmo() {
		AZTrailModel model = new AZTrailModel();
		assertEquals(0, model.getCartAmmo());
		model.setCartAmmo(10.0);
		assertEquals(10.0, model.getCartAmmo());
	}

	@Test
	public void testCartFood() {
		AZTrailModel model = new AZTrailModel();
		assertEquals(0, model.getCartFood());
		model.setCartFood(10.0);
		assertEquals(10.0, model.getCartFood());
	}

	@Test
	public void testCartOxen() {
		AZTrailModel model = new AZTrailModel();
		assertEquals(0, model.getCartOxen());
		model.setCartOxen(10.0);
		assertEquals(10.0, model.getCartOxen());
	}

	@Test
	public void testGetCartTotal() {
		AZTrailModel model = new AZTrailModel();
		assertEquals(0.0, model.getCartTotal());
	}

	@Test
	public void testCartParts() {
		AZTrailModel model = new AZTrailModel();
		assertEquals(0, model.getCartParts());
		model.setCartParts(10.0);
		assertEquals(10.0, model.getCartParts());
	}

	@Test
	public void testCartClothes() {
		AZTrailModel model = new AZTrailModel();
		assertEquals(0, model.getCartClothes());
		model.setCartClothes(10.0);
		assertEquals(10.0, model.getCartClothes());
	}

	@Test
	public void testGetSeason() {
		AZTrailModel model = new AZTrailModel();
		Calendar cal = new Calendar();
		model.setCalendar(cal);

		model.setMonth("February");
		assertEquals("winter", model.getSeason());

		model.setMonth("May");
		assertEquals("spring", model.getSeason());

		model.setMonth("August");
		assertEquals("summer", model.getSeason());

		model.setMonth("November");
		assertEquals("fall", model.getSeason());
	}

	@Test
	public void testAdvanceCalendar() {
		AZTrailModel model = new AZTrailModel();
		Calendar cal = new Calendar();
		model.setCalendar(cal);
		assertEquals(1, model.getDay());
		assertEquals(0, model.getMonth());
		assertEquals(1848, model.getYear());
		assertEquals("January 1, 1848", model.getDateStr());

		model.advanceCalendar();
		assertEquals(2, model.getDay());
		assertEquals(0, model.getMonth());
		assertEquals(1848, model.getYear());
		assertEquals("January 2, 1848", model.getDateStr());

		model.setDay(31);
		model.setMonth("December");
		model.setYear(1848);
		model.advanceCalendar();
		assertEquals(1, model.getDay());
		assertEquals(0, model.getMonth());
		assertEquals(1849, model.getYear());
		assertEquals("January 1, 1849", model.getDateStr());

		model.setDay(31);
		model.setMonth(11);
		model.setYear(1848);
		model.advanceCalendar();
		assertEquals(1, model.getDay());
		assertEquals(0, model.getMonth());
		assertEquals(1849, model.getYear());
		assertEquals("January 1, 1849", model.getDateStr());
	}

	@Test
	public void testTongues() {
		AZTrailModel model = new AZTrailModel();
		assertEquals(0, model.getTongues());
		model.addTongues(20);
		assertEquals(20, model.getTongues());
		model.removeTongues(10);
		assertEquals(10, model.getTongues());

		// try to remove too many
		model.removeTongues(11);
		assertEquals(10, model.getTongues());
	}

	@Test
	public void testAxles() {
		AZTrailModel model = new AZTrailModel();
		assertEquals(0, model.getAxles());
		model.addAxles(20);
		assertEquals(20, model.getAxles());
		model.removeAxles(10);
		assertEquals(10, model.getAxles());

		// try to remove too many
		model.removeAxles(11);
		assertEquals(10, model.getAxles());
	}

	@Test
	public void testWheels() {
		AZTrailModel model = new AZTrailModel();
		assertEquals(0, model.getWheels());
		model.addWheels(20);
		assertEquals(20, model.getWheels());
		model.removeWheels(10);
		assertEquals(10, model.getWheels());

		// try to remove too many
		model.removeWheels(11);
		assertEquals(10, model.getWheels());
	}

	@Test
	public void testWater() {
		AZTrailModel model = new AZTrailModel();
		assertEquals(0, model.getWater());
		model.addWater(20);
		assertEquals(20, model.getWater());
		model.removeWater(10);
		assertEquals(10, model.getWater());

		// try to remove too many
		model.removeWater(11);
		assertEquals(10, model.getWater());
	}

	@Test
	public void testFood() {
		AZTrailModel model = new AZTrailModel();
		assertEquals(0, model.getFood());
		model.addFood(20);
		assertEquals(20, model.getFood());
		model.removeFood(10);
		assertEquals(10, model.getFood());

		// try to remove too many
		model.removeFood(11);
		assertEquals(10, model.getFood());
	}

	@Test
	public void testBullets() {
		AZTrailModel model = new AZTrailModel();
		assertEquals(0, model.getBullets());
		model.addBullets(20);
		assertEquals(20, model.getBullets());
		model.removeBullets(10);
		assertEquals(10, model.getBullets());

		// try to remove too many
		model.removeBullets(11);
		assertEquals(10, model.getBullets());
	}

	@Test
	public void testBlankets() {
		AZTrailModel model = new AZTrailModel();
		assertEquals(0, model.getBlankets());
		model.addBlankets(20);
		assertEquals(20, model.getBlankets());
		model.removeBlankets(10);
		assertEquals(10, model.getBlankets());

		// try to remove too many
		model.removeBlankets(11);
		assertEquals(10, model.getBlankets());
	}

	@Test
	public void testClothes() {
		AZTrailModel model = new AZTrailModel();
		assertEquals(0, model.getClothes());
		model.addClothes(20);
		assertEquals(20, model.getClothes());
		model.removeClothes(10);
		assertEquals(10, model.getClothes());

		// try to remove too many
		model.removeClothes(11);
		assertEquals(10, model.getClothes());
	}

	@Test
	public void testOxen() {
		AZTrailModel model = new AZTrailModel();
		assertEquals(0, model.getOxen());
		model.addOxen(12);
		assertEquals(12, model.getOxen());
		model.removeOxen(2);
		assertEquals(10, model.getOxen());

		// try to remove too many
		model.removeOxen(23);
		assertEquals(10, model.getOxen());
	}

	@Test
	public void testParty() {
		AZTrailModel model = new AZTrailModel();
		Party party = new Party();
		model.setParty(party);
		assertEquals(party, model.getParty());
	}

	@Test
	public void testCalendar() {
		AZTrailModel model = new AZTrailModel();
		Calendar cal = new Calendar();
		model.setCalendar(cal);
		assertEquals(cal, model.getCalendar());
	}

	@Test
	public void testMap() {
		AZTrailModel model = new AZTrailModel();
		MapModel map = new MapModel();
		model.setMap(map);
		assertEquals(map, model.getMap());
	}

	@Test
	public void testMoney() {
		AZTrailModel model = new AZTrailModel();
		assertEquals(1600.00, model.getMoney());
		model.removeMoney(600.00);
		assertEquals(1000.00, model.getMoney());
		model.addMoney(0.50);
		assertEquals(1000.50, model.getMoney());

		// try to remove too much
		model.removeMoney(1100.00);
		assertEquals(1000.5, model.getMoney());
	}
	@Test
	public void testPartySize() {
		AZTrailModel model = new AZTrailModel();
		assertEquals(5, model.partySize());
		assertEquals(5, model.currPartySize());
	}
	@Test
	public void testSetName() {
		AZTrailModel model = new AZTrailModel();
		model.setName(1, "Jordan");
		assertEquals("Jordan", model.getName(1));
	}
	@Test
	public void testGetProf() {
		AZTrailModel model1 = new AZTrailModel();
		model1.setProf("Carpenter");
		assertEquals("Carpenter", model1.getProf());

		AZTrailModel model2 = new AZTrailModel();
		model2.setProf("Banker");
		assertEquals("Banker", model2.getProf());

		AZTrailModel model3 = new AZTrailModel();
		model3.setProf("Farmer");
		assertEquals("Farmer", model3.getProf());

		AZTrailModel model4 = new AZTrailModel();
		assertThrows(IllegalStateException.class, () -> {model4.setProf("Invalid");});
	}

	@Test
	public void testConstructors() {
		AZTrailModel model = new AZTrailModel();
		assertTrue(model.getHunted());
		assertEquals(6, model.getTravelRate());
	}

}
