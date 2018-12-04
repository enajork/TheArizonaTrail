package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.Test;

import model.Cart;

public class CartTest {
	@Test
	public void testResetCart() {
		Cart cart = new Cart();
		cart.setAmmo(1.0);
		cart.setClothes(2.0);
		cart.setFood(3.0);
		cart.setOxen(4.0);
		cart.setParts(5.0);

		cart.resetCart();
		assertEquals(0.0, cart.getAmmo());
		assertEquals(0.0, cart.getClothes());
		assertEquals(0.0, cart.getFood());
		assertEquals(0.0, cart.getOxen());
		assertEquals(0.0, cart.getParts());
		assertEquals(0.0, cart.getTotal());
	}

	@Test
	public void testGetTotal() {
		Cart cart = new Cart();
		cart.setAmmo(1.0);
		cart.setClothes(2.0);
		cart.setFood(3.0);
		cart.setOxen(4.0);
		cart.setParts(5.0);
		assertEquals(15.0, cart.getTotal());
	}

	@Test
	public void testConstructor() {
		Cart cart = new Cart();
		assertEquals(0.0, cart.getAmmo());
		assertEquals(0.0, cart.getClothes());
		assertEquals(0.0, cart.getFood());
		assertEquals(0.0, cart.getOxen());
		assertEquals(0.0, cart.getParts());
		assertEquals(0.0, cart.getTotal());
	}
}
