package it.uniroma3.diadia.ambienti;

public class StanzaBloccata extends Stanza{
	
	private Direzione direzioneBloccata;
	private String nomeAttrezzoPerSbloccare;
	
	public StanzaBloccata(String nome, String direzione, String nomeAttrezzo) {
		super(nome);
		this.direzioneBloccata = Direzione.valueOf(direzione);
		this.nomeAttrezzoPerSbloccare = nomeAttrezzo;
	}
	
	@Override
	public Stanza getStanzaAdiacente(String direzione) {
		
		if(!super.hasAttrezzo(nomeAttrezzoPerSbloccare) && direzione.equals(this.direzioneBloccata.toString())) {
			
			return this;
		}
		
		return super.getStanzaAdiacente(direzione);
	}
	
	@Override
	public String getDescrizione() {
		
		if(!super.hasAttrezzo(nomeAttrezzoPerSbloccare)) {
			
			return ("Stanza bloccata nella direzione " +this.direzioneBloccata +  ", posa l'attrezzo " + this.nomeAttrezzoPerSbloccare + " per sbloccare la direzione");
		}
		
		return super.getDescrizione();
	}

	
}
