package tpe.utils;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

import tpe.Procesador;
import tpe.Tarea;


public class AsignacionTareasGreedy {
    private LinkedList<Tarea> tareasDisponibles;
    private HashMap<String, Procesador> procesadores;
    private Integer tiempoX;
    private Solucion solucion;
    private int limite;

    public AsignacionTareasGreedy(HashMap<String, Procesador> procesadores, HashMap<String, Tarea> tareas, Integer tiempoX, int limite) {
        this.tareasDisponibles = new LinkedList<Tarea>(tareas.values());
        Collections.sort(tareasDisponibles, Collections.reverseOrder()); 
        this.tiempoX = tiempoX;
        this.solucion = null;
        this.procesadores = procesadores;
        this.limite = limite;
    }

    public Solucion greedy() {
        Estado estadoInicial = new Estado(procesadores);
        return greedy(estadoInicial, tareasDisponibles);
    }
    
    /*Estrategia greedy:
     *Se crea un estado inicial con todos los procesadores sin tareas asignadas.
     *Se invoca a un metodo privado greedy pasandole por parametro el estado inicial y la lista de tareas disponibles para asignar
     * ya ordenadas de mayor a menor comparando el tiempo de ejecucion de la misma.
     *El criterio para la asignacion de una tarea es tomar la tarea mas grande y asignarla al procesador apto que menos tiempo de ejecucion tenga.
     *En el caso que exista al menos una tarea que no se pueda asignar a ningun procesador, se corta la ejecucion y se retorna null.
     * */
    
    private Solucion greedy(Estado estado, LinkedList<Tarea> tareasDisponibles){
        while(!tareasDisponibles.isEmpty()){
            Tarea tarea = tareasDisponibles.removeFirst();
            Procesador procesador = obtenerMejorProcesador(estado, tarea); 
            if (procesador == null){
                return null;
            }           
            estado.asignarTarea(procesador.getId(), tarea);
        }                                                 
        this.solucion = new Solucion(estado);
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
            estado.incrementarMetrica(); //La metrica se incrementa evaluando todos los procesadores y no lo saltos que hace de nivel
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
        return !estado.superaCantidadCriticas(idProcesador,limite) && 
                    (estado.esRefrigerado(idProcesador) || 
                    (!estado.esRefrigerado(idProcesador) && estado.getTiempoProcesador(idProcesador) <= tiempoX ));
    }
}
