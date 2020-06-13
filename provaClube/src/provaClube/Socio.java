package provaClube;

import java.io.Serializable;

public class Socio extends Pessoa implements Serializable {
	private static final long serialVersionUID = 2L;
	private int numero;
	public int getNumero() { return this.numero; }
	
	private Carteira carteira;
	public Carteira getCarteira() { return this.carteira; }
	
	public Socio(int numeroSocio, int numeroCarteira, String nome) {
		super(nome);
		this.numero = numeroSocio;
		this.carteira = new Carteira(numeroCarteira, this);
	}
}
