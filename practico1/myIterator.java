package practico1;

import java.util.Iterator;

public class myIterator<T> implements Iterator<T>{

	private Node<T> cursor;
	
	public myIterator(Node<T> cursor) {  //DEBE ARRANCAR SIEMPRE EN EL PRIMER NODO.
		this.cursor = cursor;
	}
	
	//hasNext nos dice si hay un proximo nodo.
	@Override
	public boolean hasNext() {
		return this.cursor != null;  //TIENE QUE CHEQUEAR QUE EL PRIMERO NO SEA NULL. SI NO APUNTO A NULL QUIERE DECIR QUE HAY ALGO MAS.
	}

	//Me da info de ese nodo y avanza al siguiente
	@Override
	public T next() {
		T info = this.cursor.getInfo();
		this.cursor = this.cursor.getNext();
		return info;
	}
	
	//ESTE METODO SE CREA PARA QUE ME DEVUELVA LA INFO DE ESE NODO Y NO AVANCE.
	public T get() {
		return this.cursor.getInfo();
	}

	
}
