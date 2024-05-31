package tpe.utils;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

import tpe.Procesador;
import tpe.Tarea;

public class AsignacionTareasGreedy {
    Integer tiempoX;
    Estado solucion;
    LinkedList<Tarea> tareasDisponibles;
    HashMap<String, Procesador> procesadores;
    Integer metrica;

    public AsignacionTareasGreedy(HashMap<String, Procesador> procesadores, HashMap<String, Tarea> tareas, Integer tiempoX) {
        this.tareasDisponibles = new LinkedList<Tarea>(tareas.values());
        Collections.sort(tareasDisponibles, Collections.reverseOrder());    // ordena las tareas de mayor a menor tiempo de ejecución
        this.tiempoX = tiempoX;
        this.solucion = null;
        this.procesadores = procesadores;
        this.metrica = 0;
    }

    public Estado asignarTareas() {
        Estado estadoInicial = new Estado(procesadores);
        return greedy(estadoInicial, tareasDisponibles);
    }
    
    private Estado greedy(Estado estado, LinkedList<Tarea> tareasDisponibles){
        
        while(!tareasDisponibles.isEmpty()){
            Tarea tarea = tareasDisponibles.removeFirst();
            Procesador procesador = obtenerMejorProcesador(estado, tarea); 
            if (procesador == null){
                return null;
            }           
            metrica++;
            estado.asignarTarea(procesador.getId(), tarea);
        }                                                           //Si sale del while, es porque asignó todas las tareas. De lo contrario, corta con return null
        this.solucion = new Estado(estado);
        solucion.setMetricaGenerada(metrica);
        return solucion;
    }

    public boolean esSolucion(Estado estado) {
        return estado.getTiempoFinalEjecucion() < this.solucion.getTiempoFinalEjecucion();
    }

    private Procesador obtenerMejorProcesador(Estado estado, Tarea tarea) {
        HashMap<String,Procesador> procesadores = estado.getProcesadores();
        Procesador mejorProcesador = null;
        Iterator<String> itProcesadores = estado.iterarProcesadores();
        while(itProcesadores.hasNext()){
            String idProcesador = itProcesadores.next();
            Procesador procesadorActual = procesadores.get(idProcesador);
            if (procesadorPuedeRealizarTarea(estado, idProcesador, tarea)){
                if (mejorProcesador == null || procesadorActual.getTiempoEjecucion() < mejorProcesador.getTiempoEjecucion()){
                    mejorProcesador = procesadorActual;
                }
            }
        }
        return mejorProcesador;        
    }

    private boolean procesadorPuedeRealizarTarea(Estado estado, String idProcesador, Tarea tarea){
        return !estado.superaCantidadCriticas(idProcesador) && 
                    (estado.esRefrigerado(idProcesador) || 
                    (!estado.esRefrigerado(idProcesador) && tarea.getTiempo() <= tiempoX ));
    }
}
