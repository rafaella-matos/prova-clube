package provaClube;

import java.io.Serializable;

public class Visitante extends Pessoa implements Serializable {
	private static final long serialVersionUID = 3L;
	private Convite convite;
	public Convite getConvite() { return this.convite; }
	
	public Visitante(Convite convite, String nome) {
		super(nome);
		this.convite = convite;
	}
}
