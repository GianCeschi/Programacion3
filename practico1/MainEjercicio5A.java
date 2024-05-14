package practico1;

import java.util.Iterator;

//AL ESTAR LAS LISTAS DESORDENADAS HAY UNA QUE VOY A TENER QUE RECORRER TODA SI O SI, Y EN LA OTRA USO UN ITERADOR!

public class MainEjercicio5A {

	public static void main(String[] args) {
		mySimpleLinkedList <Integer> lista1 = new mySimpleLinkedList<Integer>();  //CREAMOS LA LISTA DE INTEGERS Y EL ITERATOR DEL MISMO TIPO
		mySimpleLinkedList <Integer> lista2 = new mySimpleLinkedList<Integer>();
		//mySimpleLinkedList <Integer> listaRes = new mySimpleLinkedList<Integer>();
		
		lista1.insertFront(25);
		lista1.insertFront(3);  //SI CAMBIO EL 1 POR EL 25 FUNCIONA!! PORQUE?
		lista1.insertFront(2);
		lista1.insertFront(1);
		
		lista2.insertFront(5);
		lista2.insertFront(1);
		lista2.insertFront(25);
		lista2.insertFront(3);
		
	/*	for(int i = 0; i< lista.getSize(); i++) { //O(size) --> O(n).
			Object info = lista.get(i);   //METODO GET INDICE. Y ESE METODO TIENE UNA COMPLEJIDAD O(size) en el peor de los casos
			System.out.println(info);
		}
		//COMPLEJIDAD DE ESTE FOR ENTONCES O(size*2) al cuadrado
	*/
	 //------------------------------------------------------------------ 
	 //ES LO MISMO QUE LO DE ARRIBA PERO UNA MEJOR PRACTICA	
	 /* Iterator<Object> it = lista.iterator();
	  while(it.hasNext()) {                    //O(size)  --         MIENTRAS ITERADOR TENGA UN SIGUIENTE.
		  Object info = it.next();             //O(1)  Avanzo una vez, es atomica, avanzo al siguiente, no arranco desde el principio
		  System.out.println(info);
	  }*/
	  //COMPLEJIDAD DE ESTE ES SOLO O(n).
	  
	  /*FOR EACH, HACE LO MISMO QUE EL WHILE      ME AHORRO DE USAR hasNext y next!!
	   *  for(Object info: lista){
	   *  		System.out.println(info);
	   *  }
	   * */
	  
	  /*Forma de pablo
	  Iterator<Integer>it1 = lista1.iterator();  //POR LO TANTO EL ITERADOR TAMB INTEGER PARA PROBAR
	  boolean encontro;
	  while(it1.hasNext()) {
		  encontro = false;
		  Integer valor1 = it1.next();
		  Iterator<Integer>it2 = lista2.iterator();
		  while(it2.hasNext() && !encontro) {
			  Integer valor2 = it2.next();
			  if(valor1 == valor2) {
				  listaRes.insertarOrdenado(valor1);
				  encontro = true;
			  }
		  }
	  }*/
	  
	  //FORMA GIAN
	  /*Escriba un procedimiento que dadas dos listas construya otra con los elementos comunes,
		suponiendo que: a) Las listas están desordenadas y la lista resultante debe quedar ordenada.*/
		
		mySimpleLinkedList <Integer> listaRes = elementosComunesListasDesordenadas(lista1, lista2); //HAY QUE HACER ESTO O DIRECTAMENTE O UN SYSO LA FUNCION?
		
	    System.out.println("lista resultante ordenada: " + listaRes);
	}
	
	public static mySimpleLinkedList<Integer> elementosComunesListasDesordenadas(mySimpleLinkedList<Integer> lista1, mySimpleLinkedList<Integer> lista2) {
		mySimpleLinkedList <Integer> listaRes = new mySimpleLinkedList<Integer>();
		
		boolean encontro;
		
		for(Integer info1 : lista1) { //UTILIZO FOR EACH PORQUE ESTOY SEGURO QUE LA LISTA 1 LA TENGO QUE RECORRER COMPLETA
			encontro = false;
			Iterator<Integer>it2 = lista2.iterator();
			while(it2.hasNext()&& !encontro) {    //SI LO ENCONTRO CORTO EL WHILE Y SIGO POR LA PRIMER LISTA.
				Integer info2 = it2.next();  
				System.out.println(info1);
				System.out.println(info2);
				if(info1 == info2) {
					
					listaRes.insertarOrdenado(info1); 
					encontro = true;
					System.out.println(listaRes);
				}
			}
		}
		
		return listaRes;
	}
}
