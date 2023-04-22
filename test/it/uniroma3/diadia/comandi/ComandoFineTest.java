package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.DiaDia;
import it.uniroma3.diadia.IOSimulator;

class ComandoFineTest {

	@Test
	public void testIoSimulatorComandoFine() {
		String[] righeDaLeggere = {"fine"};
		IOSimulator io = new IOSimulator(righeDaLeggere);
		DiaDia diadia = new DiaDia(io);
		diadia.gioca();
		
		assertTrue(io.hasNextMessaggio());
		assertEquals(DiaDia.MESSAGGIO_BENVENUTO,io.nextMessaggio());
		assertTrue(io.hasNextMessaggio());
		assertEquals("Grazie di aver giocato!",io.nextMessaggio());
		assertFalse(io.hasNextMessaggio());
	}

}
