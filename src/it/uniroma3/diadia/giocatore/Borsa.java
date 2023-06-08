package it.uniroma3.diadia.giocatore;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import it.uniroma3.diadia.Configurazione;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Borsa {
	//public final static int DEFAULT_PESO_MAX_BORSA = 10;
	
	private Map<String, Attrezzo> attrezzi;
	private int pesoMax;
	private int pesoCorrente;

	public Borsa() {
		this(Configurazione.getPesoMax());
	}

	public Borsa(int pesoMax) {
		this.pesoMax = pesoMax;		
		this.attrezzi = new HashMap<>();
		this.pesoCorrente=0;
	}

	/**
	 * Restituisce false se la somma dei pesi degli attrezzi, all'interno della borsa (compreso quello
	 * che sto provando ad aggiungere), è maggiore del pesoMax della borsa.
	 * Restituisce false se la borsa è piena
	 * Restituisce true se sono riuscito ad aggiungere l'attrezzo
	 * 
	 * @param Attrezzo da aggiungere alla borsa
	 * 
	 * @return false(se borsa piena o troppo pesante), true se ho aggiunto l'attrezzo
	 */
	public boolean addAttrezzo(Attrezzo attrezzo) {
		if (this.getPeso() + attrezzo.getPeso() > this.getPesoMax())
			return false;

		this.attrezzi.put(attrezzo.getNome(), attrezzo);
		this.pesoCorrente+=attrezzo.getPeso();
		return true;
	}

	/**
	 * Restituisce peso massimoa della borsa
	 * 
	 * @return pesoMassimo borsa
	 */
	public int getPesoMax() {
		return pesoMax;
	}

	/**
	 * Restituisce un oggetto di tipo Attrezzo, trovato all'interno della borsa attraverso il nome
	 * 
	 * @param nomeAttrezzo da cercare
	 * @return Attrezzo cercato
	 */
	public Attrezzo getAttrezzo(String nomeAttrezzo) {

		return this.attrezzi.get(nomeAttrezzo);
	}

	/**
	 * Restituisce il peso effettivo della borsa
	 * 
	 * @return peso borsa
	 */
	public int getPeso() {
		return this.pesoCorrente;
	}

	/**
	 * Restituisce true se la borsa è vuota
	 * 
	 * @return true se borsa vuota
	 */
	public boolean isEmpty() {
		return this.attrezzi.isEmpty();
	}

	/**
	 * Restituisce true se la borsa contiene un determinato attrezzo
	 * 
	 * @param nomeAttrezzo da cercare
	 * @return true se l'attrezzo è presente
	 */
	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.getAttrezzo(nomeAttrezzo) != null;
	}

	/**
	 * Restituisce un determinato Attrezzo rimosso dalla borsa
	 * 
	 * @param nomeAttrezzo da cercare
	 * @return Attrezzo rimosso dalla borsa
	 */
	public Attrezzo removeAttrezzo(String nomeAttrezzo) {
		
		return this.attrezzi.remove(nomeAttrezzo);
	}

	public int getNumeroAttrezzi() {
		return this.attrezzi.size();
	}

	public String toString() {
		StringBuilder s = new StringBuilder();

		if (!this.isEmpty()) {
			s.append("Contenuto borsa (" + this.getPeso() + "kg/" + this.getPesoMax() + "kg): ");
			
			for(Attrezzo a : this.attrezzi.values()) {
				
				s.append(a.toString()+" ");
			}
		} else
			s.append("Borsa vuota");
		return s.toString();
	}
	
	public List<Attrezzo> getContenutoOrdinatoPerPeso(){
		
		List<Attrezzo> listaAttrezziOrdinatiPerPeso = new ArrayList<>(this.attrezzi.values());
		Collections.sort(listaAttrezziOrdinatiPerPeso,new ComparatoreAttrezziPerPeso());
		return listaAttrezziOrdinatiPerPeso;
	}
	
	public SortedSet<Attrezzo> getContenutoOrdinatoPerNome(){
		
		SortedSet<Attrezzo> insiemePerNome = new TreeSet<>(this.attrezzi.values());
		return insiemePerNome;
	}
	
	public Map<Integer,Set<Attrezzo>> getContenutoRaggruppatoPerPeso(){
		
		Map<Integer,Set<Attrezzo>> mappaAttrezzi = new HashMap<>();
		
		for(Attrezzo a : this.attrezzi.values()) {
			
			if(mappaAttrezzi.containsKey(a.getPeso())) {
				
				mappaAttrezzi.get(a.getPeso()).add(a);
			}
			else {
				
				Set<Attrezzo> nuovoInsieme = new HashSet<>();
				nuovoInsieme.add(a);
				mappaAttrezzi.put(a.getPeso(), nuovoInsieme);
			}
		}
		
		return mappaAttrezzi;
	}
	
	public SortedSet<Attrezzo> getSortedSetOrdinatoPerPeso(){
		
		SortedSet<Attrezzo> insiemePerPeso = new TreeSet<>(new ComparatoreAttrezziPerPeso());
		insiemePerPeso.addAll(this.attrezzi.values());
		return insiemePerPeso;
	}
}