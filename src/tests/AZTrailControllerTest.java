package tests;

import controller.*;
import model.AZTrailModel;

import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AZTrailControllerTest {
	@Test
	public void testLoadGame() {
		AZTrailController control = new AZTrailController();
		control.loadGame();
	}

	@Test
	public void testSaveGame() {
		AZTrailController control = new AZTrailController();
		control.saveGame();
	}

	@Test
	public void testDeleteSave() {
		AZTrailController control = new AZTrailController();
		control.deleteSave();
	}

	@Test
	public void testResetGame() {
		AZTrailController control = new AZTrailController();
		control.resetGame();
	}

	@Test
	public void setCheckpoint() {
		AZTrailController control = new AZTrailController();
		control.setCheckpoint();
	}

	@Test
	public void testTopTen() {
		AZTrailController control = new AZTrailController();
		control.loadTopTen();
		control.saveTopTen();
		control.resetTopTen();
	}

	@Test
	public void testAdvance() {
		AZTrailController control1 = new AZTrailController();
		assertFalse(control1.advance());

		AZTrailController control2 = new AZTrailController();
		control2.setMonth("January");
		assertFalse(control2.advance());
	}

	@Test
	public void testChangePace() {
		AZTrailController control = new AZTrailController();
		control.changePace(1);
		assertEquals(2, control.getTravelRate());

		control.changePace(2);
		assertEquals(4, control.getTravelRate());

		control.changePace(3);
		assertEquals(6, control.getTravelRate());

		control.changePace(0);
		assertEquals(2, control.getTravelRate());

		control.changePace(4);
		assertEquals(2, control.getTravelRate());
	}

	@Test
	public void testProfMenu() {
		AZTrailController control = new AZTrailController();
		control.profMenu(1);
		control.profMenu(2);
		control.profMenu(3);
		assertThrows(IllegalStateException.class, () -> {control.profMenu(4);});
	}

	@Test
	public void testToString() {
		AZTrailController control = new AZTrailController();
		control.toString();
	}

	@Test
	public void testNameMethods() {
		AZTrailController control = new AZTrailController();
		control.setName(1, "Bill");
		assertEquals("Bill", control.getName(1));
	}

	@Test
	public void partySize() {
		AZTrailController control = new AZTrailController();
		assertEquals(5, control.partySize());
	}

	@Test
	public void testCalendarMethods() {
		AZTrailController control = new AZTrailController();
		control.setMonth("May");
		assertEquals(4, control.getMonth());
		assertEquals("May 1, 1848", control.getDateStr());
	}

	@Test
	public void testMoneyMethods() {
		AZTrailController control = new AZTrailController();
		assertEquals(400.00, control.getMoney());
		control.removeMoney(200.00);
		assertEquals(200.00, control.getMoney());
		control.addMoney(0.50);
		assertEquals(200.50, control.getMoney());

		// try to remove too much
		control.removeMoney(1000.00);
		assertEquals(200.50, control.getMoney());
	}

	@Test
	public void testOxenMethods() {
		AZTrailController control = new AZTrailController();
		assertEquals(0, control.getOxen());
		control.addOxen(2);
		assertEquals(2, control.getOxen());
		control.removeOxen(2);
		assertEquals(0, control.getOxen());

		// try to remove too much
		control.removeOxen(5);
		assertEquals(0, control.getOxen());
	}

	@Test
	public void testClothesMethods() {
		AZTrailController control = new AZTrailController();
		assertEquals(0, control.getClothes());
		control.addClothes(2);
		assertEquals(2, control.getClothes());
		control.removeClothes(2);
		assertEquals(0, control.getClothes());

		// try to remove too much
		control.removeClothes(5);
		assertEquals(0, control.getClothes());
	}

	@Test
	public void testBlanketsMethods() {
		AZTrailController control = new AZTrailController();
		assertEquals(0, control.getBlankets());
		control.addBlankets(2);
		assertEquals(2, control.getBlankets());
		control.removeBlankets(2);
		assertEquals(0, control.getBlankets());

		// try to remove too much
		control.removeBlankets(5);
		assertEquals(0, control.getBlankets());

	}

	@Test
	public void testBulletsMethods() {
		AZTrailController control = new AZTrailController();
		assertEquals(0, control.getBullets());
		control.addBullets(2);
		assertEquals(2, control.getBullets());
		control.removeBullets(2);
		assertEquals(0, control.getBullets());

		// try to remove too much
		control.removeBullets(5);
		assertEquals(0, control.getBullets());
	}

	@Test
	public void testFoodMethods() {
		AZTrailController control = new AZTrailController();
		assertEquals(0, control.getFood());
		control.addFood(2);
		assertEquals(2, control.getFood());
		control.removeFood(2);
		assertEquals(0, control.getFood());

		// try to remove too much
		control.removeFood(5);
		assertEquals(0, control.getFood());
	}

	@Test
	public void testWaterMethods() {
		AZTrailController control = new AZTrailController();
		assertEquals(0, control.getWater());
		control.addWater(2);
		assertEquals(2, control.getWater());
		control.removeWater(2);
		assertEquals(0, control.getWater());

		// try to remove too much
		control.removeWater(5);
		assertEquals(0, control.getWater());
	}

	@Test
	public void testWheelsMethods() {
		AZTrailController control = new AZTrailController();
		assertEquals(0, control.getWheels());
		control.addWheels(2);
		assertEquals(2, control.getWheels());
		control.removeWheels(2);
		assertEquals(0, control.getWheels());

		// try to remove too much
		control.removeWheels(5);
		assertEquals(0, control.getWheels());
	}

	@Test
	public void testAxlesMethods() {
		AZTrailController control = new AZTrailController();
		assertEquals(0, control.getAxles());
		control.addAxles(2);
		assertEquals(2, control.getAxles());
		control.removeAxles(2);
		assertEquals(0, control.getAxles());

		// try to remove too much
		control.removeAxles(5);
		assertEquals(0, control.getAxles());
	}

	@Test
	public void testTonguesMethods() {
		AZTrailController control = new AZTrailController();
		assertEquals(0, control.getTongues());
		control.addTongues(2);
		assertEquals(2, control.getTongues());
		control.removeTongues(2);
		assertEquals(0, control.getTongues());

		// try to remove too much
		control.removeTongues(5);
		assertEquals(0, control.getTongues());
	}

	@Test
	public void testCartClothesMethods() {
		AZTrailController control = new AZTrailController();
		assertEquals(0.0, control.getCartClothes());
		control.setCartClothes(10.0);
		assertEquals(10.0, control.getCartClothes());
	}

	@Test
	public void testCartPartsMethods() {
		AZTrailController control = new AZTrailController();
		assertEquals(0.0, control.getCartParts());
		control.setCartParts(10.0);
		assertEquals(10.0, control.getCartParts());
	}

	@Test
	public void testCartTotal() {
		AZTrailController control = new AZTrailController();
		assertEquals(0.0, control.getCartTotal());
	}

	@Test
	public void testCartOxenMethods() {
		AZTrailController control = new AZTrailController();
		assertEquals(0.0, control.getCartOxen());
		control.setCartOxen(10.0);
		assertEquals(10.0, control.getCartOxen());
	}

	@Test
	public void testCartFoodMethods() {
		AZTrailController control = new AZTrailController();
		assertEquals(0.0, control.getCartFood());
		control.setCartFood(10.0);
		assertEquals(10.0, control.getCartFood());
	}

	@Test
	public void testCartAmmoMethods() {
		AZTrailController control = new AZTrailController();
		assertEquals(0.0, control.getCartAmmo());
		control.setCartAmmo(10.0);
		assertEquals(10.0, control.getCartAmmo());
	}

	@Test
	public void testResetCart() {
		AZTrailController control = new AZTrailController();
		control.setCartAmmo(5.0);
		control.setCartClothes(12.0);
		control.setCartFood(1.0);
		control.setCartOxen(3.0);
		control.setCartParts(1.0);

		control.resetCart();
		assertEquals(0.0, control.getCartAmmo());
		assertEquals(0.0, control.getCartClothes());
		assertEquals(0.0, control.getCartFood());
		assertEquals(0.0, control.getCartOxen());
		assertEquals(0.0, control.getCartParts());
	}

	@Test
	public void testMapMethods() {
		AZTrailController control = new AZTrailController();
		assertFalse(control.getAtDestination());
		assertEquals("Nogales", control.getCurrentCity());
		assertEquals("Tombstone", control.getNextCity());
		assertEquals(0, control.getTotalMiles());
		assertEquals(70, control.milesToLandmark());
		assertEquals(0.0, control.getDistRatio());
	}

	@Test
	public void testHuntedMethods() {
		AZTrailController control = new AZTrailController();
		control.setHunted(true);
		assertTrue(control.getHunted());

		control.setHunted(false);
		assertFalse(control.getHunted());
	}

	@Test
	public void testTravelRateMethods() {
		AZTrailController control = new AZTrailController();
		assertEquals(4, control.getTravelRate());

		control.setTravelRate(7);
		assertEquals(7, control.getTravelRate());
	}

	@Test
	public void testGetWeather() {
		AZTrailController control1 = new AZTrailController();
		AZTrailController control2 = new AZTrailController();
		AZTrailController control3 = new AZTrailController();
		AZTrailController control4 = new AZTrailController();
		String weather1, weather2, weather3, weather4;
		control1.setMonth("January");
		control2.setMonth("April");
		control3.setMonth("July");
		control4.setMonth("October");
		for (int i = 0; i < 10; i++) {
			weather1 = control1.getWeather();
			weather2 = control2.getWeather();
			weather3 = control3.getWeather();
			weather4 = control4.getWeather();
			assertTrue(weather1.equals("cold") || weather1.equals("cool"));
			assertTrue(weather2.equals("cool") || weather2.equals("warm"));
			assertTrue(weather3.equals("hot") || weather3.equals("scorching"));
			assertTrue(weather4.equals("warm") || weather4.equals("cool") ||
					weather4.equals("hot"));
		}
	}

	@Test
	public void testTopTenMethods() {
		AZTrailController control = new AZTrailController();
		control.setScore("Albus", 100);
		assertEquals("1. Albus\n2. \n3. \n4. \n5. \n6. \n7. \n8. \n9. \n10. \n",
				control.getTopTenNames());
		assertEquals("100\n\n\n\n\n\n\n\n\n\n", control.getTopTenScores());
	}

	@Test
	public void testGetHealth() {
		AZTrailController control = new AZTrailController();
		assertEquals("great", control.getHealth());
	}

	@Test
	public void testGetScore() {
		AZTrailController control1 = new AZTrailController();
		control1.addOxen(2);
		control1.addWheels(3);
		control1.addAxles(4);
		control1.addTongues(5);
		control1.addBullets(6);
		control1.addFood(7);
		control1.addClothes(8);
		control1.addWater(9);
		control1.addBlankets(10);
		control1.setHunted(false);
		control1.profMenu(1);
		assertEquals(2270, control1.getScore());

		AZTrailController control2 = new AZTrailController();
		control2.addOxen(2);
		control2.addWheels(3);
		control2.addAxles(4);
		control2.addTongues(5);
		control2.addBullets(6);
		control2.addFood(7);
		control2.addClothes(8);
		control2.addWater(9);
		control2.addBlankets(10);
		control2.setHunted(true);
		control2.profMenu(1);
		assertEquals(4540, control2.getScore());

		AZTrailController control3 = new AZTrailController();
		control3.addOxen(2);
		control3.addWheels(3);
		control3.addAxles(4);
		control3.addTongues(5);
		control3.addBullets(6);
		control3.addFood(7);
		control3.addClothes(8);
		control3.addWater(9);
		control3.addBlankets(10);
		control3.setHunted(false);
		control3.profMenu(2);
		assertEquals(2540, control3.getScore());

		AZTrailController control4 = new AZTrailController();
		control4.addOxen(2);
		control4.addWheels(3);
		control4.addAxles(4);
		control4.addTongues(5);
		control4.addBullets(6);
		control4.addFood(7);
		control4.addClothes(8);
		control4.addWater(9);
		control4.addBlankets(10);
		control4.setHunted(true);
		control4.profMenu(2);
		assertEquals(5080, control4.getScore());

		AZTrailController control5 = new AZTrailController();
		control5.addOxen(2);
		control5.addWheels(3);
		control5.addAxles(4);
		control5.addTongues(5);
		control5.addBullets(6);
		control5.addFood(7);
		control5.addClothes(8);
		control5.addWater(9);
		control5.addBlankets(10);
		control5.setHunted(false);
		control5.profMenu(3);
		assertEquals(3080, control5.getScore());

		AZTrailController control6 = new AZTrailController();
		control6.addOxen(2);
		control6.addWheels(3);
		control6.addAxles(4);
		control6.addTongues(5);
		control6.addBullets(6);
		control6.addFood(7);
		control6.addClothes(8);
		control6.addWater(9);
		control6.addBlankets(10);
		control6.setHunted(true);
		control6.profMenu(3);
		assertEquals(6160, control6.getScore());
	}

	@Test
	public void testDeplete() {
		AZTrailController control1 = new AZTrailController();
		control1.setMonth("January");
		control1.setHunted(true);
		assertEquals("Your party died from cannibalism.",
				control1.deplete());
		control1.addWater(10000);
		assertEquals("Your party died from cannibalism.",
				control1.deplete());
		control1.removeWater(10000);
		control1.setHunted(false);
		assertEquals("Your party died from starvation\nand dehydration.",
				control1.deplete());
		control1.addWater(10000);
		assertEquals("Your party died from starvation.",
				control1.deplete());
		control1.removeWater(10000);
		control1.addFood(10000);
		assertEquals("Your party died from dehydration.",
				control1.deplete());
		control1.addWater(10000);
		assertEquals("", control1.deplete());

		AZTrailController control2 = new AZTrailController();
		control2.setMonth("April");
		control2.setHunted(true);
		assertEquals("Your party died from cannibalism.",
				control2.deplete());
		control2.addWater(10000);
		assertEquals("Your party died from cannibalism.",
				control2.deplete());
		control2.removeWater(10000);
		control2.setHunted(false);
		assertEquals("Your party died from starvation\nand dehydration.",
				control2.deplete());
		control2.addWater(10000);
		assertEquals("Your party died from starvation.",
				control2.deplete());
		control2.removeWater(10000);
		control2.addFood(10000);
		assertEquals("Your party died from dehydration.",
				control2.deplete());
		control2.addWater(10000);
		assertEquals("", control2.deplete());

		AZTrailController control3 = new AZTrailController();
		control3.setMonth("July");
		control3.setHunted(true);
		assertEquals("Your party died from cannibalism.",
				control3.deplete());
		control3.addWater(10000);
		assertEquals("Your party died from cannibalism.",
				control3.deplete());
		control3.removeWater(10000);
		control3.setHunted(false);
		assertEquals("Your party died from starvation\nand dehydration.",
				control3.deplete());
		control3.addWater(10000);
		assertEquals("Your party died from starvation.",
				control3.deplete());
		control3.removeWater(10000);
		control3.addFood(10000);
		assertEquals("Your party died from dehydration.",
				control3.deplete());
		control3.addWater(10000);
		assertEquals("", control3.deplete());

		AZTrailController control4 = new AZTrailController();
		control4.setMonth("October");
		control4.setHunted(true);
		assertEquals("Your party died from cannibalism.",
				control4.deplete());
		control4.addWater(10000);
		assertEquals("Your party died from cannibalism.",
				control4.deplete());
		control4.removeWater(10000);
		control4.setHunted(false);
		assertEquals("Your party died from starvation\nand dehydration.",
				control4.deplete());
		control4.addWater(10000);
		assertEquals("Your party died from starvation.",
				control4.deplete());
		control4.removeWater(10000);
		control4.addFood(10000);
		assertEquals("Your party died from dehydration.",
				control4.deplete());
		control4.addWater(10000);
		assertEquals("", control4.deplete());
	}

	@Test
	public void testRandomEvent() {
		AZTrailController control = new AZTrailController();
		control.randomEvent();
	}
}
