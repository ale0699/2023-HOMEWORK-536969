package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.DiaDia;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.IOSimulator;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;
import it.uniroma3.diadia.ambienti.Stanza;


class ComandoVaiTest {
	
	private Comando comandoVai;
	private Partita partita;
	private Stanza stanza;
	private Labirinto labirinto;
	
	@BeforeEach
	public void setUp() {
		
		this.comandoVai = new ComandoVai();
		this.comandoVai.setIO(new IOConsole());
		this.labirinto = new LabirintoBuilder()
				.addStanzaIniziale("Inizio")
				.addStanzaVincente("Vincente")
				.addStanza("Laboratorio Campus")
				.addAdiacenza("Inizio", "Laboratorio Campus", "ovest")
				.getLabirinto();
		this.partita = new Partita(labirinto);
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
		//String[] righeDaLeggere = {"vai ovest","fine"};
		List<String> righeDaLeggere = new ArrayList<>();
		righeDaLeggere.add("vai ovest");
		righeDaLeggere.add("fine");
		IOSimulator io = new IOSimulator(righeDaLeggere);
		DiaDia diadia = new DiaDia(this.labirinto,io);
		diadia.gioca();
		
		assertTrue(io.hasNextMessaggio());
		assertEquals(DiaDia.MESSAGGIO_BENVENUTO,io.nextMessaggio());
		assertTrue(io.hasNextMessaggio());
		assertEquals("Laboratorio Campus",io.nextMessaggio());
		assertTrue(io.hasNextMessaggio());
		assertEquals("Grazie di aver giocato!",io.nextMessaggio());
		assertFalse(io.hasNextMessaggio());
	}
	
	@Test
	public void testIoSimulatorMappaMessaggiProdottiPerOgniRiga() {
		
		List<String> righeDaLeggere = new ArrayList<>();
		righeDaLeggere.add("vai ovest");
		righeDaLeggere.add("fine");
		IOSimulator io = new IOSimulator(righeDaLeggere);
		DiaDia diadia = new DiaDia(this.labirinto,io);
		diadia.gioca();
		
		assertTrue(io.hasNextMessaggio());
		io.nextMessaggio();
		List<String> listaMessaggiBenevenuto = io.getRiga2messaggiProdottiDallaRiga().get("Messaggio Benvenuto");
		assertEquals(DiaDia.MESSAGGIO_BENVENUTO,listaMessaggiBenevenuto.get(0));
		
		assertTrue(io.hasNextMessaggio());
		io.nextMessaggio();
		List<String> listaMessaggiComandoVaiOvest = io.getRiga2messaggiProdottiDallaRiga().get("vai ovest");
		assertEquals("Laboratorio Campus", listaMessaggiComandoVaiOvest.get(0));
		
		assertTrue(io.hasNextMessaggio());
		io.nextMessaggio();
		List<String> listaMessaggiComandoFine = io.getRiga2messaggiProdottiDallaRiga().get("fine");
		assertEquals("Grazie di aver giocato!", listaMessaggiComandoFine.get(0));
		
		assertFalse(io.hasNextMessaggio());
		
	}
}
