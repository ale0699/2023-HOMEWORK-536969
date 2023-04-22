package it.uniroma3.diadia.giocatore;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class GiocatoreTest {
	
	private Giocatore giocatore;
	
	@BeforeEach
	public void setUp() {
		
		this.giocatore = new Giocatore();
	}
	
	@Test
	public void testCFUInziali() {
		
		assertEquals(20, this.giocatore.getCfu());
	}
	
	@Test
	public void testSetCfu() {
		
		this.giocatore.setCfu(15);
		assertEquals(15, this.giocatore.getCfu());
	}
	
	@Test
	public void testBorsaGiocatoreVuota() {
		
		assertTrue(this.giocatore.getBorsa().isEmpty());
	}
	
}
