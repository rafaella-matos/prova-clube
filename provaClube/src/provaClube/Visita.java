package provaClube;

import java.io.Serializable;
import java.util.Calendar;

public class Visita implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Calendar dataVisita;
	public Calendar getDataVisita() { return this.dataVisita; }
	
	private Pessoa frequentador;
	public Pessoa getFrequentador() { return this.frequentador; }
	
	public Visita(Carteira carteira) {
		this.frequentador = carteira.getSocio();
		this.dataVisita = Calendar.getInstance();
	}
	
	public Visita(Convite convite) {
		this.frequentador = convite.getVisitante();
		this.dataVisita = Calendar.getInstance();
	}
}
