package it.uniroma3.diadia.ambienti;

public class StanzaBuia extends Stanza{
	
	private String nomeAttrezzoCheIllumina;
	
	public StanzaBuia(String nome, String nomeAttrezzo) {
		super(nome);
		this.nomeAttrezzoCheIllumina = nomeAttrezzo;
	}
	
	@Override
	public String getDescrizione() {
		if(!super.hasAttrezzo(this.nomeAttrezzoCheIllumina)) {
			
			return "qui c'è buio pesto";
		}
		return super.getDescrizione();
	}
}
