package provaClube;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Apoio<T> {
	String arquivo;
	
	public Apoio(String arquivo) {
		this.arquivo = arquivo;
	}
	
	public List<T> carregar() throws IOException, ClassNotFoundException {
		FileInputStream str = new FileInputStream(this.arquivo);
		ObjectInputStream leitor = new ObjectInputStream(str);
		
		Object leitura = leitor.readObject();
		ArrayList<T> visitas = new ArrayList<T>();
		while(leitura != null) {
			visitas.add((T)leitura);
			leitura = leitor.readObject();
		}
		
		leitor.close();
		
		return visitas;
	}
	
	public void salvar(List<T> itens) throws IOException, ClassNotFoundException {
		FileOutputStream str = new FileOutputStream(this.arquivo);
		ObjectOutputStream gravador = new ObjectOutputStream(str);
		
		for(T item: itens) {
			gravador.writeObject(item);
		}
		
		gravador.close();
	}
}
