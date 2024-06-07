package tpe.utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import tpe.Procesador;
import tpe.Tarea;

public class Solucion {

	private HashMap<String, Procesador> procesadores;
	private Integer tiempoFinalEjecucion;
	private Integer metricaGenerada;
	    
	    public Solucion(Estado estado) {
	    	this.procesadores = new HashMap<>();
	        for (Entry<String, Procesador> entry : estado.getProcesadores().entrySet()) {
	            this.procesadores.put(entry.getKey(), new Procesador(entry.getValue()));
	        }
	        this.tiempoFinalEjecucion = estado.getTiempoFinalEjecucion(); 
	    	
	         this.metricaGenerada = estado.getMetricaGenerada();
	    }
	    
	    public Integer getTiempoFinalEjecucion() {
	    	return tiempoFinalEjecucion;
	    }
	    
	    public String toString() {
	    	String res =  "Estado: \nTiempo final ejecución: " + tiempoFinalEjecucion + "\nMetrica: " + metricaGenerada + 
	    				  "\nDetalle tareas asignadas: \n";
	    	Iterator<String> itProcesadores = procesadores.keySet().iterator();
	    	while(itProcesadores.hasNext()) {
	    		String idProcesador = itProcesadores.next();
	    		Procesador p = procesadores.get(idProcesador);
	    		res += "Procesador " + p.getId() + "[" ;
	    		Iterator<Tarea> itTareas = p.iterarTareas();
	    		while(itTareas.hasNext()) {
	    			Tarea t = itTareas.next();
	    			res += t.getNombre() + ", ";
	    		}
	    		res += "]\n";
	    	}	
	    	 return res;
	    }
}
