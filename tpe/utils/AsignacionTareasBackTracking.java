package tpe.utils;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

import tpe.Procesador;
import tpe.Tarea;

public class AsignacionTareasBackTracking {
    Integer tiempoX;
    Estado mejorSolucion;
    Estado estadoInicial;
    LinkedList<Tarea> tareasDisponibles;
    

    public AsignacionTareasBackTracking(HashMap<String, Procesador> procesadores, HashMap<String, Tarea>tareas, Integer tiempoMaxEjecucion) {
        this.tareasDisponibles = new LinkedList<Tarea>(tareas.values());
        this.tiempoX = tiempoMaxEjecucion;
        this.mejorSolucion = null;
        this.estadoInicial = new Estado(procesadores);
    }
    
    //Tengo que sacar la tarea de la lista de tareas disponiobles
    public Tarea obtenerTarea() {
    	Tarea asignada = tareasDisponibles.removeFirst();
    	return asignada;
    }
    
    //Tengo que volver al estado anterior por eso devuelvo la tarea a la lista de disponibles
    public void devolverTarea(Tarea t) {
    	tareasDisponibles.addFirst(t);
    }

    public void asignarTareas() {
        backtracking(estadoInicial, tareasDisponibles);
        //no puedo asignar al mismo procesador mas de dos tareas críticas.
        //no puedo asignar a un procesador no refrigerado si el tiempo de ejecución de la tarea es mayor a tiempoMaxEjecucion
        //return mejorSolucion; DEBERIA DEVOLVER LA MEJOR SOLUCION
    }

    private void backtracking(Estado estado, LinkedList<Tarea> tareasDisponibles) {
        if(tareasDisponibles.size()== 0){ //Es una solucion, ahora tengo que ir guardando la mejor solucion
            //operar solucion
        	if(this.mejorSolucion == null || estado.getTiempoFinalEjecucion()< this.mejorSolucion.getTiempoFinalEjecucion()) {
        		this.mejorSolucion = new Estado(estado.getProcesadores(), estado.getTiempoFinalEjecucion()); //Creo otro constructor para pasarle tiempo
        	}
        }
        else{
            Iterator<String> itProcesadores = estado.iterarProcesadores();  
            while(itProcesadores.hasNext()) {
            	String procesador = itProcesadores.next(); //Me da el String de procesador para luego poder pasarlo al estado y asignarle la tarea
            	Tarea t = obtenerTarea(); //Metodo de esta clase porque necesito las tareas disponibles
            	int tiempoFinalAnterior = estado.getTiempoFinalEjecucion(); //Guardamos el tiempo anterior
				estado.asignarTarea(procesador,t); //Se actualizo el estado
				//Aca debo hacer la poda, ya se realizo una
				if(!estado.tieneDosCriticas(procesador) && estado.getTiempoFinalEjecucion()< this.mejorSolucion.getTiempoFinalEjecucion()) { // tambien tenemos que chequear si ese es una solucion peor para no seguir con el backtracking
					backtracking(estado, tareasDisponibles); //Tengo que actualizar el estado para pasarle el nuevo estado, no el procesador
				}
				devolverTarea(t);
				estado.desasignarTarea(procesador,tiempoFinalAnterior);
            }
        }
    }
}