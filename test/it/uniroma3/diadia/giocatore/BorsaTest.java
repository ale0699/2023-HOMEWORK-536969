package it.uniroma3.diadia.giocatore;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class BorsaTest {

	private Borsa borsaSingleton;
	private Borsa borsaVuota;
	private Borsa borsaConPeso60;
	private Attrezzo martello;

	@BeforeEach
	public void setUp() {

		this.borsaSingleton = new Borsa(50);
		this.borsaVuota = new Borsa();
		this.borsaConPeso60 = new Borsa(60);
		this.martello = new Attrezzo("Martello", 10);
		this.borsaSingleton.addAttrezzo(martello);
	}

	@Test
	public void testBorsaVuota() {

		assertTrue(this.borsaVuota.isEmpty());
	}
	
	@Test
	public void testBorsaNonVuota() {
		
		assertFalse(this.borsaSingleton.isEmpty());
	}
	
	@Test
	public void testGetPesoMax() {
		
		assertEquals(50, this.borsaSingleton.getPesoMax());
	}

	@Test
	public void testPesoBorsaNonVuota() {

		assertEquals(10, this.borsaSingleton.getPeso());
	}
	
	@Test
	public void testPesoBorsaVuota() {
		
		assertNotEquals(10, this.borsaVuota.getPeso());
	}
	
	@Test
	public void testGetAttrezzoBorsaNonVuota() {
		
		assertEquals(this.martello, this.borsaSingleton.getAttrezzo("Martello"));
	}
	
	@Test
	public void testAddAttrezzoBorsaVuota() {
		
		Attrezzo spada = new Attrezzo("Spada", 5);
		this.borsaVuota.addAttrezzo(spada);

		assertEquals(spada, this.borsaVuota.getAttrezzo("Spada"));
	}

	@Test
	public void testAddAttrezzoTroppoPesante() {

		Attrezzo spada = new Attrezzo("Spada", 100);
		assertFalse(this.borsaSingleton.addAttrezzo(spada));
	}
	
	@Test
	public void testRemoveAttrezzoDallaBorsaNonVuota() {
		
		assertEquals(this.martello, this.borsaSingleton.removeAttrezzo("Martello"));
	}
	
	@Test
	public void testRemoveAttrezzoNonPresenteNellaBorsaNonVuota() {
		
		assertNull(this.borsaSingleton.removeAttrezzo("Spada"));
	}
	
	@Test
	public void testHasAttrezzoBorsaNonVuota() {
		
		assertNotNull(this.borsaSingleton.hasAttrezzo("Martello"));
	}
	
	@Test
	public void testListaAttrezziOrdinatiPerPeso() {
		
		this.borsaConPeso60.addAttrezzo(new Attrezzo("piombo", 10));
		this.borsaConPeso60.addAttrezzo(new Attrezzo("ps", 5));
		this.borsaConPeso60.addAttrezzo(new Attrezzo("piuma", 1));
		this.borsaConPeso60.addAttrezzo(new Attrezzo("libro", 5));

		List<Attrezzo> lista = this.borsaConPeso60.getContenutoOrdinatoPerPeso();
		assertEquals("piuma",lista.get(0).getNome());
		assertEquals("libro",lista.get(1).getNome());
		assertEquals("ps",lista.get(2).getNome());
		assertEquals("piombo",lista.get(3).getNome());
	}
	
	@Test
	public void testInsiemeOrdinatoPerNome() {
		
		this.borsaConPeso60.addAttrezzo(new Attrezzo("piombo", 10));
		this.borsaConPeso60.addAttrezzo(new Attrezzo("ps", 5));
		this.borsaConPeso60.addAttrezzo(new Attrezzo("piuma", 1));
		this.borsaConPeso60.addAttrezzo(new Attrezzo("libro", 5));
		
		SortedSet<Attrezzo> insieme = this.borsaConPeso60.getContenutoOrdinatoPerNome();

		Iterator<Attrezzo> iteratore = insieme.iterator();
		assertEquals("libro",iteratore.next().getNome());
		assertEquals("piombo",iteratore.next().getNome());
		assertEquals("piuma",iteratore.next().getNome());
		assertEquals("ps",iteratore.next().getNome());
	}
	
	@Test
	public void testMappaRaggruppatoPerPeso() {
		
		this.borsaConPeso60.addAttrezzo(new Attrezzo("piombo", 10));
		this.borsaConPeso60.addAttrezzo(new Attrezzo("ps", 5));
		this.borsaConPeso60.addAttrezzo(new Attrezzo("piuma", 1));
		this.borsaConPeso60.addAttrezzo(new Attrezzo("libro", 5));
		
		Map<Integer,Set<Attrezzo>> attrezzi = this.borsaConPeso60.getContenutoRaggruppatoPerPeso();

		assertEquals("{1=[piuma (1kg)], 5=[libro (5kg), ps (5kg)], 10=[piombo (10kg)]}",attrezzi.toString());
		
	}
	
	@Test
	public void testInsiemeOrdinatoPerPeso() {
		
		this.borsaConPeso60.addAttrezzo(new Attrezzo("piombo", 10));
		this.borsaConPeso60.addAttrezzo(new Attrezzo("ps", 5));
		this.borsaConPeso60.addAttrezzo(new Attrezzo("piuma", 1));
		this.borsaConPeso60.addAttrezzo(new Attrezzo("libro", 5));
		
		SortedSet<Attrezzo> insieme = this.borsaConPeso60.getSortedSetOrdinatoPerPeso();

		assertEquals("[piuma (1kg), libro (5kg), ps (5kg), piombo (10kg)]",insieme.toString());
	}

}
