package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaMagicaTest {
	
	private Stanza stanzaMagicaDefault;
	private Stanza stanzaMagicaSoglia2;

	@BeforeEach
	public void setUp() {
		
		this.stanzaMagicaDefault = new StanzaMagica("Stanza Magica Default");
		
		this.stanzaMagicaSoglia2 = new StanzaMagica("Stanza Magica con soglia uguale a 2", 2);
	}
	
	@Test
	public void testStanzaMagicaDefaultAddAttrezzoQuandoHoRaggiuntoLaSoglia() {
		
		this.stanzaMagicaDefault.addAttrezzo(new Attrezzo("osso", 2));
		this.stanzaMagicaDefault.addAttrezzo(new Attrezzo("ascia", 3));
		this.stanzaMagicaDefault.addAttrezzo(new Attrezzo("spada", 6));
		
		this.stanzaMagicaDefault.addAttrezzo(new Attrezzo("martello", 2));
		assertTrue(this.stanzaMagicaDefault.hasAttrezzo("olletram"), "L'attrezzo viene aggiunto con il nome al contrario");
	}
	
	@Test
	public void testStanzaMagicaDefaultAddAttrezzoQuandoNonHoRaggiuntoLaSoglia() {
		
		this.stanzaMagicaDefault.addAttrezzo(new Attrezzo("osso", 2));
		this.stanzaMagicaDefault.addAttrezzo(new Attrezzo("ascia", 3));
		
		this.stanzaMagicaDefault.addAttrezzo(new Attrezzo("martello", 2));
		assertFalse(this.stanzaMagicaDefault.hasAttrezzo("olletram"), "L'attrezzo viene aggiunto con il nome corretto");
	}
	
	@Test
	public void testStanzaMagicaConSoglia2AddAttrezzoQuandoHoRaggiuntoLaSoglia() {
		
		this.stanzaMagicaSoglia2.addAttrezzo(new Attrezzo("osso", 2));
		this.stanzaMagicaSoglia2.addAttrezzo(new Attrezzo("ascia", 3));
		
		this.stanzaMagicaSoglia2.addAttrezzo(new Attrezzo("spada", 6));
		this.stanzaMagicaSoglia2.addAttrezzo(new Attrezzo("martello", 2));
		
		assertTrue(this.stanzaMagicaSoglia2.hasAttrezzo("olletram"), "L'attrezzo viene aggiunto con il nome al contrario");
		assertTrue(this.stanzaMagicaSoglia2.hasAttrezzo("adaps"), "L'attrezzo viene aggiunto con il nome al contrario");
	}
}
