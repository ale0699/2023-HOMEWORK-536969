package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class FabbricaDiComandiFisarmonicaTest {
	
	private FabbricaDiComandiFisarmonica factory;
	
	@BeforeEach
	public void setUp() {
		
		this.factory = new FabbricaDiComandiFisarmonica();
	}
	
	@Test
	public void testComandoVai() {
		
		Comando comandoVai = this.factory.costruisciComando("vai sud", null);
		
		assertEquals("vai", comandoVai.getNome());
		assertEquals("sud", comandoVai.getParametro());
	}
	
	@Test
	public void testComandoPrendi() {
		
		Comando comandoPrendi = this.factory.costruisciComando("prendi osso", null);
		
		assertEquals("prendi", comandoPrendi.getNome());
		assertEquals("osso", comandoPrendi.getParametro());
	}
	
	@Test
	public void testComandoPosa() {
		
		Comando comandoPosa = this.factory.costruisciComando("posa osso", null);
		
		assertEquals("posa", comandoPosa.getNome());
		assertEquals("osso", comandoPosa.getParametro());
	}
	
	@Test
	public void testComandoAiuto() {
		
		Comando comandoAiuto = this.factory.costruisciComando("aiuto", null);
		
		assertEquals("aiuto", comandoAiuto.getNome());
		assertNull(comandoAiuto.getParametro());
	}
	
	@Test
	public void testComandoFine() {
		
		Comando comandoFine = this.factory.costruisciComando("fine", null);
		
		assertEquals("fine", comandoFine.getNome());
		assertNull(comandoFine.getParametro());
	}
	
	@Test
	public void testComandoGuarda() {
		
		Comando comandoGuarda = this.factory.costruisciComando("guarda", null);
		
		assertEquals("guarda", comandoGuarda.getNome());
		assertNull(comandoGuarda.getParametro());
	}
	
	@Test
	public void testComandoNonValido() {
		
		Comando comandoNonValido = this.factory.costruisciComando("", null);
		
		assertEquals("Non Valido", comandoNonValido.getNome());
		assertNull(comandoNonValido.getParametro());
	}

}
