package tpe;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import java.util.Iterator;

import tpe.utils.AsignacionTareasBackTracking;
import tpe.utils.CSVReader;

/**
 * NO modificar la interfaz de esta clase ni sus métodos públicos.
 * Sólo se podrá adaptar el nombre de la clase "Tarea" según sus decisiones
 * de implementación.
 */
public class Servicios {
	
	private HashMap<String, Tarea> tareas = new HashMap<String, Tarea>();
	private HashMap<String, Procesador> procesadores = new HashMap<String, Procesador>();
	/*
     * Expresar la complejidad temporal del constructor.
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
		while (itTareas.hasNext()){									// O(n) con n = cantidad de tareas
			String idTareaTemp = itTareas.next();
			if(tareas.get(idTareaTemp).isCritica() == esCritica){				// O(1) constante, ya que es un get de un HashMap
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
		while (itTareas.hasNext()){									// O(n) con n = cantidad de tareas
			String idTareaTemp = itTareas.next();
			Integer prioridad = tareas.get(idTareaTemp).getPrioridad();				// O(1) constante, ya que es un get de un HashMap
			if(prioridad > prioridadInferior && prioridad < prioridadSuperior){				
				resultado.add(tareas.get(idTareaTemp));
			}
		}
		return resultado;	
	}

	public void servicio4(int tiempoMaxEjecucion){   //Servicio que se encarga de asignar las tareas a los procesadores usando backtracking
		// Implementar
		AsignacionTareasBackTracking backTracking = new AsignacionTareasBackTracking(tiempoMaxEjecucion);
		backTracking.asignarTareas(tareas, procesadores);
	}

	public void servicio5(int tiempoMaxEjecucion){   //Servicio que se encarga de asignar las tareas a los procesadores usando greedy
		// Implementar
		AsignacionTareasGreedy.asignarTareas(tareas, procesadores);
	}
}
