package tpe.utils;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

import tpe.Procesador;
import tpe.Tarea;

public class AsignacionTareasBackTracking {
    private Integer tiempoX;
    private Estado mejorSolucion;
    private LinkedList<Tarea> tareasDisponibles;
    private HashMap<String, Procesador> procesadores;
    private Integer metrica;
    private int limite;
    

    public AsignacionTareasBackTracking(HashMap<String, Procesador> procesadores, HashMap<String, Tarea>tareas, Integer tiempoMaxEjecucion,int limite) {
        this.tareasDisponibles = new LinkedList<Tarea>(tareas.values());
        this.tiempoX = tiempoMaxEjecucion;
        this.mejorSolucion = null;
        this.procesadores = procesadores;
        this.metrica = 0;
        this.limite = limite;
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

    /*Estrategia backtracking: 
     *Se crea un estado inicial con todos los procesadores sin tareas asignadas.
     *Se invoca a un metodo privado backtracking pasandole por parametro el estado inicial y la lista de tareas disponibles para asignar.
     *Este metodo de manera recursiva genera todas las combinaciones posibles de tareas y procesadores, comparandolas y retornando la mejor solucion, si 
     *es que existe al menos alguna solución.
     *La mejor solucion es la que minimiza el tiempo maximo de ejecucion de todos los procesadores.
     *En caso de no haber una solución se retorna null.
     * */
    
    public Estado backtracking() {
        Estado estadoInicial = new Estado(procesadores);
        backtracking(estadoInicial, tareasDisponibles);
        if (mejorSolucion == null) {
            return null;
        }
        mejorSolucion.setMetricaGenerada(metrica);
        return mejorSolucion; 
    }

    private void backtracking(Estado estado, LinkedList<Tarea> tareasDisponibles) {
        if(tareasDisponibles.size()== 0){ //Es una posible solucion, ahora tengo que ir guardando la mejor solucion
        	if(this.mejorSolucion == null || estado.getTiempoFinalEjecucion()< this.mejorSolucion.getTiempoFinalEjecucion()) {
        		this.mejorSolucion = new Estado(estado);
        	}
        }
        else{
            Iterator<String> itProcesadores = estado.iterarProcesadores();  
            while(itProcesadores.hasNext()) {
            	String procesador = itProcesadores.next(); 
            	Tarea t = obtenerTarea();             		
            	Integer tiempoFinalAnterior = estado.getTiempoFinalEjecucion(); 
				estado.asignarTarea(procesador,t); //Se actualiza el estado
				//Se aplican restricciones y se analiza una posible poda
				if(!estado.superaCantidadCriticas(procesador,limite) && 
                (this.mejorSolucion == null || (estado.getTiempoFinalEjecucion()< this.mejorSolucion.getTiempoFinalEjecucion())) && 
                ((!estado.esRefrigerado(procesador) && estado.getTiempoProcesador(procesador)<= tiempoX) || estado.esRefrigerado(procesador))) {
					backtracking(estado, tareasDisponibles); 
					this.metrica++;
				}
				devolverTarea(t);
				estado.desasignarTarea(procesador,tiempoFinalAnterior);
            }
        }
    }
}