package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.Test;

import model.TopTen;

public class TopTenTest {
	@Test
	public void testSize() {
		TopTen topTen = new TopTen();
		assertEquals(10, topTen.size());
	}

	@Test
	public void testScores() {
		TopTen topTen = new TopTen();
		topTen.setScore("Kenny", 100);
		assertEquals(100, topTen.getScore(0));

	}

	@Test
	public void testGetName() {
		TopTen topTen = new TopTen();
		topTen.setScore("Burt", 100);
		assertEquals("Burt", topTen.getName(0));
	}
}
