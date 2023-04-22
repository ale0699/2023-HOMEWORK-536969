package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.DiaDia;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.IOSimulator;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;


class ComandoVaiTest {
	
	private Comando comandoVai;
	private Partita partita;
	private Stanza stanza;
	
	@BeforeEach
	public void setUp() {
		
		this.comandoVai = new ComandoVai();
		this.comandoVai.setIO(new IOConsole());
		this.partita = new Partita();
		this.stanza = new Stanza("Stanza");
		this.partita.setStanzaCorrente(stanza);
	}
	
	@Test
	public void testDirezioneEsistente() {
		
		this.comandoVai.setParametro("sud");
		this.stanza.impostaStanzaAdiacente("sud", new Stanza("Aula a Sud"));
		this.comandoVai.esegui(this.partita);

		assertEquals("Aula a Sud", this.partita.getStanzaCorrente().getNome());
	}
	
	@Test
	public void testDirezioneInesistente() {
		
		this.comandoVai.setParametro("ovest");
		this.stanza.impostaStanzaAdiacente("sud", new Stanza("Aula a Sud"));
		this.comandoVai.esegui(this.partita);

		assertNotEquals("Aula a Sud", this.partita.getStanzaCorrente().getNome());
	}
	
	@Test
	public void testIoSimulatorComandoVaiOvest() {
		String[] righeDaLeggere = {"vai ovest","fine"};
		IOSimulator io = new IOSimulator(righeDaLeggere);
		DiaDia diadia = new DiaDia(io);
		diadia.gioca();
		
		assertTrue(io.hasNextMessaggio());
		assertEquals(DiaDia.MESSAGGIO_BENVENUTO,io.nextMessaggio());
		assertTrue(io.hasNextMessaggio());
		assertEquals("Laboratorio Campus",io.nextMessaggio());
		assertTrue(io.hasNextMessaggio());
		assertEquals("Grazie di aver giocato!",io.nextMessaggio());
		assertFalse(io.hasNextMessaggio());
	}
}
