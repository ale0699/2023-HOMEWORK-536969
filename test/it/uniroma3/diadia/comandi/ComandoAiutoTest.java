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

class ComandoAiutoTest {

	@Test
	public void testIoSimulatorComandoAiuto() {
		//String[] righeDaLeggere = {"aiuto", "fine"};
		List<String> righeDaLeggere = new ArrayList<>();
		righeDaLeggere.add("aiuto");
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
		
		for(int i=0; i < ComandoAiuto.elencoComandi.length; i++) {
			assertTrue(io.hasNextMessaggio());
			assertEquals(ComandoAiuto.elencoComandi[i]+" ", io.nextMessaggio());
		}
		
		io.nextMessaggio();
		assertTrue(io.hasNextMessaggio());
		assertEquals("Grazie di aver giocato!",io.nextMessaggio());
		assertFalse(io.hasNextMessaggio());
	}
}
