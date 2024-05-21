package practico4Grafos;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;


public class GrafoDirigido<T> implements Grafo<T> {
	
	private HashMap<Integer, LinkedList<Arco<T>>> vertices;
	

	public GrafoDirigido() {
		this.vertices = new HashMap<Integer, LinkedList<Arco<T>>>();
	}

	@Override
	public void agregarVertice(int verticeId) {
		if(!contieneVertice(verticeId)) {
			vertices.put(verticeId, new LinkedList<Arco<T>>() ); //VerticeId es la clave y ahora el valor es la lista de arcos
		}																				
	}																					

	//BORRAR LOS QUE LLEGAN A ESE VERTICE, LOS QUE SON ADYACENTES NO ES NECESARIO PORQUE AL ELIMINAR EL VERTICE SE VAN TAMBIEN SUS ADYACENTES
	
	@Override
	public void borrarVertice(int verticeId) {
		if(contieneVertice(verticeId)) {			 //Borramos el vertice y se van tambien todos los arcos que salen de el
			vertices.remove(verticeId);				 
			Iterator<Integer> itVertices = obtenerVertices(); //Recorro todos los vertices usando el iterador de vertices que ya tengo
			while(itVertices.hasNext()) {
				int vertice1 = itVertices.next();
				borrarArco(vertice1, verticeId);  //Tengo que borrar todos los arcos que llegan a ese verticeId ya eliminado! 
			}									  //Si hay arco se van a borrar si no el borrarArco no va a hacer nada
			
		} //EL VERTICE TIENE QUE SABER SI EXISTE ARCO, YO MANDO A BORRAR
	}

	@Override
	public void agregarArco(int verticeId1, int verticeId2, T etiqueta) {
		
		if(contieneVertice(verticeId1) && contieneVertice(verticeId2)) { //Si existe arco no lo chequeamos aca, eso lo delegamos a vertice
			Arco<T> nuevoArco = new Arco<T>(verticeId1, verticeId2, etiqueta); //NO DELEGAMOS MAS A VERTICE PORQUE ESA CLASE SE FUE
			if(!vertices.get(verticeId1).contains(nuevoArco)) {
				vertices.get(verticeId1).add(nuevoArco); //Agregamos el arco a la lista de arcos del vertice con la clave del origen
			}
		}
	}

	/*@Override			FORMA ANTERIOR NO DELEGAMOS MAS A VERTICE PORQUE ESA CLASE SE FUE									
	public void borrarArco(int verticeId1, int verticeId2) {
		//if(existeArco(verticeId1, verticeId2)) { //No es necesario chequear si existe arco
			Vertice<T> v = vertices.get(verticeId1); //Si no esta no hace nada
		    v.borrarArco(verticeId2);   // delego a vertice, le mandamos el verticeDestino (mas optimo mandar el integer)
		}*/
	
	@Override												
	public void borrarArco(int verticeId1, int verticeId2) {
		if(contieneVertice(verticeId1)) {	    
			Arco<T> arco = obtenerArco(verticeId1, verticeId2);
			if(arco != null) { //Si hay arco lo borra
				vertices.get(verticeId1).remove(arco); 
			}
		}
	}
	
	@Override
	public boolean contieneVertice(int verticeId) {
		return vertices.containsKey(verticeId);	  //Si contiene la clave verticeId contiene el vertice!
	}

	@Override	
	public boolean existeArco(int verticeId1, int verticeId2) { //Existe arco sin repetir el codigo
		return obtenerArco(verticeId1, verticeId2) != null;
	}

	@Override
	public Arco<T> obtenerArco(int verticeId1, int verticeId2) {  //tengo que ver si esta el vertice de origen sino retorno null, no esta el arco
		if(contieneVertice(verticeId1)) {
			Iterator <Arco<T>> itA = obtenerArcos(verticeId1);  //Necesito retornar el arco por eso itero los arcos del vertice origen
			while( itA.hasNext()) {                             //usamos iterator de obtenerArcos
				Arco<T> arco = itA.next();
				if(arco.getVerticeDestino() == verticeId2) {
					return arco; 
				}
			}
		}
		return null;  // VA SIN ELSE PORQUE RETORNA EL ARCO O RETORNA EL NULO.
	}
		
	@Override
	public int cantidadVertices() {
		return vertices.size(); //El hashmap tiene el size().
	}

	@Override //COMPLEJIDAD O(Vertices)
	public int cantidadArcos() {  //preguntar si mantener actualizado cantidad arcos y vertices --> ES LO MISMO HACERLO O NO. TENES PRO Y CONTRAS DE LOS2
		int cant = 0;
		for(int i = 0; i<vertices.size();i++) {
			Iterator<Arco<T>> itArcos = obtenerArcos(i);
			while(itArcos.hasNext()) {
				itArcos.next();
				cant++;
			}  
		}								   
		return cant;
	}
	/* MEJOR FORMA PERO A MI ME CUESTA MAS
	public int cantidadArcos() { 
	    int cant = 0;
	    for (LinkedList<Arco<T>> listaArcos : vertices.values()) {
	        cant += listaArcos.size();
	    }
	    return cant;
	}*/ 

	//YO VOY A TENER MAS COMPLEJIDAD EN CANTIDAD ARCOS Y VERTICES.  !!!!!!
	//PERO TENGO MENOS COMPLEJIDAD EN EL ELIMINAR Y AGREGAR !!!!!!
	
	@Override
	public Iterator<Integer> obtenerVertices() {
	   return vertices.keySet().iterator();   //HASHMAP UTILIZA ITERATOR
	}
	
	@Override
	public Iterator<Integer> obtenerAdyacentes(int verticeId) {  //EN ESTE HAY UNA MEJOR FORMA DE HACER UN ITERADOR PROPIO
		LinkedList<Arco<T>>arcos = vertices.get(verticeId); //LISTA DE ARCOS DADA UNA CLAVE
		
		LinkedList<Integer> adyacentes = new LinkedList<Integer>();
		for(Arco<T>arco : arcos) {   //O(n) donde n es la cantidad de arcos salientes
			adyacentes.add(arco.getVerticeDestino());
		}
		return adyacentes.iterator();
	}

	@Override
	public Iterator<Arco<T>> obtenerArcos() { 
		LinkedList<Arco<T>> arcosTotales = new LinkedList<Arco<T>>(); //creo una lista de arcos
		Iterator<Integer> itVertices = obtenerVertices();
		while(itVertices.hasNext()) {
			Integer v = itVertices.next();
			LinkedList<Arco<T>> arcosParcial = vertices.get(v);
			arcosTotales.addAll(arcosParcial);
		}
		return arcosTotales.iterator();
	}

	@Override
	public Iterator<Arco<T>> obtenerArcos(int verticeId) {
		if(vertices.containsKey(verticeId)) {
			return vertices.get(verticeId).iterator();
		}
		return null; 
	}
	
}