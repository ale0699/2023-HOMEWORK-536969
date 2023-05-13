package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class LabirintoTest {

	private Labirinto labirinto;
	
	@BeforeEach
	public void setUp() {
		
		this.labirinto = new LabirintoBuilder()
				.addStanzaVincente("Biblioteca")
				.addStanzaIniziale("Atrio")
				.getLabirinto();
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
