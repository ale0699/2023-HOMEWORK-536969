package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaBloccataTest {
	
	private Stanza stanzaBloccata;
	private Stanza stanzaSud;
	private Stanza stanzaNord;
	
	@BeforeEach
	public void setUp() {
		
		this.stanzaBloccata = new StanzaBloccata("Stanza Bloccata", "sud", "piedediporco");
		
		this.stanzaSud = new Stanza("Stanza Sud");
		this.stanzaNord  = new Stanza("Stanza Nord");
		this.stanzaBloccata.impostaStanzaAdiacente("sud", this.stanzaSud);
		this.stanzaBloccata.impostaStanzaAdiacente("nord", this.stanzaNord);
		
	}
	
	@Test
	public void testStanzaConAttrezzoPerSbloccarla() {
		
		this.stanzaBloccata.addAttrezzo(new Attrezzo("piedediporco", 2));
		assertEquals(this.stanzaSud, this.stanzaBloccata.getStanzaAdiacente("sud"));
	}
	
	@Test
	public void testStanzaSenzaAttrezzoPerSbloccarla() {
		
		assertNotEquals(this.stanzaSud, this.stanzaBloccata.getStanzaAdiacente("sud"));
	}
	
	@Test
	public void testGetDescrizioneStanzaBloccata() {
		
		String stringa = ("Stanza bloccata nella direzione sud, posa l'attrezzo piedediporco per sbloccare la direzione");
		assertEquals(stringa, this.stanzaBloccata.getDescrizione());
	}
	
	@Test
	public void testGetDescrizioneStanzaSbloccata() {
		
		this.stanzaBloccata.addAttrezzo(new Attrezzo("piedediporco", 2));
		assertEquals(this.stanzaBloccata.toString(), this.stanzaBloccata.getDescrizione());
	}
}
