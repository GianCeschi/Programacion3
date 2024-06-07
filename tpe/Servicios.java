package tpe;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Iterator;

import tpe.utils.AsignacionTareasBackTracking;
import tpe.utils.AsignacionTareasGreedy;
import tpe.utils.CSVReader;
import tpe.utils.Solucion;

/**
 * NO modificar la interfaz de esta clase ni sus métodos públicos.
 * Sólo se podrá adaptar el nombre de la clase "Tarea" según sus decisiones
 * de implementación.
 */
public class Servicios {
	
	private HashMap<String, Tarea> tareas = new HashMap<String, Tarea>();
	private HashMap<String, Procesador> procesadores = new HashMap<String, Procesador>();
	private final int LIMITE = 2;
	/*
     * Expresar la complejidad temporal del constructor.
     * O(n) en donde n es la suma de la cantiadad de procesadores y tareas.
     */
	public Servicios(String pathProcesadores, String pathTareas)
	{
		CSVReader reader = new CSVReader();
		this.procesadores = reader.readProcessors(pathProcesadores);
		this.tareas = reader.readTasks(pathTareas);
	}
	
	/*
     * Expresar la complejidad temporal del servicio 1.
	 * Complejidad temporal: O(1)
     */
	public Tarea servicio1(String ID) {
		return tareas.get(ID);
	}
    
    /*
     * Expresar la complejidad temporal del servicio 2.
	 * Complejidad temporal: O(n) con n = cantidad de tareas porque siempre se iteran TODAS las tareas
     */
	public List<Tarea> servicio2(boolean esCritica) {
		LinkedList<Tarea> resultado = new LinkedList<Tarea>();
		Iterator<String> itTareas= tareas.keySet().iterator();
		while (itTareas.hasNext()){									
			String idTareaTemp = itTareas.next();
			if(tareas.get(idTareaTemp).isCritica() == esCritica){	
				resultado.add(tareas.get(idTareaTemp));
			}
		}
		return resultado;
	}

    /*
     * Expresar la complejidad temporal del servicio 3.
	 * Complejidad temporal: O(n) con n = cantidad de tareas porque siempre se iteran TODAS las tareas
     */
	public List<Tarea> servicio3(int prioridadInferior, int prioridadSuperior) {
		LinkedList<Tarea> resultado = new LinkedList<Tarea>();
		Iterator<String> itTareas= tareas.keySet().iterator();
		while (itTareas.hasNext()){									
			String idTareaTemp = itTareas.next();
			Integer prioridad = tareas.get(idTareaTemp).getPrioridad();				
			if(prioridad > prioridadInferior && prioridad < prioridadSuperior){				
				resultado.add(tareas.get(idTareaTemp));
			}
		}
		return resultado;	
	}

	public void servicio4(int tiempoMaxEjecucion){   //Servicio que se encarga de asignar las tareas a los procesadores usando backtracking
		
		if(servicio2(true).size() > procesadores.size() * 2) { //Si la cantidad de tareas criticas es mayor a la cantidad de procesadores, no se puede asignar
			System.out.println("No se puede asignar las tareas a los procesadores");
		}
		else {
			AsignacionTareasBackTracking backTracking = new AsignacionTareasBackTracking(procesadores, tareas, tiempoMaxEjecucion, LIMITE);
			Solucion solucion = backTracking.backtracking();	
			if(solucion == null)	//si viene null, hay alguna tarea que no se pudo asignar a ningún procesador (por ejemplo, todos procesadores no refrigerados y tarea con tiempoEj > tiempo dado por el usuario)
				System.out.println("No se puede asignar las tareas a los procesadores");
			else
			System.out.println(solucion);
		}
	}

	public void servicio5(int tiempoMaxEjecucion){   //Servicio que se encarga de asignar las tareas a los procesadores usando greedy
		if(servicio2(true).size() > procesadores.size() * 2) { //Si la cantidad de tareas criticas es mayor a la cantidad de procesadores, no se puede asignar
			System.out.println("No se puede asignar las tareas a los procesadores");
		}
		else {
			AsignacionTareasGreedy greedy = new AsignacionTareasGreedy(procesadores, tareas, tiempoMaxEjecucion, LIMITE);
			Solucion solucion = greedy.greedy();
			if(solucion == null)  //si viene null, hay alguna tarea que no se pudo asignar a ningún procesador (por ejemplo, todos procesadores no refrigerados y tarea con tiempoEj > tiempo dado por el usuario)
				System.out.println("No se puede asignar las tareas a los procesadores");
			else
			System.out.println(solucion);
		}
	}
}
