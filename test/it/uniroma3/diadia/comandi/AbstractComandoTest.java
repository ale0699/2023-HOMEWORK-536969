package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;

class AbstractComandoTest {
	
	private IO io;
	private AbstractComando comandoPosa;
	private AbstractComando aiuto;

	@BeforeEach
	public void setUp() throws Exception {
		
		this.io = new IOConsole(new Scanner(System.in));
		this.comandoPosa = new ComandoPosa();
		this.comandoPosa.setParametro("martello");
		this.aiuto = new ComandoAiuto();
		this.comandoPosa.setIO(io);
		this.aiuto.setIO(io);
	}

	@Test
	public void testGetParametro() {
		
		assertNotNull(this.comandoPosa.getParametro());
		assertNull(this.aiuto.getParametro());
	}
	
	@Test
	public void testGetNome() {
		
		assertEquals("posa",this.comandoPosa.getNome());
		assertEquals("aiuto",this.aiuto.getNome());
	}
	
	@Test
	public void testGetIO() {
		
		assertSame(this.comandoPosa.getIO(), this.aiuto.getIO());
	}
}
