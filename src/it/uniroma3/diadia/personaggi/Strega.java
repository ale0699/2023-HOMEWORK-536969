package it.uniroma3.diadia.personaggi;

import java.util.TreeSet;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.ComparatoreStanzaPerAttrezzi;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Strega extends AbstractPersonaggio {
	
	public Strega(String nome, String presentaz) {
		super(nome, presentaz);
	}

	@Override
	public String agisci(Partita partita) {
		
		TreeSet<Stanza> stanzeAdiacentiOrdinatePerAttrezzi = new TreeSet<>(new ComparatoreStanzaPerAttrezzi());
		stanzeAdiacentiOrdinatePerAttrezzi.addAll(partita.getStanzaCorrente().getMapStanzeAdiacenti().values());
		
		if(this.haSalutato()) {
			
			partita.setStanzaCorrente(stanzeAdiacentiOrdinatePerAttrezzi.last());
		}
		else {
			
			partita.setStanzaCorrente(stanzeAdiacentiOrdinatePerAttrezzi.first());
		}
		
		return ("Sei stato spostato in " + partita.getStanzaCorrente());
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		
		return "AHAHAHA";
	}

}
