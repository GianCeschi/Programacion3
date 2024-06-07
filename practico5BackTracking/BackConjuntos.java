package practico5BackTracking;

import java.util.ArrayList;

//Pseudo Java

public class BackConjuntos {

	private ArrayList<Solucion> soluciones;
	private ArrayList<Integer> conjunto;
	private int m;
	
	public BackConjuntos(ArrayList<Integer> conjunto, int m) {
		this.conjunto = conjunto;
		this.m = m;
	}
	
	public void backtracking(Estado estado) {
		
		if (estado.conjuntoVacio()) { // Estado final -- Si no hay mas elementos en el subconjunto para considerar
			
			if (estado.getSolucion().getSuma() == this.m) {
				soluciones.add(estado.getSolucion());
			}
			
		} else { // Seguir explorando el arbol
			
			// Llamado que NO pone el valor en S
			int valor = estado.sacarPrimeroDelConjunto(); // hago cambios
			if (estado.getSolucion().getSuma() <= this.m) { // poda (no seria necesario en este caso)
				backtracking(estado);
			}
			estado.ponerPrimeroEnElConjunto(valor); // deshago cambios
			
			// Llamado que SI pone el valor en S
			valor = estado.sacarPrimeroDelConjunto(); // hago cambios
			estado.agregarASolucion(valor);
			estado.sumarASolucion(valor);
			if (estado.getSolucion().getSuma() <= this.m) { // poda
				backtracking(estado);
			}
			estado.restarDeSolucion(valor);
			estado.borrarDeSolucion(valor);
			estado.ponerPrimeroEnElConjunto(valor); // deshago cambios
			
		}
		
		
	}
	
}
