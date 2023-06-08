package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.DiaDia;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.IOSimulator;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class ComandoPosaTest {
	
	private AbstractComando comandoPosa;
	private Partita partita;
	private Stanza stanzaVuota;
	private Labirinto labirinto;
	
	@BeforeEach
	public void setUp() {
		
		this.comandoPosa = new ComandoPosa();
		this.comandoPosa.setIO(new IOConsole(new Scanner(System.in)));
		this.labirinto = new Labirinto.LabirintoBuilder()
				.addStanzaIniziale("Inizio")
				.addStanzaVincente("Vincente")
				.getLabirinto();
		this.partita = new Partita(labirinto);
		
		this.stanzaVuota = new Stanza("StanzaVuota");
		this.partita.setStanzaCorrente(stanzaVuota);
		
		this.partita.getGiocatore().getBorsa().addAttrezzo(new Attrezzo("ascia", 5));
		
	}
	
	@Test
	public void testPosaAttrezzoNonPresenteNellaBorsaInUnaStanzaVuota() {
		
		this.comandoPosa.setParametro("osso");
		this.comandoPosa.esegui(this.partita);
		
		assertFalse(this.partita.getGiocatore().getBorsa().hasAttrezzo("osso"));
		assertFalse(this.partita.getStanzaCorrente().hasAttrezzo("osso"));
	}
	
	@Test
	public void testPosaAttrezzoPresenteNellaBorsaInUnaStanzaVuota() {
		
		this.comandoPosa.setParametro("ascia");
		this.comandoPosa.esegui(this.partita);
		
		assertTrue(this.partita.getStanzaCorrente().hasAttrezzo("ascia"));
		assertFalse(this.partita.getGiocatore().getBorsa().hasAttrezzo("ascia"));
	}
	
	@Test
	public void testPosaAttrezzoPresenteNellaBorsaInUnaStanzaPiena() {
		
		Stanza stanzaPiena = new Stanza("Stanza Piena");
		this.partita.setStanzaCorrente(stanzaPiena);
		
		for(int i=0; i<10; i++) {
			
			stanzaPiena.addAttrezzo(new Attrezzo("Attrezzo random"+i, 5));
		}
		
		this.comandoPosa.setParametro("ascia");
		this.comandoPosa.esegui(this.partita);
		
		assertFalse(stanzaPiena.hasAttrezzo("ascia"));
	}
	
	@Test
	public void testIoSimulatorComandoPosa() {
		//String[] righeDaLeggere = {"posa ascia","fine"};
		List<String> righeDaLeggere = new ArrayList<>();
		righeDaLeggere.add("posa ascia");
		righeDaLeggere.add("fine");
		IOSimulator io = new IOSimulator(righeDaLeggere);
		DiaDia diadia = new DiaDia(this.labirinto,io);
		diadia.gioca();
		
		assertTrue(io.hasNextMessaggio());
		assertEquals(DiaDia.MESSAGGIO_BENVENUTO,io.nextMessaggio());
		assertTrue(io.hasNextMessaggio());
		assertEquals("Non è presente nella borsa nessun attrezzo chiamato: ascia",io.nextMessaggio());
		assertTrue(io.hasNextMessaggio());
		assertEquals("Grazie di aver giocato!",io.nextMessaggio());
		assertFalse(io.hasNextMessaggio());
	}
}
