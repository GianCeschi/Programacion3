package practico1;


//AL ESTAR ORDENADAS LAS LISTAS PUEDO USAR ITERADOR PARA LAS DOS Y LA COMPLEJIDAD ES MUCHO MEJOR!

public class MainEjercicio5B {

	public static void main(String[] args) {
		mySimpleLinkedList <Integer> lista1 = new mySimpleLinkedList<Integer>();  //CREAMOS LA LISTA DE INTEGERS Y EL ITERATOR DEL MISMO TIPO
		mySimpleLinkedList <Integer> lista2 = new mySimpleLinkedList<Integer>();
		//mySimpleLinkedList <Integer> listaRes = new mySimpleLinkedList<Integer>();
		
		//SI SON LISTAS ORDENADAS INSERTAR ORDENADO!! ESTABA INSERTANDO MAL (DE MAYOR A MENOR)
		lista1.insertFront(15); 
		lista1.insertFront(7);
		lista1.insertFront(6);
		lista1.insertFront(1);
		
		lista2.insertFront(17);
		lista2.insertFront(7);
		lista2.insertFront(6);
		lista2.insertFront(1);
		 
		mySimpleLinkedList<Integer> listaRes = elementosComunesListasOrdenadas(lista1, lista2);
		System.out.println("Lista resultante ordenada" + listaRes);
		
		/*for (int i = 0; i < listaRes.getSize(); i++) {
			System.out.println(listaRes.get(i)); 
		}*/ //PARA IMPRIMIR LISTA RESULTANTE, PREGUNTAR FORMA CORRECTA! METODO EN LISTA QUE RETORNE UNA LISTA RESULTANTE, COMO LO HAGO??

		//SI DE UNA LISTA LLEGA AL FINAL YA NO COMPARO MAS PORQUE SI NO SON IGUALES AL SER ORDENADA NO ESTA.
				

	}
	
	//LO HICE FUNCION NO PROCEDIMIENTO.
	public static mySimpleLinkedList<Integer> elementosComunesListasOrdenadas(mySimpleLinkedList<Integer>lista1, mySimpleLinkedList<Integer>lista2){

		mySimpleLinkedList<Integer> listaRes = new mySimpleLinkedList<Integer>();	
		//CONSULTAR PORQUE NO ME DEJA USAR ITERATOR() --> ME DABA ERROR PORQUE EN mYSimpleLinkedList tenia public iterator y no myIterator en el override.
		myIterator<Integer>it1 = lista1.iterator();
		myIterator<Integer>it2 = lista2.iterator();


		while(it1.hasNext() && it2.hasNext()) {
			Integer val1 = it1.get();
			Integer val2 = it2.get();
			if(val1 == val2) {
				listaRes.insertLast(val1); //INSERTAMOS ULTIMO PARA QUE SE MANTENGA ORDENADA LA LISTA.
				it1.next();
				it2.next();
			//System.out.println("["+val1+"]"); //CONSULTAR SI HAY PODEMOS IMPRIMIR ASI LA RESULTANTE?? OSEA SI EL MAIN NO LE DAN IMPORTANCIA LOS PROFES
			}
			else if(val1 > val2) {
				it2.next();
			}
			else {
				it1.next();
			}
		}
		return listaRes;
	}

}
