package provaClube;

import java.io.Serializable;

public class Carteira implements Serializable {
	private static final long serialVersionUID = 6L;
	private int numero;
	public int getNumero() { return this.numero; }
	
	private Socio socio;
	public Socio getSocio() { return this.socio; }
	
	public Carteira(int numero, Socio socio) {
		this.numero = numero;
		this.socio = socio;
	}
}
