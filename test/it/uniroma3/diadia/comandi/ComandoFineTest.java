package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.DiaDia;
import it.uniroma3.diadia.IOSimulator;
import it.uniroma3.diadia.ambienti.Labirinto;

class ComandoFineTest {

	@Test
	public void testIoSimulatorComandoFine() {
		//String[] righeDaLeggere = {"fine"};
		List<String> righeDaLeggere = new ArrayList<>();
		righeDaLeggere.add("fine");
		IOSimulator io = new IOSimulator(righeDaLeggere);
		Labirinto labirinto = new Labirinto.LabirintoBuilder()
				.addStanzaIniziale("Inizio")
				.addStanzaVincente("Vincente")
				.getLabirinto();
		DiaDia diadia = new DiaDia(labirinto,io);
		diadia.gioca();
		
		assertTrue(io.hasNextMessaggio());
		assertEquals(DiaDia.MESSAGGIO_BENVENUTO,io.nextMessaggio());
		assertTrue(io.hasNextMessaggio());
		assertEquals("Grazie di aver giocato!",io.nextMessaggio());
		assertFalse(io.hasNextMessaggio());
	}

}
