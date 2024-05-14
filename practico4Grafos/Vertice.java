package practico4Grafos;

import java.util.LinkedList;


public class Vertice<T> {

	private int verticeId;
	private LinkedList<Arco<T>> arcos; //Adyacentes
	
	public Vertice( int verticeId) {  
		this.verticeId = verticeId;
		this.arcos = new LinkedList<Arco<T>>(); //Adyacentes al verticeId. //Consultar si inicializamos vacia. Porque en principio no tiene adyacentes
	}

	public int getVerticeId() {
		return verticeId;
	}

	public void setVerticeId(int verticeId) {
		this.verticeId = verticeId;
	}
	

	public LinkedList<Arco<T>> getArcos(){
		return new LinkedList<Arco<T>>(arcos);
	}
	
	//Retornamos una lista con los adyacentes de un vertice
	public LinkedList<Integer> obtenerAdyacentes(){
		LinkedList<Integer> lista = new LinkedList<Integer>();
		for(Arco<T> arco : arcos) {
			lista.add(arco.getVerticeDestino());
		}
		return lista;
	}
	
	//METODOS PARA AGREGAR Y BORRAR ARCOS ADYACENTES A UN VERTICE LOS HACEMOS BOOLEANOS PARA QUE SEAN MAS EFICIENTES
	
	//Agregamos agregar arco
	public boolean addArco(Arco<T> arco) { 
		if(arcos.contains(arco)) {        //Cada vertice va a saber si contiene arco
			return false;
		}
		arcos.add(arco);
		return true;
	} 
	
	public boolean borrarArco(Integer verticeDestino) {
		for(Arco<T> arco : arcos) {
			if(arco.getVerticeDestino() == verticeDestino) {
				arcos.remove(arco); //Borra y ademas retorna true.
			}
		}
		return false; //Si llega aca es porque no elimino ya que no estaba
	}
	
}
