package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.DiaDia;
import it.uniroma3.diadia.IOSimulator;

class ComandoAiutoTest {

	@Test
	public void testIoSimulatorComandoAiuto() {
		String[] righeDaLeggere = {"aiuto", "fine"};
		IOSimulator io = new IOSimulator(righeDaLeggere);
		DiaDia diadia = new DiaDia(io);
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
