package practico4Grafos;

import java.util.Iterator;
import java.util.LinkedList;

public class IteradorVertice<T> implements Iterator<Integer> {

	private Iterator<Vertice<T>> it;
	
	public IteradorVertice(LinkedList<Vertice<T>> lista) {
		this.it = lista.iterator();
	}

	@Override
	public boolean hasNext() {
		return it.hasNext();
	}

	@Override
	public Integer next() {
		return it.next().getVerticeId(); //No expone el objeto vertice sino el id del vertice.
	}
	
	
}
