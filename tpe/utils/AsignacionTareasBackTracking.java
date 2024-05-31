package tpe.utils;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

import tpe.Procesador;
import tpe.Tarea;

public class AsignacionTareasBackTracking {
    Integer tiempoX;
    Estado mejorSolucion;
    LinkedList<Tarea> tareasDisponibles;
    HashMap<String, Procesador> procesadores;
    Integer metrica;
    

    public AsignacionTareasBackTracking(HashMap<String, Procesador> procesadores, HashMap<String, Tarea>tareas, Integer tiempoMaxEjecucion) {
        this.tareasDisponibles = new LinkedList<Tarea>(tareas.values());
        this.tiempoX = tiempoMaxEjecucion;
        this.mejorSolucion = null;
        this.procesadores = procesadores;
        this.metrica = 0;
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

    public Estado asignarTareas() {
        Estado estadoInicial = new Estado(procesadores);
        backtracking(estadoInicial, tareasDisponibles);
        if (mejorSolucion == null) {
            return null;
        }
        mejorSolucion.setMetricaGenerada(metrica);
        return mejorSolucion; 
    }

    private void backtracking(Estado estado, LinkedList<Tarea> tareasDisponibles) {
        if(tareasDisponibles.size()== 0){ //Es una solucion, ahora tengo que ir guardando la mejor solucion
            //operar solucion
        	if(this.mejorSolucion == null || estado.getTiempoFinalEjecucion()< this.mejorSolucion.getTiempoFinalEjecucion()) {
        		this.mejorSolucion = new Estado(estado);
        	}
        }
        else{
            Iterator<String> itProcesadores = estado.iterarProcesadores();  
            while(itProcesadores.hasNext()) {
            	String procesador = itProcesadores.next(); //Me da el String de procesador para luego poder pasarlo al estado y asignarle la tarea
            	Tarea t = obtenerTarea(); //Metodo de esta clase porque necesito las tareas disponibles            		
            	Integer tiempoFinalAnterior = estado.getTiempoFinalEjecucion(); //Guardamos el tiempo anterior
				estado.asignarTarea(procesador,t); //Se actualizo el estado
				//Aca debo hacer la poda, ya se realizo una
				if(!estado.superaCantidadCriticas(procesador) && 
                (this.mejorSolucion == null || (estado.getTiempoFinalEjecucion()< this.mejorSolucion.getTiempoFinalEjecucion())) && 
                ((!estado.esRefrigerado(procesador) && t.getTiempo()< tiempoX) || estado.esRefrigerado(procesador))) { // tambien tenemos que chequear si ese es una solucion peor para no seguir con el backtracking
					backtracking(estado, tareasDisponibles); //Tengo que actualizar el estado para pasarle el nuevo estado, no el procesador
					this.metrica++;
				}
				devolverTarea(t);
				estado.desasignarTarea(procesador,tiempoFinalAnterior);
            }
        }
    }
}