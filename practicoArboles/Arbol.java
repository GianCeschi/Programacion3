package practicoArboles;

import java.util.ArrayList;

public class Arbol {


		private ArbolNodo raiz;
		
		
		public Arbol() {
			this.raiz = null;
			
		}
		
		//ADD SI TENGO UN PUNTERO AL ANTERIOR, TENGO QUE IR ACTUALIZANDOLO SIEMPRE!
		
		public void add(Integer valor) {
			if (this.raiz == null)
				this.raiz = new ArbolNodo(valor);
			else
				this.add(this.raiz,valor);
		}
		
		private void add(ArbolNodo actual, Integer valor) {
			if (actual.getValor() > valor) {
				if (actual.getIzq() == null) { 
					ArbolNodo temp = new ArbolNodo(valor);
					actual.setIzq(temp);
					temp.setAnterior(actual); //Le seteo el anterior
				} else {
					add(actual.getIzq(),valor);
				}
			} else if (actual.getValor() < valor) {
				if (actual.getDer() == null) { 
					ArbolNodo temp = new ArbolNodo(valor);
					actual.setDer(temp);
					temp.setAnterior(actual); //Le seteo el anterior
				} else {
					add(actual.getDer(),valor);
				}
			}
		}
		/*
		public void add(Integer valor) {
			if (this.raiz == null)
				this.raiz = new ArbolNodo(valor);
			else
				this.add(this.raiz,valor);
		}
		
		private void add(ArbolNodo actual, Integer valor) {
			if (actual.getValor() > valor) {
				if (actual.getIzq() == null) { 
					ArbolNodo temp = new ArbolNodo(valor);
					actual.setIzq(temp);
				} else {
					add(actual.getIzq(),valor);
				}
			} else if (actual.getValor() < valor) {
				if (actual.getDer() == null) { 
					ArbolNodo temp = new ArbolNodo(valor);
					actual.setDer(temp);
				} else {
					add(actual.getDer(),valor);
				}
			}
		}*/
		
		public int getAltura() {
			if(this.raiz == null) {
				return 0;
			}
			else return this.getAltura(this.raiz);
		}
		
		private int getAltura(ArbolNodo nodo) {
			if(nodo.getIzq()==null && nodo.getDer()==null) {
				return 0;
			}
			else {
				int alturaIzquierda = 0;
				int alturaDerecha = 0;
				if(nodo.getIzq() != null) {
					alturaIzquierda = getAltura(nodo.getIzq()) + 1;
				}
				if(nodo.getDer() != null) {
					alturaDerecha = getAltura(nodo.getDer()) + 1;
				}
				if(alturaIzquierda >= alturaDerecha) {
					return alturaIzquierda;
				}
				else {
					return alturaDerecha;
				}
			}
		}
		
		public ArrayList<Integer> getLongestBranch(){
			ArrayList<Integer> resultado = new ArrayList<Integer>();
			if (this.raiz != null){
				resultado.addAll( getLongestBranch(this.raiz));
			}
			return resultado;
		}

		private ArrayList<Integer> getLongestBranch(ArbolNodo nodo) {
			ArrayList<Integer> resParcial = new ArrayList<Integer>();
			resParcial.add(nodo.getValor());
			if(nodo.getDer()==null && nodo.getIzq()==null){
				return resParcial;
			}
			else {
				ArrayList<Integer> resParcialDer = new ArrayList<Integer>();
				ArrayList<Integer> resParcialIzq = new ArrayList<Integer>();
				//CREO LAS LISTAS ACA PORQUE NO LAS PUEDO CREAR DENTRO DE LOS BLOQUES IF PORQUE NO LAS VA A CONOCER DE AFUERA
				if(nodo.getDer() != null){
					resParcialDer = getLongestBranch(nodo.getDer());	   
				} else {
					resParcialIzq = getLongestBranch(nodo.getIzq());
				}	
				if (resParcialDer.size() > resParcialIzq.size()) {
					resParcial.addAll(resParcialDer);
				}
				else {
					resParcial.addAll(resParcialIzq);
				}
			}

			return resParcial;

		}
		
		
		public ArrayList<Integer> getElementLevel(int valor){
			ArrayList<Integer>resultado = new ArrayList<Integer>();
			if(this.raiz !=null) {
				resultado.addAll(getElementLevel(this.raiz,valor));
			}
			return resultado;
		}
		
		private ArrayList<Integer>getElementLevel(ArbolNodo nodo, int valor){
			ArrayList<Integer>resultado = new ArrayList<Integer>();

			if(valor == 0) {  //Condicion de corte
				resultado.add(nodo.getValor());
			}
			else {
				if(nodo.getIzq()!=null) {
					resultado.addAll(getElementLevel(nodo.getIzq(), valor - 1)); //para llevar el valor a 0 y entraria al primer if
				}
				if(nodo.getDer()!= null) {
					resultado.addAll(getElementLevel(nodo.getDer(), valor -1)); //no olvidar el resultado.addAll
				}
			}
			return resultado;
		}
		
		public ArrayList<Integer>getFrontera(){
			ArrayList<Integer>resultado = new ArrayList<Integer>();
			if(this.raiz != null) {
				resultado.addAll(getFrontera(this.raiz));
			}

			return resultado ;

		}
		
		private ArrayList<Integer>getFrontera(ArbolNodo nodo){
			ArrayList<Integer>resultado = new ArrayList<Integer>();
			if(nodo.getIzq()==null && nodo.getDer()==null) {
				resultado.add(nodo.getValor());
			}
			else {
				ArrayList<Integer>resultadoParcialIzquierda = new ArrayList<Integer>();
				ArrayList<Integer>resultadoParcialDerecha = new ArrayList<Integer>();
				
				if(nodo.getIzq()!= null) {
					resultadoParcialIzquierda = getFrontera(nodo.getIzq());
				}
				if(nodo.getDer()!=null) {
					resultadoParcialDerecha = getFrontera(nodo.getDer());
				}
				resultado.addAll(resultadoParcialIzquierda); //PORQUE SI YO COMENTO ESTO NO ME DA EL 7 Y ME DA SOLO EL 12, SI TENDRIA QUE DAR TODOS DER
				resultado.addAll(resultadoParcialDerecha); //7 NO SERIA UN NODO DERECHO DE LA RAIZ 
			}
			return resultado;
		}
		
		
		
		public boolean delete(int valor) {
			if(this.raiz == null) {
				return false;
			}
			else return delete(this.raiz, valor);
		}
		
		private boolean delete(ArbolNodo nodo, int valor) {
			if(nodo.getIzq()== null && nodo.getDer()== null) {
				if(valor == nodo.getValor()) {
					if(nodo.getAnterior()==null) { //La hoja es una raiz, si no tengo en cuenta null--->null y explota todo
						this.raiz = null; //LA RAIZ SE VUELVE NULA!! NO el nodo, el nodo es otro objeto que apunta a null, la raiz apunta a null
						
						
						
						
						
					}
					else {
						if( nodo.getAnterior().getValor()>valor) {  //Tengo que ver de que lado esta la hoja.
							nodo.getAnterior().setIzq(null);
						}
						else {
							nodo.getAnterior().setDer(null);
						}
					}
					return true; //Se elimino el valor
				}
				else {
					return false;//No esta el valor
				}
			}

			if(valor< nodo.getValor() && nodo.getIzq() != null) {  //Chequeo que el valor a eliminar este en la izquierda y haya subarbol.
				return delete(nodo.getIzq(), valor);
			}
			else if(valor> nodo.getValor() && nodo.getDer() != null) {  //Chequeo que el valor a eliminar este en la derecha y haya subarbol.
				return delete(nodo.getDer(),valor);
			}

			//CREO QUE EN ESTOS DOS IF NO ES NECESARIO CHEQUEAR SI EL VALOR ES IGUAL, PORQUE YA HUBIESE ENTRADO EN EL < o > 

			//Chequeo de nodo con dos hijos:

			if(valor == nodo.getValor() && nodo.getIzq()!= null && nodo.getDer()!= null) {
				int nmd ;

				//Hay subarbol izquierdo elijo ese para buscar el NMD, //SOLO CHEQUEAR UNA FORMA ELIJO NMD

				nmd = getMaxElement(nodo.getIzq()); //Le paso el nodo izquierdo de la raiz para que busque el mayor del subarbol izquierdo
				nodo.setValor(nmd); //hacer setValor(); no cambiar variables porque no estaria seteando el valor del nodo
				delete(nodo.getIzq(),nmd); //Tengo que hacer el delete del nmd.

				return true;
			}

			//Chequeo de nodo con un solo hijo:

			else if(valor == nodo.getValor() && (nodo.getIzq()!= null || nodo.getDer()!=null) ) {  //SI LOS DOS HIJOS SON NULOS VA A ENTRAR AL PRIMER IF
				if(nodo.getIzq()!=null) {                                                       //SI LOS DOS HIJOS NO SON NULOS ENTRA AL CHEQUEO ANTERIOR
					if(nodo.getAnterior().getValor()>valor) {
						nodo.getAnterior().setIzq(nodo.getIzq());  //El anterior apunta al hijo izquierdo
					}
					else {
						nodo.getAnterior().setDer(nodo.getIzq());  //El anterior apunta al hijo izquierdo !!
					}
				}
				else {
					if(nodo.getAnterior().getValor()>valor) {
						nodo.getAnterior().setIzq(nodo.getDer());  //El anterior apunta al hijo derecho
					}
					else {
						nodo.getAnterior().setDer(nodo.getDer());  //El anterior apunta al hijo derecho !!
					}
				}

				return true;
			}

			else {
				return false; //No encontro valor para eliminar
			}

		}
		//Al hacer el delete(nodo.getIzq(),nmd) va a ser el caso 1 que es una hoja, o el caso 2 que tiene un solo hijo
		
		
		
		//SUMATORIA DE NODOS INTERNOS DE UN ARBOL
		public Integer sumatoriaNodosInternos() {
		    if (this.raiz != null) {
		        return sumatoriaNodosInternos(this.raiz);
		    } else {
		        return null; // Si el método es tipo int no se puede retornar null
		    }
		}

		
		
		private Integer sumatoriaNodosInternos(ArbolNodo nodo) {
			int sumatoria = 0;
			if (nodo.getIzq() != null || nodo.getDer() != null) {
				sumatoria += nodo.getValor();
			}
			if (nodo.getIzq() != null) {
				sumatoria += sumatoriaNodosInternos(nodo.getIzq()); //No olvidar sumatoria += recursion...
			}
			if (nodo.getDer() != null) {
				sumatoria += sumatoriaNodosInternos(nodo.getDer());
			}
			
			//NO RETORNAR NULL!!! RETORNA 0 SI SOLO TIENE LA RAIZ!!
			
			return sumatoria;
		}

		//Tengo que chequear si es una raiz sin hijos tiene que dar su valor
		
		//Lista con mayores que K  ESTA BIEN!!!

		public ArrayList<Integer>getMayoresK(int k){
			ArrayList<Integer>resultado = new ArrayList<Integer>();
			if(this.raiz != null) {
				resultado.addAll(getMayoresK(this.raiz, k));
			}
			return resultado;
		}

		private ArrayList<Integer> getMayoresK(ArbolNodo nodo, int k){
			ArrayList<Integer>resultado = new ArrayList<Integer>();
			if(nodo.getIzq() == null && nodo.getDer() == null && nodo.getValor()> k) {  //Mientras sea hoja y cuyo valor supere a K.
				resultado.add(nodo.getValor());
			}
			else if(nodo.getIzq() != null && nodo.getValor()> k) { //No olvidar preguntar si lo de la izqueirda es menor a k
				resultado.addAll(getMayoresK(nodo.getIzq(),k));
			}
			if(nodo.getDer() != null) {
				resultado.addAll(getMayoresK(nodo.getDer(), k));
			}
			return resultado;
		}
		
		//EJERCICIO 4 - COLOCAR VALOR EN NODO VACIO ---- BIEN!!!!
		
		public void colocarValorEnNodoVacio() {
			if(this.raiz != null) {
				colocarValorEnNodoVacio(this.raiz);
			}
			else {
				System.out.println("El arbol no tiene ningun valor");
			}
		}
		
		private int colocarValorEnNodoVacio(ArbolNodo nodo) {
			int izq = 0;
			int der = 0;
			if(nodo.getValor() == null) { //Se que es nodo interno si no tiene valor
				if(nodo.getIzq() != null && nodo.getIzq().getValor() == null) {
					izq = colocarValorEnNodoVacio(nodo.getIzq());
				}
				if(nodo.getDer() != null && nodo.getDer().getValor() == null) { //Tengo que ir para el lado que tiene nodos internos primero
					der = colocarValorEnNodoVacio(nodo.getDer());               //Para que realice las operaciones de mas abajo siempre
				}
				if(nodo.getIzq() != null && nodo.getIzq().getValor() != null) {
					izq = nodo.getIzq().getValor();
				}
				if(nodo.getDer() != null && nodo.getDer().getValor() != null) {
					der = nodo.getDer().getValor();
				}
				 nodo.setValor(der - izq); //Le seteo el nuevo valor
				 return nodo.getValor();   //retorno para que corte ejecucion y devuelva valor nuevo. Si retorno der - izq es mas optimo? O una variable
			}
			else {
				return nodo.getValor(); //devuelvo el valor de la raiz si no tiene nodo interno
			}
		}
		
		public static void main(String[] args) {
	        // Crear un árbol
	        Arbol arbol = new Arbol();

	        // Agregar algunos valores al árbol
	        arbol.add(10);
	        arbol.add(5);
	        arbol.add(3);
	        arbol.add(6);
	        arbol.add(11);
	        arbol.add(12);
	        arbol.add(7);
	        
	        
	       /* 

	        // Obtener frontera
	        ArrayList<Integer> frontera = arbol.getFrontera();

	        // Imprimir la rama más larga
	        System.out.println("Frontera:");
	        for (Integer valor : frontera) {
	            System.out.print(valor + "-");
	        }*/
	        
	        //System.out.println("Sumatoria de nodos internos: " + arbol.sumatoriaNodosInternos());
	        
	        //Obtener listado de mayores a k
	        ArrayList<Integer> mayoresK = arbol.getMayoresK(2);

	        System.out.println(mayoresK);
	        
	    }

}
