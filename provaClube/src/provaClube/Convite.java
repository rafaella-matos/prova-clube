package provaClube;

import java.io.Serializable;
import java.util.Calendar;

public class Convite implements Serializable {
	private static final long serialVersionUID = 5L;
	private int numero;
	public int getNumero() { return this.numero; }
	
	private Visitante visitante;
	public Visitante getVisitante() { return this.visitante; }
	
	private Calendar dataVisita;
	public Calendar getDataVisita() { return this.dataVisita; }
	
	public Convite(int numero, String nome, Calendar dataVisita) {
		this.numero = numero;
		this.dataVisita = dataVisita;
		this.visitante = new Visitante(this, nome);
	}
}
