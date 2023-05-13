package it.uniroma3.diadia.giocatore;

import java.util.Comparator;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComparatoreAttrezziPerPeso implements Comparator<Attrezzo> {

	@Override
	public int compare(Attrezzo a1, Attrezzo a2) {
		int differenzaPeso = a1.getPeso() - a2.getPeso();
		
		if (differenzaPeso != 0) {
			return differenzaPeso;
		}
		
		return a1.compareTo(a2);
	}

}
