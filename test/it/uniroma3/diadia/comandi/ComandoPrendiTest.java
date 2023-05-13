package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;

class ComandoPrendiTest {

	private Comando comandoPrendi;
	private Partita partita;
	private Stanza stanza;
	private Labirinto labirinto;
	
	@BeforeEach
	public void setUp() {
		
		this.comandoPrendi = new ComandoPrendi();
		this.comandoPrendi.setIO(new IOConsole());
		
		this.labirinto = new LabirintoBuilder()
				.addStanzaIniziale("Inizio")
				.addStanzaVincente("Vincente")
				.getLabirinto();
		this.partita = new Partita(labirinto);
		
		this.stanza = new Stanza("Stanza con un'ascia");
		this.partita.setStanzaCorrente(stanza);
		this.stanza.addAttrezzo(new Attrezzo("ascia", 5));
		
	}
	
	@Test
	public void testPrendiAttrezzoNonPresenteNellaStanza() {
		
		this.comandoPrendi.setParametro("osso");
		this.comandoPrendi.esegui(this.partita);
		
		assertFalse(this.partita.getGiocatore().getBorsa().hasAttrezzo("osso"));
		assertFalse(this.partita.getStanzaCorrente().hasAttrezzo("osso"));
	}
	
	@Test
	public void testPrendiAttrezzoPresenteNellaStanzaEAggiungiAllaBorsa() {
		
		this.comandoPrendi.setParametro("ascia");
		this.comandoPrendi.esegui(this.partita);
		
		assertTrue(this.partita.getGiocatore().getBorsa().hasAttrezzo("ascia"));
		assertFalse(this.partita.getStanzaCorrente().hasAttrezzo("ascia"));
	}
	
	@Test
	public void testPrendiAttrezzoNellaStanzaEAggiungiAdUnaBorsaCheHaRaggiuntoPesoMax() {
		
		Borsa borsaPesoMassimoRaggiunto = new Borsa(20);
		borsaPesoMassimoRaggiunto.addAttrezzo(new Attrezzo("osso", 20));
		this.partita.getGiocatore().setBorsa(borsaPesoMassimoRaggiunto);
		
		this.comandoPrendi.setParametro("ascia");
		this.comandoPrendi.esegui(this.partita);
		
		assertFalse(borsaPesoMassimoRaggiunto.hasAttrezzo("ascia"));
	}
}
