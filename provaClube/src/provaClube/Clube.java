package provaClube;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Clube {
	private List<Visita> visitas;
	public List<Visita> getVisitas() { return this.visitas; }
	
	private List<Convite> convitesEmitidos;
	public List<Convite> getConvitesEmitidos() { return this.convitesEmitidos; }
	
	private List<Socio> socios;
	public List<Socio> getSocios() { return this.socios; }
	
	public Clube(List<Visita> visitas, List<Convite> convites, List<Socio> socios) {
		this.visitas = visitas;
		this.convitesEmitidos = convites;
		this.socios = socios;
	}
	
	public Convite emitirConvite(String nome, Calendar data) {
		Convite convite = new Convite(this.convitesEmitidos.size() + 1, nome, data);
		this.convitesEmitidos.add(convite);
		return convite;
	}
	
	public boolean registrarEntrada(Carteira carteira) {
		Socio registro = localizarSocio(carteira.getSocio().getNumero());
		if (registro == null) { return false; }
		this.visitas.add(new Visita(carteira));
		return true;
	}
	
	Socio localizarSocio(int numero) {
		Optional<Socio> localizado = this.socios.stream()
			.filter(x -> x.getNumero() == numero)
			.findFirst();
		
		if (localizado.isPresent()) { return localizado.get(); }
		else { return null; }
	}
	
	public boolean registrarEntrada(Convite convite, Calendar data) {
		Convite registro = localizarConvite(convite.getNumero(), data);
		if (registro == null) { return false; }
		this.visitas.add(new Visita(convite));
		return true;
	}
	
	Convite localizarConvite(int numero, Calendar data) {
		Optional<Convite> localizado = this.convitesEmitidos.stream()
			.filter(x -> x.getNumero() == numero 
					&& x.getDataVisita().get(Calendar.YEAR) == data.get(Calendar.YEAR)
					&& x.getDataVisita().get(Calendar.MONTH) == data.get(Calendar.MONTH)
					&& x.getDataVisita().get(Calendar.DAY_OF_MONTH) == data.get(Calendar.DAY_OF_MONTH))
			.findFirst();
		
		if (localizado.isPresent()) { return localizado.get(); }
		else { return null; }
	}
	
	public int contarTodosFrequentadores(Calendar data) {
		return contarSocios(data) + contarVisitantes(data);
	}
	
	public List<Socio> obterSocios(Calendar data) {
		return visitasDoDia(data)
			.filter(x -> x.getFrequentador() instanceof Socio)
			.map(x -> (Socio)x.getFrequentador())
			.collect(Collectors.toList());
	}
	
	public List<Visitante> obterVisitantes(Calendar data) {
		return visitasDoDia(data)
			.filter(x -> x.getFrequentador() instanceof Visitante)
			.map(x -> (Visitante)x.getFrequentador())
			.collect(Collectors.toList());
	}
	
	public int contarSocios(Calendar data) {
		return obterSocios(data)
				.stream()
				.mapToInt(x -> 1)
				.sum();
	}
	
	public int contarVisitantes(Calendar data) {
		return obterVisitantes(data)
				.stream()
				.mapToInt(x -> 1)
				.sum();
	}
	
	public Stream<Visita> visitasDoDia(Calendar data) {
		return this.visitas
			.stream()
			.filter(x -> {
				return x.getDataVisita().get(Calendar.YEAR) == data.get(Calendar.YEAR)
						&& x.getDataVisita().get(Calendar.MONTH) == data.get(Calendar.MONTH)
						&& x.getDataVisita().get(Calendar.DAY_OF_MONTH) == data.get(Calendar.DAY_OF_MONTH);
			});
	}
}
