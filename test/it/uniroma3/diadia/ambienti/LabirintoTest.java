package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Labirinto;

class LabirintoTest {

	private Labirinto labirinto;
	
	@BeforeEach
	public void setUp() {
		
		this.labirinto = new Labirinto();
	}
	
	@Test
	public void testStanzaInziale() {
		
		assertEquals("Atrio", this.labirinto.getStanzaIniziale().getNome());
	}
	
	@Test
	public void testStanzaVincente() {
		
		assertEquals("Biblioteca", this.labirinto.getStanzaVincente().getNome());
	}
	
}
