package practico5BackTracking;

import java.util.ArrayList;

public class TableroMagico {
	private int tamanio;
	private int sumaSolucion;
	private int k;
	private Tablero tablero;
	private Posicion origen;
	
	public TableroMagico(int n, int k, int s) {
		this.tamanio = n*n;
		this.sumaSolucion = s;
		this.k = k;
	}
	
	public Tablero tableroMagico() {
		tablero = new Tablero(n,n); //Creo el tablero pasandole cantidad de filas y columnas, es un tablero de posiciones vacias
		tablero.generacionNumerosDisponibles(k); //Este metodo en la clase tablero genera una lista de posiciones con los numeros de 1 hasta k
		origen = tablero.getPosConNumero(); //Este metodo saca un numero de esa lista y me lo da para ponerlo en el tablero(es una posicion con el num)
		tablero.agregar(origen); //Se agrega en 0,0 la primera vez
		tablero.marcarVisitado(origen);
		tablero.incrementarFila(origen.getValor()); //La posicion tiene un int con su valor
		tablero.incrementarColumna(origen.getValor()); //El tablero va acumulando los valores de fila y columnas para controlar que no me pase de restric
		tablero = backtracking(origen,tablero);
		return tablero;
	}
	
	private Tablero backtracking(Posicion actual, Tablero tablero) {
		if(tablero.obtenerTamanio() == tamanio) { //Ese metodo me devuelve la cantidad de elementos que tiene el tablero en su interior
			return tablero;                       //osea las posiciones con su getValor()
		}
		else {
			ArrayList<Posicion>itAdyacentes = actual.getAdyacentes(); //Cada posicion sabe a donde puede moverse (Abajo-Arriba-Izq-Der)
			Posicion siguiente = itAdyacentes.next();
			siguiente = tablero.getPosConNumero(); //Te da otro numero para poner (tareas). Las posiciones son las decisiones que puede tomar(procesador)
			if(!tablero.estaVisitado(siguiente)) {
				tablero.agregar(siguiente); 
				tablero.marcarVisitado(siguiente);
				tablero.incrementarFila(siguiente.getValor()); 
				tablero.incrementarColumna(siguiente.getValor());
				if(tablero.getValorFila()<=sumaSolucion && tablero.getValorColumna()<= sumaSolucion) { //El tablero tiene un metodo que devuelve el valor
					backtracking(siguiente, tablero);										//de la fila y col, si es mayor corta por restriccion
				}
				tablero.quitar(siguiente); 
				tablero.desmarcarVisitado(siguiente);
				tablero.decrementarFila(siguiente.getValor()); 
				tablero.decrementarColumna(siguiente.getValor());
			}
			siguiente.quitarValor(); //La posicion tiene un metodo para quitar el valor que le asignaron y queda una pos vacia
		}
	}
}
