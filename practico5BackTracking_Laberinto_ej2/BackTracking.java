package practico5BackTracking_Laberinto_ej2;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

public class BackTracking {

	private int mejorCosto;
	private LinkedList<Casillero> mejorCamino;
	private HashMap<Casillero, Boolean> visitados;
	
	public BackTracking() {
		this.mejorCosto = 0;
		this.mejorCamino = new LinkedList<Casillero>();
		this.visitados = new HashMap<Casillero, Boolean>();
	}
	
	
	public LinkedList<Casillero> caminoLongitudMinima(Casillero[][]mat, Casillero origen, Casillero destino){
		this.visitados.clear();//Limpiamos los visitados
		LinkedList<Casillero>caminoActual = new LinkedList<Casillero>();
		int costoActual = 0;
		backtracking(mat,origen,destino,caminoActual, costoActual);
		return mejorCamino; //No retorno en la recursion porque es de tipo void. Retornar mejorCamino
	}
	
	private void backtracking(Casillero[][]mat,Casillero actual, Casillero destino, LinkedList<Casillero> caminoActual, int costoActual) {
	
		if(actual.equals(destino)) {//Estado final
			if(mejorCosto == 0 || costoActual < mejorCosto) {  //Es una solucion mas pequeña, tengo que preguntar por la primera vez que va a entrar
				this.mejorCamino = new LinkedList<Casillero>(caminoActual); // No apuntar a caminoActual
				mejorCosto = costoActual;
			}
		}
		else {
			LinkedList<Casillero> casillerosVecinos = actual.getVecinos(); //No es necesario implementarlo, pero me va a dar a los que puede avanzar
			Iterator<Casillero> itCasilleros = casillerosVecinos.iterator(); //Casillero es iterable
			while(itCasilleros.hasNext()) {
				Casillero siguiente = itCasilleros.next();
				
				if(!visitados.containsKey(siguiente)) {
					caminoActual.add(siguiente);
					visitados.put(siguiente, true);
					costoActual += siguiente.getValor(); //Al costoActual le incremento el valor del casillero
					if(costoActual<mejorCosto) { //Poda --> Si el costoActual tiene mayor costo que el mejorCosto 
						backtracking(mat, siguiente,destino, caminoActual, costoActual);
					}
					caminoActual.remove(siguiente); //No olvidar deshacer los cambios
					visitados.remove(siguiente);
					costoActual -= siguiente.getValor();
				}
			}
		}
	}
	
}
