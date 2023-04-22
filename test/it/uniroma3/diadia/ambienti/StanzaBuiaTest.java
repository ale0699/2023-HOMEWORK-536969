package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaBuiaTest {

	private Stanza stanzaBuia;
	
	@BeforeEach
	public void setUp() {
		
		this.stanzaBuia = new StanzaBuia("Stanza Buia", "lanterna");
	}
	
	@Test
	public void testStanzaSenzaAttrezzoCheIllumina() {
		
		this.stanzaBuia.addAttrezzo(new Attrezzo("osso", 3));
		String stringa = "qui c'è buio pesto";
		assertEquals(stringa, this.stanzaBuia.getDescrizione());
	}
	
	@Test
	public void testStanzaConAttrezzoCheIllumina() {
		
		this.stanzaBuia.addAttrezzo(new Attrezzo("lanterna", 3));
		assertEquals(this.stanzaBuia.toString(), this.stanzaBuia.getDescrizione());
	}
}
