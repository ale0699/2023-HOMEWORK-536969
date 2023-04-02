package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaTest {
	
	static final private int NUMERO_MASSIMO_ATTREZZI = 10;
	
	private Stanza stanza;
	private Stanza stanzaNord;
	private Stanza stanzaEst;
	private Stanza stanzaOvest;
	private int numeroAttrezzi = 0;
	
	@BeforeEach
	public void setUp() {
		
		this.stanza = new Stanza("Stanza non vuota");
		Attrezzo martello = new Attrezzo("Martello", 5);
		this.stanza.addAttrezzo(martello);
		this.numeroAttrezzi++;
		
		this.stanzaNord = new Stanza("Stanza a Nord");
		this.stanzaEst = new Stanza("Stanza a Est");
		this.stanzaOvest = new Stanza("Stanza a Ovest");
		this.stanza.impostaStanzaAdiacente("nord", this.stanzaNord);
		this.stanza.impostaStanzaAdiacente("est", this.stanzaEst);
		this.stanza.impostaStanzaAdiacente("ovest", stanzaOvest);
	}
	
	@Test
	public void testGetNome() {
		
		assertEquals("Stanza non vuota", this.stanza.getNome());
	}
	
	@Test
	public void testAddAttrezzi() {
		
		Attrezzo spada = new Attrezzo("Spada", 10);
		if(this.stanza.addAttrezzo(spada)) {
			
			this.numeroAttrezzi++;
		}

		assertEquals(spada, this.stanza.getAttrezzo("Spada"));
	}
	
	@Test
	public void testNumeroAttrezzi() {
		Attrezzo spada = new Attrezzo("Spada", 10);
		if(this.stanza.addAttrezzo(spada)) {
			
			this.numeroAttrezzi++;
		}
		
		assertEquals(2, this.stanza.getNumeroAttrezzi());
	}
	
	@Test
	public void testHasAttrezzi() {
		
		assertTrue(this.stanza.hasAttrezzo("Martello"));
	}
	
	@Test
	public void testHasAttrezzoNonPresente() {
		
		assertFalse(this.stanza.hasAttrezzo("Ascia"));
	}

	@Test
	public void testRimuoviAttrezzoPresenteNellaStanza() {
		
		assertTrue(this.stanza.removeAttrezzo("Martello"));
	}
	
	@Test
	public void testRimuoviAttrezzoNonPresenteNellaStanza() {
		
		assertFalse(this.stanza.removeAttrezzo("Ascia"));
	}
	
	@Test
	public void testGetAttrezziSuUnaStanzaNonVuota() {
		
		Attrezzo[] attrezziStanza = this.stanza.getAttrezzi();
		boolean foundElementAttrezzo = false;
		
		for(Attrezzo a : attrezziStanza) {
			
			if(a!=null) {
				
				foundElementAttrezzo = true;
			}
		}
		
		assertTrue(foundElementAttrezzo);
	}
	
	@Test
	public void testGetAttrezzo() {
		
		Attrezzo spada = new Attrezzo("Spada", 20);
		this.stanza.addAttrezzo(spada);
		this.numeroAttrezzi++;
		
		assertEquals(spada, this.stanza.getAttrezzo("Spada"));
	}
	
	@Test
	public void testAddAttrezzoQuandoLaStanzaÈPiena() {
		
		//riempio la stanza con degli attrezzi random
		for(int i=0; i<NUMERO_MASSIMO_ATTREZZI-this.numeroAttrezzi; i++) {
			
			Attrezzo attrezzoRandom = new Attrezzo("Nuovo attrezzo random", 10);
			this.stanza.addAttrezzo(attrezzoRandom);
		}
		
		Attrezzo ascia = new Attrezzo("Ascia", 10);
		assertFalse(this.stanza.addAttrezzo(ascia));
		
	}
	
	@Test
	public void testImpostaStanzaAdiacente() {
		
		Stanza stanzaSud = new Stanza("Stanza a sud");
		this.stanza.impostaStanzaAdiacente("sud", stanzaSud);
		assertEquals(stanzaSud, this.stanza.getStanzaAdiacente("sud"));
	}
	
	@Test
	public void testGetStanzaAdiacente() {
		
		assertEquals(this.stanzaNord, this.stanza.getStanzaAdiacente("nord"));
	}
	
	@Test
	public void testDirezioneInesistentePerLaStanzaCorrente() {
		
		String direzioneSud = "sud";
		assertNull(this.stanza.getStanzaAdiacente(direzioneSud));
	}

}
