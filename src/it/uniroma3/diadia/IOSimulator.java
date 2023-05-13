package it.uniroma3.diadia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class IOSimulator implements IO {
	
	private List<String> righeDaLeggere;
	private int indiceRigheLette;
	
	private List<String> messaggiProdotti;
	private int indiceMessaggiProdotti;
	private int indiceMessaggiMostrati;

	//se volessimo tener traccia per ogni riga letta i messaggi prodotti potrebbe essere
	//opportuno optare per una mappa ad esempio Map<String,List<String>> righe2messaggiProdottiDallaRiga
	private Map<String,List<String>> riga2messaggiProdottiDallaRiga;

	public IOSimulator(List<String> righeDaLeggere) {
		
		this.righeDaLeggere = righeDaLeggere;
		this.indiceRigheLette = 0;
		
		this.riga2messaggiProdottiDallaRiga = new HashMap<>();
		
		this.messaggiProdotti = new ArrayList<>();
		this.indiceMessaggiProdotti = 0;
		this.indiceMessaggiMostrati = 0;
	}

	@Override
	public void mostraMessaggio(String messaggio) {
		
		if(this.indiceMessaggiProdotti==0) {
			
			List<String> lista = new ArrayList<>();
			lista.add(messaggio);
			this.riga2messaggiProdottiDallaRiga.put("Messaggio Benvenuto", lista);
		}
		else {
			
			String prova = this.righeDaLeggere.get(indiceRigheLette-1);
			this.riga2messaggiProdottiDallaRiga.get(prova).add(messaggio);
		}
		
		this.messaggiProdotti.add(this.indiceMessaggiProdotti,messaggio);
		this.indiceMessaggiProdotti++;
	}

	@Override
	public String leggiRiga() {
		
		String riga = this.righeDaLeggere.get(indiceRigheLette);
		this.indiceRigheLette++;
		
		this.riga2messaggiProdottiDallaRiga.put(riga, new ArrayList<String>());
		return riga;
	}

	public String nextMessaggio() {
		
		String next = this.messaggiProdotti.get(indiceMessaggiMostrati);
		this.indiceMessaggiMostrati++;
		return next;
	}

	public boolean hasNextMessaggio() {
		
		return this.indiceMessaggiMostrati < this.indiceMessaggiProdotti;
	}
	
	public Map<String, List<String>> getRiga2messaggiProdottiDallaRiga() {
		return riga2messaggiProdottiDallaRiga;
	}

}