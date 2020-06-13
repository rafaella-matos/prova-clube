package provaClube;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Aplicacao {
	static Clube clube;
	
	static Apoio<Visita> apoioVisita;
	static Apoio<Convite> apoioConvite;
	static Apoio<Socio> apoioSocio;
	
	public static int main(String[] args) {
		try {
			apoioVisita = new Apoio<Visita>("visita.dat");
			apoioConvite = new Apoio<Convite>("convite.dat");
			apoioSocio = new Apoio<Socio>("socio.dat");
			
			clube = new Clube(apoioVisita.carregar(), apoioConvite.carregar(), apoioSocio.carregar());
		} catch (ClassNotFoundException | IOException e) {
			System.out.println("Falha ao inicializar o sistema");
			e.printStackTrace();
			return 1;
		}
		
		int opcao = -1;
		while(opcao != 9)
		{
			opcao = menuGeral();
			
			if (opcao == 1) {
				registrarVisitaSocio();
				opcao = -1;
			} else if (opcao == 2) {
				emitirConvite();
				opcao = -1;
			} else if (opcao == 3) {
				registrarVisitante();
				opcao = -1;
			} else if (opcao == 4) {
				contarVisitantes();
				opcao = -1;
			} else if (opcao == 5) {
				contarFrequentadores();
				opcao = -1;
			}
		}
		
		try {
			apoioVisita.salvar(clube.getVisitas());
			apoioConvite.salvar(clube.getConvitesEmitidos());
			apoioSocio.salvar(clube.getSocios());
		} catch (ClassNotFoundException | IOException e) {
			System.out.println("Falha ao gravar os dados do sistema");
			e.printStackTrace();
			return 1;
		}
		
		return 0;
	}
	
	public static int menuGeral() {
		int entrada = -1;
		
		while(entrada == -1) {
			System.out.println("Selecione uma opcao:");
			System.out.println("---------------------");
			System.out.println("1 - Entrada de socio");
			System.out.println("---------------------");
			System.out.println("2 - Emissao de convite");
			System.out.println("3 - Entrada de visitante");
			System.out.println("---------------------");
			System.out.println("4 - Contagem de visitantes");
			System.out.println("5 - Contagem total de frequentadores");
			System.out.println("---------------------");
			System.out.println("9 - SAIR");
			
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			
			try { entrada = Integer.parseInt(in.readLine()); }
			catch(Exception erro) { entrada = -1; }
		}
		
		return entrada;
	}
	
	public static void registrarVisitaSocio() {
		boolean valido = false;
		int carteira = 0;
		while(!valido) {
			try {
				System.out.println("Informe a carteira do sócio: ");
				BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
				carteira = Integer.parseInt(in.readLine());
				valido = true;
			} catch (Exception erro) {
				valido = false;
			}
			
		}
		
		Socio socio = clube.localizarSocio(carteira);
		if (socio == null) {
			System.out.println("Sócio não localizado!");
		} else {
			if (clube.registrarEntrada(socio.getCarteira())) {
				System.out.println("Sócio liberado");
			} else {
				System.out.println("Sócio inválido");
			}
		}
	}
	
	public static void emitirConvite() {
		boolean valido = false;
		int numeroConvite = 0;
		String nome = "";
		while(!valido) {
			try {
				System.out.println("Informe o nome do visitante: ");
				BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
				nome = in.readLine();
				
				Calendar data = solicitarData();
				
				numeroConvite = clube.emitirConvite(nome, data)
						.getNumero();
				
				valido = true;
			} catch (Exception erro) {
				valido = false;
			}
			
		}
		
		System.out.println("Convite " + numeroConvite + " emitido.");
	}
	
	public static void contarVisitantes() {
		Calendar data = solicitarData();		
		System.out.println("Foram registrados " + clube.contarVisitantes(data) + " visitantes na data informada.");
	}
	
	public static void contarFrequentadores() {
		Calendar data = solicitarData();		
		System.out.println("Foram registrados " + clube.contarTodosFrequentadores(data) + " frequentadores totais (socios + visitantes) na data informada.");
	}
	
	public static void registrarVisitante() {
		boolean valido = false;
		int numeroConvite = 0;
		while(!valido) {
			try {
				System.out.println("Informe o número do convite: ");
				BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
				numeroConvite = Integer.parseInt(in.readLine());
				valido = true;
			} catch (Exception erro) {
				valido = false;
			}
			
		}
		
		Convite convite = clube.localizarConvite(numeroConvite, Calendar.getInstance());
		if (convite == null) {
			System.out.println("Convite não localizado!");
		} else {
			if (clube.registrarEntrada(convite, Calendar.getInstance())) {
				System.out.println("Visitante liberado");
			} else {
				System.out.println("Visitante inválido");
			}
		}
	}
	
	public static Calendar solicitarData() {
		Calendar instancia = null;
		
		while(instancia == null) {
			try {
				System.out.println("Informe a data (dd/MM/yyyy):");
				BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
				String data = in.readLine();
				Date d= new SimpleDateFormat("dd/MM/yyyy").parse(data);
				Calendar cal = Calendar.getInstance();
				cal.setTime(d);
				instancia = cal;
			}
			catch (Exception erro) {
				instancia = null;
			}
		}
		
		return instancia;
	}
}
