package tpe.utils;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;

import tpe.Procesador;
import tpe.Tarea;

public class AsignacionTareasGreedy {
    Integer tiempoX;
    Estado mejorSolucion;
    LinkedList<Tarea> tareasDisponibles;
    HashMap<String, Procesador> procesadores;
    Integer metrica;

    public AsignacionTareasGreedy(HashMap<String, Procesador> procesadores, HashMap<String, Tarea> tareas, Integer tiempoX) {
        this.tareasDisponibles = new LinkedList<Tarea>(tareas.values());
        Collections.sort(tareasDisponibles, Collections.reverseOrder());    // ordena las tareas de mayor a menor tiempo de ejecución
        this.tiempoX = tiempoX;
        this.mejorSolucion = null;
        this.metrica = 0;
    }

    public Estado asignarTareas() {
        Estado estadoInicial = new Estado(procesadores);
        greedy(estadoInicial, tareasDisponibles);
        mejorSolucion.setMetricaGenerada(metrica);
        return new Estado(mejorSolucion);
    }

    private void greedy(Estado estado, LinkedList<Tarea> tareasDisponibles){
        
    }
}
