package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.StringReader;

import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class CaricatoreLabirintoTest {
	
	private final String monolocale = 
			 "Stanze:N10\n"+
			 "Inizio:N10\n"+
			 "Vincente:N10\n"+
			 "Attrezzi:pinza 2 N10\n"+
			 "Uscite:\n"+
			 "Buia:\n"+
			 "Bloccata:\n"+
			 "Magica:\n"+
			 "Strega:\n"+
			 "Cane:\n"+
			 "Mago:\n";
	
	private final String bilocale = 
			 "Stanze:N10,N11\n"+
			 "Inizio:N10\n"+
			 "Vincente:N11\n"+
			 "Attrezzi:pinza 2 N10\n"+
			 "Uscite:\n"+
			 "Buia:\n"+
			 "Bloccata:\n"+
			 "Magica:\n"+
			 "Strega:\n"+
			 "Cane:\n"+
			 "Mago:\n";
	
	
	@Test
	public void testMonolocale() throws FileNotFoundException, FormatoFileNonValidoException {
		
		CaricatoreLabirinto caricatore = new CaricatoreLabirinto(new StringReader(monolocale));
		caricatore.carica();
		assertEquals("N10", caricatore.getStanzaIniziale().getNome());
		assertEquals("N10", caricatore.getStanzaVincente().getNome());
	}
	
	@Test
	public void testBilocale() throws FileNotFoundException, FormatoFileNonValidoException {
		
		CaricatoreLabirinto caricatore = new CaricatoreLabirinto(new StringReader(bilocale));
		caricatore.carica();
		assertEquals("N10", caricatore.getStanzaIniziale().getNome());
		assertEquals("N11", caricatore.getStanzaVincente().getNome());
	}
	
	@Test
	public void testBilocaleAttrezzo() throws FileNotFoundException, FormatoFileNonValidoException {
		
		CaricatoreLabirinto caricatore = new CaricatoreLabirinto(new StringReader(bilocale));
		caricatore.carica();
		Attrezzo pinza = new Attrezzo("pinza", 2);
		assertEquals(pinza, caricatore.getStanzaIniziale().getAttrezzo("pinza"));
	}

}
