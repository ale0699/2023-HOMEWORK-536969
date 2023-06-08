package it.uniroma3.diadia.ambienti;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.Cane;
import it.uniroma3.diadia.personaggi.Mago;
import it.uniroma3.diadia.personaggi.Strega;

public class Labirinto {

	private Stanza stanzaIniziale;
	private Stanza stanzaVincente;
	

	public Labirinto(String nomeFile) {
		try {
			CaricatoreLabirinto caricatoreLabirinto = new CaricatoreLabirinto(nomeFile);
			caricatoreLabirinto.carica();
			this.stanzaIniziale = caricatoreLabirinto.getStanzaIniziale();
			this.stanzaVincente = caricatoreLabirinto.getStanzaVincente();
		} 
		catch (FileNotFoundException | FormatoFileNonValidoException e) {
			e.printStackTrace();
		}
	}
	
	public Labirinto() {

	}

	public static LabirintoBuilder newBuilder(String nomeFile) {
		return new LabirintoBuilder(nomeFile);
	}

	public void setStanzaIniziale(Stanza stanzaIniziale) {
		this.stanzaIniziale = stanzaIniziale;
	}

	public void setStanzaVincente(Stanza stanzaVincente) {
		this.stanzaVincente = stanzaVincente;
	}

	/**
	 * Restituisce la stanza iniziale del gioco
	 * 
	 * @return Stanza iniziale del gioco
	 */
	public Stanza getStanzaIniziale() {

		return this.stanzaIniziale;
	}

	/**
	 * Restituisce la stanza vincente del gioco
	 * 
	 * @return Stanza vincente del gioco
	 */
	public Stanza getStanzaVincente() {

		return this.stanzaVincente;
	}
	
	public static class LabirintoBuilder {
		
		private Labirinto labirinto;
		private Map<String,Stanza> stanze = new HashMap<>();
		private Stanza ultimaStanzaAggiunta;
		
		public LabirintoBuilder(String nomeFile) {
			this.labirinto = new Labirinto(nomeFile);
			this.stanze = new HashMap<String, Stanza>();
		}
		
		public LabirintoBuilder() {
			this.labirinto = new Labirinto();
			this.stanze = new HashMap<String, Stanza>();
		}
		
		public LabirintoBuilder addStanzaIniziale(String nomeStanza) {
			
			Stanza stanzaIniziale = new Stanza(nomeStanza);
			this.labirinto.setStanzaIniziale(stanzaIniziale);
			this.stanze.put(nomeStanza, stanzaIniziale);
			this.ultimaStanzaAggiunta = stanzaIniziale;
			return this;
		}
		
		public LabirintoBuilder addStanzaVincente(String nomeStanza) {
			
			Stanza stanzaVincente = new Stanza(nomeStanza);
			this.labirinto.setStanzaVincente(stanzaVincente);
			this.stanze.put(nomeStanza, stanzaVincente);
			this.ultimaStanzaAggiunta = stanzaVincente;
			return this;
		}
		
		public LabirintoBuilder addStanza(String nomeStanza) {
			
			Stanza stanza = new Stanza(nomeStanza);
			this.stanze.put(nomeStanza, stanza);
			this.ultimaStanzaAggiunta = stanza;
			return this;
		}
		
		public LabirintoBuilder addAttrezzo(String nomeAttrezzo, int pesoAttrezzo) {
			
			Attrezzo attrezzo = new Attrezzo(nomeAttrezzo, pesoAttrezzo);
			this.ultimaStanzaAggiunta.addAttrezzo(attrezzo);
			return this;
		}
		
		public LabirintoBuilder addAdiacenza(String stanza1, String stanza2, String direzione) {
			
			Stanza stanza_1 = this.stanze.get(stanza1);
			Stanza stanza_2 = this.stanze.get(stanza2);
			
			stanza_1.impostaStanzaAdiacente(direzione, stanza_2);
			return this;
		}
		
		public Labirinto getLabirinto() {
			return labirinto;
		}

		public Map<String, Stanza> getListaStanze() {
			return this.stanze;
		}

		public LabirintoBuilder addStanzaMagica(String nomeStanzaMagica, int sogliaMagica) {
			
			StanzaMagica stanzaMagica = new StanzaMagica(nomeStanzaMagica, sogliaMagica);
			this.stanze.put(nomeStanzaMagica, stanzaMagica);
			this.ultimaStanzaAggiunta=stanzaMagica;
			return this;
		}

		public LabirintoBuilder addStanzaBloccata(String nomeStanzaBloccata, String direzione, String nomeAttrezzo) {
			
			StanzaBloccata stanzaBloccata = new StanzaBloccata(nomeStanzaBloccata, direzione, nomeAttrezzo);
			this.stanze.put(nomeStanzaBloccata, stanzaBloccata);
			this.ultimaStanzaAggiunta=stanzaBloccata;
			return this;
		}

		public LabirintoBuilder addStanzaBuia(String nomeStanza, String nomeAttrezzo) {
			
			StanzaBuia stanzaBuia = new StanzaBuia(nomeStanza, nomeAttrezzo);
			this.stanze.put(nomeStanza, stanzaBuia);
			this.ultimaStanzaAggiunta=stanzaBuia;
			return this;
		}
		
		public LabirintoBuilder addCane(String nome, String presentaz, String ciboPreferito, Attrezzo attrezzoCane) {
			this.ultimaStanzaAggiunta.setPersonaggio(new Cane(nome, presentaz, ciboPreferito, attrezzoCane));
			return this;
		}
		
		public LabirintoBuilder addStrega(String nome, String presentaz ) {
			this.ultimaStanzaAggiunta.setPersonaggio(new Strega(nome, presentaz));
			return this;
		}
		
		public LabirintoBuilder addMago(String nome, String presentaz, Attrezzo daRegalare) {
			this.ultimaStanzaAggiunta.setPersonaggio(new Mago(nome, presentaz, daRegalare));
			return this;
		}
	}
}
