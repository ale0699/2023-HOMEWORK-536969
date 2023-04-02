package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;

class PartitaTest {

	private Partita partita;

	@BeforeEach
	public void setUp() {

		this.partita = new Partita();
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
