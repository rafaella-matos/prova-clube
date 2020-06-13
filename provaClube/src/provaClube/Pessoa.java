package provaClube;

import java.io.Serializable;

public class Pessoa implements Serializable {
	private static final long serialVersionUID = 4L;
	private String nome;
	public String getNome() { return this.nome; }

	public Pessoa(String nome) {
		this.nome = nome;
	}
}
