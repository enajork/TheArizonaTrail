package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.Test;

import model.Inventory;

public class InventoryTest {
	@Test
	public void testMoney() {
		// starting amount
		Inventory inv = new Inventory();
		assertEquals(1600.0, inv.getMoney());

		// negative amount added
		inv.addMoney(-12.0);
		assertEquals(1600.0, inv.getMoney());

		// positive amount added
		inv.addMoney(10.0);
		assertEquals(1610.0, inv.getMoney());

		// negative amount removed
		inv.removeMoney(-12.0);
		assertEquals(1610.0, inv.getMoney());

		// remove too much
		inv.removeMoney(2000.0);
		assertEquals(1610.0, inv.getMoney());

		// remove valid amount
		inv.removeMoney(10.0);
		assertEquals(1600.00, inv.getMoney());
	}

	@Test
	public void testOxen() {
		// starting amount
		Inventory inv = new Inventory();
		assertEquals(0, inv.getOxen());

		// negative amount added
		inv.addOxen(-5);
		assertEquals(0, inv.getOxen());

		// positive amount added
		inv.addOxen(5);
		assertEquals(5, inv.getOxen());

		// negative amount removed
		inv.removeOxen(-1);
		assertEquals(5, inv.getOxen());

		// remove too much
		inv.removeOxen(6);
		assertEquals(5, inv.getOxen());

		// remove valid amount
		inv.removeOxen(1);
		assertEquals(4, inv.getOxen());
	}

	@Test
	public void testClothes() {
		// starting amount
		Inventory inv = new Inventory();
		assertEquals(0, inv.getClothes());

		// negative amount added
		inv.addClothes(-5);
		assertEquals(0, inv.getClothes());

		// positive amount added
		inv.addClothes(5);
		assertEquals(5, inv.getClothes());

		// negative amount removed
		inv.removeClothes(-1);
		assertEquals(5, inv.getClothes());

		// remove too much
		inv.removeClothes(6);
		assertEquals(5, inv.getClothes());

		// remove valid amount
		inv.removeClothes(1);
		assertEquals(4, inv.getClothes());
	}

	@Test
	public void testBlankets() {
		// starting amount
		Inventory inv = new Inventory();
		assertEquals(0, inv.getBlankets());

		// negative amount added
		inv.addBlankets(-5);
		assertEquals(0, inv.getBlankets());

		// positive amount added
		inv.addBlankets(5);
		assertEquals(5, inv.getBlankets());

		// negative amount removed
		inv.removeBlankets(-1);
		assertEquals(5, inv.getBlankets());

		// remove too much
		inv.removeBlankets(6);
		assertEquals(5, inv.getBlankets());

		// remove valid amount
		inv.removeBlankets(1);
		assertEquals(4, inv.getBlankets());
	}

	@Test
	public void testFood() {
		// starting amount
		Inventory inv = new Inventory();
		assertEquals(0, inv.getFood());

		// negative amount added
		inv.addFood(-5);
		assertEquals(0, inv.getFood());

		// positive amount added
		inv.addFood(5);
		assertEquals(5, inv.getFood());

		// negative amount removed
		inv.removeFood(-1);
		assertEquals(5, inv.getFood());

		// remove too much
		inv.removeFood(6);
		assertEquals(5, inv.getFood());

		// remove valid amount
		inv.removeFood(1);
		assertEquals(4, inv.getFood());
	}

	@Test
	public void testWater() {
		// starting amount
		Inventory inv = new Inventory();
		assertEquals(0, inv.getWater());

		// negative amount added
		inv.addWater(-5);
		assertEquals(0, inv.getWater());

		// positive amount added
		inv.addWater(5);
		assertEquals(5, inv.getWater());

		// negative amount removed
		inv.removeWater(-1);
		assertEquals(5, inv.getWater());

		// remove too much
		inv.removeWater(6);
		assertEquals(5, inv.getWater());

		// remove valid amount
		inv.removeWater(1);
		assertEquals(4, inv.getWater());
	}

	@Test
	public void testBullets() {
		// starting amount
		Inventory inv = new Inventory();
		assertEquals(0, inv.getBullets());

		// negative amount added
		inv.addBullets(-5);
		assertEquals(0, inv.getBullets());

		// positive amount added
		inv.addBullets(5);
		assertEquals(5, inv.getBullets());

		// negative amount removed
		inv.removeBullets(-1);
		assertEquals(5, inv.getBullets());

		// remove too much
		inv.removeBullets(6);
		assertEquals(5, inv.getBullets());

		// remove valid amount
		inv.removeBullets(1);
		assertEquals(4, inv.getBullets());
	}

	@Test
	public void testWheels() {
		// starting amount
		Inventory inv = new Inventory();
		assertEquals(0, inv.getWheels());

		// negative amount added
		inv.addWheels(-5);
		assertEquals(0, inv.getWheels());

		// positive amount added
		inv.addWheels(5);
		assertEquals(5, inv.getWheels());

		// negative amount removed
		inv.removeWheels(-1);
		assertEquals(5, inv.getWheels());

		// remove too much
		inv.removeWheels(6);
		assertEquals(5, inv.getWheels());

		// remove valid amount
		inv.removeWheels(1);
		assertEquals(4, inv.getWheels());
	}

	@Test
	public void testAxles() {
		// starting amount
		Inventory inv = new Inventory();
		assertEquals(0, inv.getAxles());

		// negative amount added
		inv.addAxles(-5);
		assertEquals(0, inv.getAxles());

		// positive amount added
		inv.addAxles(5);
		assertEquals(5, inv.getAxles());

		// negative amount removed
		inv.removeAxles(-1);
		assertEquals(5, inv.getAxles());

		// remove too much
		inv.removeAxles(6);
		assertEquals(5, inv.getAxles());

		// remove valid amount
		inv.removeAxles(1);
		assertEquals(4, inv.getAxles());
	}

	@Test
	public void testTongues() {
		// starting amount
		Inventory inv = new Inventory();
		assertEquals(0, inv.getTongues());

		// negative amount added
		inv.addTongues(-5);
		assertEquals(0, inv.getTongues());

		// positive amount added
		inv.addTongues(5);
		assertEquals(5, inv.getTongues());

		// negative amount removed
		inv.removeTongues(-1);
		assertEquals(5, inv.getTongues());

		// remove too much
		inv.removeTongues(6);
		assertEquals(5, inv.getTongues());

		// remove valid amount
		inv.removeTongues(1);
		assertEquals(4, inv.getTongues());
	}
}
