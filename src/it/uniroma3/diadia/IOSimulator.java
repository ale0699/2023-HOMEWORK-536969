package it.uniroma3.diadia;

public class IOSimulator implements IO {

	private String[] righeDaLeggere;
	private int indiceRigheLette;
	private String[] messaggiProdotti;
	private int indiceMessaggiProdotti;
	private int indiceMessaggiMostrati;
	
	public IOSimulator(String[] righeDaLeggere) {
		this.righeDaLeggere = righeDaLeggere;
		this.indiceRigheLette = 0;
		
		this.messaggiProdotti = new String[50];
		this.indiceMessaggiProdotti = 0;
		this.indiceMessaggiMostrati = 0;
	}

	@Override
	public void mostraMessaggio(String messaggio) {
		this.messaggiProdotti[indiceMessaggiProdotti] = messaggio;
		this.indiceMessaggiProdotti++;
	}

	@Override
	public String leggiRiga() {
		String riga = null;
		riga = this.righeDaLeggere[indiceRigheLette];
		this.indiceRigheLette++;
		return riga;
	}

	public String nextMessaggio() {
		String next = this.messaggiProdotti[this.indiceMessaggiMostrati];
		this.indiceMessaggiMostrati++;
		return next;
	}

	public boolean hasNextMessaggio() {
		return this.indiceMessaggiMostrati < this.indiceMessaggiProdotti;
	}

}