package it.uniroma3.diadia.giocatore;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;

class BorsaTest {

	private Borsa borsa;
	private Borsa borsaVuota;
	private Attrezzo martello;

	@BeforeEach
	public void setUp() {

		this.borsa = new Borsa(50);
		this.borsaVuota = new Borsa();
		this.martello = new Attrezzo("Martello", 10);
		this.borsa.addAttrezzo(martello);
	}

	@Test
	public void testBorsaVuota() {

		assertTrue(this.borsaVuota.isEmpty());
	}
	
	@Test
	public void testBorsaNonVuota() {
		
		assertFalse(this.borsa.isEmpty());
	}
	
	@Test
	public void testGetPesoMax() {
		
		assertEquals(50, this.borsa.getPesoMax());
	}

	@Test
	public void testPesoBorsaNonVuota() {

		assertEquals(10, this.borsa.getPeso());
	}
	
	@Test
	public void testPesoBorsaVuota() {
		
		assertNotEquals(10, this.borsaVuota.getPeso());
	}
	
	@Test
	public void testGetAttrezzoBorsaNonVuota() {
		
		assertEquals(this.martello, this.borsa.getAttrezzo("Martello"));
	}
	
	@Test
	public void testAddAttrezzoBorsaVuota() {
		
		Attrezzo spada = new Attrezzo("Spada", 10);
		this.borsaVuota.addAttrezzo(spada);
		
		assertEquals(spada, this.borsaVuota.getAttrezzo("Spada"));
	}

	@Test
	public void testAddAttrezzoTroppoPesante() {

		Attrezzo spada = new Attrezzo("Spada", 100);
		assertFalse(this.borsa.addAttrezzo(spada));
	}
	
	@Test
	public void testRemoveAttrezzoDallaBorsaNonVuota() {
		
		assertEquals(this.martello, this.borsa.removeAttrezzo("Martello"));
	}
	
	@Test
	public void testRemoveAttrezzoNonPresenteNellaBorsaNonVuota() {
		
		assertNull(this.borsa.removeAttrezzo("Spada"));
	}
	
	@Test
	public void testHasAttrezzoBorsaNonVuota() {
		
		assertNotNull(this.borsa.hasAttrezzo("Martello"));
	}

}
