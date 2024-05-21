package tpe.utils;
import java.util.HashMap;
import java.util.Hashtable;

import tpe.Procesador;
import tpe.Tarea;

public class AsignacionTareasBackTracking {
    
    private int tiempoMaxEjecucion;
    private HashMap<String, String> tareasAsignadas;
    private Hashtable<String, Boolean> ultimaTareaCritica;
    private Hashtable<String, Integer> tiempoTotalEjecucion;

    public AsignacionTareasBackTracking(int tiempoMaxEjecucion) {
        this.tiempoMaxEjecucion = tiempoMaxEjecucion;
    }

    public void asignarTareas(HashMap<String, Tarea>tareas, HashMap<String, Procesador> procesadores) {
        //no puedo asignar al mismo procesador dos tareas con el críticas de manera consecutiva
        //no puedo asignar a un procesador no refrigerado si el tiempo de ejecución de la tarea es mayor a tiempoMaxEjecucion
    }

    private void method(){
        // Implementar
    }
}