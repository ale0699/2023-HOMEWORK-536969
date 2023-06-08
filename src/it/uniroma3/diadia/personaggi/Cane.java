package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Cane extends AbstractPersonaggio {
	
	private String ciboPreferito; 
	private Attrezzo attrezzoCane;
	
	public Cane(String nome, String presentaz, String ciboPreferito, Attrezzo attrezzoCane) {
		super(nome, presentaz);
		this.ciboPreferito=ciboPreferito;	
		this.attrezzoCane=attrezzoCane;
	}
	
	@Override
	public String agisci(Partita partita) {
		
		partita.setCfu((partita.getCfu()-1));
		return "Ti ho morso, ora hai " + partita.getCfu() + " CFU";
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		
		if(attrezzo.getNome().equals(ciboPreferito)) {
			
			partita.getStanzaCorrente().addAttrezzo(this.attrezzoCane);
			return "Il cane accetta il cibo e lascia cadere un attrezzo";
		} 
		else {
			partita.getStanzaCorrente().addAttrezzo(attrezzo);
			partita.setCfu(partita.getCfu() -1);
			return "Il cane non accetta il tuo regalo e ti morde";
		}
	}
}
