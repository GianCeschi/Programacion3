package TPE_Andersen_Ceschinelli;

import java.util.LinkedList;
import java.util.List;

import TPE_Andersen_Ceschinelli.utils.CSVReader;


public class Servicios {
	//Completar con las estructuras y métodos privados que se requieran.
	/*
	 * Expresar la complejidad temporal del constructor.
	 */
	//private CSVReader reader;
	
	public Servicios(String pathProcesadores, String pathTareas){
		CSVReader reader = new CSVReader();
		reader.readProcessors(pathProcesadores);
		reader.readTasks(pathTareas);
	}
	/*
	 * Expresar la complejidad temporal del servicio 1.
	 */
	public Tarea servicio1(String ID) {
		return reader.getTarea(ID);
	}
	/*
	 * Expresar la complejidad temporal del servicio 2.
	 */
	public List<Tarea> servicio2(boolean esCritica) {
		return new LinkedList<Tarea>();
	}
	/*
	 * Expresar la complejidad temporal del servicio 3.
	 */
	public List<Tarea> servicio3(int prioridadInferior, int prioridadSuperior) {
		return new LinkedList<Tarea>();
	}
}