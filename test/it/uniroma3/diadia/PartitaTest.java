package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;

class PartitaTest {
	
	private Labirinto labirinto;
	private Partita partita;

	@BeforeEach
	public void setUp() {
		
		this.labirinto = new Labirinto.LabirintoBuilder()
				.addStanzaIniziale("iniziale")
				.addStanzaVincente("vincente")
				.getLabirinto();
		this.partita = new Partita(this.labirinto);
	}

	@Test
	public void testStanzaInizialeNonNulla() {

		assertNotNull(this.partita.getStanzaCorrente());
	}

	@Test
	public void testPartitaIniziataVinta() {

		assertFalse(this.partita.vinta());
	}
	
	@Test
	public void testPartitaVintaInStanzaVincente() {
		
		this.partita.setStanzaCorrente(this.partita.getStanzaVincente());
		assertTrue(this.partita.vinta());
	}
	
	@Test
	public void testPartitaVintaNonInStanzaVincente() {
		
		this.partita.setStanzaCorrente(new Stanza("Aula N11"));
		assertFalse(this.partita.vinta());
	}
	
	@Test
	public void testPartitaFinitaConCfuFiniti() {
		
		this.partita.setCfu(0);
		assertTrue(this.partita.isFinita());
	}
	
	@Test
	public void testPartitaFinitaSeVinta() {
		
		this.partita.setStanzaCorrente(this.partita.getStanzaVincente());
		assertTrue(this.partita.isFinita());
	}
	
	@Test
	public void testPartitaPersaConCfuFinitiNonNellaStanzaVincente() {
		
		this.partita.setCfu(0);
		this.partita.setStanzaCorrente(new Stanza("Aula N11"));
		assertFalse(this.partita.vinta());
	}
	
	@Test
	public void testEsistenzaGiocatore() {
		
		assertNotNull(this.partita.getGiocatore());
	}

}
