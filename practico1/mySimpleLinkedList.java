package practico1;



/*Implemente los métodos indicados del esqueleto de Lista desarrollado en Teoría:
(insertFront, extractFront, isEmpty, size, toString). Agregar también el método: T get(index) */

public class mySimpleLinkedList<T extends Comparable<T>> implements Iterable<T> {  //EXTENDER PARA HACER QUE LOS T SE COMPAREN ENTRE SI, SEA LO QUE SEA
																				   //Y NO HAY QUE IMPLEMENTAR EL METODO COMPARE TO
	private Node<T> first;  //DATO GENERICO T.
	private int size;
	private Node<T> last;
	
	public mySimpleLinkedList() {
		this.first = null;
		this.last = null;
		this.size = 0;
	}
	
	//HECHO POR LA CATEDRA
	
	public void insertFront(T info) {
		Node<T> tmp = new Node<T>(info, null); // Crea un nuevo nodo con el elemento info como dato y un enlace al siguiente nodo que se establece en null
		tmp.setNext(this.first); //El nuevo nodo se conecta al frente de la lista.			
		this.first = tmp;  //Actualiza la referencia this.first para que apunte al nuevo nodo tmp, convirtiéndolo en el nuevo nodo al frente de la lista
		this.size++;
	}
	
	//HECHO POR LA CATEDRA
	
	public T extractFront() {
		T output = null;
		if(this.first != null) {
			output = this.first.getInfo();   //DEVUELVO EL DATO, LA INFORMACION.
			this.first = this.first.getNext();
			this.size--;
		}
		return output;
	}
	
	//HECHO POR LA CATEDRA
	
	public T get(int index) {
		if(index < size) {
			Node <T> nodoAux = this.first;
			for(int i = 0; i<index; i++) {
				nodoAux = nodoAux.getNext();		
			}
			return nodoAux.getInfo();
		}
		return null;
	}
	
	//EJERCICIO 3 METODO INDEX OF(T).
	public int indexOf(T info) {
		Node <T> aux = this.first;
		for(int i= 0; i<this.size;i++) {
			if(info.equals(aux.getInfo())) {     // ->EL EQUALS ES DE T Y NO HAY QUE IMPLEMENTARLO, CADA  SABE SU EQUALS.
				return i;
			}
			else {
				aux = aux.getNext();
			}
		}
		return -1;
	}
	
	public void insertarOrdenado(T info) {
		if(this.first == null || (this.first.getInfo().compareTo(info)>0) ) {  //>0 el primero es mayor que el segundo osea info que me pasan!
			insertFront(info);
		}
		else {
			Node <T> nuevo = new Node<T>(info,null);
			Node <T> aux = this.first;
			while(aux.getNext()!= null && aux.getNext().getInfo().compareTo(info)<0) { // <0 el primero es menor que el segundo osea info que me pasan!
				aux = aux.getNext();
			}
			if(aux.getNext() == null) {
				aux.setNext(nuevo);
			}
			else {
				Node <T> temp = aux.getNext();
				aux.setNext(nuevo);
				nuevo.setNext(temp);
			}
			this.size++;  //NO OLVIDAR ACTUALIZAR EL SIZE NUNCA!! SINO ME TRAE ERRORES POSTERIORES.
		}
	}
	
	public void insertLast(T info) {
		Node<T> nuevo = new Node<T>(info, null);
		if(isEmpty()) {
			//insertFront(info); //ES O NO NECESARIO INSERTARLO AL FRENTE SI NO HAY ELEMENTOS?
			this.first = nuevo;
			this.last = nuevo;
		}
		else {
			Node<T>temp =this.last;
			temp.setNext(nuevo);
			this.last = nuevo;
			
		}
		size++; //NO OLVIDAR!!
	} 
	//MODIFICAR TODOS LOS METODOS QUE HAGAN CAMBIOS EN EL ULTIMO Y MANTENER SIEMPRE ACTUALIZADO EL ULTIMO. POR EJ: SI EXTRACT ULTIMO (EL ULTIMO SE VUELVE
	//EL ANTEULTIMO)
	
	public T last() {
		return this.last.getInfo();
	}
	
	public boolean isEmpty() {
		return this.first == null;
	}
	
	public int getSize() {
		return this.size;
	}
	
	
	
	@Override
	public String toString() {		
        Node<T> actual = this.first;
        Integer cont = 0;
        String retorno = "|";
        while (cont < this.getSize()){
            retorno += " " + actual.getInfo().toString() + " |";
            actual = actual.getNext();
            cont++;
        }
		return retorno;
	}
	 
	//DE myIterator porque usamos el get luego PUEDE SER ITERATOR<T> O MYITERATOR<T> LO QUE NECECITE
	@Override
	public myIterator<T> iterator() {    //TENGO QUE DEFINIR MI PROPIO ITERADOR EN UNA CLASE NUEVA.
		return new myIterator<T>(this.first);  //CREO UN ITERADOR Y LE PASO EL PRIMER NODO. Para que arranque a partir de ahi.
	}
	
}
